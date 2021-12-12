package id.kelompok9.tripsys.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity (tableName = "trips")
public class TripsModel {

    @PrimaryKey(autoGenerate = true)
    private int id_trip;
    @ColumnInfo(name = "id_user_trip")
    private int id_user_trip;
    @ColumnInfo(name = "trip_name")
    private String trip_name;
    @ColumnInfo(name = "trip_category")
    private int trip_category;
    @ColumnInfo(name = "trip_start")
    private String trip_start;
    @ColumnInfo(name = "trip_end")
    private String trip_end;

    @Ignore
    public TripsModel(int id_user_trip, String trip_name, int trip_category,
                     String trip_start,String trip_end) {
        this.id_user_trip = id_user_trip;
        this.trip_name = trip_name;
        this.trip_category = trip_category;
        this.trip_start = trip_start;
        this.trip_end = trip_end;
    }

    public TripsModel(int id_trip, int id_user_trip, String trip_name, int trip_category,
                      String trip_start,String trip_end) {
        this.id_trip = id_trip;
        this.id_user_trip = id_user_trip;
        this.trip_name = trip_name;
        this.trip_category = trip_category;
        this.trip_start = trip_start;
        this.trip_end = trip_end;
    }

    public int getId_trip() {
        return id_trip;
    }

    public void setId_trip(int id_trip) {
        this.id_trip = id_trip;
    }

    public int getId_user_trip() {
        return id_user_trip;
    }

    public void setId_user_trip(int id_user_trip) {
        this.id_user_trip = id_user_trip;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public int getTrip_category() {
        return trip_category;
    }

    public void setTrip_category(int trip_category) {
        this.trip_category = trip_category;
    }

    public String getTrip_start() {
        return trip_start;
    }

    public void setTrip_start(String trip_start) {
        this.trip_start = trip_start;
    }

    public String getTrip_end() {
        return trip_end;
    }

    public void setTrip_end(String trip_end) {
        this.trip_end = trip_end;
    }
}
