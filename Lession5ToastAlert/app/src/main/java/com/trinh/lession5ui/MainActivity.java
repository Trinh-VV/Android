package com.trinh.lession5ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Integer> listIdPhoto = new ArrayList<>();
    private List<String> listNamePhoto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        TypedArray tArray = getResources().obtainTypedArray(R.array.photo_arr);
        for (int i = 0; i < tArray.length(); i++) {
            listIdPhoto.add(tArray.getResourceId(i, 0));
        }
        tArray.recycle();
        listNamePhoto.addAll(Arrays.asList(getResources().getStringArray(R.array.name_arr)));
    }

    private void initView() {
        findViewById(R.id.bt_toast).setOnClickListener(this);
        findViewById(R.id.bt_show_animal).setOnClickListener(this);
        findViewById(R.id.bt_alert).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_toast) {
            new MyToast().getText(this, "hello").show();
        } else if (view.getId() == R.id.bt_show_animal) {
            int index = new Random().nextInt(listIdPhoto.size());
            int idPhoto = listIdPhoto.get(index);
            String name = listNamePhoto.get(index);
            new MyToast().getView(this, idPhoto, name).show();
        } else if (view.getId() == R.id.bt_alert) {
            showAlert("Warning", "Would you get out?", "yes", "no", new OnActionCallback() {
                @Override
                public void callBack(int key, Object data) {
                    if (key == AlertDialog.BUTTON_POSITIVE) {
                        finish();
                    }
                }
            });
        }
    }

    private void showAlert(String title, String message, String txtBT1, String txtBT2, final OnActionCallback callBack) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);

        alert.setTitle(title);
        alert.setMessage(message);
        if (txtBT1 != null) {
            alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, get out !", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    callBack.callBack(AlertDialog.BUTTON_POSITIVE, null);
//                    finish();
                }
            });
        }
        if (txtBT2 != null) {
            alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No, keep !", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    callBack.callBack(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE, null);
                }
            });
        }
        alert.show();
    }
}