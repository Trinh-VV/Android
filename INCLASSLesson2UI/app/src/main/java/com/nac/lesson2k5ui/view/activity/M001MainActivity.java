package com.nac.lesson2k5ui.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nac.lesson2k5ui.R;
import com.nac.lesson2k5ui.entites.Expression;

class MainActivity extends BaseActivity {
    public static final String KEY_SUM = "KEY_SUM";
    private static final String TAG = MainActivity.class.getName();
    private EditText edtA, edtB;
    private int countExit = 0;

    @Override
    protected void initView() {
        edtA = findViewById(R.id.edt_num_a);
        edtB = findViewById(R.id.edt_num_b);
        findViewById(R.id.bt_reset, this);
        findViewById(R.id.bt_sum, this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle data = intent.getBundleExtra(SplashActivity.KEY_DATA);
        Expression exp = (Expression) data.getSerializable(SplashActivity.KEY_EXPRESSION);
        edtA.setText(exp.getA() + "");
        edtB.setText(exp.getB() + "");
        //int numA = intent.getIntExtra(SplashActivity.KEY_NUM_A, -1);
        //int numB = intent.getIntExtra(SplashActivity.KEY_NUM_B, -1);
    }

    @Override
    public void clickView(int id, View view) {
        if (id == R.id.bt_reset) {
            resetData();
        } else if (id == R.id.bt_sum) {
            doSum();
        }
    }

    private void doSum() {
        try {
            double a = Double.parseDouble(edtA.getText().toString());
            double b = Double.parseDouble(edtB.getText().toString());
            double sum = a + b;

            Log.i(TAG, "Sum = " + sum);

            //chuan bi du lieu tra ve
            Intent data = new Intent();
            data.putExtra(KEY_SUM, sum);

            //SU dung applicationContext
            //App app = (App) getApplicationContext();
            getStorage().setM001Sum(sum);


            //setResult(RESULT_OK,data);
            // ket thuc de tra ve data
            //finish();

            //Toast.makeText(this,
            //      "Sum = " + sum, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetData() {
        edtA.setText("");
        edtB.setText("");
    }

    @Override
    public void onBackPressed() {
        countExit++;
//        setResult(RESULT_CANCELED);
        if (countExit > 1) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press again to exit app",
                    Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    countExit = 0;
                }
            }, 2000);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}