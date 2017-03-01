package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.hottest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubHottestAdapterBean;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.activity.ThirdPagerSubHottestActivity;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;

/**
 * Created by dllo on 17/2/27.
 * 三级页面适配器
 */

public class ThirdPagerSubHottestFragmentAdapter extends RecyclerView.Adapter<ThirdPagerSubHottestFragmentAdapter.NewestViewHolder> {
    private Context context;
    private  List<SubHottestAdapterBean.DataBean.ResultsBean> data;

    public ThirdPagerSubHottestFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SubHottestAdapterBean.DataBean.ResultsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public NewestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter_fragment_threepage_sub,parent,false);
       NewestViewHolder holer = new NewestViewHolder(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(NewestViewHolder holder, final int position) {
        GlideManger glideManger = GlideManger.getsInstance();
        glideManger.loadImageView(context, data.get(position).getCover(), holder.imageView);
        holder.textView.setText(data.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThirdPagerSubHottestActivity.class);

                String hottest = data.get(position).getAuthor().getHeadImgUrl();
                intent.putExtra("hottest",hottest);//不太对
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewestViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public NewestViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_subscription_threepage_image);
            textView = (TextView) itemView.findViewById(R.id.tv_subscription_threepage_body);
        }
    }
}
