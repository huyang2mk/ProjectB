package com.lanou.yhz.article.grouparticle_b.home.featured.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.video.VideoPlayerActivity;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.BannerBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;

/**
 * Created by zhaochunyu on 2017/2/24.
 * @author zhaochunyu
 * 轮播图适配器
 */

public class FeaturedPagerAdapter extends PagerAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<BannerBean>data;

    public FeaturedPagerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<BannerBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int newPosition = position % data.size();
        View convertView = inflater.inflate(R.layout.item_home_fratured_head,container,false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_home_fratured_head_iv);
        GlideManger.getsInstance().loadImageView(context,data.get(newPosition).getImgUrl(),imageView);
        container.addView(convertView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, VideoPlayerActivity.class).putExtra("id",data.get(newPosition).getId()+""));
            }
        });
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
