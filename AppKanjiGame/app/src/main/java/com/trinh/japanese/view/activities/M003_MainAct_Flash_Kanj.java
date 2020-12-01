package com.trinh.japanese.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ramotion.foldingcell.FoldingCell;
import com.trinh.japanese.R;
import com.trinh.japanese.database.DataManager;
import com.trinh.japanese.entities.KanjiEntity;
import com.trinh.japanese.view.fragment.M003FlashFragment;

import java.util.List;

public class M003_MainAct_Flash_Kanj extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = M003FlashFragment.class.getName();
    private List<KanjiEntity> listKanji;
    private TextView tvAfter, tvBefore, tvTopic;
    private FoldingCell foldingCell;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_act_flash);
        initView();
    }

    protected void initView() {
        tvTopic = findViewById(R.id.tv_topic);
        tvAfter = findViewById(R.id.tv_kanji);
        tvBefore = findViewById(R.id.tv_mean);
        findViewById(R.id.bt_next_Kanji).setOnClickListener(this);
        foldingCell = findViewById(R.id.folding_cell);
        foldingCell.setOnClickListener(this);
        initData();
    }

    private void handleClickView(int idView) {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.folding_cell) {
            foldingCell.toggle(false);
        } else if (view.getId() == R.id.bt_next_Kanji) {
            if (count<=listKanji.size()-1){
                setContent();
            }else {
                count=0;
                setContent();
            }
        }
    }

    private void setContent() {
        KanjiEntity kanji = listKanji.get(count);
        String before = unescape(kanji.getBefore());
        tvAfter.setText(kanji.getAfter());
        tvBefore.setText(before);
        count++;
    }

    private void initData() {
        Intent intent = getIntent();
        int level = intent.getIntExtra(M003FlashFragment.KEY_FLASH, 0);
        tvTopic.setText("HÁN TỰ N" + level);
        DataManager.getInstance().getListKanjiByLevel(new DataManager.OnDataCallBack() {
            @Override
            public void callBack(Object data) {
                listKanji = (List<KanjiEntity>) data;
            }
        }, level);
    }

    private String unescape(String description) {
        return description.replaceAll("\\\\n", "\\\n");
    }
}