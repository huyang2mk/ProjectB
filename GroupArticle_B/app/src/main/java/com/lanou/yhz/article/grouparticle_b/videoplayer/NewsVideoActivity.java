package com.lanou.yhz.article.grouparticle_b.videoplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean.VideoViewBean;
import com.lanou.yhz.article.grouparticle_b.ok.Constant;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * package com.lanou.yhz.mvp.videoviewvitamio;
 * Vitamio视频播放框架Demo
 */
public class NewsVideoActivity extends BaseActivity implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private android.widget.VideoView w;

    private Uri uri;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private CustomMediaController mCustomMediaController;
    private VideoView mVideoView;



    @Override
    protected int getLayout() {
        return R.layout.activity_video_news;
    }

    @Override
    protected void initView() {

        mVideoView = (VideoView) findViewById(R.id.buffer);
        //w.setMediaController(new MediaController());
        mCustomMediaController = new CustomMediaController(this, mVideoView, this);
        mCustomMediaController.setVideoName("白火锅 x 红火锅");
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);

    }


    @Override
    protected void initData() {
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = NewsVideoActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);

        videoplay();
        initView();

    }


    public void videoplay() {

        Intent intent = getIntent();

        String key = "videoId";

        String value = intent.getExtras().getInt("New_videoId") + "";



        Constant.NEW_MAP_VIDEO.put(key, value);

        OkHttpManger.getInstance().startPost(Constant.NEW_VIDEO, Constant.NEW_MAP_VIDEO, Constant.NEW_MAP_POST_HEAT, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {

                Gson gson = new Gson();
                VideoViewBean bean = gson.fromJson(successStr, VideoViewBean.class);
                videoinitData(bean.getData().getM3u8().getUrl());

            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });


    }



    //初始化数据
    private void videoinitData(String url) {
        uri = Uri.parse(url);
        mVideoView.setVideoURI(uri);//设置视频播放地址

        mCustomMediaController.show(5000);
        mVideoView.setMediaController(mCustomMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null) {

            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);

        }

        super.onConfigurationChanged(newConfig);
    }

}
