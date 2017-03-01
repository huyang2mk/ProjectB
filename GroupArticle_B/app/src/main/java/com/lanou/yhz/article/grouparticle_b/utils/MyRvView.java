package com.lanou.yhz.article.grouparticle_b.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zhaochunyu on 2017/2/28.
 */

public class MyRvView extends RecyclerView{
    public MyRvView(Context context) {
        super(context);
    }

    public MyRvView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRvView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
