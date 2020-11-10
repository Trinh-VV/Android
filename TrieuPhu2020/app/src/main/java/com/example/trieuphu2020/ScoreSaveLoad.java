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

public final class ScoreSaveLoad {
    public static final String PREF_FILE_NAME = "pref_share";
    public static final String KEY_SHARE = "KEY_SHARE";
    private static final String LIST_KEY = "LIST_KEY";
    private static ScoreSaveLoad instance;
    List<User> arrayItems;

    private ScoreSaveLoad() {
        //for singleton
    }

    public static ScoreSaveLoad getInstance() {
        if (instance == null) {
            instance = new ScoreSaveLoad();
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
}