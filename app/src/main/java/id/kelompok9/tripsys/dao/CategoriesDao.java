package id.kelompok9.tripsys.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripsModel;

@Dao
public interface CategoriesDao {

    @Query("Select * from trip_categories")
    List<CategoriesModel> getAllCategory();

    @Query("Select * from trip_categories WHERE id_category = :idmasuk")
    List<CategoriesModel> getOneCategory(int idmasuk);

    @Query("Select * from trip_categories WHERE id_user_category = :idmasuk")
    List<CategoriesModel> getOneCategoryOnUSERID(int idmasuk);

    @Query("DELETE FROM trip_categories WHERE id_category = :idmasuk")
    void deleteOneCategories(int idmasuk);

    @Insert
    void tambahCategory(CategoriesModel categoriesModel);

    @Update
    void updateCategory(CategoriesModel categoriesModel);

    @Delete
    void hapusCategory(CategoriesModel categoriesModel);
}
