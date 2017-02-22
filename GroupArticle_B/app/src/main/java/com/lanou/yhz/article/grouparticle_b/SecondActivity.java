package com.lanou.yhz.article.grouparticle_b;



import android.view.View;

import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;
import com.lanou.yhz.article.grouparticle_b.base.TitleBuilder;

public class SecondActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return  R.layout.activity_second;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new TitleBuilder(this).setTitleText("测试标题").setLeftIco(R.mipmap.ic_launcher).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        }).setRightIco(R.mipmap.ic_launcher);

        

    }
}
