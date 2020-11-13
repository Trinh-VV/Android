package com.trinh.japanese.database;

import com.trinh.japanese.entities.App;

public class KanjiManager {
    private static KanjiManager instance;

    private KanjiManager() {
    }

    public static KanjiManager getInstance() {
        if (instance == null) {
            instance = new KanjiManager();
        }
        return instance;
    }

    public void getKanjiById(final OnHSCallBack cb, int level) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getKanjiDB().getKanjiDAO().getKanjiById(level));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
  public void getListKanjiByLevel(final OnHSCallBack cb, int level) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getKanjiDB().getKanjiDAO().getListKanjiByLevel(level));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }



    public interface OnHSCallBack {
        void callBack(Object data);
    }
}

