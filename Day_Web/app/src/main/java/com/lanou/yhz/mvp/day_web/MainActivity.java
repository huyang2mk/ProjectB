package com.lanou.yhz.mvp.day_web;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // webView = (WebView) findViewById(R.id.fragment_list_item_grid_intenWb);
        Uri uri = Uri.parse("http://qcloud.rrmj.tv/2017/02/23/8240f71876654aa7876eb0f9ee0b879d.mp4.f30.mp4?sign=bdacfc0152e1cbee97bbcf1fdcc08032&t=58aed58d");
        VideoView videoView = (VideoView)this.findViewById(R.id.video_view);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        //videoView.start();
        videoView.requestFocus();


       // webView.loadUrl("http://qcloud.rrmj.tv/2017/02/23/8240f71876654aa7876eb0f9ee0b879d.mp4.f30.mp4?sign=bdacfc0152e1cbee97bbcf1fdcc08032&t=58aed58d");
//
//        // 设置相关的webView参数
//        WebSettings set =  webView.getSettings();
//
//
//        set.setJavaScriptEnabled(true);
//
//        //设置能否加载js脚本
//        set.setJavaScriptEnabled(true);
//
//        // 设置能否显示缩放条
//        set.setDisplayZoomControls(true);
//        // 设置是否支持缩放
//        set.setSupportZoom(true);
//        //
//        webView.setWebChromeClient(new WebChromeClient() {
//            // new WebChromeClient()  加 {}
//
//
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                // 网页加载进度发生改变时候会调用从方法
//                Log.d("WebViewActivity", "newProgress:" + newProgress);
//            }
//        });
//        // 设置网页浏览客户端,可以监听当前网页的客户端,可以监听当前网页
//        webView.setWebViewClient(new WebViewClient());
    }
}
