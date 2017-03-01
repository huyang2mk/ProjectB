package com.lanou.yhz.article.grouparticle_b.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by zhaochunyu on 2017/2/27.
 */

public class MyScrollView extends ScrollView {

    private RecyclerView horizontalRv;


    public void setHorizontalRv(RecyclerView horizontalRv) {
        this.horizontalRv = horizontalRv;
        invalidate();// shuaxin
    }

    public MyScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    int downX;
    int downY;
    int mTouchSlop;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }

        return super.onInterceptTouchEvent(e);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (horizontalRv != null){
//            return horizontalRv.onTouchEvent(ev);
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("zzzd", "--touch--");
        return super.onTouchEvent(ev);
    }
}