package com.trinh.japanese.view.fragment;


import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.common.LessionAdapter;
import com.trinh.japanese.database.DataManager;
import com.trinh.japanese.entities.Lession;
import com.trinh.japanese.entities.MimiEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M002MimiN3Frament extends Basefragment implements LessionAdapter.OnLessionListener {
    public static final String TAG = M002MimiN3Frament.class.getName();
    int mimiIndex = 0;
    int saveIndex = 0;
    List<MimiEntity> mList;
    private RecyclerView rcvLession;
    private LessionAdapter lessionAdapter;
    private ArrayList<Lession> listLession;
    private List<MimiEntity> listSaved;
    private List<MimiEntity> listMimi;
    private boolean isListMimi;
    private boolean isListSaved = false;
    private int level = 1;
    private TextView tvViDu, tvDich, tvTuVung, tvNghia, tvCount, tvLession;
    private SwitchCompat switchCompat;
    private ImageView imvLession;
    private List<Integer> listIDSaved;
    private TableRow btNext, btBack;
    private Button btSave, btRemove;
    private LinearLayout lnListSaved;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m002_list;
    }

    protected void initView() {
        listIDSaved = new ArrayList<>();
        tvLession = findViewById(R.id.tv_lession);
        imvLession = findViewById(R.id.imv_lession);
        tvCount = findViewById(R.id.tv_count);
        tvTuVung = findViewById(R.id.tv_tu_vung);
        switchCompat = findViewById(R.id.sw_random);
        tvNghia = findViewById(R.id.tv_nghia_tu_vung);
        tvViDu = findViewById(R.id.tv_vidu);
        tvDich = findViewById(R.id.tv_dich);

        btBack = findViewById(R.id.bt_back);
        btBack.setOnClickListener(this);
        btNext = findViewById(R.id.bt_next);
        btNext.setOnClickListener(this);

        btSave = findViewById(R.id.bt_save_list);
        btSave.setOnClickListener(this);
        btRemove = findViewById(R.id.bt_remove_list);
        btRemove.setOnClickListener(this);
        lnListSaved = findViewById(R.id.ln_list_save);
        lnListSaved.setOnClickListener(this);

        findViewById(R.id.bt_save_list).setOnClickListener(this);
        findViewById(R.id.bt_remove_list).setOnClickListener(this);
        findViewById(R.id.ln_list_save).setOnClickListener(this);
        listSaved = new ArrayList<>();
        initTopic();
        initData();
    }

    private void initTopic() {
        listLession = new ArrayList<>();
        try {
            String[] listPath = getActivity().getAssets().list("image");
            listLession = new ArrayList<>();
            for (String fileName : listPath) {
                final String name = fileName.replaceFirst("[.][^.]+$", "");
                listLession.add(new Lession("file:///android_asset/image/" + fileName, name));
                rcvLession = findViewById(R.id.rcv_lession);
                lessionAdapter = new LessionAdapter(listLession, this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                rcvLession.setLayoutManager(linearLayoutManager);
                lessionAdapter.setData(listLession);
                rcvLession.setAdapter(lessionAdapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        DataManager.getInstance().getListTVByLevel(new DataManager.OnDataCallBack() {
            @Override
            public void callBack(Object data) {
                listMimi = (List<MimiEntity>) data;
                showContent(0);
            }
        }, level);
    }

    @Override
    public void onClick(View view) {
        if (mList.size() == 0) return;

        Random random = new Random();
        int r = random.nextInt(mList.size());
        mimiIndex = (switchCompat.isChecked() && mList == listMimi ? r : mimiIndex);
        switch (view.getId()) {
            case R.id.bt_next:
                animClick(btNext);
                mimiIndex = (mimiIndex == mList.size() - 1 ? -1 : mimiIndex);
                mimiIndex++;
                showContent(mimiIndex);
                break;
            case R.id.bt_back:
                animClick(btBack);
                mimiIndex = (mimiIndex == 0 ? mimiIndex = mList.size() : mimiIndex);
                mimiIndex--;
                showContent(mimiIndex);
                break;
            case R.id.bt_save_list:
                saveToList();
                break;
            case R.id.bt_remove_list:
                removeListSaved();
                break;
            case R.id.ln_list_save:
                showListSaved();
                break;
        }
    }

    private void animClick(View view){
        view.startAnimation(AnimationUtils
                .loadAnimation(mContext, R.anim.anim_alpha));
    }

    private void showListSaved() {
        animClick(lnListSaved);
        isListSaved = true;
        isListMimi = false;
        LessionAdapter adapter = (LessionAdapter) rcvLession.getAdapter();
        adapter.updateUI();
        isListMimi = false;
        if (listSaved.size() == 0) {
            return;
        }
        mimiIndex = 0;
        showContent(0);
    }

    private void saveToList() {
        animClick(btSave);
        if (!isListMimi) return;
        if (!listIDSaved.contains(listMimi.get(mimiIndex).getId())) {
            listSaved.add(listMimi.get(mimiIndex));
            listIDSaved.add(listMimi.get(mimiIndex).getId());
            tvCount.setText(listSaved.size() + "");
        } else {
            Toast.makeText(getContext(), "Từ này đã được lưu trong list", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeListSaved() {
        animClick(btRemove);
        if (!isListSaved || listSaved.size() == 0) {
            return;
        }else{
            int currentID = listSaved.get(mimiIndex).getId();
            listIDSaved.remove(listIDSaved.indexOf(currentID));
            listSaved.remove(mimiIndex);
            if (listSaved.size() == 0){
                tvTuVung.setText("");
                tvNghia.setText("");
                tvViDu.setText("");
                tvDich.setText("");
                Toast.makeText(getContext(), "List trống", Toast.LENGTH_SHORT).show();
            }else {
                showContent(0);
                mimiIndex =0;
            }
        }
        tvCount.setText(listSaved.size() + "");
    }

    private void showContent(int index) {
        mList = (List<MimiEntity>) (isListMimi ? listMimi : listSaved);
        MimiEntity tuVung = mList.get(index);
        String htmlText = unescape(tuVung.getNghia());
        tvTuVung.setText(tuVung.getTuvung());
        tvNghia.setText(htmlText);
        tvViDu.setText(tuVung.getVidu());
        tvDich.setText(tuVung.getDich());
    }

    private String unescape(String description) {
        return description.replaceAll("\\\\n", "\\\n");
    }

    @Override
    public void backToPrevious() {
        callBack.showFrg(TAG, sourceTag, false);
    }

    @Override
    public void onLesstionClick(int position) {
        isListSaved = false;
        isListMimi = true;
        mimiIndex = 0;
        level = position + 1;
        initData();

    }
}
