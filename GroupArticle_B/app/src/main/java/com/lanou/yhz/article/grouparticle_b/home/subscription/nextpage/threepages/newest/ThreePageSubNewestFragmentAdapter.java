package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.newest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubHottestAdapterBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;

import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class ThreePageSubNewestFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<SubHottestAdapterBean.DataBean.ResultsBean> data;

    public ThreePageSubNewestFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SubHottestAdapterBean.DataBean.ResultsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThreePageSubNewestFragmentAdapter.MyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_fragment_threepage_sub, parent, false);
            holder = new ThreePageSubNewestFragmentAdapter.MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ThreePageSubNewestFragmentAdapter.MyViewHolder) convertView.getTag();
        }
        GlideManger glideManger = GlideManger.getsInstance();
        glideManger.loadImageView(context, data.get(position).getCover(), holder.imageView);
        holder.textView.setText(data.get(position).getTitle());
        return convertView;
    }

    class MyViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv_subscription_threepage_image);
            textView = (TextView) view.findViewById(R.id.tv_subscription_threepage_body);
        }
    }
}
