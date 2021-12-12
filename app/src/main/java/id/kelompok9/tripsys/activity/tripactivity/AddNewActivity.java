package id.kelompok9.tripsys.activity.tripactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.ActivityModel;

public class AddNewActivity extends AppCompatActivity {

    EditText todo;
    TextView time;
    Button addTime, back, save;
    String time_in, todo_in;
    int statusJam, statusForm, iddetailtrip, idtrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity_detail);
        todo = findViewById(R.id.addToDoActivity);
        time = findViewById(R.id.showAddClockActivity);
        addTime = findViewById(R.id.addClockActivity);
        back = findViewById(R.id.backAddActivityDetail);
        save = findViewById(R.id.tombolSaveNewActivity);

        Intent intent = getIntent();
        idtrip = intent.getIntExtra("idtrip", 0);
        iddetailtrip = intent.getIntExtra("iddetailtrip", 0);

        Log.d("BBBBBBBBBBB", String.valueOf(idtrip));

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
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay+":"+minute);
                        time_in = hourOfDay+":"+minute;
                        statusJam = 1;
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(AddNewActivity.this));
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
                    AppDatabase db = AppDatabase.getDbInstance(AddNewActivity.this);
                    ActivityModel activity = new ActivityModel(idtrip, iddetailtrip, time_in, todo_in);
                    db.activitiesDao().tambahActivity(activity);

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast sukses = Toast.makeText(context, "Activity created.", duration);
                    sukses.show();

                    finish();
                }
            }
        });
    }

    //Bacatodo
    public void bacatodo(){
        todo_in = todo.getText().toString();
        if(TextUtils.isEmpty(todo_in)){
            todo.setError("Please enter your to-do in activity!");
        }else{
            todo.setError(null);
            statusForm=1;
        }
    }
}