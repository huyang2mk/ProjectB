package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewTitleBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 17/2/20.
 */
//最新页面的总fragment
public class NewestHomeFragment extends BaseFragment {

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private NewestHomeAdapter adapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_home_newest;
    }


    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.newest_Vpger);
        tabLayout = (TabLayout) view.findViewById(R.id.newest_Tab);

    }


    @Override
    public void initData() {


        adapter = new NewestHomeAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        requestInternet();


    }

    //  请求网络数据
    private void requestInternet() {

        OkHttpManger.getInstance().startHeader(Constant.NEW_TITLE, Constant.NEW_TITLE_MAP, new OnNetResultListener() {

            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                NewTitleBean bean = gson.fromJson(successStr, NewTitleBean.class);
                List<NewTitleBean.DataBean.CategoryBean> list = bean.getData().getCategory();
                adapter.setData(list);

            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });


    }




}