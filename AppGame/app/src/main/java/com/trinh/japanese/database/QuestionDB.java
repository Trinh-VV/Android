package com.trinh.japanese.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.trinh.japanese.entities.KanjiEntity;
import com.trinh.japanese.entities.QuestionEntity;

@Database(entities = {QuestionEntity.class, KanjiEntity.class}, version = 1)
public abstract class QuestionDB extends RoomDatabase {
    public abstract QuestionDAO getQuestionDAO();
}
