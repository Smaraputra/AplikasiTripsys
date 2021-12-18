package id.kelompok9.tripsys.activity.tripdetail;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.TripDetailsModel;

public class EditTripDetail extends AppCompatActivity {

    TextView showDate;
    EditText location, note, activity;
    Button back, save, adddate;
    String locactionStr, noteStr, activityStr, dateStr;
    int mYear, mMonth, mDay, statusDate, idtrip, iddetailtrip, statusForm;
    String start, end;
    long starttime, endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_detail);

        Intent intent = getIntent();
        idtrip = intent.getIntExtra("idtrip", 0);
        iddetailtrip = intent.getIntExtra("iddetailtrip", 0);

        location = findViewById(R.id.editLocationDetailTrip);
        note = findViewById(R.id.editNoteDetailTrip);
        activity = findViewById(R.id.editActivityDetailTrip);
        back = findViewById(R.id.backEditDetailTrip);
        save = findViewById(R.id.tombolSaveEditDetailTrip);
        adddate = findViewById(R.id.editDateDetailTrip);
        showDate = findViewById(R.id.showEditDateDetailTrip);
        AppDatabase db = AppDatabase.getDbInstance(EditTripDetail.this);

        noteStr = db.tripDetailsDao().getOneTripDetail(iddetailtrip).get(0).getNote_trip_detail();
        activityStr = db.tripDetailsDao().getOneTripDetail(iddetailtrip).get(0).getActivity_trip_detail();
        locactionStr = db.tripDetailsDao().getOneTripDetail(iddetailtrip).get(0).getLocation_trip_detail();
        dateStr = db.tripDetailsDao().getOneTripDetail(iddetailtrip).get(0).getDate_trip_detail();

        note.setText(noteStr);
        activity.setText(activityStr);
        showDate.setText(dateStr);
        location.setText(locactionStr);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        start = db.tripsDao().getOneTrip(idtrip).get(0).getTrip_start();
        end = db.tripsDao().getOneTrip(idtrip).get(0).getTrip_end();

        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date s = f.parse(start);
            Date e = f.parse(end);
            starttime = s.getTime();
            endtime = e.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        adddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTripDetail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateStr = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(year);
                        statusDate = 1;
                        showDate.setText(dateStr);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(starttime);
                datePickerDialog.getDatePicker().setMaxDate(endtime);
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusForm=0;
                bacaAktivitasTrip();
                bacaNoteTrip();
                bacaLokasiTrip();

                if(statusForm==3){
                    AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
                    db.tripDetailsDao().updateOneTripDetail(iddetailtrip, activityStr, dateStr, locactionStr, noteStr);

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast sukses = Toast.makeText(context, "Trip detail updated.", duration);
                    sukses.show();

                    finish();
                }else{
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast informasikurang = Toast.makeText(context, "Data is not valid.", duration);
                    informasikurang.show();
                }
            }
        });
    }
    //Validasi Aktivitas Trip
    public void bacaAktivitasTrip(){
        activityStr = activity.getText().toString();
        if(TextUtils.isEmpty(activityStr)){
            activity.setError("Please enter your activity!");
        }else{
            activity.setError(null);
            statusForm=statusForm+1;
        }
    }

    //Validasi Note Trip
    public void bacaNoteTrip(){
        noteStr = note.getText().toString();
        if(TextUtils.isEmpty(noteStr)){
            note.setError("Please enter your note for the activity!");
        }else{
            note.setError(null);
            statusForm=statusForm+1;
        }
    }

    //Validasi Lokasi Trip
    public void bacaLokasiTrip(){
        locactionStr = location.getText().toString();
        if(TextUtils.isEmpty(locactionStr)){
            location.setError("Please enter the location of activity!");
        }else{
            location.setError(null);
            statusForm=statusForm+1;
        }
    }
}