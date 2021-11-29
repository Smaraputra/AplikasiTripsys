package id.kelompok9.tripsys.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.kelompok9.tripsys.LoginManager;
import id.kelompok9.tripsys.R;

public class StartMenu extends AppCompatActivity {

    TextView tblregister;
    Button tbllogin;
    SharedPreferences sharedPreferences;

    EditText username, password;
    String username_in;
    String password_in;
    String status;

    String usernamesudahlogin, passwordsudahlogin;
    LoginManager managerlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.inusername);
        password = (EditText) findViewById(R.id.inpassword);
        tblregister = (TextView) findViewById(R.id.tombolregister);
        tbllogin = (Button) findViewById(R.id.tombollogin);

        sharedPreferences = getSharedPreferences("loginsession", Context.MODE_PRIVATE);
        managerlogin = new LoginManager();

        //Login Automatis Ketika Buka Aplikasi (Apabila Sudah Login Sebelumnya dan Tidak Melakukan Logout)
        if(!sharedPreferences.getString("username","defaultValue").equals("defaultValue")
                && !sharedPreferences.getString("password","defaultValue").equals("defaultValue")){

            usernamesudahlogin = sharedPreferences.getString("username","defaultValue");
            passwordsudahlogin = sharedPreferences.getString("password","defaultValue");

            status="";
            status=managerlogin.getStatus(usernamesudahlogin, passwordsudahlogin, getApplicationContext());
            if(status.equals("SUCCESS")){
                Intent intent = new Intent(StartMenu.this, DashboardApp.class);
                startActivity(intent);
            }
        }

        tbllogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username_in = username.getText().toString();
                password_in = password.getText().toString();
                status="";
                status=managerlogin.getStatus(username_in, password_in, getApplicationContext());

                if(status.equals("DOUBLEEMPTY")){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast kurangusernamepassword = Toast.makeText(context, "Username and Password are not inputted.", duration);
                    kurangusernamepassword.show();
                }else if(status.equals("PASSEMPTY")){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast kurangpassword = Toast.makeText(context, "Password is not inputted.", duration);
                    kurangpassword.show();
                }else if(status.equals("USEREMPTY")){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast kurangusername = Toast.makeText(context, "Username is not inputted.", duration);
                    kurangusername.show();
                }else if(status.equals("NOTEXIST")){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast akuntidakada = Toast.makeText(context, "Account is not registered.", duration);
                    akuntidakada.show();
                }else if(status.equals("SUCCESS")){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast sukses = Toast.makeText(context, "Login Success. Welcome " + username_in + ".", duration);
                    sukses.show();

                    Intent intent = new Intent(StartMenu.this, DashboardApp.class);
                    startActivity(intent);
                }
            }
        });
        tblregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartMenu.this, RegisterUser.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set title dialog
        alertDialogBuilder.setTitle("You want to close the app?");

        // Set pesan dari dialog
        alertDialogBuilder
                .setMessage("Choose 'Exit' to close the app.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Exit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // Jika tombol diklik, maka akan menutup activity ini
                        StartMenu.this.finishAffinity();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Jika tombol ini diklik, akan menutup dialog
                        dialog.cancel();
                    }
                });

        // Membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Menampilkan alert dialog
        alertDialog.show();
    }
}