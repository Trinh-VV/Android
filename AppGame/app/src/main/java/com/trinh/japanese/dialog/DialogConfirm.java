package com.trinh.japanese.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.trinh.japanese.R;
import com.trinh.japanese.entities.OnActionCallback;

public class DialogConfirm extends Dialog implements View.OnClickListener {
    public static final int KEY_CONFIRM = 101;
    private TextView tvTittle, tvContent;
    private OnActionCallback callback;

    public DialogConfirm(String tittle, String content,  @NonNull Context context, OnActionCallback event) {
        super(context, R.style.dialog_theme);
        setContentView(R.layout.dialog_confirm);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        inintView(tittle,content);
        callback = event;
    }

    private void inintView(String tittle, String content) {
        findViewById(R.id.bt_confirm).setOnClickListener(this);
        tvTittle =findViewById(R.id.tv_tittle_dialog_confirm);
        tvContent =findViewById(R.id.tv_content_dialog_confirm);
        tvTittle.setText(tittle);
        tvContent.setText(content);
    }

    @Override
    public void onClick(View view) {
        callback.callBack(KEY_CONFIRM,null);
    }

}
