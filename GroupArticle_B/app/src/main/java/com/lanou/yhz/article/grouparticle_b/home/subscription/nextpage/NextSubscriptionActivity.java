package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubHottestAdapterBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.subscriptionbean.SubscriptionBean;
import com.lanou.yhz.article.grouparticle_b.home.HomeFragmentPagerAdapter;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.hottest.ThreePageSubHottestFragment;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.newest.ThreePageSubNewestFragment;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 订阅_第二级界面Activity
* */

public class NextSubscriptionActivity extends BaseActivity {
    private ViewPager viewPager;
    private ImageView iv_subscription_next_topbar_back;
    private List<Fragment> data;
    private TabLayout tabLayout;
    private ImageView iv_subscription_next_picture, iv_subscription_next_all, iv_subscription_next_avatar;
    private TextView tv_subscription_next_title, tv_subscription_next_fans, tv_subscription_next_compilation, tv_subscription_next_food;
    private SubHottestAdapterBean datas;
    private SubscriptionBean bean;


    @Override
    protected int getLayout() {

        return R.layout.activity_next_subscription;

    }


    @Override
    protected void initView() {
        iv_subscription_next_topbar_back = (ImageView) findViewById(R.id.iv_subscription_next_topbar_back);
        iv_subscription_next_avatar = (ImageView) findViewById(R.id.iv_subscription_next_avatar);
        iv_subscription_next_all = (ImageView) findViewById(R.id.iv_subscription_next_all);

        tv_subscription_next_title = (TextView) findViewById(R.id.tv_subscription_next_title);
        tv_subscription_next_fans = (TextView) findViewById(R.id.tv_subscription_next_fans);
        tv_subscription_next_compilation = (TextView) findViewById(R.id.tv_subscription_next_compilation);
        tv_subscription_next_food = (TextView) findViewById(R.id.tv_subscription_next_food);


//        GlideManger glideManger = GlideManger.getsInstance();
//        glideManger.loadImageView(this, bean.getData().getUperList().get(1).getHeadImg(),iv_subscription_next_avatar);

//        int a = bean.getData().getUperList().size();


//        iv_subscription_next_avatar.setImageResource(bean.getData().getUperList().get(1).getHeadImg().charAt(1));


        iv_subscription_next_topbar_back = (ImageView) findViewById(R.id.iv_subscription_next_topbar_back);
        iv_subscription_next_topbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tab_home_subscription_next);
        viewPager = (ViewPager) findViewById(R.id.vp_home_subsciption_next);
        //添加tab颜色
        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorSkyBlue));
        // 将tabLayout的下划线颜色设置成蓝色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorSkyBlue));

        data = new ArrayList<>();
        data.add(new ThreePageSubNewestFragment());
        data.add(new ThreePageSubHottestFragment());
        iv_subscription_next_picture = (ImageView) findViewById(R.id.iv_subscription_next_picture);
        iv_subscription_next_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //地图
            }
        });


        okhttp();


    }

    private void okhttp() {


        String postUrl = "http://api.rr.tv/v3plus/uper/recommendList";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";
        final String keyNew1 = "page";
        final String valueNew1 = "1";
        final String keyNew2 = "rows";
        final String valueNew2 = "10";

        Map<String, String> maps = new HashMap<>();
        Map<String, String> mapNew = new HashMap<>();
        maps.put(key1, value1);
        mapNew.put(keyNew1, valueNew1);
        mapNew.put(keyNew2, valueNew2);

        OkHttpManger okHttpManger = OkHttpManger.getInstance();
        okHttpManger.startPost(postUrl, mapNew, maps, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {

                Gson gson = new Gson();
                bean = gson.fromJson(successStr, SubscriptionBean.class);

                Log.d("sssssssssssssssssss", successStr);
                for (int i = 0; i < bean.getData().getUperList().size(); i++) {
                    tv_subscription_next_title.setText(bean.getData().getUperList().get(i).getName().toString());
                    tv_subscription_next_food.setText(bean.getData().getUperList().get(i).getUserIntro().toString());
                    GlideManger glideManger = GlideManger.getsInstance();
                    glideManger.loadImageView(NextSubscriptionActivity.this, bean.getData().getUperList().get(i).getHeadImg(), iv_subscription_next_avatar);
                    glideManger.loadImageView(NextSubscriptionActivity.this, bean.getData().getUperList().get(i).getHeadImg(), iv_subscription_next_all);

                }


            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }


    @Override
    protected void initData() {
        HomeFragmentPagerAdapter viewPage = new HomeFragmentPagerAdapter(getSupportFragmentManager(), data);
        viewPager.setAdapter(viewPage);


        tabLayout.setupWithViewPager(viewPager);


    }
}
