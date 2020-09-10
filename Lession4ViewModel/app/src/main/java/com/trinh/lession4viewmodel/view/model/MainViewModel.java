package com.trinh.lession4viewmodel.view.model;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private int count;

    public void counting() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
