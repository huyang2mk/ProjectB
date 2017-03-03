package com.lanou.yhz.article.grouparticle_b.search.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by dllo on 17/2/7.
 */

/****
 * (2) 管理数据库的类
 *****/
public class ORMliteHelper extends OrmLiteSqliteOpenHelper {

    //数据库的名称
    public static final String ORMLITE_NAME = "giftsearch.db";
    // 数据库版本
    private static final int DATABASE_VERSION = 1;
    // 实体类对应的表的dao的对象
    private Dao<Search, Integer> searchORM;

    public ORMliteHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    /**
     * 第一次创建数据库时会回调的方法
     *
     * @param sqLiteDatabase   数据库对象
     * @param connectionSource 请无视
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(searchORM);//创建用户表
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 数据里版本发生变化时会回调的的方法,一般指的是版本升级时会回调的方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(searchORM, true);//删除表
            onCreate(sqLiteDatabase, connectionSource);// 重新调用数据库创建过程
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Dao<Search, Integer> getSearchORM() {
        try {
            searchORM = getDao(Search.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchORM;
    }
    public ORMliteHelper(Context context) {
        super(context, ORMLITE_NAME, null, DATABASE_VERSION);
    }

}
