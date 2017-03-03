package com.lanou.yhz.article.grouparticle_b.search.ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/2/7.
 */

public class SearchDao {
    /**********
     * 实际的真正的用户表操作对象
     *************/
    private Dao<Search, Integer> searches;

    // 单例模式  保证一个工程师一个表  (1)将 构造方法私有化  (2)
    private static SearchDao sTnstance;

    public static SearchDao getsTnstance(Context context) {
        if (sTnstance == null) {
            sTnstance = new SearchDao(context);
        }
        return sTnstance;
    }

    //(1)
    private SearchDao(Context context) {
        // 获取数据路的辅助对象
        ORMliteHelper helper = new ORMliteHelper(context);
        // 从数据库辅助对象获得用户表的操作对象
        searches = helper.getSearchORM();
    }

    //3将对象添加到数据库中的用户表中

    public void add(Search s) {
        try {
            searches.create(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询所有数据

    public List<Search> queryAll() {

        try {
            QueryBuilder<Search, Integer> qb = searches.queryBuilder();
            qb.orderBy("tb_time", false);
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据内容查询内容相同的用户
    public List<Search> queryForContent(String content) {
        // 获得查询器
        QueryBuilder<Search, Integer> qb = searches.queryBuilder();
        // 获得条件对象
        Where<Search, Integer> where = qb.where();
        try {
            where.eq("tb_content", content);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    // 通过内容删除单个数据
    public void deleteOne(String content) {
        try {
            searches.delete(queryForContent(content));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 删除所有数据

    public void deleteAll() {
        try {
            searches.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
