package com.trinh.ls8fragment.view.fragment;

import android.view.View;

import com.trinh.ls8fragment.R;

public class M001HomeFragment extends Basefragment {
    public static final String TAG = M001HomeFragment.class.getName();


    protected void initView() {
        findViewById(R.id.iv_list).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m001_home;
    }


    @Override
    public void onClick(View view) {
        callBack.showFrg(M002ListFrament.TAG2);
    }
}

