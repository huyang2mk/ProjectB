package com.lanou.yhz.article.grouparticle_b.home.subscription;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubscriptionBean;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/2/20.
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



        postRequest();

    }

    private void postRequest() {
        String postUrl = "http://api.rr.tv/v3plus/uper/recommendList";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";
        Map<String, String> maps = new HashMap<>();

        maps.put(key1, value1);


        OkHttpManger okHttpManger = OkHttpManger.getInstance();
        okHttpManger.startHeader(postUrl, maps, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {

                Gson gson = new Gson();
                data = gson.fromJson(successStr,SubscriptionBean.class);
                adapter.setData(data);
                bindAdapter();
                Log.d("SubscriptionHomeFragmen", successStr);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }

    public void bindAdapter(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                listView.setAdapter(adapter);
            }
        });
    }
}
