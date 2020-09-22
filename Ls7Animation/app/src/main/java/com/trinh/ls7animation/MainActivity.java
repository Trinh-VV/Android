package com.trinh.ls7animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPic1, ivPic2, ivPic3;
    private Animation mAnim;
    private boolean isAnimEnd;
    private int idView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        isAnimEnd = true;
        mAnim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        mAnim.setAnimationListener(new AdapterAnim() {
            @Override
            public void onAnimationEnd(Animation animation) {
                handleClickView(idView);
                isAnimEnd = true;
            }
        });
    }

    private void handleClickView(int idView) {
        if (idView == R.id.bt_giai_que) {
            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        View frMain = findViewById(R.id.ln_main);
        frMain.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_scale));

        ivPic1 = findViewById(R.id.iv_bai1);
        ivPic2 = findViewById(R.id.iv_bai2);
        ivPic3 = findViewById(R.id.iv_bai3);

        ivPic1.setOnClickListener(this);

        startAnimPic(ivPic1, R.anim.anim_card);

        findViewById(R.id.bt_giai_que).setOnClickListener(this);

    }

    private void startAnimPic(final ImageView iv, final int anim) {
        iv.setVisibility(View.VISIBLE);
        Animation animAlpha = AnimationUtils.loadAnimation(this, anim);
        animAlpha.setAnimationListener(new AdapterAnim() {
            @Override
            public void onAnimationEnd(Animation animation) {

                if (iv == ivPic1 && anim == R.anim.anim_card) {
                    ivPic1.setImageResource(R.drawable.ic_1);
                    startAnimPic(ivPic1, R.anim.anim_scale);

                } else if (iv == ivPic1 && anim == R.anim.anim_scale) {
                    startAnimPic(ivPic1, R.anim.anim_up);
                    startAnimPic(ivPic2, R.anim.anim_card);

                } else if (iv == ivPic2 && anim == R.anim.anim_card) {
                    ivPic2.setImageResource(R.drawable.ic_2);
                    startAnimPic(ivPic2, R.anim.anim_scale);

                } else if (iv == ivPic2 && anim == R.anim.anim_scale) {
                    startAnimPic(ivPic3, R.anim.anim_card);

                } else if (iv == ivPic3 && anim == R.anim.anim_card) {
                    ivPic3.setImageResource(R.drawable.ic_3);
                    startAnimPic(ivPic3, R.anim.anim_scale);

                } else if (iv == ivPic3 && anim == R.anim.anim_scale) {
                    startAnimPic(ivPic3, R.anim.anim_down);
                }
            }
        });
        iv.startAnimation(animAlpha);
    }

    @Override
    public void onClick(View view) {
        if (isAnimEnd) {
            isAnimEnd = false;
            idView = view.getId();
            view.startAnimation(mAnim);
        }
    }
}