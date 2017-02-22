package com.lanou.yhz.article.grouparticle_b.take;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.takebean.TakeBean;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.HashMap;
import java.util.Map;

public class TakeFragment extends BaseFragment {
private TakeFragmentAdapter adapter;
    private TakeBean data;
    private ListView listView;
    @Override
    public int setLayout() {
        return R.layout.fragment_take;
    }

    @Override
    public void initView(View view) {

        listView = (ListView) view.findViewById(R.id.lv_take);

    }

    @Override
    public void initData() {

        adapter = new TakeFragmentAdapter(context);

        listView.setAdapter(adapter);

        postRequest();
    }

    private void postRequest() {

        String postUrl = "http://api.rr.tv/v3plus/category/all";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";
        final String key2 = "token";
        final String value2 = "39740d660ce56921bed639fda374e1f3c9032717";
        Map<String, String> maps = new HashMap<>();
        maps.put(key1,value1);
        maps.put(key2,value2);
        OkHttpManger okHttpmanger = OkHttpManger.getInstance();
        okHttpmanger.startHeader(postUrl, maps, new OnNetResultListener() {
            @Override
            public void onSUccessListener(String successStr) {
                Gson gson = new Gson();
                data = gson.fromJson(successStr,TakeBean.class);
                adapter.setData(data);

                Log.d("TakeFragment", successStr);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }
}
