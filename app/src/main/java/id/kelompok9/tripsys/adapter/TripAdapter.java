package id.kelompok9.tripsys.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.tripdetail.ShowTripDetail;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripsModel;


public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder>{

    private Context context;
    private List<TripsModel> tripData = new ArrayList<>();
    private List<TripsModel> mArrayList = new ArrayList<>();
    private List<CategoriesModel> categories = new ArrayList<>();
    AppDatabase db;

    public TripAdapter(Context ct, List<TripsModel> trip_in){
        this.context = ct;
        this.tripData = trip_in;
        this.mArrayList = tripData;
        db  = AppDatabase.getDbInstance(ct);
        categories = db.categoriesDao().getAllCategory();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_trip, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TripsModel trip = mArrayList.get(position);
        holder.namaTrip.setText(trip.getTrip_name());
        holder.categoryTrip.setText(categories.get(trip.getTrip_category()).getCategory_name());
        holder.startTrip.setText(trip.getTrip_start());
        holder.endTrip.setText(trip.getTrip_end());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView namaTrip, categoryTrip, startTrip, endTrip;
        Button showdetail, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTrip = itemView.findViewById(R.id.nameTrip);
            categoryTrip = itemView.findViewById(R.id.categoryTrip);
            startTrip = itemView.findViewById(R.id.startDateTrip);
            endTrip = itemView.findViewById(R.id.endDateTrip);

            showdetail = itemView.findViewById(R.id.buttonManageTrip);
            delete = itemView.findViewById(R.id.buttonDeleteTrip);

            showdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idtrip = mArrayList.get(getAdapterPosition()).getId_trip();
                    Intent intent = new Intent(context, ShowTripDetail.class);
                    intent.putExtra("idtrip", idtrip);
                    context.startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idTrip = mArrayList.get(getAdapterPosition()).getId_trip();
                    showDialogDelete(idTrip);
                }
            });
        }
    }

    private void showDialogDelete(int idTrip){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("You want to delete this trip?");

        alertDialogBuilder
                .setMessage("Choose 'Yes' to delete.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        AppDatabase db  = AppDatabase.getDbInstance(context);
                        db.tripsDao().deleteOneTrip(idTrip);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }
}