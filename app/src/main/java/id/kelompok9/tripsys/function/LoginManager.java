package id.kelompok9.tripsys.function;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.UsersModel;

public class LoginManager {
        public String status;
        SharedPreferences sharedPreferences;
        AppDatabase db;
        public String getStatus(String username, String password, Context context){
                sharedPreferences = context.getSharedPreferences("loginsession", context.MODE_PRIVATE);
                status="";
                if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                        status="DOUBLEEMPTY";
                }else if (TextUtils.isEmpty(username)){
                        status="USEREMPTY";
                }else if(TextUtils.isEmpty(password)){
                        status="PASSEMPTY";
                }else{
                        db = AppDatabase.getDbInstance(context);
                        List<UsersModel> user;
                        user = db.usersDao().loginUser(username,password);
                        if(user.size()==0){
                                status="NOTEXIST";
                        }else{
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.putString("password", password);
                                editor.putString("nama", user.get(0).getName_user());
                                editor.putInt("iduser", user.get(0).getId_user());
                                editor.putString("gender", user.get(0).getGender_user());
                                editor.apply();
                                status="SUCCESS";
                        }
                }
                return status;
        }
}
