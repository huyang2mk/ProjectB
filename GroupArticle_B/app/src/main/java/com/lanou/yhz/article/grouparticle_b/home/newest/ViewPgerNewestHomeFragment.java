package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewestViewPgerBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.Map;

/**
 * Created by dllo on 17/2/21.
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */

// 最新页面,每个Tablayout的item页

public class ViewPgerNewestHomeFragment extends BaseFragment {

    //private TextView showTv;
    private ListView listView;
    private ViewPgerNewestHomeAdapter adapter;


    // 复用机制
    public static ViewPgerNewestHomeFragment newInstance(int id, String token) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("token", token);
        ViewPgerNewestHomeFragment fragment = new ViewPgerNewestHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home_newest_viewpager_listview;
    }

    @Override
    public void initView(View view) {
        //showTv = (TextView) view.findViewById(R.id.ttttt_v);
        listView = (ListView) view.findViewById(R.id.fragment_home_newest_viewpager_listview);

    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String token = bundle.getString("token");

        //showTv.setText(id + "-----" + token);


        String key = "token";

        Constant.NEW_MAP_VIEWPAGER.put(key, token);

        Log.d("ViewPgerNewestHomeFragm", token);

        // 网络请求
        OkHttpManger.getInstance().startHeader(Constant.NEW_VIEWPAGER, Constant.NEW_MAP_VIEWPAGER, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {

                Gson gson = new Gson();
                NewestViewPgerBean bean = gson.fromJson(successStr, NewestViewPgerBean.class);

                //Log.d("ViewPgerNewestHomeFragm", "bean.getData().getCurrentPage():" + bean.getData().getResults());
                adapter = new ViewPgerNewestHomeAdapter(context);
                listView.setAdapter(adapter);
                adapter.setData(bean.getData().getResults());
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }



}
