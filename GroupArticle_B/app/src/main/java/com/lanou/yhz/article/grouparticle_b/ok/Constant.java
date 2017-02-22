package com.lanou.yhz.article.grouparticle_b.ok;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/2/21.
 *
// ********************************专门放网址********************************/


public class Constant {


    // 首页 最新 全部标题接口网址
    public final static String NEW_TITLE="http://api.rr.tv/v3plus/user/myFoucsCategory";


    // 首页 最新 全部标题接口网址的key,value

    public final static Map<String,String>NEW_TITLE_MAP = new HashMap<>();

    static {
        String key1 = "clientType";
        String value1 = "android_360%E5%B8%82%E5%9C%BA";
        String key2 = "token";
        String value2 = "70b80cdf992836d1b7a7a9b44e878866793fc5af";
        String key3 = "clientVersion";
        String value3 = "3.5.2";
        NEW_TITLE_MAP.put(key1, value1);
        NEW_TITLE_MAP.put(key2, value2);
        NEW_TITLE_MAP.put(key3, value3);

    }







}
