package com.trinh.truyencuoi.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trinh.truyencuoi.R;
import com.trinh.truyencuoi.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class M001TopicActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_NAME_TOPIC = "KEY_NAME_TOPIC";
    public static final String KEY_NAMEFILE_TOPIC = "KEY_NAMEFILE_TOPIC";
    private static final int LEVEL_CLOSE = 0;
    private static final int LEVEL_OPEN = 1;
    private DrawerLayout mDrawer;
    private ImageView ivMenu;
    private List<Topic> listTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_activity_main);
        initView();
    }

    private void initView() {
        mDrawer = findViewById(R.id.drawer);
        mDrawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                ivMenu.getDrawable().setLevel(LEVEL_OPEN);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                ivMenu.getDrawable().setLevel(LEVEL_CLOSE);

            }
        });
        ivMenu = findViewById(R.id.iv_menu);
        ivMenu.setOnClickListener(this);
        initTopic();
    }

    private void initTopic() {
        try {
            String[] listPath = getAssets().list("image");
            listTopic = new ArrayList<>();
            LinearLayout lnTopic = findViewById(R.id.ln_topic);
            lnTopic.removeAllViews();

            for (final String fileName : listPath) {
                final String name = fileName.replaceFirst("\\..+", "");
                listTopic.add(new Topic("image/" + fileName, fileName));

                final View topicView = LayoutInflater.from(this).inflate(R.layout.item_topic, lnTopic, false);
                ImageView ivTopic = topicView.findViewById(R.id.iv_topic);
                TextView tvTopic = topicView.findViewById(R.id.tv_topic);
                //ivTopic.setImageBitmap(BitmapFactory.decodeStream(getAssets().open("image/" + fileName)));
                Glide.with(this)
                        .load(Uri.parse("file:///android_asset/image/"+fileName))
                        .placeholder(R.drawable.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .useAnimationPool(true)
                        .into(ivTopic);

                tvTopic.setText(name);

                topicView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(M001TopicActivity.this, name, Toast.LENGTH_LONG).show();
                        topicView.startAnimation(AnimationUtils.loadAnimation(M001TopicActivity.this, R.anim.anim_alpha));
                        Intent intent = new Intent();
                        intent.setClass(M001TopicActivity.this, M002ListStoryActivity.class);
                        intent.putExtra(KEY_NAME_TOPIC, name);
                        startActivity(intent);
                    }
                });
                lnTopic.addView(topicView);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_menu) {
            mDrawer.openDrawer(Gravity.LEFT);
        }
    }
}