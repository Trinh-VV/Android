package com.trinh.japanese.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.common.HighScoreAdapter;
import com.trinh.japanese.entities.OnActionCallback;
import com.trinh.japanese.entities.User;

import java.util.List;

public class DialogScore extends Dialog implements View.OnClickListener {

    public static final int KEY_EXIT_SCORE = 104;
    public Activity activity;
    private OnActionCallback callback;
    private RecyclerView rcvScore;
    private HighScoreAdapter mScoreAdapter;

    public DialogScore(List<User> list, @NonNull Context context, OnActionCallback event) {
        super(context, R.style.dialog_theme);
        setContentView(R.layout.item_highscore);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        inintView(list);
        callback = event;
    }

    private void inintView(List<User> list) {
        rcvScore = findViewById(R.id.rcv_score);
        mScoreAdapter = new HighScoreAdapter(activity);
        LinearLayoutManager ln = new LinearLayoutManager(getContext());
        rcvScore.setLayoutManager(ln);
        mScoreAdapter.setData(list);
        rcvScore.setAdapter(mScoreAdapter);
        //findViewById(R.id.bt_highscore_close).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_high_score) {
            callback.callBack(KEY_EXIT_SCORE, null);
        }
    }
}
