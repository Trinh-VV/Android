package com.trinh.lession4viewmodel.view.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.trinh.lession4viewmodel.R;
import com.trinh.lession4viewmodel.view.model.MainViewModel;
import com.trinh.lession4viewmodel.view.widget.TextAdapter;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView tvCount;
    private MainViewModel mModel;
    private EditText edtFirstName, edtLastName;
    private TextView tvFullName;


    @Override
    protected void initView() {
        mModel = new ViewModelProvider(this).get(MainViewModel.class);


        tvCount = findViewById(R.id.tv_count);
        edtFirstName = findViewById(R.id.edt_first_name);
        edtLastName = findViewById(R.id.edt_last_name);
        tvFullName = findViewById(R.id.tv_fullname);
        ((SwitchCompat)findViewById(R.id.sw_language)).setOnCheckedChangeListener(this);

        edtLastName.addTextChangedListener(new TextAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                mModel.setLastName(editable.toString());
            }
        });

        edtFirstName.addTextChangedListener(new TextAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                mModel.setFirstName(editable.toString());
            }
        });
        //edtLastName.addTextChangedListener(txtWatcher);

        findViewById(R.id.bt_count, this);

        mModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                tvCount.setText(String.format("%s", count));
            }
        });

        mModel.getFullNameLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String fullName) {
                tvFullName.setText(fullName);
            }
        });
    }

    @Override
    protected void clickView(int id, View view) {
        if (id == R.id.bt_count) {
            mModel.counting();
            //tvCount.setText(String.format("%s", mModel.getCount()));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
        mModel.changeEN(value);

    }
}