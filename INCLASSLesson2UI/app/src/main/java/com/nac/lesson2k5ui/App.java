package com.nac.lesson2k5ui;

import android.app.Application;

public class App extends Application {
    private Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        storage = new Storage();
    }

    public Storage getStorage() {
        return storage;
    }
}
