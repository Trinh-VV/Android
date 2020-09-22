package com.trinh.ls6drawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPlay, ivPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ivPlay = findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(this);

        ivPin = findViewById(R.id.iv_pin);

        AnimationDrawable anim = (AnimationDrawable)ivPin.getDrawable();
        anim.start();

    }

    @Override
    public void onClick(View view) {
        ivPlay.setImageLevel(ivPlay.getDrawable().getLevel() == 0 ? 1 : 0);
    }
}