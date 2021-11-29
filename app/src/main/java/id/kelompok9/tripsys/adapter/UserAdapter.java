package id.kelompok9.tripsys.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.DashboardApp;
import id.kelompok9.tripsys.model.UserModel;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<UserModel> userModels;
    private ArrayList<UserModel> mArrayList;

    public UserAdapter(Context ct, ArrayList<UserModel> userModels_in){
        this.context = ct;
        this.userModels = userModels_in;
        this.mArrayList = userModels;
//        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        final UserModel users = mArrayList.get(position);
//        holder.namaT.setText(users.getName());
//        holder.userT.setText(users.getUsername());
//
//        holder.idT.setText(String.valueOf(users.getId()));
//        if(users.getValid()==1){
//            holder.validT.setText("Yes");
//        }else{
//            holder.validT.setText("No");
//        }

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView namaT, userT, validT, idT;
        Button showdetail, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            namaT = itemView.findViewById(R.id.namauserrecyler);
//            userT = itemView.findViewById(R.id.usernamerecyler);
//            validT = itemView.findViewById(R.id.validuserrecyler);
//            idT = itemView.findViewById(R.id.iduserrecyler);
//            showdetail = itemView.findViewById(R.id.buttonManage);
//            delete = itemView.findViewById(R.id.buttonDelete);
//            showdetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int iduser = mArrayList.get(getAdapterPosition()).getId();
//                    Intent intent = new Intent(context, EditUser.class);
//                    intent.putExtra("idu", iduser);
//                    context.startActivity(intent);
//                }
//            });
//
//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int iduser = mArrayList.get(getAdapterPosition()).getId();
//                    showDialogDelete(iduser);
//                }
        }
    }
}

//    private void showDialogDelete(int iduser){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//        alertDialogBuilder.setTitle("You want to delete this user?");
//
//        alertDialogBuilder
//                .setMessage("Choose 'Yes' to delete.")
//                .setIcon(R.mipmap.ic_launcher)
//                .setCancelable(false)
//                .setPositiveButton("Delete",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        dbHandler.deleteUser(iduser);
//                        Intent intent = new Intent(context, DashboardApp.class);
//                        context.startActivity(intent);
//                    }
//                })
//                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        alertDialog.show();
//    }
