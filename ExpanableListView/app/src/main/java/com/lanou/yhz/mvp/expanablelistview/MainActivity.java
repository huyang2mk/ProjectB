package com.lanou.yhz.mvp.expanablelistview;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    // 二级列表
    // Expand:展开
    // Expandable: 又展开能力的
    // groud(父级),child (子级)


    // 在android中
    // 有两个放可以存出一些大的资源文件 IMb
    // assets: 与src和res同级
    // raw : 在res目录下

    // 区别
    // 1, 存在res/raw中的文件, 会在R文件里映射出一个ID
    // 通过ID去使用
    // 存在assets中的文件, 通过open方法传入文件名去使用
    // 2, 在打包时,raw下的文件会编译成二进制码
    //  但是 assets 中的文件会存在apk中, 不会编译成二进制
    // 3, 虽然两个文件夹都能放置不大于1mb的文件, 但assets存在apk中,
    // 因此不要过大
    // 但是raw会编译为二进制, 因此3,4,5MB的音频文件都可以存
    // 4 , assets 可以创建子目录
    // raw 不能, 因为res目录结构是Android规定好的
    // 名字和附属关系是定死的


    // bean  具有私有属性和行为,对外提供公有设置和访问方式
    // dao : 数据存储操作的单位或工具

    private ExpandableListView eleListView;

    // 父级列别ABCD
    private List<String> groupList;

    // 子级列表 listBean存储的是车的名字和图标
    private Map<String,List<CarEntity.ResultBean.BrandlistBean.ListBean> >
            childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eleListView = (ExpandableListView) findViewById(R.id.expand_list_view);
        eleListView.setGroupIndicator(null);
        eleListView.setGroupIndicator(this.getResources().getDrawable(R.drawable.the_arrow));
        // 获取数据
        createData();
        ExAdapter  exAdapter = new ExAdapter();
        exAdapter.setData(groupList,childList);
        eleListView.setAdapter(exAdapter);


        for (int i = 0; i < groupList.size(); i++) {
            eleListView.expandGroup(i);



        }

        // ExpandableListView的 监听事件
        eleListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return false;
            }
        });
        eleListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return false;
            }
        });

    }

    private void jsonData(String string) {
        Gson gson = new Gson();
        CarEntity car = gson.fromJson(string, CarEntity.class);
        // 解析完成, 拆分数据, 做成父子二级的嵌套数据
        groupList =new ArrayList<>();
        childList = new HashMap<>();
       List<CarEntity.ResultBean.BrandlistBean> brandList=
               car.getResult().getBrandlist();

        for (int i = 0; i <brandList.size() ; i++) {
            groupList.add(brandList.get(i).getLetter());

            childList.put(brandList.get(i).getLetter(),
                    brandList.get(i).getList());

        }



    }


    private void createData() {
        // 1,从Assets中获取文件

        try {
            InputStream is = getAssets().open("car.txt");
            InputStreamReader isr = new InputStreamReader(is);
            // 读流
            // 可逐行读取的字符流
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            isr.close();
            is.close();

            Log.d("MainActivity", result);

            jsonData(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
