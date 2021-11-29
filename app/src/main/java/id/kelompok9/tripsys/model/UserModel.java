package id.kelompok9.tripsys.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "user")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;

    @Ignore
    public UserModel(String name, String phone,
                     String address,String gender,
                     String username, String password) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public UserModel(int id, String name, String phone,
                     String address,String gender,
                     String username, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
