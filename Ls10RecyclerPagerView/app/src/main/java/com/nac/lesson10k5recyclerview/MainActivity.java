package com.nac.lesson10k5recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nac.lesson10k5recyclerview.entities.Story;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    public static final String KEY_LIST_STORY = "KEY_LIST_STORY";
    public static final String KEY_STORY_SELECTED = "KEY_STORY_SELECTED";
    private List<Story> mListData;
    private RecyclerView rvTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initData() {
        try {
            InputStream in = getAssets().open("Thơ ca cười.txt");
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
//                    Toast.makeText(MainActivity.this,
//                            story.getName()+"\n\n"+story.getContent(),
//                            Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DetailStoryAct.class);
                intent.putExtra(KEY_LIST_STORY, (Serializable) mListData);
                intent.putExtra(KEY_STORY_SELECTED, story);

                startActivity(intent);
            }
        });
    }

    private void initViews() {
        rvTopic = findViewById(R.id.rv_topic);
    }
}