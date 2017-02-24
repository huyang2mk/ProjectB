package com.lanou.yhz.article.grouparticle_b.home.subscription.nextpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/23.
 * 二级Viewpager
 */

public class NextSubscriptionActivityViewPager extends FragmentPagerAdapter {
    private List<Fragment> dateBean;
    private String[] title = {"最新", "最热"};

    public NextSubscriptionActivityViewPager(FragmentManager fm, List<Fragment> dateBean) {
        super(fm);
        this.dateBean = dateBean;
    }

    @Override
    public Fragment getItem(int position) {
        return dateBean.get(position);
    }

    @Override
    public int getCount() {
        return dateBean.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
