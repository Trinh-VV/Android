package com.example.trieuphu2020.databases.entities;

import com.example.trieuphu2020.common.App;

public class QuestionManager  {
    private static QuestionManager instance;
    private  QuestionManager(){

    }

    public  static QuestionManager getInstance(){
        if(instance==null){
            instance= new QuestionManager();
        }
        return instance;
    }

    public  interface  OnHSCallBack{
        void callBack(Object data);
    }

    public void getQuestionById(final OnHSCallBack cb, int level){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getQuestionDB().getQuestionDAO().getQuestionById(level));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

