package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewestViewPgerBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;
import com.lanou.yhz.article.grouparticle_b.videoplayer.NewsVideoActivity;

import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 17/2/22.
 */

//最新页面,每个Tablayout的item的适配器
public class ViewPgerNewestHomeAdapter extends BaseAdapter {
    private List<NewestViewPgerBean.DataBean.ResultsBean> data;
    private Context context;
    private ViewHolder holder;
    private int fpostion;

    public ViewPgerNewestHomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewestViewPgerBean.DataBean.ResultsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null && data.size() > 0 ? data.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        fpostion = position;
        holder = null;
        if (null == holder) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_home_newest_viewpager_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);


        } else {

            holder = (ViewHolder) convertView.getTag();

        }
        GlideManger.getsInstance().loadImageView(context, data.get(position).getCover(), holder.imageView);

        GlideManger.getsInstance().loadImageView(context, data.get(position).getAuthor().getHeadImgUrl(), holder.newest_viewpager_listview_item_head);


        holder.new_viewpager_author.setText(data.get(position).getAuthor().getNickName());

        holder.introduce.setText(data.get(position).getTitle());
        holder.time.setText(data.get(position).getDuration());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewsVideoActivity.class);
                Log.d("ViewPgerNewestHomeAdapt", "data.get(position).getId():" + data.get(position).getId());
                intent.putExtra("New_videoId", data.get(position).getId());

                context.startActivity(intent);
            }
        });


        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        return convertView;
    }

    class ViewHolder {

        ImageView imageView, newest_viewpager_listview_item_head, detailed, share;
        TextView new_viewpager_author, introduce, time;
        RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.newest_viewpager_listview_item_ImageView);
            newest_viewpager_listview_item_head = (ImageView) view.findViewById(R.id.newest_viewpager_listview_item_head);
            new_viewpager_author = (TextView) view.findViewById(R.id.new_viewpager_author);
            introduce = (TextView) view.findViewById(R.id.introduce);
            detailed = (ImageView) view.findViewById(R.id.detailed);
            time = (TextView) view.findViewById(R.id.time);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.videoRelativeLayout);
            share = (ImageView) view.findViewById(R.id.share);
        }
    }


    // 第三方分享
    private void showShare() {



        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        oks.setImageUrl(data.get(fpostion).getCover());
        oks.setUrl("https://mobile.rr.tv/pages/videoShare?id=" + data.get(fpostion).getId() + "&share=12372265");
        oks.setTitleUrl("https://mobile.rr.tv/pages/videoShare?id=" + data.get(fpostion).getId() + "&share=12372265");
        oks.setTitle(data.get(fpostion).getTitle());
        oks.setText(data.get(fpostion).getTitle());
        oks.show(context);

    }
}
