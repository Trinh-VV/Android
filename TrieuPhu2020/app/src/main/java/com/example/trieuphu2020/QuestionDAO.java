package com.example.trieuphu2020;

import androidx.room.Dao;
import androidx.room.Query;


import com.example.trieuphu2020.databases.entities.QuestionEntity;

import java.util.List;

@Dao
public interface QuestionDAO {
    int x = 0;
    @Query("SELECT * FROM Question")
    List<QuestionEntity> getAllQuestion();

    @Query("SELECT * FROM (SELECT * FROM Question WHERE level= :level) ORDER BY random() limit 1 ")
    QuestionEntity getQuestionById(int level);
}
