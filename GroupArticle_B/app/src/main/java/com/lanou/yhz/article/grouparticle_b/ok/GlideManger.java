package com.lanou.yhz.article.grouparticle_b.ok;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou.yhz.article.grouparticle_b.R;


//imgUrl 图片请求地址
//imageView 要绑定的视图组件
public class GlideManger extends ImageManager{

    private static GlideManger sInstance;


    public static GlideManger getsInstance(){
        if (sInstance ==null){
            sInstance = new GlideManger();
        }
        return sInstance;
    }
    private GlideManger(){

    }

    public void loadImageView(Context context, String imgUrl, ImageView imageView) {

        Glide.with(context).load(imgUrl)//请求的图片地址
                .error(R.mipmap.ic_launcher)//请求错误的图片
                .into(imageView);//要设置的组件
    }

    //imgUrl 图片请求地址
//imageView 要绑定的视图组件
    public void loadGifImageView(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context).load(imgUrl).asGif().into(imageView);
    }
}
