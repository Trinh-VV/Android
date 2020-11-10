package com.example.trieuphu2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.trieuphu2020.common.OnActionCallback;
import com.example.trieuphu2020.R;

public class DialogStop extends Dialog implements View.OnClickListener {
    public static final int KEY_STOP_GAME = 102 ;
    private OnActionCallback callback;


    public DialogStop(@NonNull Context context, OnActionCallback event) {
        super(context, R.style.dialog_theme);
        setContentView(R.layout.dialog_stop);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        inintView();
        callback = event;
    }

    private void inintView() {
        findViewById(R.id.bt_getout).setOnClickListener(this);
        findViewById(R.id.bt_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_save){
            dismiss();
            callback.callBack(KEY_STOP_GAME,null);
        }else if(view.getId() == R.id.bt_getout){
            dismiss();
        };
    }
}
