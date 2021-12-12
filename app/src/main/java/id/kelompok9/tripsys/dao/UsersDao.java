package id.kelompok9.tripsys.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import id.kelompok9.tripsys.model.UsersModel;

@Dao
public interface UsersDao {

    @Query("Select * from users")
    List<UsersModel> getUser();

    @Query("SELECT * FROM users WHERE username_user = :usermasuk " + "and password_user = :passmasuk")
    List<UsersModel> loginUser(String usermasuk, String passmasuk);

    @Query("SELECT * FROM users WHERE username_user = :usermasuk ")
    List<UsersModel> cekUsername(String usermasuk);

    @Query("SELECT * FROM users WHERE id_user = :idmasuk")
    List<UsersModel> getOneUser(int idmasuk);

    @Query("DELETE FROM users WHERE id_user = :idmasuk")
    void deleteOneUser(int idmasuk);

    @Insert
    long tambahUser(UsersModel usersModel);

    @Update
    void updateUser(UsersModel usersModel);

    @Delete
    void hapusUser(UsersModel usersModel);
}
