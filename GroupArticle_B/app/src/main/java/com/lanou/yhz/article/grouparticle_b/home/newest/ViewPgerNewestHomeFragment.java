package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewestViewPgerBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

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

public class ViewPgerNewestHomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    // 下拉刷新
    private SwipeRefreshLayout mSwipeLayout;
    private ListView listView;
    private ViewPgerNewestHomeAdapter adapter;


    // 复用机制
    public static ViewPgerNewestHomeFragment newInstance(int channelsId) {

        Bundle args = new Bundle();
        args.putInt("id", channelsId);
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
        networkRequest();
        listView = (ListView) view.findViewById(R.id.fragment_home_newest_viewpager_listview);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);


    }

    @Override
    public void initData() {



        //  下拉刷新
        downRefresh();
        // (复用中)总fragment 通过适配器的方法传过来每个title的ID
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        Constant.NEW_MAP_BODY_VIEWPAGER.put("categoryId", id + "");

        Log.d("ViewPgerNewestHomeFragm", "id:" + id);

    }

    private void networkRequest() {
        OkHttpManger.getInstance().startPost(Constant.NEW_VIEWPAGER, Constant.NEW_MAP_BODY_VIEWPAGER, Constant.NEW_MAP_POST_HEAT, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                NewestViewPgerBean bean = gson.fromJson(successStr, NewestViewPgerBean.class);
                adapter = new ViewPgerNewestHomeAdapter(context);

                listView.setAdapter(adapter);
                adapter.setData(bean.getData().getResults());
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }

    // 设置下拉刷新的属性
    private void downRefresh() {

        mSwipeLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小
    }

    // 下拉刷新方法
    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                mSwipeLayout.setRefreshing(false);
                // (复用中)总fragment 通过适配器的方法传过来每个title的ID
                Bundle bundle = getArguments();
                int id = bundle.getInt("id");
                Constant.NEW_MAP_BODY_VIEWPAGER.put("categoryId", id + "");

                // 网络请求

                networkRequest();
            }
        }, 5000); // 5秒后发送消息，停止刷新


    }


}
