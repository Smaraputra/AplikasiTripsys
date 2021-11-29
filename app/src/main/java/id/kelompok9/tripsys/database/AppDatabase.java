package id.kelompok9.tripsys.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.kelompok9.tripsys.dao.UserDao;
import id.kelompok9.tripsys.model.UserModel;

@Database(entities = {UserModel.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

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