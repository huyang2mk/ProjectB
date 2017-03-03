package com.lanou.yhz.article.grouparticle_b.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.search.ormlite.Search;
import com.lanou.yhz.article.grouparticle_b.search.ormlite.SearchDao;

import java.util.List;

/**
 * Created by dllo on 17/2/7.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Search> searchList;

    public void setSearchList(List<Search> searchList) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchList != null && searchList.size() > 0 ? searchList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return searchList != null && searchList.size() > 0 ? searchList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.content.setText(searchList.get(position).getContent());
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = searchList.get(position).getContent();
                SearchDao.getsTnstance(context).deleteOne(content);
                List<Search> date = SearchDao.getsTnstance(context).queryAll();
                setSearchList(date);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private TextView content, delete;


        public ViewHolder(View view) {
            content = (TextView) view.findViewById(R.id.item_content);
            delete = (TextView) view.findViewById(R.id.item_delte);
        }
    }
}
