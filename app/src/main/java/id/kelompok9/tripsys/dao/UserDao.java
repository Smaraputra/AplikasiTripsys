package id.kelompok9.tripsys.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.kelompok9.tripsys.model.UserModel;

@Dao
public interface UserDao {

    @Query("Select * from user")
    List<UserModel> listUser();

    @Query("SELECT * FROM user WHERE username = :usermasuk " + "and password = :passmasuk")
    List<UserModel> loginUser(String usermasuk, String passmasuk);

    @Query("SELECT * FROM user WHERE username = :usermasuk ")
    List<UserModel> cekUsername(String usermasuk);

    @Query("SELECT * FROM user WHERE id = :idmasuk")
    List<UserModel> listOneUser(int idmasuk);

    @Query("DELETE FROM user WHERE id = :idmasuk")
    void deleteOne(int idmasuk);

    @Insert
    void tambahUser(UserModel userModel);

    @Update
    void updateUser(UserModel userModel);

    @Delete
    void hapusUser(UserModel userModel);
}
