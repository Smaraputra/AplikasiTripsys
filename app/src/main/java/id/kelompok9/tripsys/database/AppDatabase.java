package id.kelompok9.tripsys.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.kelompok9.tripsys.dao.ActivitiesDao;
import id.kelompok9.tripsys.dao.CategoriesDao;
import id.kelompok9.tripsys.dao.TripDetailsDao;
import id.kelompok9.tripsys.dao.TripsDao;
import id.kelompok9.tripsys.dao.UsersDao;
import id.kelompok9.tripsys.model.ActivityModel;
import id.kelompok9.tripsys.model.CategoriesModel;
import id.kelompok9.tripsys.model.TripDetailsModel;
import id.kelompok9.tripsys.model.TripsModel;
import id.kelompok9.tripsys.model.UsersModel;

@Database(entities = {TripsModel.class, TripDetailsModel.class ,CategoriesModel.class, ActivityModel.class, UsersModel.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
    public abstract TripsDao tripsDao();
    public abstract TripDetailsDao tripDetailsDao();
    public abstract CategoriesDao categoriesDao();
    public abstract ActivitiesDao activitiesDao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getDbInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "tripsysdb")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}