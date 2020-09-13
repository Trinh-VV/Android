package com.trinh.lession5ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MyToast {
    public Toast getText(Context context, String text) {
        Random rd = new Random();
        int wScreen = context.getResources().getDisplayMetrics().widthPixels;
        int hScreen = context.getResources().getDisplayMetrics().heightPixels;
        int x = rd.nextInt(wScreen);
        int y = rd.nextInt(hScreen);

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.START, x, y);
        return toast;
    }

    public Toast getView(Context context, int idPhoto, String name) {
        Random rd = new Random();
        int wScreen = context.getResources().getDisplayMetrics().widthPixels;
        int hScreen = context.getResources().getDisplayMetrics().heightPixels;
        int x = rd.nextInt(wScreen);
        int y = rd.nextInt(hScreen);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP | Gravity.START, x, y);
        toast.setDuration(Toast.LENGTH_SHORT);

        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
        ImageView ivAnimal = view.findViewById(R.id.iv_animal);
        TextView tvAnimal = view.findViewById(R.id.tv_animal);

        ivAnimal.setImageResource(idPhoto);
        tvAnimal.setText(name);
        toast.setView(view);
        return toast;
    }
}
