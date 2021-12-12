package id.kelompok9.tripsys.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "activities")
public class ActivityModel {

    @PrimaryKey(autoGenerate = true)
    private int id_activity;
    @ColumnInfo(name = "id_trip_activity")
    private int id_trip_activity;
    @ColumnInfo(name = "id_trip_detail_activity")
    private int id_trip_detail_activity;
    @ColumnInfo(name = "clock_activity")
    private String clock_activity;
    @ColumnInfo(name = "to_do_activity")
    private String to_do_activity;

    @Ignore
    public ActivityModel(int id_trip_activity, int id_trip_detail_activity, String clock_activity, String to_do_activity) {
        this.id_trip_detail_activity = id_trip_detail_activity;
        this.clock_activity = clock_activity;
        this.to_do_activity = to_do_activity;
    }

    public ActivityModel(int id_activity, int id_trip_activity, int id_trip_detail_activity, String clock_activity, String to_do_activity) {
        this.id_activity = id_activity;
        this.id_trip_detail_activity = id_trip_detail_activity;
        this.clock_activity = clock_activity;
        this.to_do_activity = to_do_activity;
    }

    public int getId_activity() {
        return id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    public int getId_trip_activity() {
        return id_trip_activity;
    }

    public void setId_trip_activity(int id_trip_activity) {
        this.id_trip_activity = id_trip_activity;
    }

    public int getId_trip_detail_activity() {
        return id_trip_detail_activity;
    }

    public void setId_trip_detail_activity(int id_trip_detail_activity) {
        this.id_trip_detail_activity = id_trip_detail_activity;
    }

    public String getClock_activity() {
        return clock_activity;
    }

    public void setClock_activity(String clock_activity) {
        this.clock_activity = clock_activity;
    }

    public String getTo_do_activity() {
        return to_do_activity;
    }

    public void setTo_do_activity(String to_do_activity) {
        this.to_do_activity = to_do_activity;
    }
}
