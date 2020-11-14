package com.trinh.japanese.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ramotion.foldingcell.FoldingCell;
import com.trinh.japanese.R;
import com.trinh.japanese.entities.KanjiEntity;
import com.trinh.japanese.database.KanjiManager;
import com.trinh.japanese.fragment.M001FlashFragment;

import java.util.List;


public class M002_MainAct_Flash_Kanj extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = M001FlashFragment.class.getName();
    List<KanjiEntity> listKanji;
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
        findViewById(R.id.bt_next).setOnClickListener(this);
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
        } else if (view.getId() == R.id.bt_next) {
            if (count<=listKanji.size()){
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
        int level = intent.getIntExtra(M001FlashFragment.KEY_FLASH, 10);
        tvTopic.setText("HÁN TỰ N" + level);
        KanjiManager.getInstance().getListKanjiByLevel(
                data -> listKanji = (List<KanjiEntity>) data, level);

        KanjiManager.getInstance().getKanjiById(data -> {
            if (data == null) {
                return;
            }
            KanjiEntity kanjiEntity = (KanjiEntity) data;
            String be = unescape(kanjiEntity.getBefore());
            tvAfter.setText(kanjiEntity.getAfter());
            tvBefore.setText(be);
        }, level);
    }

    private String unescape(String description) {
        return description.replaceAll("\\\\n", "\\\n");
    }
}