package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewTitleBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */
//最新页面的总fragment
public class NewestHomeFragment extends BaseFragment {

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private NewestHomeAdapter adapter;

    private List<Fragment> fragments;
    private List<String> tokens;


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
        fragments = new ArrayList<>();
        tokens = new ArrayList<>();

        tokens.add("30f8d608454f069f71e2b9c3bff67885d4fc8814");
        tokens.add("23ac6d7c689e367cd9055eb3ac1088a6e02675b4");
        tokens.add("f131688680fa2c68bade2b74a9a8760adaef8b10");
        tokens.add("8ef644243d42a2026525cb7f53ee398648a3956a");
        tokens.add("809d84c0f6ea7163d87a9a670f01802d769f7fe6");
        tokens.add("6237c06afe755b097b206504e7059e137e728415");
        tokens.add("c21d475e30d3ab5943e9c228ba049a1a45fa42d8");
        tokens.add("18d4e5ec70b15196db3c859703406c16f3c57e75");
        tokens.add("6d1943addbf03fc773796dd81554ab955f8d5aa2");
        tokens.add("036c19694b2c57647fd446aaf397e8596ec32e10");
        tokens.add("9b9a1e08ae33687577e82a3db2fa34f912488b7e");

        OkHttpManger.getInstance().startHeader(Constant.NEW_TITLE, Constant.NEW_TITLE_MAP, new OnNetResultListener() {

            @Override
            public void onSuccessListener(String successStr) {
                Log.d("TAG", "1:" + 1);
                Gson gson = new Gson();
                NewTitleBean bean = gson.fromJson(successStr, NewTitleBean.class);
                List<NewTitleBean.DataBean.CategoryBean> list = bean.getData().getCategory();
                for (int i = 0; i < list.size(); i++) {
                    int useId = list.get(i).getId();
                    String useToken = tokens.get(i);
                    fragments.add(ViewPgerNewestHomeFragment.newInstance(useId,useToken));
                }

                adapter.setData(list,fragments);

            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });


    }
}