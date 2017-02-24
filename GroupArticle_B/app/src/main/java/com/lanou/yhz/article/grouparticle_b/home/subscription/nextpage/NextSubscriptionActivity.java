package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;

import com.lanou.yhz.article.grouparticle_b.home.HomeFragmentPagerAdapter;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.hottest.ThreePageSubHottestFragment;
import com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage.threepages.newest.ThreePageSubNewestFragment;

import java.util.ArrayList;
import java.util.List;
/*
* 订阅_第二级界面Activity
* */

public class NextSubscriptionActivity extends BaseActivity {
    private ViewPager viewPager;
    private ImageView iv_subscription_next_topbar_back;
    private List<Fragment> data;
    private TabLayout tabLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_next_subscription;
    }

    @Override
    protected void initView() {
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

    }

    @Override
    protected void initData() {
        HomeFragmentPagerAdapter viewPage = new HomeFragmentPagerAdapter (getSupportFragmentManager(), data);
        viewPager.setAdapter(viewPage);
        tabLayout.setupWithViewPager(viewPager);
    }
}
