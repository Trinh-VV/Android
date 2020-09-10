package com.trinh.lession4viewmodel.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trinh.lession4viewmodel.view.App;
import com.trinh.lession4viewmodel.view.Storage;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }


    @Override
    public final <T extends View> T findViewById(int id) {
        return findViewById(id, null);
    }

    public final <T extends View> T findViewById(int id, View.OnClickListener event) {
        T view = super.findViewById(id);

        if (view != null && event != null) {
            view.setOnClickListener(event);
        }
        return view;
    }

    @Override
    public final void onClick(View view) {
        clickView(view.getId(), view);
    }

    protected void clickView(int id, View view) {
        //do nothing
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    public Storage getStorage() {
        return ((App) getApplicationContext()).getStorage();
    }
}
