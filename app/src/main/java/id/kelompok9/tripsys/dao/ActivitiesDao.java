package id.kelompok9.tripsys.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.model.ActivityModel;
import id.kelompok9.tripsys.model.CategoriesModel;

@Dao
public interface ActivitiesDao {

    @Query("Select * from activities")
    List<ActivityModel> getAllActivity();

    @Query("Select * from activities WHERE id_activity = :idmasuk")
    List<ActivityModel> getOneActivity(int idmasuk);

    @Query("Select * from activities WHERE id_trip_detail_activity = :idmasuk")
    List<ActivityModel> getActivityOnTripDetailID(int idmasuk);

    @Query("DELETE FROM activities WHERE id_activity = :idmasuk")
    void deleteOneActivity(int idmasuk);

    @Query("DELETE FROM activities WHERE id_trip_detail_activity = :idmasuk")
    void deleteAllActivityOnTripDetailID(int idmasuk);

    @Query("DELETE FROM activities WHERE id_trip_activity = :idmasuk")
    void deleteAllActivityOnTripID(int idmasuk);

    @Insert
    void tambahActivity(ActivityModel activityModel);

    @Update
    void updateActivity(ActivityModel activityModel);

    @Delete
    void hapusActivity(ActivityModel activityModel);
}
