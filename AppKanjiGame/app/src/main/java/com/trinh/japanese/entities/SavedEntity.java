package com.trinh.japanese.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Saved", primaryKeys = {"_id"})
public class SavedEntity {

    @NonNull
    @ColumnInfo(name = "_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "level")
    private int level;

    @ColumnInfo(name = "tuvung")
    private String tuvung;

    @ColumnInfo(name = "nghia")
    private String nghia;

    @ColumnInfo(name = "vidu")
    private String vidu;

    @ColumnInfo(name = "dich")
    private String dich;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTuvung() {
        return tuvung;
    }

    public void setTuvung(String tuvung) {
        this.tuvung = tuvung;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getVidu() {
        return vidu;
    }

    public void setVidu(String vidu) {
        this.vidu = vidu;
    }

    public String getDich() {
        return dich;
    }

    public void setDich(String dich) {
        this.dich = dich;
    }
}
