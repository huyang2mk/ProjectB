package com.lanou.yhz.article.grouparticle_b.home;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.home.featured.FeaturedHomeFragment;
import com.lanou.yhz.article.grouparticle_b.home.newest.NewestHomeFragment;
import com.lanou.yhz.article.grouparticle_b.home.subscription.SubscriptionHomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private ViewPager viewPager;
    private List<Fragment> data;
    private TabLayout tabLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.tab_home);

        // 把首页上面的TabLayout加
        tabLayout.setTabTextColors(Color.BLACK, getContext().getResources().getColor(R.color.colorSkyBlue));

        viewPager = (ViewPager) view.findViewById(R.id.vp_home);

        // 将tabLayout的下划线颜色设置成白色
        tabLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.colorTransparent));


        data = new ArrayList<>();
        data.add(new NewestHomeFragment());
        data.add(new FeaturedHomeFragment());
        data.add(new SubscriptionHomeFragment());

    }

    @Override
    public void initData() {
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getChildFragmentManager(),data);
        viewPager.setAdapter(adapter);



        tabLayout.setupWithViewPager(viewPager);
    }
}
