package com.example.trieuphu2020.common;

import android.app.Application;

import androidx.room.Room;

import com.example.trieuphu2020.databases.entities.QuestionDB;

public class App extends Application {
    private static App instance;
    private QuestionDB questionDB;

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
        questionDB = Room.databaseBuilder(this, QuestionDB.class, "Question.db")
                .createFromAsset("databases/Question.db").build();
    }

    public QuestionDB getQuestionDB() {
        return questionDB;
    }
}
