package id.kelompok9.tripsys.activity.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.DashboardApp;
import id.kelompok9.tripsys.activity.tripdetail.AddNewTripDetail;
import id.kelompok9.tripsys.activity.tripdetail.ShowTripDetail;
import id.kelompok9.tripsys.adapter.TripAdapter;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripsModel;

public class AddNewTrip extends AppCompatActivity {

    TextView start, end;
    EditText nama;
    Spinner spinner;
    Button addStart, addEnd, back, save;
    SharedPreferences sharedPreferences;

    int mYear, mMonth, mDay, iduser;
    int statusForm, statusDate;
    String namaTrip;
    String startTrip;
    long startMS=0,endMS=0;
    String endTrip;
    int categoryTrip;
    List<CategoriesModel> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);

        sharedPreferences = getSharedPreferences("loginsession", getApplicationContext().MODE_PRIVATE);
        iduser = sharedPreferences.getInt("iduser",0);

        start = findViewById(R.id.showAddStartDateTrip);
        end = findViewById(R.id.showAddEndDateTrip);
        nama = findViewById(R.id.addTripName);
        spinner = findViewById(R.id.spinnerAddCategoryTrip);
        addStart = findViewById(R.id.addStartDateTrip);
        addEnd = findViewById(R.id.addEndDateTrip);
        back = findViewById(R.id.backAddActivityDetail);
        save = findViewById(R.id.tombolSaveNewTrip);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusForm=0;
                bacaNamaTrip();

                if(statusDate<2){
                    start.setError("Please enter your start date!");
                    end.setError("Please enter your end date!");
                }else{
                    start.setError(null);
                    end.setError(null);
                }

                if(statusForm==1 && statusDate >= 2) {
                    AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
                    TripsModel tripsModel = new TripsModel(
                            iduser, namaTrip, categoryTrip, startTrip, endTrip
                    );
                    db.tripsDao().tambahTrip(tripsModel);

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast sukses = Toast.makeText(context, "Trip created.", duration);
                    sukses.show();

                    Intent intent1 = new Intent(AddNewTrip.this, DashboardApp.class);
                    startActivity(intent1);
                }else{
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast informasikurang = Toast.makeText(context, "Data is not valid.", duration);
                    informasikurang.show();
                }
            }
        });

        addStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialogStart = new DatePickerDialog(AddNewTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        startTrip = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(year);
                        if(TextUtils.isEmpty(endTrip)){
                            statusDate = 0;
                        }
                        statusDate = statusDate + 1;
                        start.setText(startTrip);

                        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date s = f.parse(startTrip);
                            startMS = s.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
                datePickerDialogStart.getDatePicker().setMinDate(System.currentTimeMillis() + 86400000);
                if(endMS!=0){
                    datePickerDialogStart.getDatePicker().setMaxDate(endMS - 86400000);
                }
                datePickerDialogStart.show();
            }
        });

        addEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialogEnd = new DatePickerDialog(AddNewTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endTrip = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(year);
                        if(TextUtils.isEmpty(startTrip)){
                            statusDate = 0;
                        }
                        statusDate = statusDate + 1;
                        end.setText(endTrip);

                        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date e = f.parse(endTrip);
                            endMS = e.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
                if(startMS!=0){
                    datePickerDialogEnd.getDatePicker().setMinDate(startMS + 86400000);
                }
                datePickerDialogEnd.show();
            }
        });

        AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
        categories = db.categoriesDao().getOneCategoryOnUSERID(iduser);
        ArrayAdapter<CategoriesModel> spinneradapter = new ArrayAdapter<CategoriesModel>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                categoryTrip = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }
    //Validasi Nama Trip
    public void bacaNamaTrip(){
        namaTrip = nama.getText().toString();
        if(TextUtils.isEmpty(namaTrip)){
            nama.setError("Please enter your trip name!");
        }else{
            nama.setError(null);
            statusForm=statusForm+1;
        }
    }
}