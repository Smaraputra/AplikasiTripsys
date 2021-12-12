package id.kelompok9.tripsys.activity.tripactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.tripdetail.ShowTripDetail;
import id.kelompok9.tripsys.adapter.ActivityAdapter;
import id.kelompok9.tripsys.adapter.TripDetailAdapter;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.ActivityModel;
import id.kelompok9.tripsys.model.TripDetailsModel;

public class ShowActivityDetail extends AppCompatActivity {

    TextView showDate, showNote, showLoc, showAct, nodata;
    FloatingActionButton add;
    RecyclerView recyclerView;
    ActivityAdapter activityAdapter;
    Button back;

    List<TripDetailsModel> tripdetail = new ArrayList<>();
    List<ActivityModel> activity = new ArrayList<>();
    int idtrip, iddetailtrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);

        showAct = findViewById(R.id.showActivityActivity);
        showLoc = findViewById(R.id.showLocationActivity);
        showNote = findViewById(R.id.showNoteActivity);
        showDate = findViewById(R.id.showDateActivity);
        nodata = findViewById(R.id.textNoDataActivityDetail);
        add = findViewById(R.id.fabCreateActivityDetail);
        back = findViewById(R.id.tombolBackActivity);
        recyclerView = findViewById(R.id.recyclerViewActivityTrip);

        Intent intent = getIntent();
        idtrip = intent.getIntExtra("idtrip", 0);
        iddetailtrip = intent.getIntExtra("iddetailtrip", 0);

        Log.d("AAAAAAAAAAAA", String.valueOf(idtrip));

        AppDatabase db = AppDatabase.getDbInstance(ShowActivityDetail.this);
        tripdetail = db.tripDetailsDao().getOneTripDetail(iddetailtrip);
        showAct.setText(tripdetail.get(0).getActivity_trip_detail());
        showLoc.setText(tripdetail.get(0).getLocation_trip_detail());
        showNote.setText(tripdetail.get(0).getNote_trip_detail());
        showDate.setText(tripdetail.get(0).getDate_trip_detail());

        activity = db.activitiesDao().getActivityOnTripDetailID(iddetailtrip);
        if(activity.size()==0){
            nodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            nodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShowActivityDetail.this, AddNewActivity.class);
                intent1.putExtra("idtrip", idtrip);
                intent1.putExtra("iddetailtrip", iddetailtrip);
                startActivity(intent1);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowActivityDetail.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        activityAdapter = new ActivityAdapter(ShowActivityDetail.this, activity);
        recyclerView.setAdapter(activityAdapter);
    }
}