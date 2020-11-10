package com.example.trieuphu2020.common;

import android.os.AsyncTask;

public class MTask extends AsyncTask<Object,Object,Object> {
    private String key;
    private  onMTaskCallBack callBack;
    public MTask(String key) {
        this.key = key;
    }

    public MTask(String key, onMTaskCallBack callBack) {
        this.key = key;
        this.callBack = callBack;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return callBack.executeTask(this,key, objects[0]);
    }

    public  void requestUI(Object data){
        publishProgress(data);
    }
    @Override
    protected void onProgressUpdate(Object... values) {
        callBack.updateUI(this,key,values[0]);
    }

    @Override
    protected void onPostExecute(Object value) {
        callBack.completeTask(this,key,value);
    }

    public interface onMTaskCallBack{
        Object executeTask(MTask mTask, String key, Object data);
        void updateUI(MTask mTask, String key, Object data);
        void completeTask(MTask mTask, String key, Object data);
    }

    public  void start(Object data){
        execute(data);
    }
    public MTask startAsync(Object data){
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,data);
        return null;
    }
    public  void stop(){
        cancel(true);
    }
}
