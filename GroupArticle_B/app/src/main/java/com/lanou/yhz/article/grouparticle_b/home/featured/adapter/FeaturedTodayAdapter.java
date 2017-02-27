package com.lanou.yhz.article.grouparticle_b.home.featured.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.BriefBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;
import java.util.Random;

/**
 * Created by zhaochunyu on 2017/2/24.
 * @author zhaochunyu
 * 今日推荐适配器
 */

public class FeaturedTodayAdapter extends RecyclerView.Adapter<FeaturedTodayAdapter.MyViewHolder>{
    private Context context;
    private List<BriefBean>data;

    public FeaturedTodayAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setData(List<BriefBean> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_fratured_today,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Random random = new Random();
        position = random.nextInt(23);
        GlideManger.getsInstance().loadImageView(context,data.get(position).getCover(),holder.imageView);
        holder.contentTv.setText(data.get(position).getTitle());
        holder.timeTv.setText(data.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView timeTv,contentTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_fratured_today_iv);
            timeTv = (TextView) itemView.findViewById(R.id.item_home_fratured_today_time_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_home_fratured_today_content_tv);
        }
    }
}
