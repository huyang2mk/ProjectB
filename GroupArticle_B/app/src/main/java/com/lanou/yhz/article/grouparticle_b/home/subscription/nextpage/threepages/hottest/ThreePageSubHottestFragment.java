package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.hottest;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

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
    private ListView listView;
    private ThreePageSubHottestFragmentAdapter adapter;
    private SubHottestAdapterBean data;
    @Override
    public int setLayout() {
        return R.layout.fragment_subhottest_threepage;
    }

    @Override
    public void initView(View view) {

        listView = (ListView) view.findViewById(R.id.lv_subscription_next_hottest);
    }

    @Override
    public void initData() {

        adapter = new ThreePageSubHottestFragmentAdapter(context);
        postRequest();
    }

    private void postRequest() {
        String postUrl = "http://api.rr.tv/v3plus/uper/videoList";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";
        final String key2 = "token";
        final String value2 = "c180f7cb746589e634b1098d16c07e7e2bd75f8b";
        Map<String, String> maps = new HashMap<>();
        maps.put(key1, value1);
        maps.put(key2, value2);

        OkHttpManger okHttpManger = OkHttpManger.getInstance();
        okHttpManger.startHeader(postUrl, maps, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                data = gson.fromJson(successStr,SubHottestAdapterBean.class);
                adapter.setData(data.getData().getResults());
                listView.setAdapter(adapter);
                Log.d("SubscriptionHomeFragmen", successStr);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }
}
