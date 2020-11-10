package com.example.trieuphu2020.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trieuphu2020.dialog.highscore.HighScoreAdapter;
import com.example.trieuphu2020.R;
import com.example.trieuphu2020.ScoreSetGet;
import com.example.trieuphu2020.dialog.highscore.User;

import java.util.List;

public class M001_MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static TextView textView;
    public static Dialog dialog;
    private Button dialogBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_activity_main);
        intitView();
    }

    private void intitView() {
        findViewById(R.id.bt_start).setOnClickListener(this);
        findViewById(R.id.bt_high_score).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_start) {
            Intent intent = new Intent();
            intent.setClass(this, M002_PlayActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.bt_high_score) {
            showDialog(this);
        }
    }

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_high_score);

        Button btndialog = dialog.findViewById(R.id.bt_highscore_close);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        List<User> data = ScoreSetGet.getInstance().getData(M002_PlayActivity.KEY_SCORE);
        RecyclerView recyclerView = dialog.findViewById(R.id.rcv_score);
        HighScoreAdapter adapterRCV = new HighScoreAdapter(this);
        adapterRCV.setData(data);
        recyclerView.setAdapter(adapterRCV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        dialog.show();

    }
}