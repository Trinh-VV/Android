package com.trinh.japanese.database;

import com.trinh.japanese.entities.App;

public class DataManager {
    private static DataManager instance;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void getQuestionById(final OnDataCallBack cb, int level) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getDataDB().getDataDAO().getQuestionById(level));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getListKanjiByLevel(final OnDataCallBack cb, int level) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getDataDB().getDataDAO().getListKanjiByLevel(level));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getListTVByLevel(final OnDataCallBack cb, int level) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getDataDB().getDataDAO().getListTVByLevel(level));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getListSaved(final OnDataCallBack cb) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getDataDB().getDataDAO().getListSaved());
                } catch (Exception e) {
                    e.printStackTrace();
                    cb.callBack(null);
                }
            }
        }.start();
    }

    public void themHS(OnDataCallBack callback
            , int id, int level, String tuvung, String nghia, String vidu, String dich) {
        new Thread() {
            @Override
            public void run() {
                try {
                    App.getInstance().getDataDB().getDataDAO().themTV(id, level, tuvung, nghia, vidu, dich);
                    callback.callBack(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.callBack(false);
                }
            }
        }.start();
    }

    public interface OnDataCallBack {
        void callBack(Object data);
    }
}

