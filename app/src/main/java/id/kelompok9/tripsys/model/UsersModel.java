package id.kelompok9.tripsys.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "users")
public class UsersModel {

    @PrimaryKey(autoGenerate = true)
    private int id_user;
    @ColumnInfo(name = "name_user")
    private String name_user;
    @ColumnInfo(name = "phone_user")
    private String phone_user;
    @ColumnInfo(name = "address_user")
    private String address_user;
    @ColumnInfo(name = "gender_user")
    private String gender_user;
    @ColumnInfo(name = "username_user")
    private String username_user;
    @ColumnInfo(name = "password_user")
    private String password_user;

    @Ignore
    public UsersModel(String name_user, String phone_user,
                      String address_user, String gender_user,
                      String username_user, String password_user) {
        this.name_user = name_user;
        this.phone_user = phone_user;
        this.address_user = address_user;
        this.gender_user = gender_user;
        this.username_user = username_user;
        this.password_user = password_user;
    }

    public UsersModel(int id_user, String name_user, String phone_user,
                      String address_user, String gender_user,
                      String username_user, String password_user) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.phone_user = phone_user;
        this.address_user = address_user;
        this.gender_user = gender_user;
        this.username_user = username_user;
        this.password_user = password_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }

    public String getAddress_user() {
        return address_user;
    }

    public void setAddress_user(String address_user) {
        this.address_user = address_user;
    }

    public String getGender_user() {
        return gender_user;
    }

    public void setGender_user(String gender_user) {
        this.gender_user = gender_user;
    }

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }
}
