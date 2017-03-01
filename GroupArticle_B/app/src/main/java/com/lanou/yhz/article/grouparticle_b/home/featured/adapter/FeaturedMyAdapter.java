package com.lanou.yhz.article.grouparticle_b.home.featured.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.DataBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;

/**
 * Created by zhaochunyu on 2017/2/27.
 * @author zhaochunyu
 * 公用的适配器
 */

public class FeaturedMyAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<DataBean.CategoryBean> data;
    private int num;

    public FeaturedMyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataBean.CategoryBean> data ,int num) {
        this.data = data;
        this.num = num;
        notifyDataSetChanged();
    }
    public static final int FIRST = 1;
    public static final int SECOND = 2;

    public boolean isHeader(int position){
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)){
            return FIRST;
        }
        return SECOND;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case FIRST:
                View fisrtView = LayoutInflater.from(context).inflate(R.layout.item_home_fratured_repeat_first,parent,false);
                holder = new FirstMyViewHolder(fisrtView);
                break;
            case SECOND:
                View secondView = LayoutInflater.from(context).inflate(R.layout.item_home_fratured_repeat_second,parent,false);
                holder = new SecondMyViewHolder(secondView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case FIRST:
                FeaturedMyAdapter.FirstMyViewHolder firstHolder = (FirstMyViewHolder) holder;
                firstHolder.timeTv.setText(data.get(num).getVideoList().get(position).getDuration());
                firstHolder.contentTv.setText(data.get(num).getVideoList().get(position).getTitle());
                GlideManger.getsInstance().loadImageView(context,data.get(num).getVideoList().get(position).getCover(),firstHolder.imageView);
                break;
            case SECOND:
                FeaturedMyAdapter.SecondMyViewHolder secondHolder = (SecondMyViewHolder) holder;
                secondHolder.timeTv.setText(data.get(num).getVideoList().get(position).getDuration());
                secondHolder.contentTv.setText(data.get(num).getVideoList().get(position).getTitle());
                GlideManger.getsInstance().loadImageView(context,data.get(num).getVideoList().get(position).getCover(),secondHolder.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }



    class FirstMyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView contentTv,timeTv;
        public FirstMyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_fratured_repeat_first_imageview);
            contentTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_first_content);
            timeTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_first_time);
        }
    }
    class SecondMyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView contentTv,timeTv;
        public SecondMyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_fratured_repeat_second_imageview);
            contentTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_second_content);
            timeTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_second_time);
        }
    }
}
