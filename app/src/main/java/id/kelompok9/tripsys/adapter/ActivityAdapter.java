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
import id.kelompok9.tripsys.activity.tripactivity.EditActivity;
import id.kelompok9.tripsys.activity.tripactivity.ShowActivityDetail;
import id.kelompok9.tripsys.activity.tripdetail.ShowTripDetail;
import id.kelompok9.tripsys.database.AppDatabase;
import id.kelompok9.tripsys.model.ActivityModel;
import id.kelompok9.tripsys.model.TripDetailsModel;


public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder>{

    private Context context;
    private List<ActivityModel> activityData = new ArrayList<>();
    private List<ActivityModel> mArrayList = new ArrayList<>();
    AppDatabase db;

    public ActivityAdapter(Context ct, List<ActivityModel> activityData){
        this.context = ct;
        this.activityData = activityData;
        this.mArrayList = activityData;
        db  = AppDatabase.getDbInstance(ct);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_activity_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ActivityModel activity = mArrayList.get(position);
        holder.toDo.setText(activity.getTo_do_activity());
        holder.hour.setText(activity.getClock_activity());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView toDo, hour;
        Button edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toDo = itemView.findViewById(R.id.showToDoActivity);
            hour = itemView.findViewById(R.id.showHourActivity);

            edit = itemView.findViewById(R.id.buttonManageActivity);
            delete = itemView.findViewById(R.id.buttonDeleteActivity);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idact = mArrayList.get(getAdapterPosition()).getId_activity();
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("idact", idact);
                    context.startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idact = mArrayList.get(getAdapterPosition()).getId_activity();
                    showDialogDelete(idact);
                }
            });
        }
    }

    private void showDialogDelete(int idacts){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("You want to delete this activity?");

        alertDialogBuilder
                .setMessage("Choose 'Yes' to delete.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        AppDatabase db  = AppDatabase.getDbInstance(context);
                        db.activitiesDao().deleteOneActivity(idacts);
                        activityDeletePressed.DeleteActivity();
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

    ActivityDeletePressed activityDeletePressed;
    public interface ActivityDeletePressed {
        void DeleteActivity();
    }
    public void setClickEvent(ActivityDeletePressed event) {
        this.activityDeletePressed = event;
    }
}