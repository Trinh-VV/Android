package com.example.trieuphu2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trieuphu2020.common.OnActionCallback;
import com.example.trieuphu2020.R;

public class DialogCall extends Dialog implements View.OnClickListener {
    private static final int KEY_CALL = 103;
    private TextView tvTrueCase;
    private int trueKey;
    private OnActionCallback callback;

    public DialogCall(int key, @NonNull Context context, OnActionCallback event) {
        super(context, R.style.dialog_theme);
        trueKey = key;
        setContentView(R.layout.dialog_call);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        inintView();
        callback = event;
    }

    private void inintView() {
        findViewById(R.id.bt_call_exit).setOnClickListener(this);
        tvTrueCase = findViewById(R.id.tv_help_call);
        tvTrueCase.setText(trueKey+"");
    }

    @Override
    public void onClick(View view) {
        dismiss();
        callback.callBack(KEY_CALL,null);
    }
}
