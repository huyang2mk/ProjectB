package com.lanou.yhz.article.grouparticle_b.take;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.takebean.TakeBean;

/**
 * Created by dllo on 17/2/22.
 * 频道- ListView适配器
 */

public class TakeFragmentAdapter extends BaseAdapter {
    private Context context;
    private TakeBean data;

    public TakeFragmentAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setData(TakeBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null&&data.getData()!=null&& data.getData().getCategory()!=null?data.getData().getCategory().size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.getData()!=null? data.getData().getCategory().get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_take_list,parent,false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {

           holder = (MyViewHolder) convertView.getTag();
        }
        holder.textViewTitle.setText(data.getData().getCategory().get(position).getName());
        GridViewTakeAdapter gridViewTakeAdapter = new GridViewTakeAdapter(context);
        holder.gridView.setAdapter(gridViewTakeAdapter);
        gridViewTakeAdapter.setDatas(data.getData().getCategory().get(position).getLeafCategory());


        return convertView;
    }
    class MyViewHolder{
        TextView textViewTitle;
        ImageView imageViewTitle,imageViewBtnEnter;
        GridView gridView;
        public MyViewHolder(View view){

            textViewTitle = (TextView) view.findViewById(R.id.tv_take_title);
            imageViewTitle = (ImageView) view.findViewById(R.id.iv_take_title);
            imageViewBtnEnter = (ImageView) view.findViewById(R.id.iv_btn_enter_take);
            gridView = (GridView) view.findViewById(R.id.gv_take);

        }
    }
}
