package com.lanou.yhz.article.grouparticle_b.video;


import android.content.res.Configuration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.FeaturedVideoBean;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.VideoDetailsBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import org.lynxz.zzplayerlibrary.controller.IPlayerImpl;
import org.lynxz.zzplayerlibrary.util.OrientationUtil;
import org.lynxz.zzplayerlibrary.widget.VideoPlayer;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhaochunyu
 * 视频播放二级界面
 */
public class VideoPlayerActivity extends BaseActivity {
    private VideoPlayer videoView;
    private Map<String,String> header = new HashMap<>();
    private Map<String,String> body = new HashMap<>();
    private FeaturedVideoBean featuredVideoBean;


    private VideoPlayerAdapter videoPlayerAdapter;
    private RecyclerView videoPlayerRv;
    //首页 精选接口网址的key,value

    String key3 = "clientVersion";
    String value3 = "3.5.2";

    @Override
    protected int getLayout() {
        return R.layout.activity_video_player;
    }



    @Override
    protected void initView() {
        videoView = (VideoPlayer) findViewById(R.id.video_player_videoview);
        videoPlayerRv = (RecyclerView) findViewById(R.id.video_player_recyclerview);
    }
    @Override
    protected void initData() {
        String id = this.getIntent().getStringExtra("id");
        Log.d("VideoPlayerActivity", id);
        header.put(key3,value3);
        body.put("videoId",id);
        OkHttpManger.getInstance().startPost(Constant.HOME_FEATURED_VIDEO, body, header, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                featuredVideoBean = gson.fromJson(successStr,FeaturedVideoBean.class);
                String url = featuredVideoBean.getData().getM3u8().getUrl();
                videoView.loadAndStartVideo(VideoPlayerActivity.this,url);
                videoView.setTitle(" ");
            }
            @Override
            public void onFailureListener(String errMsg) {

            }
        });
        player();
        initListener();
        videoPlayerAdapter = new VideoPlayerAdapter(this);
        videoDetail();
        videoPlayerRv.setAdapter(videoPlayerAdapter);
        videoPlayerRv.setLayoutManager(new LinearLayoutManager(VideoPlayerActivity.this));

    }
    private IPlayerImpl playerImpl = new IPlayerImpl() {

        @Override
        public void onNetWorkError() {
            showToast(null);
        }

        @Override
        public void onBack() {
            // 全屏播放时,单击左上角返回箭头,先回到竖屏状态,再关闭
            // 这里功能最好跟onBackPressed()操作一致
            int orientation = OrientationUtil.getOrientation(VideoPlayerActivity.this);
            if (orientation == OrientationUtil.HORIZONTAL) {
                OrientationUtil.forceOrientation(VideoPlayerActivity.this, OrientationUtil.VERTICAL);
            } else {
                finish();
            }
        }

        @Override
        public void onError() {
            showToast("播放器发生异常");
        }
    };
    private void initListener() {
        videoView.setPlayerController(playerImpl);
    }

    private void player(){

        //设置控制栏播放/暂停/全屏/退出全屏按钮图标
        videoView.setIconPlay(R.mipmap.play);
        videoView.setIconPause(R.mipmap.pause);
        videoView.setIconExpand(R.mipmap.expand);
        videoView.setIconShrink(R.mipmap.shrink);
        // 自定义加载框图标
        videoView.setIconLoading(R.drawable.loading_red);
        // 设置进度条样式
        videoView.setProgressThumbDrawable(R.mipmap.progress_thumb);
        videoView.setProgressLayerDrawables(R.drawable.biz_video_progressbar);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoView != null) {
            videoView.updateActivityOrientation();
        }
    }
    private void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = getResources().getString(R.string.zz_player_network_invalid);
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void videoDetail(){
        OkHttpManger.getInstance().startPost(Constant.HOME_FEATURED_VIDEO_DETAIL, body, header, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {
                Gson gson = new Gson();
                Log.d("11111", successStr);
                VideoDetailsBean videoDetailsBean = gson.fromJson(successStr,VideoDetailsBean.class);
                videoPlayerAdapter.setData(videoDetailsBean);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoView.onHostPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.onHostDestroy();
    }
}
