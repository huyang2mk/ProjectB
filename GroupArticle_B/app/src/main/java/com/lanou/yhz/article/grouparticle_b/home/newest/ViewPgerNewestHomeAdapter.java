package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewestViewPgerBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

//最新页面,每个Tablayout的item的适配器
public class ViewPgerNewestHomeAdapter extends BaseAdapter {
    private List<NewestViewPgerBean.DataBean.ResultsBean> data;
    private Context context;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
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


        return convertView;
    }

    class ViewHolder {

        ImageView imageView, newest_viewpager_listview_item_head;
        TextView new_viewpager_author,introduce;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.newest_viewpager_listview_item_ImageView);
            newest_viewpager_listview_item_head = (ImageView) view.findViewById(R.id.newest_viewpager_listview_item_head);
            new_viewpager_author = (TextView) view.findViewById(R.id.new_viewpager_author);
           introduce = (TextView) view.findViewById(R.id.introduce);
        }
    }
}
