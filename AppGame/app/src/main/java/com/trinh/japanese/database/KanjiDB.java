package com.trinh.japanese.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.trinh.japanese.entities.KanjiEntity;
import com.trinh.japanese.entities.QuestionEntity;

@Database(entities = {KanjiEntity.class, QuestionEntity.class}, version = 1)
public abstract class KanjiDB extends RoomDatabase {
    public abstract KanjiDAO getKanjiDAO();
}
