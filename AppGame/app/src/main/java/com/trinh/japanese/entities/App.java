package com.trinh.japanese.entities;

import android.app.Application;

import androidx.room.Room;

import com.trinh.japanese.database.KanjiDB;
import com.trinh.japanese.database.QuestionDB;

public class App extends Application {
    private static App instance;
    private QuestionDB questionDB;
    private KanjiDB kanjiDB;

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

        kanjiDB = Room.databaseBuilder(this, KanjiDB.class, "Question.db")
                .createFromAsset("databases/Question.db").build();
    }

    public QuestionDB getQuestionDB() {
        return questionDB;
    }
    public KanjiDB getKanjiDB() {
        return kanjiDB;
    }
}
