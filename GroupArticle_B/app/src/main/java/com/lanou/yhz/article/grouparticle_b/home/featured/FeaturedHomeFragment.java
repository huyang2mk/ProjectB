package com.lanou.yhz.article.grouparticle_b.home.featured;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.BannerBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.DataBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.FeaturedBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.RootBean;
import com.lanou.yhz.article.grouparticle_b.home.featured.adapter.FeaturedPagerAdapter;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */

public class FeaturedHomeFragment extends BaseFragment {
    private static final int TIME = 2000;
    private ViewPager viewPager;
    private LinearLayout pointll;//轮播状态改变的小圆点容器
    private FeaturedPagerAdapter fpa;
    private List<BannerBean>data;

    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    @Override
    public int setLayout() {
        return R.layout.fragment_home_fratured;
    }

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.home_fratured_viewpager);
        pointll = (LinearLayout) view.findViewById(R.id.home_fratured_linearlayout);
    }

    @Override
    public void initData() {
        //构造数据
        buildDatas();
        fpa = new FeaturedPagerAdapter(context);
        viewPager.setAdapter(fpa);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        viewPager.setCurrentItem(data.size() * 100);
        // 开始轮播
        handler = new Handler();


    }


    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有小点设置为白色
                    for (int i = 0; i < data.size(); i++) {
                        ImageView pointIv = (ImageView) pointll.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.point_white);
                    }
                    // 设置当前位置小点为灰色
                    ImageView iv = (ImageView) pointll.getChildAt(position % data.size());
                    iv.setImageResource(R.mipmap.point_grey);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 添加轮播切换小点
     */
    private void addPoints() {
        // 有多少张图加载多少个小点
        for (int i = 0; i < data.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(5,5,5,5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            pointIv.setLayoutParams(params);

            // 设置第0页小点的为灰色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_grey);
            } else {
                pointIv.setImageResource(R.mipmap.point_white);
            }
            pointll.addView(pointIv);
        }
    }
    /**
     * 开始轮播
     */
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }

    //加入数据
    private void buildDatas(){
        data = new ArrayList<>();
        OkHttpManger.getInstance().startPost(Constant.HOME_FEATURED, Constant.HOME_FEATURED_MAP,Constant.HOME_FEATURED_HEAD_MAP ,new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                RootBean rootBean = gson.fromJson(successStr,RootBean.class);
                DataBean dataBean = rootBean.getData();
                data = dataBean.getBannerTop();
                // 设置数据
                fpa.setData(data);

                startRotate();
//        // 添加轮播小点
                addPoints();
//        // 随着轮播改变小点
                changePoints();

            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });

    }
}
