package com.trinh.japanese.view.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trinh.japanese.R;
import com.trinh.japanese.common.ViewPagerAdapter;

public class M001_MainAct extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = M001_MainAct.class.getName();
    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_activity_home);
        initView();
    }


    private void initView() {
        mNavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_pager);
        setUpViewPager();
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_kanji:
                    mViewPager.setCurrentItem(ViewPagerAdapter.TAB1);
                    break;
                case R.id.action_game:
                    mViewPager.setCurrentItem(ViewPagerAdapter.TAB2);
                    break;
                case R.id.action_search:
                    mViewPager.setCurrentItem(ViewPagerAdapter.TAB3);
                    break;
            }
            return true;
        });
    }

    private void setUpViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case ViewPagerAdapter.TAB1:
                        mNavigationView.getMenu().findItem(R.id.action_kanji).setChecked(true);
                        break;
                    case ViewPagerAdapter.TAB2:
                        mNavigationView.getMenu().findItem(R.id.action_game).setChecked(true);
                        break;
                    case ViewPagerAdapter.TAB3:
                        mNavigationView.getMenu().findItem(R.id.action_search).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}