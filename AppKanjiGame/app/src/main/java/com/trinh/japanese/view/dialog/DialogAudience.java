package com.trinh.japanese.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.trinh.japanese.R;
import com.trinh.japanese.entities.OnActionCallback;

import java.util.ArrayList;
import java.util.Random;

public class DialogAudience extends Dialog implements View.OnClickListener {
    private static final int KEY_AUDIENCE = 105;
    private int trueKey;
    private OnActionCallback callback;
    private BarChart chart;

    public DialogAudience(int key, @NonNull Context context, OnActionCallback event) {
        super(context, R.style.dialog_theme);
        this.trueKey = key;
        setContentView(R.layout.dialog_audience);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        inintView();
        callback = event;
    }

    private void inintView() {
        findViewById(R.id.bt_audience_ok).setOnClickListener(this);
        chart = findViewById(R.id.barchart);
        initData();
    }

    private void initData() {
        Random rd = new Random();
        int rd1 = rd.nextInt(20);
        int rd2 = rd.nextInt(40 - rd1);
        int rd3 = rd.nextInt(40 - rd1 - rd2);
        int rd4 = 40 - rd1 - rd2 - rd3;
        int arrCase[] = new int[]{rd1 + 10, rd2 + 10, rd3 + 10, rd4 + 10};
        final int addTrueCase = 20;
        for (int i = 0; i < arrCase.length; i++) {
            if ((i + 1) == trueKey) {
                arrCase[i] += addTrueCase;
            }
        }

        ArrayList<BarEntry> audience = new ArrayList<>();
        audience.add(new BarEntry(1, arrCase[0]));
        audience.add(new BarEntry(2, arrCase[1]));
        audience.add(new BarEntry(3, arrCase[2]));
        audience.add(new BarEntry(4, arrCase[3]));

        BarDataSet bardataset = new BarDataSet(audience, "");
        bardataset.setColors(ColorTemplate.MATERIAL_COLORS);
        bardataset.setValueTextColor(Color.BLACK);
        bardataset.setValueTextSize(12);

        BarData data = new BarData(bardataset);
        chart.setFitBars(true);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.animateY(3000);
        chart.setClickable(false);
        chart.setDoubleTapToZoomEnabled(false);

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawAxisLine(false);

        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setDrawAxisLine(false);

        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisRight().setDrawAxisLine(false);
    }

    @Override
    public void onClick(View view) {
        dismiss();
        callback.callBack(KEY_AUDIENCE,null);
    }
}
