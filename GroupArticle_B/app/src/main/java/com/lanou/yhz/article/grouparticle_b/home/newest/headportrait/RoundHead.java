package com.lanou.yhz.article.grouparticle_b.home.newest.headportrait;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.lanou.yhz.article.grouparticle_b.R;


/**
 * Created by dllo on 17/1/5.
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */

public class RoundHead extends ImageView{
    // 图片的类型，圆形or圆角
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_RECT = 1;
    //圆角大小的默认值
    private static final int BODER_RADIUS_DEFAULT = 10;
    //圆角的大小
    private int mBorderRadius;

    // 绘图的Paint
    private Paint mBitmapPaint;
    //圆角的半径
    private int mRadius;
    // 3x3 矩阵，主要用于缩小放大
    private Matrix mMatrix;
    // 渲染图像，使用图像为绘制图形着色
    private BitmapShader mBitmapShader;
    // view的宽度
    private int mWidth;
    //矩形
    private RectF mRoundRect;

    public RoundHead(Context context)
    {
        this(context, null);
    }

    public RoundHead(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.BitmapShaderImageView);
        //矩形圆角幅度的获取，默认是10dp
        mBorderRadius = a.getDimensionPixelSize(
                R.styleable.BitmapShaderImageView_borderRadius, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                BODER_RADIUS_DEFAULT, getResources()
                                        .getDisplayMetrics()));
        //自定义类型属性，0是圆形，1是矩形圆角
        type = a.getInt(R.styleable.BitmapShaderImageView_type, TYPE_CIRCLE);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 如果类型是圆形，则强制改变view的宽高一致，以小值为准
        if (type == TYPE_CIRCLE)
        {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            //圆形的半径
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }

    }

    // 初始化BitmapShader,获取到图片资源
    // 等待画布的准备好，然后在画布上加上Paint就是了
    //就是说图片的载体是Paint
    private void setUpShader()
    {
        Drawable drawable = getDrawable();
        if (drawable == null)
        {
            return;
        }

        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        //TileMode.CLAMP 拉伸
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE)
        {
            // 拿到bitmap宽或高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mWidth * 1.0f / bSize;

        } else if (type == TYPE_RECT)
        {
            if (!(bmp.getWidth() == getWidth() && bmp.getHeight() == getHeight()))
            {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale = Math.max(getWidth() * 1.0f / bmp.getWidth(),
                        getHeight() * 1.0f / bmp.getHeight());
            }

        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        // scale * scale 的矩阵
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        if (getDrawable() == null)
        {
            return;
        }
        setUpShader();

        if (type == TYPE_RECT)
        {
            //绘制矩形
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius,
                    mBitmapPaint);
        } else
        {
            //绘制圆形
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        // 圆角图片的范围
        if (type == TYPE_RECT)
            mRoundRect = new RectF(0, 0, w, h);
    }

    //drawable转bitmap
    private Bitmap drawableToBitamp(Drawable drawable)
    {
        //从控件的src获取背景，也是drawable文件获取
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }

        //如果没有绘图一个，只不过是空白的图片
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    //屏幕旋转后，取出保存的值
    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        return bundle;
    }

    //屏幕旋转，变量的保存，因为外面设置值，如果不保存，一旋转就变成个xml里面设置的值
    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else
        {
            super.onRestoreInstanceState(state);
        }

    }

    //设置矩形圆角幅度后，重新绘制控件
    public void setBorderRadius(int borderRadius)
    {
        int pxVal = dp2px(borderRadius);
        if (this.mBorderRadius != pxVal)
        {
            this.mBorderRadius = pxVal;
            invalidate();
        }
    }

    //设置是圆形还是矩形圆角
    public void setType(int type)
    {
        if (this.type != type)
        {
            this.type = type;
            if (this.type != TYPE_RECT && this.type != TYPE_CIRCLE)
            {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }

    }
    //dp转px
    public int dp2px(int dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
