package com.trinh.ls8fragment.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.trinh.ls8fragment.R;
import com.trinh.ls8fragment.view.fragment.M001HomeFragment;
import com.trinh.ls8fragment.view.fragment.M002ListFrament;

public class MainActivity extends AppCompatActivity implements OnMainCallback {
    private M001HomeFragment m001Homefrg;
    private M002ListFrament m002Homefrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        m001Homefrg = new M001HomeFragment();
        m002Homefrg = new M002ListFrament();
        m001Homefrg.setCallBack(this);

        showFrg(M001HomeFragment.TAG);
    }


    @Override
    public void showFrg(String tag) {
        if (tag.equals(M001HomeFragment.TAG)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ln_main, m001Homefrg, tag)
                    .commit();

        } else if (tag.equals(M002ListFrament.TAG)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ln_main, m002Homefrg, tag)
                    .commit();
        }
    }
}