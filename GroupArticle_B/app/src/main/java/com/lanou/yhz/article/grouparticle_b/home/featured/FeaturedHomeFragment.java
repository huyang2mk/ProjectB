package com.lanou.yhz.article.grouparticle_b.home.featured;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.BannerBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.BriefBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.DataBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.RootBean;
import com.lanou.yhz.article.grouparticle_b.home.featured.adapter.FeaturedAdapter;
import com.lanou.yhz.article.grouparticle_b.home.featured.adapter.FeaturedMyAdapter;
import com.lanou.yhz.article.grouparticle_b.home.featured.adapter.FeaturedPagerAdapter;
import com.lanou.yhz.article.grouparticle_b.home.featured.adapter.FeaturedTodayAdapter;
import com.lanou.yhz.article.grouparticle_b.home.featured.adapter.FeaturedUpAdapter;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;
import com.lanou.yhz.article.grouparticle_b.utils.MyGridLayoutManager;
import com.lanou.yhz.article.grouparticle_b.utils.MyScrollView;
import com.lanou.yhz.article.grouparticle_b.utils.SpacesItemDecoration;
import com.lanou.yhz.article.grouparticle_b.utils.TestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/20.
 * @author zhaochunyu
 * 精选页
 */

public class FeaturedHomeFragment extends BaseFragment {
    private static final int TIME = 2000;
    private ViewPager viewPager;
    private LinearLayout pointll;//轮播状态改变的小圆点容器
    private FeaturedPagerAdapter fpa;
    private List<BannerBean>data;

    private List<BriefBean>briefBean;
    private FeaturedTodayAdapter todayAdapter;
    private RecyclerView todayRecycler;
    private Button fraturedRefresh;

    private List<DataBean.OfficalAlbumBean> officalBean;
    private RecyclerView officalRecycler;
    private FeaturedAdapter repeatAdapter;

    private List<DataBean.RecommendUpBean> recommendUpBeen;
    private RecyclerView recommendUpRecycler;
    private FeaturedUpAdapter upAdapter;

    private List<DataBean.CategoryBean> categoryBeen;

    private RecyclerView entertainmentView;
    private FeaturedMyAdapter entertainmentAdapter;

    private RecyclerView movieRecyclerView;
    private FeaturedMyAdapter movieAdapter;

    private RecyclerView lifeRv;
    private FeaturedMyAdapter lifeAdapter;

    private RecyclerView scienceRv;
    private FeaturedMyAdapter scienceAdapter;

    private RecyclerView musicRv;
    private FeaturedMyAdapter musicAdapter;

    private RecyclerView sportsRv;
    private FeaturedMyAdapter sportsAdapter;

    private RecyclerView educationRv;
    private FeaturedMyAdapter educationAdapter;

    private RecyclerView recordRv;
    private FeaturedMyAdapter recordAdapter;


    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    private TextView officalTv,entertainmentTv,movieTv,lifeTv,scienceTv,musicTv,sportsTv,educationTv,recordTv;
    private MyScrollView myScrollView;


    @Override
    public int setLayout() {
        return R.layout.fragment_home_fratured;
    }

    @Override
    public void initView(View view) {

        myScrollView = (MyScrollView) view.findViewById(R.id.feature_home_my_scroll);
        viewPager = (ViewPager) view.findViewById(R.id.home_fratured_viewpager);
        pointll = (LinearLayout) view.findViewById(R.id.home_fratured_linearlayout);
        todayRecycler = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_today);
        fraturedRefresh = (Button) view.findViewById(R.id.home_fratured_btn_refresh);
        officalRecycler = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_officalAlbum);
        officalTv = (TextView) view.findViewById(R.id.home_fratured_tv_offical);
        entertainmentTv = (TextView) view.findViewById(R.id.home_fratured_tv_entertainment);
        movieTv = (TextView) view.findViewById(R.id.home_fratured_tv_movie);
        lifeTv = (TextView) view.findViewById(R.id.home_fratured_tv_life);
        recommendUpRecycler = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_up);
        entertainmentView = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_entertainment);
        movieRecyclerView = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_movie);
        scienceTv = (TextView) view.findViewById(R.id.home_fratured_tv_science);
        lifeRv = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_life);
        scienceRv = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_science);
        musicTv = (TextView) view.findViewById(R.id.home_fratured_tv_music);
        musicRv = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_music);
        sportsRv = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_sports);
        sportsTv = (TextView) view.findViewById(R.id.home_fratured_tv_sports);
        educationRv = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_education);
        educationTv = (TextView) view.findViewById(R.id.home_fratured_tv_education);
        recordRv = (RecyclerView) view.findViewById(R.id.home_fratured_recycler_record);
        recordTv = (TextView) view.findViewById(R.id.home_fratured_tv_record);
        fraturedRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayAdapter.notifyDataSetChanged();
            }
        });
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

        todayAdapter = new FeaturedTodayAdapter(context);
        //设置Todayitem间距
        int itemSpacing = 10;
        todayRecycler.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        todayRecycler.setAdapter(todayAdapter);

        //设置精彩推荐数据
        repeatAdapter = new FeaturedAdapter(context);
        officalRecycler.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        officalRecycler.setAdapter(repeatAdapter);
        repeatAdapter.setTestListener(new TestListener() {
            @Override
            public void onC() {
                Toast.makeText(context, "sssss", Toast.LENGTH_SHORT).show();
            }
        });

        //设置up数据
        upAdapter = new FeaturedUpAdapter(context);
        recommendUpRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recommendUpRecycler.setAdapter(upAdapter);
        recommendUpRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myScrollView.setHorizontalRv(recommendUpRecycler);
                return false;
            }
        });

        //设置娱乐数据
        entertainmentAdapter = new FeaturedMyAdapter(context);
        entertainmentView.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        entertainmentView.setAdapter(entertainmentAdapter);
        resumeTouch(entertainmentView);

        //设置影视花絮数据
        movieAdapter = new FeaturedMyAdapter(context);
        movieRecyclerView.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        movieRecyclerView.setAdapter(movieAdapter);
        resumeTouch(movieRecyclerView);

        //设置生活数据
        lifeAdapter = new FeaturedMyAdapter(context);
        lifeRv.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        lifeRv.setAdapter(lifeAdapter);

        //设置科技数据
        scienceAdapter = new FeaturedMyAdapter(context);
        scienceRv.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        scienceRv.setAdapter(scienceAdapter);

        //设置音乐数据
        musicAdapter= new FeaturedMyAdapter(context);
        musicRv.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        musicRv.setAdapter(musicAdapter);

        //设置体育数据
        sportsAdapter = new FeaturedMyAdapter(context);
        sportsRv.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        sportsRv.setAdapter(sportsAdapter);

        //设置教育数据
        educationAdapter = new FeaturedMyAdapter(context);
        educationRv.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        educationRv.setAdapter(educationAdapter);

        //设置记录数据
        recordAdapter = new FeaturedMyAdapter(context);
        recordRv.addItemDecoration(new SpacesItemDecoration(itemSpacing));
        recordRv.setAdapter(recordAdapter);

    }

    public void resumeTouch(View view){


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
                RootBean rootBean = new Gson().fromJson(successStr,RootBean.class);
                DataBean dataBean = rootBean.getData();
                data = dataBean.getBannerTop();
                // 设置数据
                fpa.setData(data);

//                // 设置today数据
                briefBean = dataBean.getTodayRecommend();
                todayAdapter.setData(briefBean);
                GridLayoutManager manager = new GridLayoutManager(context,2);
                manager.setOrientation(GridLayoutManager.VERTICAL);
                todayRecycler.setLayoutManager(manager);

                // 设置offical数据

                officalBean = dataBean.getOfficalAlbum();
                repeatAdapter.setData(officalBean);
                officalTv.setText(officalBean.get(0).getName());
                MyGridLayoutManager layoutManager = new MyGridLayoutManager(context,2);
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return repeatAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                officalRecycler.setLayoutManager(layoutManager);

                //设置up数据
                recommendUpBeen = dataBean.getRecommendUp();
                upAdapter.setData(recommendUpBeen);

                //设置娱乐数据
                categoryBeen = dataBean.getCategoryList();
                entertainmentAdapter.setData(categoryBeen,0);
                entertainmentTv.setText(categoryBeen.get(0).getCategoryName());
                MyGridLayoutManager layoutManager1 = new MyGridLayoutManager(context,2);
                layoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return entertainmentAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                entertainmentView.setLayoutManager(layoutManager1);

                //设置影视花絮数据
                movieAdapter.setData(categoryBeen,1);
                movieTv.setText(categoryBeen.get(1).getCategoryName());
                GridLayoutManager layoutManager2 = new GridLayoutManager(context,2);
                layoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return movieAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                movieRecyclerView.setLayoutManager(layoutManager2);

                //设置生活数据
                lifeAdapter.setData(categoryBeen,2);
                lifeTv.setText(categoryBeen.get(2).getCategoryName());
                GridLayoutManager layoutManager3 = new GridLayoutManager(context,2);
                layoutManager3.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return lifeAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                lifeRv.setLayoutManager(layoutManager3);

                //设置科技数据
                scienceAdapter.setData(categoryBeen,3);
                scienceTv.setText(categoryBeen.get(3).getCategoryName());
                GridLayoutManager layoutManager4 = new GridLayoutManager(context,2);
                layoutManager4.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return scienceAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                scienceRv.setLayoutManager(layoutManager4);

                //设置音乐数据
                musicAdapter.setData(categoryBeen,4);
                musicTv.setText(categoryBeen.get(4).getCategoryName());
                GridLayoutManager layoutManager5 = new GridLayoutManager(context,2);
                layoutManager5.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return musicAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                musicRv.setLayoutManager(layoutManager5);

                //设置体育数据
                sportsAdapter.setData(categoryBeen,5);
                sportsTv.setText(categoryBeen.get(5).getCategoryName());
                GridLayoutManager layoutManager6 = new GridLayoutManager(context,2);
                layoutManager6.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return sportsAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                sportsRv.setLayoutManager(layoutManager6);

                //设置教育数据
                educationAdapter.setData(categoryBeen,6);
                educationTv.setText(categoryBeen.get(6).getCategoryName());
                GridLayoutManager layoutManager7 = new GridLayoutManager(context,2);
                layoutManager7.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return educationAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                educationRv.setLayoutManager(layoutManager7);

                //设置记录数据
                recordAdapter.setData(categoryBeen,7);
                recordTv.setText(categoryBeen.get(7).getCategoryName());
                GridLayoutManager layoutManager8 = new GridLayoutManager(context,2);
                layoutManager8.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return recordAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                recordRv.setLayoutManager(layoutManager8);





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
