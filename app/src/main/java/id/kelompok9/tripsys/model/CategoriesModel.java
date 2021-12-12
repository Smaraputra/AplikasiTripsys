package id.kelompok9.tripsys.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "trip_categories")
public class CategoriesModel {

    @PrimaryKey(autoGenerate = true)
    private int id_category;
    @ColumnInfo(name = "id_user_category")
    private int id_user_category;
    @ColumnInfo(name = "category_name")
    private String category_name;

    @Ignore
    public CategoriesModel(int id_user_category, String category_name) {
        this.id_user_category = id_user_category;
        this.category_name = category_name;
    }

    public CategoriesModel(int id_category, int id_user_category, String category_name) {
        this.id_category = id_category;
        this.id_user_category = id_user_category;
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return category_name;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_user_category() {
        return id_user_category;
    }

    public void setId_user_category(int id_user_category) {
        this.id_user_category = id_user_category;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
