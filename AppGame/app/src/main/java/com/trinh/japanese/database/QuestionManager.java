package com.trinh.japanese.database;

import com.trinh.japanese.entities.App;

public class QuestionManager {
    private static QuestionManager instance;

    private QuestionManager() {
    }

    public static QuestionManager getInstance() {
        if (instance == null) {
            instance = new QuestionManager();
        }
        return instance;
    }

    public void getQuestionById(final OnHSCallBack cb, int level) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getQuestionDB().getQuestionDAO().getQuestionById(level));
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

