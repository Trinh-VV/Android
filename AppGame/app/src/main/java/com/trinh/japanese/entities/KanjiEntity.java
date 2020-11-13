package com.trinh.japanese.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Kanji", primaryKeys = {"_id"})
public class KanjiEntity {

    @NonNull
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "before")
    private String before;

    @ColumnInfo(name = "after")
    private String after;

    @NonNull
    @ColumnInfo(name = "level")
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "KanjiEntity{" +
                "id=" + id +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                ", level=" + level +
                '}';
    }
}
