package id.kelompok9.tripsys.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.UsersModel;

public class ProfilUserFragment extends Fragment {

    AppDatabase db;
    SharedPreferences sharedPreferences;

    TextView username, nama, nomor, alamat, gender;
    String username_in, nama_in, nomor_in, alamat_in, gender_in;
    int iduser;

    List<UsersModel> userdata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        db  = AppDatabase.getDbInstance(getContext());
        sharedPreferences = getContext().getSharedPreferences("loginsession", getContext().MODE_PRIVATE);
        iduser = sharedPreferences.getInt("iduser",0);

        username = (TextView) view.findViewById(R.id.profilusername);
        nama = (TextView) view.findViewById(R.id.profilnama);
        nomor = (TextView) view.findViewById(R.id.profilnomor);
        alamat = (TextView) view.findViewById(R.id.profilalamat);
        gender = (TextView) view.findViewById(R.id.profilkelamin);

        userdata = db.usersDao().getOneUser(iduser);

        username_in = userdata.get(0).getUsername_user();
        nama_in = userdata.get(0).getName_user();
        nomor_in = userdata.get(0).getPhone_user();
        alamat_in = userdata.get(0).getAddress_user();
        gender_in = userdata.get(0).getGender_user();

        username.setText(username_in);
        nama.setText(nama_in);
        nomor.setText(nomor_in);
        alamat.setText(alamat_in);
        gender.setText(gender_in);
    }

}