package com.lanou.yhz.mvp.expanablelistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/23.
 */

public class ExAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private Map<String, List<CarEntity.ResultBean.BrandlistBean.ListBean>> childMap;

    public void setData(List<String> groupList, Map<String, List<CarEntity.ResultBean.BrandlistBean.ListBean>> childMap) {
        this.groupList = groupList;
        this.childMap = childMap;
        notifyDataSetChanged();
    }


    // 获取父级和子级item的条数
    @Override
    public int getGroupCount() {
        return groupList == null ? 0 : groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        // 或得到abcd
        String abcd = groupList.get(i);
        // 根据ABCD去Map中获取赌赢的List
        return childMap.get(abcd).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        String abcd = groupList.get(i);
        // 根据abcd从map中获取list
        return childMap.get(abcd).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder =null;
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group,viewGroup,false);

            groupViewHolder = new GroupViewHolder(view);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder= (GroupViewHolder) view.getTag();
        }

        groupViewHolder.letterTv.setText(groupList.get(i));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder =null;
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child,viewGroup,false);

            childViewHolder = new ChildViewHolder(view);
            view.setTag(childViewHolder);
        }else {
            childViewHolder= (ChildViewHolder) view.getTag();
        }

        String a_z = groupList.get(groupPosition);

        CarEntity.ResultBean.BrandlistBean.ListBean bean =
                childMap.get(a_z).get(childPosition);

        childViewHolder.nameTv.setText(bean.getName());


        Picasso.with(view.getContext())
                .load(bean.getImgurl())
                .resize(100,100)
                .rotate(360,0.5f,0.5f)
                .placeholder(R.mipmap.ic_launcher)
                .into(childViewHolder.iconIv);


        return view;


    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class ChildViewHolder {
        TextView nameTv;
        ImageView iconIv;

        public ChildViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_child_Tv);
            iconIv = (ImageView) view.findViewById(R.id.item_child_Iv);
        }
    }

    class GroupViewHolder {
        TextView letterTv;
        public GroupViewHolder(View view) {
            letterTv = (TextView) view.findViewById(R.id.item_group_tv);
        }
    }
}
