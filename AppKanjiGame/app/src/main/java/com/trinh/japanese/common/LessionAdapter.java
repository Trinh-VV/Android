package com.trinh.japanese.common;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trinh.japanese.R;
import com.trinh.japanese.entities.Lession;
import com.trinh.japanese.entities.MimiEntity;

import java.util.List;


public class LessionAdapter extends RecyclerView.Adapter<LessionAdapter.LessionViewHolder> {
    private OnLessionListener callBack;
    private List<Lession> mLession;
    private OnLessionListener mOnLessionListener;
    private Context mContext;
    private Lession currentLession;

    public LessionAdapter(List<Lession> mLession, OnLessionListener onLessionListener) {
        this.mLession = mLession;
        this.mOnLessionListener = onLessionListener;
    }

    public void setData(List<Lession> list) {
        this.mLession = list;
        notifyDataSetChanged();
    }

    public void setCallBack(OnLessionListener callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public LessionAdapter.LessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lession, parent, false);
        return new LessionViewHolder(view, mOnLessionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LessionViewHolder holder, int position) {
        Lession lession = mLession.get(position);
        holder.tvTitleLesion.setTextColor(Color.BLACK);
        if (lession == currentLession){
            holder.tvTitleLesion.setTextColor(Color.RED);
        }
        if (lession == null) {
            return;
        }
        holder.tvTitleLesion.setText(lession.getName());
        Glide.with(holder.itemView.getContext()).load(lession.getImgPath()).into(holder.imView);
        holder.tvTitleLesion.setTag(lession);
    }

    @Override
    public int getItemCount() {
        if (mLession != null) {
            return mLession.size();
        }
        return 0;
    }

    public void updateUI() {
        currentLession = null;
        notifyDataSetChanged();

    }

    public interface OnLessionListener {
        void onLesstionClick(int position);
    }

    public class LessionViewHolder extends RecyclerView.ViewHolder {

        OnLessionListener onLessionListener;
        private TextView tvTitleLesion;
        private ImageView imView;
        private LinearLayout lnLession;

        public LessionViewHolder(@NonNull View itemView, OnLessionListener onLessionListener) {
            super(itemView);
            tvTitleLesion = itemView.findViewById(R.id.tv_lession);
            imView = itemView.findViewById(R.id.imv_lession);
            lnLession = itemView.findViewById(R.id.ln_lession);
            this.onLessionListener = onLessionListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLessionListener.onLesstionClick(getAdapterPosition());
                    currentLession = (Lession) tvTitleLesion.getTag();
                    notifyDataSetChanged();
                }
            });
        }

    }
}
