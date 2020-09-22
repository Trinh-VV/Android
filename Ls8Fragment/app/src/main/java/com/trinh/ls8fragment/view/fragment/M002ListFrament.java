package com.trinh.ls8fragment.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trinh.ls8fragment.R;

public class M002ListFrament extends Basefragment{
    public static final String TAG2 = M002ListFrament.class.getName();

    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m002_list;
    }
}
