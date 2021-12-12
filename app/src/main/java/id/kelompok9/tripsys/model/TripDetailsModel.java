package id.kelompok9.tripsys.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "trip_details")
public class TripDetailsModel {

    @PrimaryKey(autoGenerate = true)
    private int id_trip_detail;
    @ColumnInfo(name = "id_trip_trip_detail")
    private int id_trip_trip_detail;
    @ColumnInfo(name = "date_trip_detail")
    private String date_trip_detail;
    @ColumnInfo(name = "activity_trip_detail")
    private String activity_trip_detail;
    @ColumnInfo(name = "location_trip_detail")
    private String location_trip_detail;
    @ColumnInfo(name = "note_trip_detail")
    private String note_trip_detail;

    @Ignore
    public TripDetailsModel(int id_trip_trip_detail, String date_trip_detail, String activity_trip_detail,
                            String location_trip_detail, String note_trip_detail) {
        this.id_trip_trip_detail = id_trip_trip_detail;
        this.date_trip_detail = date_trip_detail;
        this.activity_trip_detail = activity_trip_detail;
        this.location_trip_detail = location_trip_detail;
        this.note_trip_detail = note_trip_detail;
    }

    public TripDetailsModel(int id_trip_detail, int id_trip_trip_detail, String date_trip_detail, String activity_trip_detail,
                            String location_trip_detail, String note_trip_detail) {
        this.id_trip_detail = id_trip_detail;
        this.id_trip_trip_detail = id_trip_trip_detail;
        this.date_trip_detail = date_trip_detail;
        this.activity_trip_detail = activity_trip_detail;
        this.location_trip_detail = location_trip_detail;
        this.note_trip_detail = note_trip_detail;
    }

    public int getId_trip_detail() {
        return id_trip_detail;
    }

    public void setId_trip_detail(int id_trip_detail) {
        this.id_trip_detail = id_trip_detail;
    }

    public int getId_trip_trip_detail() {
        return id_trip_trip_detail;
    }

    public void setId_trip_trip_detail(int id_trip_trip_detail) {
        this.id_trip_trip_detail = id_trip_trip_detail;
    }

    public String getDate_trip_detail() {
        return date_trip_detail;
    }

    public void setDate_trip_detail(String date_trip_detail) {
        this.date_trip_detail = date_trip_detail;
    }

    public String getActivity_trip_detail() {
        return activity_trip_detail;
    }

    public void setActivity_trip_detail(String activity_trip_detail) {
        this.activity_trip_detail = activity_trip_detail;
    }

    public String getLocation_trip_detail() {
        return location_trip_detail;
    }

    public void setLocation_trip_detail(String location_trip_detail) {
        this.location_trip_detail = location_trip_detail;
    }

    public String getNote_trip_detail() {
        return note_trip_detail;
    }

    public void setNote_trip_detail(String note_trip_detail) {
        this.note_trip_detail = note_trip_detail;
    }
}
