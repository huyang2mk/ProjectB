package com.lanou.yhz.article.grouparticle_b.base;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;

/**
 * Created by dllo on 17/2/15.
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
/**
 * 封装标题栏
 *
 * @author yaopengfei
 */
public class TitleBuilder {
    private View titleView;
    private RelativeLayout titleBar;
    private TextView text;
    private ImageView leftIco;
    private ImageView rightIco;

    /**
     * 构造方法：用于获取对象
     * */
    public TitleBuilder(Activity context){
        titleView = context.findViewById(R.id.title_bar);
        text = (TextView)titleView.findViewById(R.id.title_text);
        titleBar = (RelativeLayout)titleView.findViewById(R.id.title_bar);
        leftIco = (ImageView)titleView.findViewById(R.id.title_leftIco);
        rightIco = (ImageView)titleView.findViewById(R.id.title_rightIco);
    }

    /**
     * 用于设置标题栏文字
     * */
    public TitleBuilder setTitleText(String titleText){
        if(!TextUtils.isEmpty(titleText)){
            text.setText(titleText);
        }
        return this;
    }

    /**
     * 用于设置标题栏左边要显示的图片
     * */
    public TitleBuilder setLeftIco(int resId){
        leftIco.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        leftIco.setImageResource(resId);
        return this;
    }

    /**
     * 用于设置标题栏右边要显示的图片
     * */
    public TitleBuilder setRightIco(int resId){
        rightIco.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        rightIco.setImageResource(resId);
        return this;
    }

    /**
     * 用于设置标题栏左边图片的单击事件
     * */
    public TitleBuilder setLeftIcoListening(View.OnClickListener listener){
        if(leftIco.getVisibility() == View.VISIBLE){
            leftIco.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 用于设置标题栏右边图片的单击事件
     * */
    public TitleBuilder setRightIcoListening(View.OnClickListener listener){
        if(rightIco.getVisibility() == View.VISIBLE){
            rightIco.setOnClickListener(listener);
        }
        return this;
    }
}