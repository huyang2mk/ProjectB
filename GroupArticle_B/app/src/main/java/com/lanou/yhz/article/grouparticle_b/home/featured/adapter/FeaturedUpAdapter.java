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
 */

public class FeaturedUpAdapter extends RecyclerView.Adapter<FeaturedUpAdapter.UpViewHolder>{
    private Context context;
    private List<DataBean.RecommendUpBean> data;


    public FeaturedUpAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataBean.RecommendUpBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public UpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_fratured_up,parent,false);
        UpViewHolder holder = new UpViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UpViewHolder holder, int position) {
        holder.titleTv.setText(data.get(position).getName());
        holder.contentTv.setText(data.get(position).getIntro());
        GlideManger.getsInstance().loadImageView(context, data.get(position).getHeadImg(),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    class UpViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv,contentTv;
        ImageView imageView;

        public UpViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_home_fratured_up_title);
            contentTv = (TextView) itemView.findViewById(R.id.item_home_fratured_up_content);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_fratured_up_image);
        }
    }
}
