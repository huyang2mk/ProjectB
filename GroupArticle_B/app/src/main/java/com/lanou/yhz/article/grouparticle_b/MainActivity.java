package com.lanou.yhz.article.grouparticle_b;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;
import com.lanou.yhz.article.grouparticle_b.find.FindFragment;
import com.lanou.yhz.article.grouparticle_b.home.HomeFragment;
import com.lanou.yhz.article.grouparticle_b.mine.MineFragment;
import com.lanou.yhz.article.grouparticle_b.take.TakeFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private TakeFragment takeFragment;
    private MineFragment mineFragment;



    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        byView(R.id.rb_home).setOnClickListener(this);
        byView(R.id.rb_find).setOnClickListener(this);
        byView(R.id.rb_take).setOnClickListener(this);
        byView(R.id.rb_mine).setOnClickListener(this);
        replaceFragment(R.id.fl_replace, new HomeFragment());

        homeFragment = new HomeFragment();
        findFragment = new FindFragment();
        takeFragment = new TakeFragment();
        mineFragment = new MineFragment();

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()){
            case R.id.rb_home:
                transaction.replace(R.id.fl_replace, homeFragment);
                break;
            case R.id.rb_find:
                transaction.replace(R.id.fl_replace, findFragment);
                break;
            case R.id.rb_take:
                transaction.replace(R.id.fl_replace, takeFragment);
                break;
            case R.id.rb_mine:
                transaction.replace(R.id.fl_replace, mineFragment);
                break;
        }
        transaction.commit();

    }

    /**
     * 优化代码(封装思想)
     */
    public void replaceFragment(int replace, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(replace, fragment);
        transaction.commit();
    }
}
