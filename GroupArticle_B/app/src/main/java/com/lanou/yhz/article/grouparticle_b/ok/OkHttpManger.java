package com.lanou.yhz.article.grouparticle_b.ok;


import android.os.Handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 17/2/17.
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
// OkHttpManger 封装单例类


public class OkHttpManger {

    // 1 , 私有构造方法
    // 2.1 定义当前类静态对象
    // 2.2 对外提供静态获取对象的方法
    // 在其中提供单例的方法


    private static OkHttpManger instance;
    private Handler handler;

    public static OkHttpManger getInstance() {
        // 单例写法之: 双重校验所
        // 判断当前类对象是否为null
        if (instance == null) {

            // 同步扫描内存, 判断当前对象是否为null
            // 线程同步锁

            synchronized (OkHttpManger.class) {
                if (instance == null) {
                    instance = new OkHttpManger();


                }
            }
        }
        return instance;

    }

    private OkHttpClient client;

    private OkHttpManger() {
        // 获取okHttpClicent
        getOkHttpClient();
        handler = new Handler();


    }

    private void getOkHttpClient() {

        client = new OkHttpClient.Builder()
                // 等线程池, 缓存, 文件下载讲完之后再来修改添加
//                .retryOnConnectionFailure()
//                .cache()
//                .readTimeout()
                .build();
    }


    // *********************封装在内部的操作方法*************************

    private void _startGet(final String url, final OnNetResultListener listener) {

        // 网络get请求
        // 将网络请求的代码写在网路操作类内容
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Request request = new Request.Builder()
                        .url(url)

                        .build();


                createCall(request, listener);

            }
        }).start();


    }


    private void _startHeader(final String url,
                              final Map<String, String> headers, final OnNetResultListener listener) {


        new Thread(new Runnable() {
            @Override
            public void run() {
//                Request request = new Request.Builder()
//                                  .url(url)
//                                  .addHeader()
//                                  .build();


                Request.Builder builder = new Request.Builder();
                builder.url(url);

                // 1, 遍历Map
                Set set = headers.keySet();
                // 迭代器
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    // 循环获取map的key值
                    String key = (String) iterator.next();
                    // 根据key值获取value
                    String value = headers.get(key);

                    builder.addHeader(key, value);


                }
                Request request = builder.build();

                createCall(request, listener);

            }
        }).start();


    }


    private void _startPost(final String url, final Map<String, String> body, final OnNetResultListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FormBody.Builder buildder =
                        new FormBody.Builder();

                Set set = body.keySet();

                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String value = body.get(key);
                    buildder.add(key, value);
                }


                RequestBody requesBody = buildder.build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(requesBody)
                        .build();

                createCall(request, listener);

            }
        }).start();
    }


    // 自定义成功失败方法
    private void createCall(Request request, final OnNetResultListener listener) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final String errMsg = e.getMessage();
                // 发回主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailureListener(errMsg);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSUccessListener(str);
                    }
                });
            }
        });
    }


    // *********************提供给外部的操作方法*************************

    // 封装: 对外提供使用方式, 具体的实现细节封装起来


    public void startGet(String url, OnNetResultListener listener) {

        _startGet(url, listener);

    }


    public void startHeader(String url, Map<String, String> headers, OnNetResultListener listener) {

        _startHeader(url, headers, listener);
    }


    public void startPost(String url, Map<String, String> body, OnNetResultListener listener) {
        _startPost(url, body, listener);
    }

}
