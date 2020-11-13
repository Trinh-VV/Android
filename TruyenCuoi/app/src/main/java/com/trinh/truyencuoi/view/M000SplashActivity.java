package com.trinh.truyencuoi.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.trinh.truyencuoi.R;

public class M000SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m000_splash);
        initViews();
    }

    private void initViews() {
        new Handler().postDelayed(new Runnable() {
            int x = 0;

            @Override
            public void run() {
                Intent intent = new Intent(M000SplashActivity.this, M001TopicActivity.class);
                startActivity(intent);
            }
        }, 800);
    }
}
