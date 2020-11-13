package com.trinh.japanese.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.trinh.japanese.entities.KanjiEntity;

import java.util.List;

@Dao
public interface KanjiDAO {
    int x = 0;
    @Query("SELECT * FROM Kanji")
    List<KanjiEntity> getAllKanji();

    @Query("SELECT * FROM (SELECT * FROM Kanji WHERE level= :level) ORDER BY random() limit 1 ")
    KanjiEntity getKanjiById(int level);

    @Query("SELECT * FROM (SELECT * FROM Kanji WHERE level= :level)")
    List<KanjiEntity> getListKanjiByLevel(int level);
}
