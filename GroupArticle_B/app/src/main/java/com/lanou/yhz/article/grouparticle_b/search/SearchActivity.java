package com.lanou.yhz.article.grouparticle_b.search;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.base.BaseActivity;
import com.lanou.yhz.article.grouparticle_b.bean.searchbean.SearchBean;
import com.lanou.yhz.article.grouparticle_b.ok.OkHttpManger;
import com.lanou.yhz.article.grouparticle_b.ok.OnNetResultListener;
import com.lanou.yhz.article.grouparticle_b.search.ormlite.Search;
import com.lanou.yhz.article.grouparticle_b.search.ormlite.SearchDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/6.
 */

public class SearchActivity extends BaseActivity {
    private Context context;
    private GridView gridView;
//    private List<SubHottestAdapterBean.DataBean.ResultsBean> datas;

    private TextView data,  search, cancel;
    private ImageView alldelete;
    private SearchBean bean;
    private SearchAdapter searchAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        data = (TextView) findViewById(R.id.ed_search);
        alldelete = (ImageView) findViewById(R.id.iv_delete_all);
        search = (TextView) findViewById(R.id.tv_search_old);
        cancel = (TextView) findViewById(R.id.tv_search_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ListView listView = (ListView) findViewById(R.id.lv_search);
        final MyAdapter adapter = new MyAdapter(this);
        listView.setAdapter(adapter);

        gridView = (GridView) findViewById(R.id.gv_search);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Intent intent = new Intent(SearchActivity.this,NewsVideoActivity.class);
////
////                intent.putExtra("New_videoId", datas.get(1).getId());
////
////                startActivity(intent);
//            }
//        });
        searchAdapter = new SearchAdapter(this);
        gridView.setAdapter(searchAdapter);

        GvSearch();

        data.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    if (data.getText().length() > 0) {
                        Search user = new Search();
                        /*************************/
                        List<Search> userList = SearchDao.getsTnstance(SearchActivity.this).queryAll();
                        for (Search user1 : userList) {
                            if (data.getText().toString().trim().equals(user1.getContent())) {
                                SearchDao.getsTnstance(SearchActivity.this).deleteOne(data.getText().toString().trim());
                            }
                        }
                        user.setContent(data.getText().toString().trim());
                        user.setTime(System.currentTimeMillis() + "");
                        SearchDao.getsTnstance(SearchActivity.this).add(user);
                        userList = SearchDao.getsTnstance(SearchActivity.this).queryAll();
                        adapter.setSearchList(userList);
                        data.setText("");
                        /****/
//                        user.setName(placeholder.getText().toString().trim());
//                        user.setTime(System.currentTimeMillis() + "");
//                        UserDao.getsTnstance(SearchActivity.this).add(user);
//                        List<User> userList = UserDao.getsTnstance(SearchActivity.this).queryAll();
//                        listadapter.setUserList(userList);
//                        placeholder.setText("");
                    }
                }

                return false;

            }
        });
//        isHotWordsResolve();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = data.getText().toString();
                List<Search> searchlist = SearchDao.getsTnstance(SearchActivity.this).queryAll();
                if (date.length() > 0) {
                    //遍历  没有不删除 有就删除
                    for (Search search1 : searchlist) {
                        if (date.equals(search1.getContent())) {
                            //删除
                            SearchDao.getsTnstance(SearchActivity.this).deleteOne(search1.getContent());
                        }
                    }
                    //初始化实体类
                    Search s = new Search();
                    //数据库以对象的方式来存储 数据库中有两个对象 内容和时间
                    s.setContent(date);
                    s.setTime(System.currentTimeMillis() + "");
//                   把对象添加到数据库中
                    SearchDao.getsTnstance(SearchActivity.this).add(s);
                    //查询数据库
                    searchlist = SearchDao.getsTnstance(SearchActivity.this).queryAll();
                    adapter.setSearchList(searchlist);
                    data.setText("");
                } else {
                    return;
                }

            }
        });

        alldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDao.getsTnstance(SearchActivity.this).deleteAll();
                List<Search> searchlist = SearchDao.getsTnstance(SearchActivity.this).queryAll();
                adapter.setSearchList(searchlist);

            }
        });
    }

    private void GvSearch() {
        String postUrl = "http://api.rr.tv/video/hotWord";
        final String key1 = "clientVersion";
        final String value1 = "3.5.2";

        Map<String, String> maps = new HashMap<>();
        maps.put(key1, value1);

        OkHttpManger okHttpManger = OkHttpManger.getInstance();
        okHttpManger.startHeader(postUrl,maps, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String successStr) {

                Gson gson = new Gson();
                bean = gson.fromJson(successStr, SearchBean.class);
                searchAdapter.setData(bean);

                Log.d("cccccccc", successStr);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });

    }


    /**
     * 搜索热词解析
     */
//    public static final int SEARCH = 1;
//    private void isHotWordsResolve() {
//      String strUrl = "http://api.liwushuo.com/v2/search/hot_words";
//        OKHttpManager okHttpManager =  OKHttpManager.getInstance();
//        okHttpManager.getAsyncAsString(strUrl, SEARCH, new OKHttpManager.StringCallback() {
//            @Override
//            public void onFailure(IOException e) {
//
//            }
//
//            @Override
//            public void onSuccessful(int requestCode, String result) {
//
//                if (requestCode == SEARCH) {
//
//                    Gson gson = new Gson();
//
//                    SearchBean searchBen = gson.fromJson(result, SearchBean.class);
//
//                    Log.d("SearchActivity", "searchBen:" + searchBen);
//                    adapter = new SearchAdapter(context);
//                    gridView.setAdapter(adapter);
//                    adapter.setData(searchBen);
//                }
//
//            }
//        });
//
//
//    }

}

