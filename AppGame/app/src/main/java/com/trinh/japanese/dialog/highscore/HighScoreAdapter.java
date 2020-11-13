package com.trinh.japanese.dialog.highscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.japanese.R;
import com.trinh.japanese.common.ScoreRule;
import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ScoreViewHolder> {
    private Context mContext;
    private List<User> mListUser;

    public HighScoreAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<User> list) {
        this.mListUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_highscore, parent, false);

        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        User user = mListUser.get(position);
        if (user == null) {
            return;
        }
        holder.tvName.setText(user.getUsername());
        holder.tvScore.setText(ScoreRule.getInstance().getScore(user.getUserScore()));
    }

    @Override
    public int getItemCount() {
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_username);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }
}
