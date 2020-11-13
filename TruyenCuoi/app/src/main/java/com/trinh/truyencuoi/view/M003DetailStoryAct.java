package com.trinh.truyencuoi.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.trinh.truyencuoi.R;
import com.trinh.truyencuoi.entities.Story;

import java.util.List;

public class M003DetailStoryAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_activity_detail);
        initViews();
    }

    private void initViews() {
        ViewPager vpStory = findViewById(R.id.vp_story);
        Intent intent = getIntent();
        if(intent!=null){
            Story story= (Story) intent.getSerializableExtra
                                    (M002ListStoryActivity.KEY_STORY_SELECTED);

            List<Story> listData = (List<Story>) intent.getSerializableExtra
                                    (M002ListStoryActivity.KEY_LIST_STORY);
            StoryAdapter adapter = new StoryAdapter(listData, this);
            vpStory.setAdapter(adapter);
            int index  =listData.indexOf(story);

            vpStory.setCurrentItem(index, true);
        }
    }
}
