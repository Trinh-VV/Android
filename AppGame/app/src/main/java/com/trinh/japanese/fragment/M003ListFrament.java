package com.trinh.japanese.fragment;


import com.trinh.japanese.R;

public class M003ListFrament extends Basefragment{
    public static final String TAG = M003ListFrament.class.getName();

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m003_list;
    }

    protected void initView() {

    }

    @Override
    public void backToPrevious() {
        callBack.showFrg(TAG, sourceTag,false);
    }
}
