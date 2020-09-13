package com.nac.lesson2k5ui.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nac.lesson2k5ui.R;
import com.nac.lesson2k5ui.entites.Expression;

public class M000SplashAct extends BaseActivity {
    public static final String KEY_DATA = "KEY_DATA";
    public static final String KEY_EXPRESSION = "KEY_EXPRESSION";
    public static final int REQ_FOR_EXP = 1;

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMainAct();
            }
        }, 2000);
    }

    private void gotoMainAct() {
        Intent intent = new Intent();
        intent.setAction("ACTION_EXPRESS");

        Bundle data = new Bundle();
        Expression exp = new Expression(40,50);
        data.putSerializable(KEY_EXPRESSION,exp);
        intent.putExtra(KEY_DATA,data);

        //data.putInt(KEY_NUM_A,20);
        //data.putInt(KEY_NUM_B,50);
        startActivity(intent);
        //startActivityForResult(intent,REQ_FOR_EXP);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_FOR_EXP) {
            if (resultCode == RESULT_OK && data != null) {
                double sum = data.getDoubleExtra(M001MainAct.KEY_SUM, -1);
                Toast.makeText(this, "Data returnd: " + sum, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Could not get data return", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"Data returned: "+ getStorage().getM001Sum(), Toast.LENGTH_SHORT).show();
    }
}
