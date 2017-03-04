package com.lanou.yhz.article.grouparticle_b.video.videorecommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.VideoDetailsBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;

/**
 * Created by zhaochunyu on 2017/3/4.
 * @author zhaochunyu
 * 播放视频界面相关推荐适配器
 */

public class VideoRecommendAdapter extends RecyclerView.Adapter<VideoRecommendAdapter.RecommendViewHolder>{
    private Context context;
    private List<VideoDetailsBean.DataBean.RecommendVideoListBean> data;

    public void setData(List<VideoDetailsBean.DataBean.RecommendVideoListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public VideoRecommendAdapter(Context context) {
        this.context = context;
    }



    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video_details_recommend_item,parent,false);
        RecommendViewHolder holder = new RecommendViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        GlideManger.getsInstance().loadImageView(context,data.get(position).getCover(),holder.imageView);
        holder.timeTv.setText(data.get(position).getDuration());
        holder.titleTv.setText(data.get(position).getTitle());
        holder.numberTv.setText(data.get(position).getViewCount()+"次播放");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView timeTv,titleTv,numberTv;
        public RecommendViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_video_details_recommend_iv);
            timeTv = (TextView) itemView.findViewById(R.id.item_video_details_recommend_time);
            titleTv = (TextView) itemView.findViewById(R.id.item_video_details_recommend_title);
            numberTv = (TextView) itemView.findViewById(R.id.item_video_details_recommend_number);
        }
    }

}
