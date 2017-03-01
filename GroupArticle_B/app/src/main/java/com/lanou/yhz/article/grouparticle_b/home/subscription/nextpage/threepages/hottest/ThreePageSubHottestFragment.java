package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.hottest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubHottestAdapterBean;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/2/23.
 */

public class ThreePageSubHottestFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ThirdPagerSubHottestFragmentAdapter adapter;
    private SubHottestAdapterBean data;

    @Override
    public int setLayout() {
        return R.layout.fragment_subhottest_threepage;
    }

    @Override
    public void initView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_subscription_next_hottest);
    }

    @Override
    public void initData() {

        adapter = new ThirdPagerSubHottestFragmentAdapter(context);
        postRequest();
    }

    private void postRequest() {
        String postUrl = "http://api.rr.tv/v3plus/uper/videoList";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";
        final String key2 = "token";
        final String value2 = "eee81b095e3041403c9c2ee7bf1e1c75076c9e8d";
        final String keyBody1 = "sort";
        final String valueBody1 = "updateTime";
        final String keyBody2 = "page";
        final String valueBody2 = "1";
        final String keyBody3 = "userId";
        final String valueBody3 = "7972372";
        final String keyBody4 = "rows";
        final String valueBody4 = "10";
        Map<String, String> maps = new HashMap<>();
        maps.put(key1, value1);
        maps.put(key2, value2);
        Map<String ,String > mapBody = new HashMap<>();
        mapBody.put(keyBody1,valueBody1);
        mapBody.put(keyBody2,valueBody2);
        mapBody.put(keyBody3,valueBody3);
        mapBody.put(keyBody4,valueBody4);

        OkHttpManger okHttpManger = OkHttpManger.getInstance();
        okHttpManger.startPost(postUrl, mapBody,maps, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                data = gson.fromJson(successStr, SubHottestAdapterBean.class);
                adapter.setData(data.getData().getResults());
                recyclerView.setAdapter(adapter);
                LinearLayoutManager lmanger = new LinearLayoutManager(context, LinearLayout.VERTICAL,true);
                recyclerView.setLayoutManager(lmanger);

                Log.d("SubscriptionHomeFragmen", successStr);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }
}
