package com.lanou.yhz.article.grouparticle_b.home.newest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.NewTitleBean;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
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

public class NewestHomeAdapter extends FragmentPagerAdapter {
    private List<NewTitleBean.DataBean.CategoryBean>data;
    public NewestHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<NewTitleBean.DataBean.CategoryBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        int channelID = data.get(position).getId();
        return ViewPgerNewestHomeFragment.getSendGirlfriendHome(channelID);

    }

    @Override
    public int getCount() {
        return data!=null? data.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }
}
