package com.trinh.ls8fragment.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.trinh.ls8fragment.view.activity.OnMainCallback;

public abstract class Basefragment extends Fragment implements View.OnClickListener {
    public static final String TAG = Basefragment.class.getName();
    protected Context mContext;
    protected View mRootView;
    protected OnMainCallback callBack;

    public void setCallBack(OnMainCallback callBack) {
        this.callBack = callBack;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initView();
        return mRootView;
    }

    protected abstract void initView();

    protected abstract int getLayoutId();


    public final <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }

    protected final <T extends View> T findViewById(int id, View.OnClickListener event) {
        T view = findViewById(id);
        if (view != null && event != null) {
            view.setOnClickListener(event);
        }
        return view;
    }


    @Override
    public void onClick(View view) {
        //do nothing
    }

}
