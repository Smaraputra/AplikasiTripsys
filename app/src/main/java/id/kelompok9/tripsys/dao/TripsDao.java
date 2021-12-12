package id.kelompok9.tripsys.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.kelompok9.tripsys.model.TripsModel;

@Dao
public interface TripsDao {

    @Query("Select * from trips WHERE id_user_trip = :idmasuk")
    List<TripsModel> getTripOnUserID(int idmasuk);

    @Query("Select * from trips WHERE id_user_trip = :iduser AND trip_category = :idcat")
    List<TripsModel> getTripOnUserIDCatID(int iduser, int idcat);

    @Query("SELECT * FROM trips WHERE id_trip = :idmasuk")
    List<TripsModel> getOneTrip (int idmasuk);

    @Query("DELETE FROM trips WHERE id_trip = :idmasuk")
    void deleteOneTrip(int idmasuk);

    @Insert
    void tambahTrip(TripsModel tripsModel);

    @Update
    void updateTrip(TripsModel tripsModel);

    @Delete
    void hapusTrip(TripsModel tripsModel);
}
