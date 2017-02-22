package com.lanou.yhz.article.grouparticle_b.ok;

import android.content.Context;
import android.widget.ImageView;


public abstract class ImageManager {

    //抽象方法 规定所有的图片管理对象都有从网络上加载图片
//    并设置到ImageView对象的方法
    public abstract void loadImageView(Context context, String url, ImageView imageView);
}
