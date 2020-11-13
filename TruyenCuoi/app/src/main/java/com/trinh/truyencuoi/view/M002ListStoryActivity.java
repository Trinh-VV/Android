package com.trinh.truyencuoi.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.truyencuoi.R;
import com.trinh.truyencuoi.entities.Story;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class M002ListStoryActivity extends AppCompatActivity {
    public static final String KEY_LIST_STORY = "KEY_LIST_STORY";
    public static final String KEY_STORY_SELECTED = "KEY_STORY_SELECTED";
    private static final String TAG = M001TopicActivity.class.getName();
    private List<Story> mListData;
    private RecyclerView rvTopic;
    private String nameTopic, nameFile;
    private TextView tv_chude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_activity_topic);
        initViews();
        initData();
    }

    private void initViews() {
        Intent intent = getIntent();
        nameFile = (String) intent.getSerializableExtra(M001TopicActivity.KEY_NAME_TOPIC);
        rvTopic = findViewById(R.id.rv_topic);
        tv_chude = findViewById(R.id.tv_chude);
        tv_chude.setText(nameFile);
    }

    private void initData() {
        try {

            InputStream in = getAssets().open(nameFile + ".txt");
            BufferedReader buffIn
                    = new BufferedReader(new InputStreamReader(in,
                    StandardCharsets.UTF_8));
            String line = buffIn.readLine();
            String name = null;
            StringBuilder content = new StringBuilder();
            mListData = new ArrayList<>();

            while (line != null) {
                if (name == null) {
                    name = line;
                } else if (line.equals("','0');")) {
                    mListData.add(new Story(name, content.toString()));
                    name = null;
                    content = new StringBuilder();
                } else {
                    content.append(line).append("\n");
                }
                line = buffIn.readLine();
            }
            buffIn.close();
            //Log.i(TAG, mListData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TopicAdapter adapter = new TopicAdapter(mListData, this);
        rvTopic.setLayoutManager(new LinearLayoutManager(this));
        rvTopic.setAdapter(adapter);
        adapter.setCallBack(new TopicAdapter.OnTopicCallBack() {
            @Override
            public void onCallBack(Story story) {
                Intent intent = new Intent();
                intent.setClass(M002ListStoryActivity.this, M003DetailStoryAct.class);
                intent.putExtra(KEY_LIST_STORY, (Serializable) mListData);
                intent.putExtra(KEY_STORY_SELECTED, story);
                startActivity(intent);
            }
        });
    }


}