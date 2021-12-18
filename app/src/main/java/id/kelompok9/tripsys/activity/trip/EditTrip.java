package id.kelompok9.tripsys.activity.trip;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.DashboardApp;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripsModel;

public class EditTrip extends AppCompatActivity {

    TextView start, end;
    EditText nama;
    Spinner spinner;
    Button addStart, addEnd, back, save;
    SharedPreferences sharedPreferences;

    int mYear, mMonth, mDay, idtrip, iduser;
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
        setContentView(R.layout.activity_edit_trip);

        Intent intent = getIntent();
        idtrip = intent.getIntExtra("idtrip", 0);
        AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());

        sharedPreferences = getSharedPreferences("loginsession", getApplicationContext().MODE_PRIVATE);
        iduser = sharedPreferences.getInt("iduser",0);

        start = findViewById(R.id.showEditStartDateTrip);
        end = findViewById(R.id.showEditEndDateTrip);
        nama = findViewById(R.id.editTripName);
        spinner = findViewById(R.id.spinnerEditCategoryTrip);
        back = findViewById(R.id.backEditActivityDetail);
        save = findViewById(R.id.tombolSaveEditTrip);

        namaTrip = db.tripsDao().getOneTrip(idtrip).get(0).getTrip_name();
        startTrip = db.tripsDao().getOneTrip(idtrip).get(0).getTrip_start();
        endTrip = db.tripsDao().getOneTrip(idtrip).get(0).getTrip_end();
        categoryTrip = db.tripsDao().getOneTrip(idtrip).get(0).getTrip_category();

        nama.setText(namaTrip);
        start.setText(startTrip);
        end.setText(endTrip);

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

                if(statusForm==1) {
                    db.tripsDao().updateOneTrip(idtrip, categoryTrip, namaTrip);

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast sukses = Toast.makeText(context, "Trip updated.", duration);
                    sukses.show();

                    Intent intent1 = new Intent(EditTrip.this, DashboardApp.class);
                    startActivity(intent1);
                }else{
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast informasikurang = Toast.makeText(context, "Data is not valid.", duration);
                    informasikurang.show();
                }
            }
        });

        categories = db.categoriesDao().getOneCategoryOnUSERID(iduser);
        ArrayAdapter<CategoriesModel> spinneradapter = new ArrayAdapter<CategoriesModel>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);
        spinner.setSelection(categoryTrip-1);
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