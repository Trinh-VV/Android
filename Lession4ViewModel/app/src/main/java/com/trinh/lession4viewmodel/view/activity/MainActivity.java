package com.trinh.lession4viewmodel.view.activity;

import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.trinh.lession4viewmodel.R;
import com.trinh.lession4viewmodel.view.model.MainViewModel;

public class MainActivity extends BaseActivity {
    private TextView tvCount;
    private MainViewModel mModel;

    @Override
    protected void initView() {
        mModel = new ViewModelProvider(this).get(MainViewModel.class);

        tvCount = findViewById(R.id.tv_count);
        findViewById(R.id.bt_count, this);
        tvCount.setText(String.format("%s", mModel.getCount()));

    }

    @Override
    protected void clickView(int id, View view) {
        if (id == R.id.bt_count) {
            mModel.counting();
            tvCount.setText(String.format("%s", mModel.getCount()));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}