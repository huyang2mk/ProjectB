package com.lanou.yhz.article.grouparticle_b.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou.yhz.article.grouparticle_b.MainActivity;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseFragment;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MineFragment extends BaseFragment {
    private Button btn_mine_login;
    private String mName;
    private String mIcon;
    private TextView tv_mine_title;
    private ImageView iv_mine_icon_1star;



    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        btn_mine_login = (Button) view.findViewById(R.id.btn_mine_login);
        tv_mine_title = (TextView) view.findViewById(R.id.tv_mine_title);
        iv_mine_icon_1star = (ImageView) view.findViewById(R.id.iv_mine_icon_1star);

    }

    @Override
    public void initData() {
        ShareSDK.initSDK(getContext());

        isQQLoginNew();
        isQQLogin();
        isExit();

    }

    private void isQQLoginNew() {

        Platform qq = ShareSDK.getPlatform(QQ.NAME);

        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

        try {
            PlatformDb platformDb = qq.getDb();
            mName = platformDb.getUserName();
            mIcon = platformDb.getUserIcon();


            if (!TextUtils.isEmpty(mName)) {
                tv_mine_title.setText(mName);
                Glide.with(getContext()).load(mIcon).into(iv_mine_icon_1star);
            }
        } catch (NullPointerException e) {

        }

        PlatformActionListener platformActionListener = new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //输出所有授权信息
                arg0.getDb().exportData();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {

            }
        };

    }


    private void isQQLogin() {
        btn_mine_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "授权qq登录", Toast.LENGTH_SHORT).show();

                Platform qq = ShareSDK.getPlatform(QQ.NAME);


                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息

                // 回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        mName = platformDb.getUserName();
                        mIcon = platformDb.getUserIcon();

                        tv_mine_title.setText(mName);

                        Glide.with(getContext()).load(mIcon).into(iv_mine_icon_1star);




                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });

            }
        });
    }
    /**
     * 退出登录
     */
    private void isExit() {

        // 长按退出
        iv_mine_icon_1star.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    platform.removeAccount(true);
                } else {
                    Toast.makeText(getContext(), "退出登录", Toast.LENGTH_SHORT).show();
                }
                Intent intentMe = new Intent(getContext(), MainActivity.class);
                startActivity(intentMe);
                return false;
            }
        });
    }
}
