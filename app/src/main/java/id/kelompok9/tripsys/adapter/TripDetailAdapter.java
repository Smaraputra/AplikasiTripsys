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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.R;
import id.kelompok9.tripsys.activity.tripactivity.ShowActivityDetail;
import id.kelompok9.tripsys.activity.tripdetail.EditTripDetail;
import id.kelompok9.tripsys.activity.tripdetail.ShowTripDetail;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.TripDetailsModel;


public class TripDetailAdapter extends RecyclerView.Adapter<TripDetailAdapter.MyViewHolder>{

    private Context context;
    private List<TripDetailsModel> tripData = new ArrayList<>();
    private List<TripDetailsModel> mArrayList = new ArrayList<>();
    int idtrip;
    AppDatabase db;

    public TripDetailAdapter(Context ct, List<TripDetailsModel> tripdetail_in, int idtrip){
        this.context = ct;
        this.tripData = tripdetail_in;
        this.mArrayList = tripData;
        this.idtrip = idtrip;
        db  = AppDatabase.getDbInstance(ct);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_trip_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TripDetailsModel tripdetail = mArrayList.get(position);
        holder.dateDetailTrip.setText(tripdetail.getDate_trip_detail());
        holder.activityDetailTrip.setText(tripdetail.getActivity_trip_detail());
        holder.locationDetailTrip.setText(tripdetail.getLocation_trip_detail());
        holder.noteDetailTrip.setText(tripdetail.getNote_trip_detail());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView activityDetailTrip, locationDetailTrip, noteDetailTrip, dateDetailTrip;
        Button edit, delete;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activityDetailTrip = itemView.findViewById(R.id.namaAktivitasDetailTrip);
            locationDetailTrip = itemView.findViewById(R.id.lokasiDetailTrip);
            noteDetailTrip = itemView.findViewById(R.id.noteDetailTrip);
            dateDetailTrip = itemView.findViewById(R.id.tanggalDetailTrip);
            cardView = itemView.findViewById(R.id.cardDetailTrip);

            edit = itemView.findViewById(R.id.buttonManageDetailTrip);
            delete = itemView.findViewById(R.id.buttonDeleteDetailTrip);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int iddetailtrip = mArrayList.get(getAdapterPosition()).getId_trip_detail();
                    Intent intent = new Intent(context, ShowActivityDetail.class);
                    intent.putExtra("idtrip", idtrip);
                    intent.putExtra("iddetailtrip", iddetailtrip);
                    context.startActivity(intent);
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int iddetailtrip = mArrayList.get(getAdapterPosition()).getId_trip_detail();
                    Intent intent = new Intent(context, EditTripDetail.class);
                    intent.putExtra("iddetailtrip", iddetailtrip);
                    intent.putExtra("idtrip", idtrip);
                    context.startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int iddetailtrip = mArrayList.get(getAdapterPosition()).getId_trip_detail();
                    showDialogDelete(iddetailtrip);
                }
            });
        }
    }

    private void showDialogDelete(int iddetailtrip){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("You want to delete this trip detail?");

        alertDialogBuilder
                .setMessage("Choose 'Yes' to delete.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        AppDatabase db  = AppDatabase.getDbInstance(context);
                        db.activitiesDao().deleteAllActivityOnTripDetailID(iddetailtrip);
                        db.tripDetailsDao().deleteOneTripDetail(iddetailtrip);
                        tripDetailDeletePressed.DeleteTripDetail();
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

    TripDetailAdapter.TripDetailDeletePressed tripDetailDeletePressed;
    public interface TripDetailDeletePressed {
        void DeleteTripDetail();
    }
    public void setClickEvent(TripDetailAdapter.TripDetailDeletePressed event) {
        this.tripDetailDeletePressed = event;
    }
}