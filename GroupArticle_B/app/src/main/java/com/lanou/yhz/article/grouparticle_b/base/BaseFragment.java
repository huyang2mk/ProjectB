package com.lanou.yhz.article.grouparticle_b.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public abstract class BaseFragment extends Fragment {
    //当activity与fragment建立连接时 存储一下关联的activity对象
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    // 抽象方法, 由继承者来实现, 填写布局
    public abstract int setLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseFragment", "aaa");
        return inflater.inflate(setLayout(), container, false);
    }

    public abstract void initView(View view);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("BaseFragment", "bbb");
        initView(view);
    }

    public abstract void initData();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("BaseFragment", "ccc");
        initData();
    }

    protected  void showToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
