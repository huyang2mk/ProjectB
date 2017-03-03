package com.lanou.yhz.article.grouparticle_b.home.featured.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.DataBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;

/**
 * Created by zhaochunyu on 2017/2/25.
 * @author zhaochunyu
 * 精选适配器
 */

public class FeaturedAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<DataBean.OfficalAlbumBean>data;
    //监听事件
    public interface MyOnClickListener {
        void onClick(int position );
    }

    //监听事件
    private MyOnClickListener myOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }


    public FeaturedAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setData(List<DataBean.OfficalAlbumBean> data) {
        this.data = data;
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
                holder = new FirstViewHolder(fisrtView);
                break;
            case SECOND:
                View secondView = LayoutInflater.from(context).inflate(R.layout.item_home_fratured_repeat_second,parent,false);
                holder = new SecondViewHolder(secondView);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d("FeaturedAdapter", "position:" + position);
        int viewType = getItemViewType(position);
        switch (viewType){
            case FIRST:
                FirstViewHolder firstHolder = (FirstViewHolder) holder;
                firstHolder.timeTv.setText(data.get(0).getResultList().get(position).getDuration());
                firstHolder.contentTv.setText(data.get(0).getResultList().get(position).getTitle());
                GlideManger.getsInstance().loadImageView(context,data.get(0).getResultList().get(position).getCover(),firstHolder.imageView);
                firstHolder.relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myOnClickListener.onClick(position);
                    }
                });
                break;
            case SECOND:
                SecondViewHolder secondHolder = (SecondViewHolder) holder;
                secondHolder.timeTv.setText(data.get(0).getResultList().get(position).getDuration());
                secondHolder.contentTv.setText(data.get(0).getResultList().get(position).getTitle());
                GlideManger.getsInstance().loadImageView(context,data.get(0).getResultList().get(position).getCover(),secondHolder.imageView);
                secondHolder.relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myOnClickListener.onClick(position);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }



    class FirstViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView contentTv,timeTv;
        RelativeLayout relativeLayout1;
        public FirstViewHolder(View itemView) {
            super(itemView);
            relativeLayout1 = (RelativeLayout) itemView.findViewById(R.id.item_home_fratured_repeat_first);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_fratured_repeat_first_imageview);
            contentTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_first_content);
            timeTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_first_time);
        }
    }
    class SecondViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView contentTv,timeTv;
        RelativeLayout relativeLayout2;
        public SecondViewHolder(View itemView) {
            super(itemView);
            relativeLayout2 = (RelativeLayout) itemView.findViewById(R.id.item_home_fratured_repeat_second);
            imageView = (ImageView) itemView.findViewById(R.id.item_home_fratured_repeat_second_imageview);
            contentTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_second_content);
            timeTv = (TextView) itemView.findViewById(R.id.item_home_fratured_repeat_second_time);
        }
    }
}
