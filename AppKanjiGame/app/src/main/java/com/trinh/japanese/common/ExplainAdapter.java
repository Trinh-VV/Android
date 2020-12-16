package com.trinh.japanese.common;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.model.Explain;

import java.util.List;

public class ExplainAdapter extends RecyclerView.Adapter<ExplainAdapter.DictViewHolder> {
    private Context mContext;
    private List<Explain> mListExplain;


    public ExplainAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Explain> list) {
        this.mListExplain = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_explain, parent, false);
        return new DictViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictViewHolder holder, int position) {
        String txtExplain ="";
        Explain explain = mListExplain.get(position);

        String mean = TextUtils.join(",", explain.getMeans());
        String example = "";
        if (explain.getExamples().size()!=0){
            for (int i = 0; i < explain.getExamples().size(); i++) {
                example = example +explain.getExamples().get(i).getS()+"\n"+explain.getExamples().get(i).getD()+"\n";
            }
        }
        String type = explain.getType();
        if (explain.getType()==null){
            txtExplain = mean+"\n"+example;
        }else {
            txtExplain =txtExplain+ mean+"\n"+explain.getType()+"\n"+example;
        }
        holder.tvWord.setText(txtExplain);
    }

    @Override
    public int getItemCount() {
        if (mListExplain != null) {
            return mListExplain.size();
        }
        return 0;
    }

    public class DictViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;

        public DictViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_explain);
        }
    }
}
