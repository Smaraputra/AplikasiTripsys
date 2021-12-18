package id.kelompok9.tripsys.activity.tripactivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.ActivityModel;

public class EditActivity extends AppCompatActivity {

    EditText todos;
    TextView time;
    Button addTime, back, save;
    String time_in, todo_in;
    int statusJam=1, statusForm, idact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity_detail);
        todos = findViewById(R.id.editToDoActivity);
        time = findViewById(R.id.showEditClockActivity);
        addTime = findViewById(R.id.editClockActivity);
        back = findViewById(R.id.backEditActivityDetail);
        save = findViewById(R.id.tombolSaveEditActivity);
        AppDatabase db = AppDatabase.getDbInstance(EditActivity.this);
        Intent intent = getIntent();
        idact = intent.getIntExtra("idact", 0);

        time_in = db.activitiesDao().getOneActivity(idact).get(0).getClock_activity();
        time.setText(time_in);
        todo_in = db.activitiesDao().getOneActivity(idact).get(0).getTo_do_activity();
        todos.setText(todo_in);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("AAAAAAA", String.valueOf(minute));
                        if(minute<10){
                            if(hourOfDay<10){
                                time.setText("0"+hourOfDay+":0"+minute);
                                time_in = "0"+hourOfDay+":0"+minute;
                            }else{
                                time.setText(hourOfDay+":0"+minute);
                                time_in = hourOfDay+":0"+minute;
                            }
                        }else{
                            if(hourOfDay<10){
                                time.setText("0"+hourOfDay+":"+minute);
                                time_in = "0"+hourOfDay+":"+minute;
                            }else{
                                time.setText(hourOfDay+":"+minute);
                                time_in = hourOfDay+":"+minute;
                            }
                        }
                        statusJam = 1;
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(EditActivity.this));
                timePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bacatodo();
                if(statusJam!=1){
                    time.setError("Please choose the activity time!");
                }else{
                    time.setError(null);
                }
                if(statusForm==1 && statusJam==1){
                    db.activitiesDao().updateOneActivity(idact, time_in, todo_in);

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast sukses = Toast.makeText(context, "Activity updated.", duration);
                    sukses.show();
                    finish();
                }
            }
        });
    }

    //Bacatodo
    public void bacatodo(){
        todo_in = todos.getText().toString();
        if(TextUtils.isEmpty(todo_in)){
            todos.setError("Please enter your to-do in activity!");
        }else{
            todos.setError(null);
            statusForm=1;
        }
    }
}