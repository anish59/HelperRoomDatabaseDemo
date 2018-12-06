package com.helperroomdatabasedemo.dbHelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.helperroomdatabasedemo.dbHelper.daos.StudentDao;
import com.helperroomdatabasedemo.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class CollegeDatabase extends RoomDatabase {
    private static final String DB_NAME = "CollegeDatabase.db";
    private static volatile CollegeDatabase instance;

    public static synchronized CollegeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static CollegeDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                CollegeDatabase.class,
                DB_NAME).allowMainThreadQueries().build();
    }

    public abstract StudentDao getCollegeDao();


}
