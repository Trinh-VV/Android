package com.trinh.japanese.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.trinh.japanese.R;
import com.trinh.japanese.entities.OnActionCallback;

public class DialogStop extends Dialog implements View.OnClickListener {
    public static final int KEY_STOP_GAME = 102 ;
    public static final int KEY_NOT_STOP_GAME = 106;
    private OnActionCallback callback;


    public DialogStop(@NonNull Context context, OnActionCallback event) {
        super(context, R.style.dialog_theme);
        setContentView(R.layout.dialog_stop);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
        callback = event;
    }

    private void initView() {
        findViewById(R.id.bt_getout).setOnClickListener(this);
        findViewById(R.id.bt_yes_stop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_yes_stop){
            dismiss();
            callback.callBack(KEY_STOP_GAME,null);
        }else if(view.getId() == R.id.bt_getout){
            dismiss();
            callback.callBack(KEY_NOT_STOP_GAME,null);
        };
    }
}
