package com.lanou.yhz.article.grouparticle_b.home.subscription;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubscriptionBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

/**
 * Created by dllo on 17/2/20.
 * 一级界面-订阅
 */

public class ListSubscriptionAdapter extends BaseAdapter {
    private SubscriptionBean data;
    private Context context;

    public ListSubscriptionAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setData(SubscriptionBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.getData() != null && data.getData().getUperList() != null ? data.getData().getUperList().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null && data.getData() != null && data.getData().getUperList() != null ? data.getData().getUperList().get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//先创建一个缓存类对象 赋值为null
        MyViewHolder holder = null;
        //提出来的同一样的集合
        final SubscriptionBean.DataBean.UperListBean bean = data.getData().getUperList().get(position);
        //我们先判断行视图是否为空
        //如果为空的话我们就把自己创建的行布局注入convertView
        //复用机制
        if (convertView == null) {
            Log.e("ListSubsc", "position:" + position);
            //将行布局注入convertView
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listsubscription_home, parent, false);
            //初始化缓存类对象
            holder = new MyViewHolder(convertView);
            //把缓存类对象设置到视图之中
            convertView.setTag(holder);
        } else {
            //当行视图不再为空的时候
            //我们复用原来的缓存类对象就可以了啦
            //调用的getTag方法
            holder = (MyViewHolder) convertView.getTag();
        }


        holder.tvBody.setText(bean.getFansCount() / 10000.2f + "万人订阅");
        holder.tvHead.setText(bean.getName());
        holder.ivPicture.setSelected(false);
        GlideManger glide = GlideManger.getsInstance();
        glide.loadImageView(context, bean.getHeadImg(), holder.ivAvatar);
        //判断订阅点击的所有的true false
        final MyViewHolder finalHolder = holder;
        //setSelected点击状态
        if (bean.isSelected()) {
            holder.ivPicture.setSelected(true);
        }
        holder.ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击为false是已订阅
                if (bean.isSelected()) {
                    bean.setSelected(false);
                    finalHolder.ivPicture.setSelected(false);
                } else {
                    bean.setSelected(true);
                    finalHolder.ivPicture.setSelected(true);
                }
                Toast.makeText(context, "已订阅", Toast.LENGTH_SHORT).show();

            }
        });


        return convertView;
    }

    class MyViewHolder {
        TextView tvHead, tvBody;
        ImageView ivAvatar, ivPicture;
        boolean isSelected;

        public MyViewHolder(View view) {
            super();
            tvHead = (TextView) view.findViewById(R.id.tv_head_subscription_item);
            tvBody = (TextView) view.findViewById(R.id.tv_body_subscription_item);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_subscription_avatar);
            ivPicture = (ImageView) view.findViewById(R.id.iv_subscription_picture);
            isSelected = false;
        }
    }
}
