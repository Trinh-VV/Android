package com.trinh.japanese.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;

import com.trinh.japanese.R;
import com.trinh.japanese.activities.M001_MainAct;
import com.trinh.japanese.activities.M002_MainAct_Flash_Kanj;

public class M001FlashFragment extends Basefragment {
    public static final String TAG = M001FlashFragment.class.getName();
    public static final String KEY_N1 = "KEY_N1";
    public static final String KEY_N2 = "KEY_N2";
    public static final String KEY_N3 = "KEY_N3";
    public static final String KEY_N4 = "KEY_N4";
    public static final String KEY_N5 = "KEY_N5";
    public static final String KEY_FLASH = "KEY_FLASH";
    public static Dialog dialog;


    protected void initView() {
        findViewById(R.id.bt_n1,this);
        findViewById(R.id.bt_n2,this);
        findViewById(R.id.bt_n3,this);
        findViewById(R.id.bt_n4,this);
        findViewById(R.id.bt_n5,this);
        initData();
    }

    private void handleClickView(int idView) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m001_menu_kanji;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_n1:
                showFlash(KEY_FLASH, 1);
                break;
            case R.id.bt_n2:
                showFlash(KEY_FLASH, 2);
                break;
            case R.id.bt_n3:
                showFlash(KEY_FLASH, 3);
                break;
            case R.id.bt_n4:
                showFlash(KEY_FLASH, 4);
                break;
            case R.id.bt_n5:
                showFlash(KEY_FLASH, 5);
                break;
        }

    }

    private void showFlash(String keyN, int i) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), M002_MainAct_Flash_Kanj.class);
        intent.putExtra(keyN, i);
        startActivity(intent);
    }

    @Override
    public void backToPrevious() {
        if (sourceTag.equals(M002GameFrament.TAG)) {
            //do nothing
        } else if (sourceTag.equals(M001_MainAct.TAG)) {
            callBack.closeApp();
        }
    }

    private void initData() {
  }
}

