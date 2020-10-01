package com.trinh.ls8rycycleview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LEVEL_CLOSE = 0;
    private static final int LEVEL_OPEN = 1;
    private DrawerLayout mDrawer;
    private ImageView ivMenu;
    private List<Topic> listTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            String[] listPath =  getAssets().list("image");
            listTopic =new ArrayList<>();
            LinearLayout lnTopic = findViewById(R.id.ln_topic);
            lnTopic.removeAllViews();

for(String fileName : listPath ){
    String name = fileName.replaceFirst("[.][^.]+$","");
    listTopic.add(new Topic("image/" + fileName, fileName));



    View topicView = LayoutInflater.from(this).inflate(R.layout.item_topic,lnTopic,false);
    ImageView ivTopic = topicView.findViewById(R.id.iv_topic);
    TextView tvTopic = topicView.findViewById(R.id.tv_topic);
    ivTopic.setImageBitmap(BitmapFactory.decodeStream(getAssets().open("image/"+fileName)));
    tvTopic.setText(name);

    lnTopic.addView(topicView);
}
        }catch (Exception e){
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