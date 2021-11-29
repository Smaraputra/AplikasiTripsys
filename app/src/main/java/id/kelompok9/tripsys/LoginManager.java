package id.kelompok9.tripsys;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.UserModel;

public class LoginManager {
        public String status;
        SharedPreferences sharedPreferences;

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
                        AppDatabase db = AppDatabase.getDbInstance(context);
                        List<UserModel> mahasiswaList;
                        mahasiswaList =db.userDao().loginUser(username,password);
                        if(mahasiswaList.size()==0){
                                status="NOTEXIST";
                        }else{
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.putString("password", password);
                                editor.putInt("iduser", mahasiswaList.get(0).getId());
                                editor.apply();
                                status="SUCCESS";
                        }
                }
                return status;
        }
}
