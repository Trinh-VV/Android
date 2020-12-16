package com.trinh.japanese.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.api.ApiService2;
import com.trinh.japanese.common.WordAdapter;
import com.trinh.japanese.model.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class M003FlashFragment extends Basefragment {
    public static final String TAG = M003FlashFragment.class.getName();
    private EditText editWord;
    private RecyclerView rcvWord;
    private WordAdapter wordAdapter;


    protected void initView() {
        editWord = findViewById(R.id.edt_search);
        findViewById(R.id.bt_search).setOnClickListener(this);
        rcvWord = findViewById(R.id.rcv_word);
        wordAdapter = new WordAdapter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m003_dictionary;
    }

    @Override
    public void onClick(View view) {
        clickCallApi();
    }


    private void clickCallApi() {
        String keySearch = editWord.getText().toString();
        if (editWord.getText() == null) {
            return;
        }
        ApiService2.apiService.getWordByKey(keySearch).enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                Toast.makeText(getContext(), "Success"+response.body().size(), Toast.LENGTH_LONG).show();
                List<Word> listWord = response.body();
                wordAdapter.setData(listWord);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                rcvWord.setLayoutManager(linearLayoutManager);
                rcvWord.setAdapter(wordAdapter);
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

