package com.trinh.japanese.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.trinh.japanese.entities.KanjiEntity;
import com.trinh.japanese.entities.MimiEntity;
import com.trinh.japanese.entities.QuestionEntity;
import com.trinh.japanese.entities.SavedEntity;

@Database(entities = {QuestionEntity.class, KanjiEntity.class, MimiEntity.class, SavedEntity.class}, version = 1)
public abstract class DataDB extends RoomDatabase {
    public abstract DataDAO getDataDAO();
}
