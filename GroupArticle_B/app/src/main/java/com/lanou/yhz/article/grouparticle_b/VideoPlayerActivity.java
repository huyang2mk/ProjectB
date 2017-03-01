package com.lanou.yhz.article.grouparticle_b;

import android.util.Log;
import android.widget.VideoView;

import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;

/**
 * @author zhaochunyu
 * 视频播放二级界面
 */
public class VideoPlayerActivity extends BaseActivity {
    private VideoView videoView;

    @Override
    protected int getLayout() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void initView() {
        videoView = (VideoView) findViewById(R.id.video_player_videoview);
        String id = this.getIntent().getStringExtra("id");
        Log.d("VideoPlayerActivity", id);
    }

    @Override
    protected void initData() {

    }
}
