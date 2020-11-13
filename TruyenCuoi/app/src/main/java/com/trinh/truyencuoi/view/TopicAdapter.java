package com.trinh.truyencuoi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinh.truyencuoi.R;
import com.trinh.truyencuoi.entities.Story;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {
    private List<Story> mListData;
    private Context mContext;
    private Story selectedData;
    private OnTopicCallBack callBack;

    public TopicAdapter(List<Story> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
    }

    public void setCallBack(OnTopicCallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_view, parent, false);
        return new TopicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder holder, int position) {
        Story data = mListData.get(position);
        holder.tvName.setText(data.getName());
        holder.tvContent.setText(data.getContent());
        holder.story = data;
//        if(selectedData==data){
//            holder.lnStory.setBackgroundResource(R.color.colorVioletLight);
//        }else{
//            holder.lnStory.setBackgroundResource(R.color.colorWhite);
//        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public interface OnTopicCallBack {
        void onCallBack(Story story);
    }

    public class TopicHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvContent;
        private LinearLayout lnStory;
        private Story story;

        public TopicHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);

            lnStory = itemView.findViewById(R.id.ln_story);
            lnStory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //selectedData = story;
                    //notifyDataSetChanged();
                    //lnStory.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_alpha));
                    callBack.onCallBack(story);
                }
            });
        }
    }
}
