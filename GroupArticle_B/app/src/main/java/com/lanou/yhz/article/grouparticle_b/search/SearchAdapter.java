package com.lanou.yhz.article.grouparticle_b.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubHottestAdapterBean;
import com.lanou.yhz.article.grouparticle_b.bean.searchbean.SearchBean;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.NextSubscriptionActivity;

import java.util.List;


/**
 * Created by dllo on 17/2/6.
 */

public class SearchAdapter extends BaseAdapter {
private Context context;
    private SearchBean bean;
    private List<SubHottestAdapterBean.DataBean.ResultsBean> datas;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setData(SearchBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean != null ? bean.getData().getWordList().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return bean != null ? bean.getData().getWordList().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SearchViewHolder holder = null;
        if (convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_gv,parent,false);
            holder = new SearchViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SearchViewHolder) convertView.getTag();
        }
        holder.textView.setText(bean.getData().getWordList().get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NextSubscriptionActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class SearchViewHolder{
        TextView textView;
        public SearchViewHolder(View view){
            super();
            textView = (TextView) view.findViewById(R.id.tv_search_gridView);
        }
    }
}
