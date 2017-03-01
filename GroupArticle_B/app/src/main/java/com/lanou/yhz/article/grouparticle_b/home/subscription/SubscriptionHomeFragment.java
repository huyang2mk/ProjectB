package com.lanou.yhz.article.grouparticle_b.home.subscription;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubscriptionBean;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.NextSubscriptionActivity;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/2/20.
 * 订阅-Fragment
 */

public class SubscriptionHomeFragment extends BaseFragment {
    private ListView listView;
    private SubscriptionBean data;
    private ListSubscriptionAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_home_subscription;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.lv_subscription);

    }

    @Override
    public void initData() {

        adapter = new ListSubscriptionAdapter(context);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, NextSubscriptionActivity.class);
                startActivity(intent);
            }
        });


        postRequest();

    }

    private void postRequest() {
        String postUrl = "http://api.rr.tv/v3plus/uper/recommendList";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";
        final String keyNew1= "page";
        final String valueNew1 = "1";
        final String keyNew2= "rows";
        final String valueNew2 = "10";

        Map<String, String> maps = new HashMap<>();
        Map<String ,String> mapNew = new HashMap<>();
        maps.put(key1, value1);
        mapNew.put(keyNew1,valueNew1);
        mapNew.put(keyNew2,valueNew2);

        OkHttpManger okHttpManger = OkHttpManger.getInstance();
        okHttpManger.startPost(postUrl, mapNew,maps, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {

                Gson gson = new Gson();
                data = gson.fromJson(successStr, SubscriptionBean.class);
                adapter.setData(data);
                bindAdapter();
                Log.d("SubscriptionHomeFragmen", successStr);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }

    public void bindAdapter() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                listView.setAdapter(adapter);
            }
        });
    }
}
