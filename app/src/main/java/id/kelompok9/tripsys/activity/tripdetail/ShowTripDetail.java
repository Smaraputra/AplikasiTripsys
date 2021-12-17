package id.kelompok9.tripsys.activity.tripdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.tripactivity.ShowActivityDetail;
import id.kelompok9.tripsys.adapter.ActivityAdapter;
import id.kelompok9.tripsys.adapter.TripDetailAdapter;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.TripDetailsModel;
import id.kelompok9.tripsys.model.TripsModel;

public class ShowTripDetail extends AppCompatActivity implements TripDetailAdapter.TripDetailDeletePressed {

    RecyclerView tripdetail;
    TripDetailAdapter tripDetailAdapter;
    FloatingActionButton fabDetail;
    TextView start, end, name, category, nodata;
    Button backToHome;
    AppDatabase db;
    int idtrip;
    List<TripsModel> trip = new ArrayList<>();
    List<TripDetailsModel> detail = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        tripdetail = findViewById(R.id.recyclerViewDetailTrip);
        start = findViewById(R.id.startDetailTrip);
        end = findViewById(R.id.endDetailTrip);
        name = findViewById(R.id.tripNameDetailTrip);
        category = findViewById(R.id.categoryTripDetail);
        fabDetail = findViewById(R.id.fabCreateDetail);
        backToHome = findViewById(R.id.tomboBackDetailTrip);
        nodata = findViewById(R.id.textNoDataDetailTrip);

        Intent intent = getIntent();
        idtrip = intent.getIntExtra("idtrip", 0);
        db = AppDatabase.getDbInstance(ShowTripDetail.this);
        getData();

        start.setText(trip.get(0).getTrip_start());
        end.setText(trip.get(0).getTrip_end());
        name.setText(trip.get(0).getTrip_name());
        category.setText(db.categoriesDao().getOneCategory(trip.get(0).getTrip_category()).get(0).getCategory_name());

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fabDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShowTripDetail.this, AddNewTripDetail.class);
                intent1.putExtra("idtrip", idtrip);
                startActivity(intent1);
            }
        });


    }

    @Override
    public void DeleteTripDetail() {
        getData();
    }

    public void getData(){

        trip = db.tripsDao().getOneTrip(idtrip);
        detail = db.tripDetailsDao().getTripDetailOnTripID(trip.get(0).getId_trip());
        if(detail.size()==0){
            nodata.setVisibility(View.VISIBLE);
            tripdetail.setVisibility(View.GONE);
        }else{
            nodata.setVisibility(View.GONE);
            tripdetail.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowTripDetail.this);
        tripdetail.setLayoutManager(linearLayoutManager);
        tripdetail.setHasFixedSize(true);
        tripDetailAdapter = new TripDetailAdapter(ShowTripDetail.this, detail, idtrip);
        tripDetailAdapter.setClickEvent((TripDetailAdapter.TripDetailDeletePressed) this);
        tripdetail.setAdapter(tripDetailAdapter);
    }
}