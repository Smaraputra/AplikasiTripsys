package id.kelompok9.tripsys.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.UsersModel;

public class RegisterUser extends AppCompatActivity {

    EditText name, phonenumber, address, username, password;
    Button cancel, save;
    RadioGroup groupGender;
    RadioButton maleGdr, femaleGdr;

    Button cancelDialog, submitDialog;

    TextView showname, shownumber, showaddress, showuser, showpass, showgender;

    String name_in, phonenumber_in,address_in, username_in, password_in, gender;
    int statusForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        name = (EditText) findViewById(R.id.inputnama);
        phonenumber = (EditText) findViewById(R.id.inputnomor);
        address = (EditText) findViewById(R.id.inputalamat);
        username = (EditText) findViewById(R.id.inputusername);
        password = (EditText) findViewById(R.id.inputpassword);

        cancel = (Button) findViewById(R.id.backRegisterUser);
        save = (Button) findViewById(R.id.tombolNextRegister);

        groupGender = (RadioGroup) findViewById(R.id.ver_ed_group);
        femaleGdr = (RadioButton) findViewById(R.id.femaleGender);
        maleGdr = (RadioButton) findViewById(R.id.maleGender);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                statusForm=0;
                bacaNama();
                bacaNomor();
                bacaAlamat();
                bacaGender();
                bacaUsername();
                bacaPassword();

                if(statusForm==6) {
                    DialogForm();
                }else{
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast informasikurang = Toast.makeText(context, "Data is not valid.", duration);
                    informasikurang.show();
                }
            }
        });
    }

    //Baca Nama
    public void bacaNama(){
        name_in = name.getText().toString();
        if(TextUtils.isEmpty(name_in)){
            name.setError("Please enter your name!");
        }else{
            name.setError(null);
            statusForm=statusForm+1;
        }
    }

    public void bacaNomor(){
        phonenumber_in = phonenumber.getText().toString();
        if(TextUtils.isEmpty(phonenumber_in)) {
            phonenumber.setError("Please enter your phone number!");
        }else{
            phonenumber.setError(null);
            statusForm=statusForm+1;
        }
    }

    //Baca Alamat
    public void bacaAlamat(){
        address_in = address.getText().toString();
        if(TextUtils.isEmpty(address_in)){
            address.setError("Please enter your address!");

        }else{
            statusForm=statusForm+1;
            address.setError(null);
        }
    }

    //Baca Radio Button Gender
    public void bacaGender() {
        if(groupGender.getCheckedRadioButtonId() != -1){
            femaleGdr.setError(null);
            statusForm=statusForm+1;
            gender = ((RadioButton)findViewById(groupGender.getCheckedRadioButtonId())).getText().toString();
        }else{
            femaleGdr.setError("Please select your gender!");
        }
    }

    //Baca Username
    public void bacaUsername() {
        username_in = username.getText().toString();
        if(TextUtils.isEmpty(username_in)) {
            username.setError("Please enter your username!");
        }else{
            AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
            List<UsersModel> mahasiswaList;
            mahasiswaList =db.usersDao().cekUsername(username_in);
            if(mahasiswaList.size()==1){
                username.setError("Username Already Exist!");
            }else{
                statusForm=statusForm+1;
                username.setError(null);
            }
        }
    }

    //Baca Password
    public void bacaPassword() {
        password_in = password.getText().toString();
        if(TextUtils.isEmpty(password_in)) {
            password.setError("Please enter your password!");

        }else{
            statusForm=statusForm+1;
            password.setError(null);
        }
    }

    private void DialogForm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_register,null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        showname= (TextView) dialogView.findViewById(R.id.isinama);
        shownumber= (TextView) dialogView.findViewById(R.id.isinomor);
        showaddress = (TextView) dialogView.findViewById(R.id.isialamat);
        showgender= (TextView) dialogView.findViewById(R.id.isigender);
        showuser= (TextView) dialogView.findViewById(R.id.isiuser);
        showpass= (TextView) dialogView.findViewById(R.id.isipass);

        showname.setText(name_in);
        shownumber.setText(phonenumber_in);
        showaddress.setText(address_in);
        showgender.setText(gender);
        showuser.setText(username_in);
        showpass.setText(password_in);

        cancelDialog= (Button) dialogView.findViewById(R.id.tombolbatalbatal);
        submitDialog= (Button) dialogView.findViewById(R.id.tombolkirimkirim);

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        submitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
                UsersModel usersModel = new UsersModel(
                        name_in, phonenumber_in,
                        address_in, gender,
                        username_in, password_in);
                int createdUser = (int) db.usersDao().tambahUser(usersModel);
                CategoriesModel categoriesModel1 = new CategoriesModel(createdUser, "Work");
                CategoriesModel categoriesModel2 = new CategoriesModel(createdUser, "Holiday");
                CategoriesModel categoriesModel3 = new CategoriesModel(createdUser, "Honey Moon");
                db.categoriesDao().tambahCategory(categoriesModel1);
                db.categoriesDao().tambahCategory(categoriesModel2);
                db.categoriesDao().tambahCategory(categoriesModel3);
                Intent intent = new Intent(RegisterUser.this, StartMenu.class);
                startActivity(intent);

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast sukses = Toast.makeText(context, "Your account is successfully created.", duration);
                sukses.show();
            }
        });
        dialog.show();
    }
}