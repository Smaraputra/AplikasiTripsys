package id.kelompok9.tripsys.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripDetailsModel;

@Dao
public interface TripDetailsDao {

    @Query("Select * from trip_details WHERE id_trip_detail = :idmasuk")
    List<TripDetailsModel> getOneTripDetail(int idmasuk);

    @Query("Select * from trip_details WHERE id_trip_trip_detail = :idmasuk")
    List<TripDetailsModel> getTripDetailOnTripID(int idmasuk);

    @Query("DELETE FROM trip_details WHERE id_trip_detail = :idmasuk")
    void deleteOneTripDetail(int idmasuk);

    @Insert
    void tambahTripDetail(TripDetailsModel tripDetailsModel);

    @Update
    void updateTripDetail(TripDetailsModel tripDetailsModel);

    @Delete
    void hapusTripDetail(TripDetailsModel tripDetailsModel);
}
