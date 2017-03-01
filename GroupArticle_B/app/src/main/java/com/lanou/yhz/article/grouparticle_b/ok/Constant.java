package com.lanou.yhz.article.grouparticle_b.ok;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/2/21.
 * <p>
 * // ********************************专门放网址
 ********************************/


public class Constant {


    // 首页 最新 全部标题接口网址
    public final static String NEW_TITLE = "http://api.rr.tv/v3plus/user/myFoucsCategory";


    // 首页 最新 全部标题接口网址的key,value

    public final static Map<String, String> NEW_TITLE_MAP = new HashMap<>();

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


    // 首页 除了(全部) 全部item接口网址的key,value
    public final static String NEW_VIEWPAGER = "http://api.rr.tv/v3plus/video/query";

    public final static Map<String, String> NEW_MAP_POST_HEAT = new HashMap<>();

    static {


        String key = "clientVersion";
        String value = "3.5.2";


        NEW_MAP_POST_HEAT.put(key, value);


    }

    public final static Map<String, String> NEW_MAP_BODY_VIEWPAGER = new HashMap<>();

    static {

        String key = "rows";
        String value = "12";

        String key1 = "clientType";
        String value1 = "page";

        String key2 = "query";
        String value2 = "newest";

        NEW_MAP_BODY_VIEWPAGER.put(key, value);
        NEW_MAP_BODY_VIEWPAGER.put(key1, value1);
        NEW_MAP_BODY_VIEWPAGER.put(key2, value2);

    }


    //  视频播放功能
    public final static String NEW_VIDEO = "http://api.rr.tv/video/findM3u8ByVideoId";

    public final static Map<String, String> NEW_MAP_VIDEO = new HashMap<>();

    static {

        String key = "quality";
        String value = "high";
        NEW_MAP_VIDEO.put(key, value);

    }


}
