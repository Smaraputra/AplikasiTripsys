package id.kelompok9.tripsys.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.activity.trip.AddNewTrip;
import id.kelompok9.tripsys.adapter.TripAdapter;
import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripsModel;

public class HomeFragment extends Fragment {

    List<TripsModel> trips = new ArrayList<>();
    List<CategoriesModel> categories=new ArrayList<>();
    List<CategoriesModel> categories1=new ArrayList<>();

    FloatingActionButton tambahTrip;
    Spinner spinnerCategory;
    TextView textNoDataTrip;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    TripAdapter tripAdapter;

    int iduser;
    String pilihanCategoryStr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        textNoDataTrip = view.findViewById(R.id.textNoDataTrip);
        tambahTrip = (FloatingActionButton) view.findViewById(R.id.fabCreateTrip);
        tambahTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddNewTrip.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTrip);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AppDatabase db  = AppDatabase.getDbInstance(getContext());
        sharedPreferences = getContext().getSharedPreferences("loginsession", getContext().MODE_PRIVATE);
        iduser = sharedPreferences.getInt("iduser",0);

        categories.add(new CategoriesModel(0, iduser, "All Category"));
        categories1 = db.categoriesDao().getOneCategoryOnUSERID(iduser);
        categories.addAll(categories1);

        //Spinner
        spinnerCategory = (Spinner) view.findViewById(R.id.spinnerCategory);
        ArrayAdapter<CategoriesModel> spinneradapter = new ArrayAdapter<CategoriesModel>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinneradapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Membaca pilihan periode
                pilihanCategoryStr = spinnerCategory.getSelectedItem().toString();
                Log.d("IDDDDDD HOME", String.valueOf(position));
                if(pilihanCategoryStr.equals("All Category")){
                    trips.clear();
                    trips = db.tripsDao().getTripOnUserID(iduser);

                    if(trips.size()==0){
                        recyclerView.setVisibility(View.GONE);
                        textNoDataTrip.setVisibility(View.VISIBLE);
                    }else{
                        recyclerView.setVisibility(View.VISIBLE);
                        textNoDataTrip.setVisibility(View.GONE);
                    }

                    tripAdapter = new TripAdapter(getContext(), trips);
                    recyclerView.setAdapter(tripAdapter);
                }else{
                    trips.clear();
                    trips = db.tripsDao().getTripOnUserIDCatID(iduser, position);

                    if(trips.size()==0){
                        recyclerView.setVisibility(View.GONE);
                        textNoDataTrip.setVisibility(View.VISIBLE);
                    }else{
                        recyclerView.setVisibility(View.VISIBLE);
                        textNoDataTrip.setVisibility(View.GONE);
                    }

                    tripAdapter = new TripAdapter(getContext(), trips);
                    recyclerView.setAdapter(tripAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }
}