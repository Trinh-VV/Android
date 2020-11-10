package com.example.trieuphu2020.databases.entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trieuphu2020.QuestionDAO;

@Database(entities = {QuestionEntity.class}, version = 1)
public abstract class QuestionDB extends RoomDatabase {
    public abstract QuestionDAO getQuestionDAO();
}
