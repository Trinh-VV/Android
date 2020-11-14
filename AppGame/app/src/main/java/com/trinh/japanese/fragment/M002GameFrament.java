package com.trinh.japanese.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.ScoreSaveLoad;
import com.trinh.japanese.activities.M001_MainAct;
import com.trinh.japanese.activities.M003_MainAct_Game;
import com.trinh.japanese.common.HighScoreAdapter;
import com.trinh.japanese.entities.User;

import java.util.List;

public class M002GameFrament extends Basefragment{
    public static final String TAG = M001FlashFragment.class.getName();
    public static Dialog dialog;


    protected void initView() {
        findViewById(R.id.bt_start,this);
        findViewById(R.id.bt_high_score,this);
        initData();
    }

    private void handleClickView(int idView) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m002_game;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_start) {
            Intent intent = new Intent(getActivity(), M003_MainAct_Game.class);
            startActivity(intent);
        } else if (view.getId() == R.id.bt_high_score) {
            showDialog(getActivity());
        }
    }

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_high_score);
        Button btndialog = dialog.findViewById(R.id.bt_highscore_close);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        List<User> data = ScoreSaveLoad.getInstance().getData(M003_MainAct_Game.KEY_SCORE);
        RecyclerView recyclerView = dialog.findViewById(R.id.rcv_score);
        HighScoreAdapter adapterRCV = new HighScoreAdapter(getContext());
        adapterRCV.setData(data);
        recyclerView.setAdapter(adapterRCV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        dialog.show();

    }

    @Override
    public void backToPrevious() {
        if (sourceTag.equals(M002GameFrament.TAG)) {
            //do nothing
        } else if (sourceTag.equals(M001_MainAct.TAG)) {
            callBack.closeApp();
        }
    }

    private void initData() {
    }
}
