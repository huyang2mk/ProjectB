package com.lanou.yhz.article.grouparticle_b.take;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.takebean.TakeBean;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 * 频道-一级视图-ListView中的-GridView适配器
 */

public class GridViewTakeAdapter extends BaseAdapter {
    private Context context;
    private List<TakeBean.DataBean.CategoryBean.LeafCategoryBean> datas;

    public GridViewTakeAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<TakeBean.DataBean.CategoryBean.LeafCategoryBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas!=null?datas.get(position):0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TakeViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_take_list_gridview,parent,false);
            holder = new TakeViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (TakeViewHolder) convertView.getTag();
        }

        holder.textView_grid.setText(datas.get(position).getName());
        return convertView;
    }
    class TakeViewHolder{
        Button textView_grid;
        public TakeViewHolder(View view){
            super();
            textView_grid = (Button) view.findViewById(R.id.tv_take_list_grid);
        }
    }
}
