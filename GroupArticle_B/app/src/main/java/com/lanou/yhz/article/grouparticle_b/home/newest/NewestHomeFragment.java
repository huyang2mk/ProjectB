package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewTitleBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

/**
 * Created by dllo on 17/2/20.
 */

public class NewestHomeFragment extends BaseFragment {

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_home_newest;
    }


    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.newest_Vpger);

        tabLayout = (TabLayout) view.findViewById(R.id.newest_Tab);


    }

    private static final String TAG = "NewestHomeFragment";

    @Override
    public void initData() {
        //    Bundle bundle = getArguments();
//        boolean load = true;
//        if (bundle != null) {
//
//            load = bundle.getBoolean("isNeedLoad", true);
//        }

        final NewestHomeAdapter adapter = new NewestHomeAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);


//        if (load) {

        OkHttpManger.getInstance().startHeader(Constant.NEW_TITLE, Constant.NEW_TITLE_MAP, new OnNetResultListener() {

            @Override
            public void onSuccessListener(String successStr) {
                Log.e(TAG, "onSUccessListener: ");
                Gson gson = new Gson();
                NewTitleBean bean = gson.fromJson(successStr, NewTitleBean.class);
                adapter.setData(bean.getData().getCategory());

            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });

        //   }
    }
}