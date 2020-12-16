package com.trinh.japanese.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.model.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.DictViewHolder> {
    private Context mContext;
    private List<Word> mListWord;


    public WordAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Word> list) {
        this.mListWord = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_word, parent, false);
        return new DictViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictViewHolder holder, int position) {
        Word word = mListWord.get(position);
        if (word==null){
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        holder.rcvWord.setLayoutManager(linearLayoutManager);
        ExplainAdapter explainAdapter = new ExplainAdapter(mContext);
        explainAdapter.setData(word.getExplains());
        holder.rcvWord.setAdapter(explainAdapter);
        holder.tvReading.setText(word.getReading());
    }

    @Override
    public int getItemCount() {
        if (mListWord != null) {
            return mListWord.size();
        }
        return 0;
    }

    public class DictViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rcvWord;
        TextView tvReading;

        public DictViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReading = itemView.findViewById(R.id.tv_reading);
            rcvWord = itemView.findViewById(R.id.rcv_explain);
        }
    }
}
