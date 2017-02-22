package com.lanou.yhz.article.grouparticle_b.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */

public class HomeFragmentViewPage extends FragmentPagerAdapter {
private List<Fragment> dataBean;
    private String[] title = {"最新","精选","订阅"};

    public HomeFragmentViewPage(FragmentManager fm) {
        super(fm);
    }

    public HomeFragmentViewPage(FragmentManager fm, List<Fragment> dataBean) {
        super(fm);
        this.dataBean = dataBean;
    }

    @Override
    public Fragment getItem(int position) {
        return dataBean.get(position);
    }

    @Override
    public int getCount() {
        return dataBean.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  title[position];
    }
}
