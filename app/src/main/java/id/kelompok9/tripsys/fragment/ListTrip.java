package id.kelompok9.tripsys.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.kelompok9.tripsys.activity.AddNewTrip;
import id.kelompok9.tripsys.activity.DashboardApp;
import id.kelompok9.tripsys.adapter.UserAdapter;
import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.model.UserModel;

public class ListTrip extends Fragment {

    private ArrayList<UserModel> user=new ArrayList<>();
    private UserAdapter mAdapter;
    FloatingActionButton tambahTrip;

    RadioGroup groupRecylerType;
    RadioButton ver, notver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tripmanagement, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tambahTrip = (FloatingActionButton) view.findViewById(R.id.fab1);
        tambahTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddNewTrip.class);
                startActivity(intent);
            }
        });
//        RecyclerView userView = (RecyclerView) view.findViewById(R.id.recylerviewuser);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        userView.setLayoutManager(linearLayoutManager);
//        userView.setHasFixedSize(true);
//        dbHandler = new DBHandler(getContext());
//        user = dbHandler.listUserVerified();
//        mAdapter = new UserAdapter(getContext(), user);
//        userView.setAdapter(mAdapter);
//
//        groupRecylerType = (RadioGroup) view.findViewById(R.id.ver_group);
//        groupRecylerType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                String id=getResources().getResourceEntryName(i);
//                if(id.equals("verifed")){
//                    dbHandler = new DBHandler(getContext());
//                    user = dbHandler.listUserVerified();
//                    mAdapter = new UserAdapter(getContext(), user);
//                    userView.setAdapter(mAdapter);
//                }else if(id.equals("notverifed")){
//                    dbHandler = new DBHandler(getContext());
//                    user = dbHandler.listUserNotVerified();
//                    mAdapter = new UserAdapter(getContext(), user);
//                    userView.setAdapter(mAdapter);
//                }
//            }
//        });
    }

}