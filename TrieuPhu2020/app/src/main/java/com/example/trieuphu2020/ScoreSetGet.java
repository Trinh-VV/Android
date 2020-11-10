package com.example.trieuphu2020;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.trieuphu2020.common.App;
import com.example.trieuphu2020.dialog.highscore.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class ScoreSetGet {
    public static final String PREF_FILE_NAME = "pref_share";
    public static final String KEY_SHARE = "KEY_SHARE";
    private static final String LIST_KEY = "LIST_KEY";
    private static ScoreSetGet instance;
    List<User> arrayItems;

    private ScoreSetGet() {
        //for singleton
    }

    public static ScoreSetGet getInstance() {
        if (instance == null) {
            instance = new ScoreSetGet();
        }
        return instance;
    }

    public void saveData(String key, List<User> list) {
        SharedPreferences sharedPreferences = App.getInstance()
                .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_SHARE, json);
        editor.apply();
    }

    public List<User> getData(String key) {
        arrayItems = new ArrayList<>();
        SharedPreferences sharedPreferences = App.getInstance()
                .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_SHARE, null);
        Type type = new TypeToken<List<User>>() {}.getType();

        arrayItems = gson.fromJson(json, type);
        if (arrayItems == null){
            arrayItems = new ArrayList<>();
        }
        return arrayItems;
    }

//    public void savePref(String key, String value) {
//        SharedPreferences pref = App.getInstance()
//                .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
//        pref.edit().putString(key, value).apply();
//    }
//
//    public String getPref(String key) {
//        return getPref(key, false);
//    }
//
//    public void clearPref(String key) {
//        SharedPreferences pref = App.getInstance()
//                .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
//        pref.edit().remove(key).apply();
//    }
//
//    public String getPref(String key, boolean isDelete) {
//        SharedPreferences pref = App.getInstance()
//                .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
//        String value = pref.getString(key, null);
//        if (isDelete) {
//            pref.edit().remove(key).apply();
//        }
//        return value;
//    }
}