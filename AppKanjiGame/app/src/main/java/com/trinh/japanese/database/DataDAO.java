package com.trinh.japanese.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.trinh.japanese.entities.KanjiEntity;
import com.trinh.japanese.entities.MimiEntity;
import com.trinh.japanese.entities.QuestionEntity;
import com.trinh.japanese.entities.SavedEntity;

import java.util.List;

@Dao
public interface DataDAO {

    //Question
    @Query("SELECT * FROM Question")
    List<QuestionEntity> getAllQuestion();

    @Query("SELECT * FROM (SELECT * FROM Question WHERE level= :level) ORDER BY random() limit 1 ")
    QuestionEntity getQuestionById(int level);

    //Kanji
    @Query("SELECT * FROM Kanji")
    List<KanjiEntity> getAllKanji();

    @Query("SELECT * FROM (SELECT * FROM Kanji WHERE level= :level) ORDER BY random() limit 1 ")
    KanjiEntity getKanjiById(int level);

    @Query("SELECT * FROM (SELECT * FROM Kanji WHERE level= :level)")
    List<KanjiEntity> getListKanjiByLevel(int level);

    //Mimi
    @Query("SELECT * FROM MimiN3")
    List<MimiEntity> getAllTuVung();

    @Query("SELECT * FROM (SELECT * FROM MimiN3 WHERE level= :level)")
    List<MimiEntity> getListTVByLevel(int level);

    //List Saved
    @Query("SELECT * FROM Saved")
    List<SavedEntity> getListSaved();

    @Query("INSERT INTO Saved Values(:id,:level,:tuvung, :nghia, :vidu, :dich) ")
    void themTV(int id, int level, String tuvung, String nghia, String vidu, String dich);
}
