package com.trinh.japanese.entities;

import android.app.Application;

import androidx.room.Room;

import com.trinh.japanese.database.DataDB;

public class App extends Application {
    private static App instance;
    private DataDB dataDB;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDB();
    }

    private void initDB() {
        dataDB = Room.databaseBuilder(this, DataDB.class, "Question.db")
                .createFromAsset("databases/Question.db").build();
    }

    public DataDB getDataDB() {
        return dataDB;
    }
}
