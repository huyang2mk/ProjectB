package com.lanou.yhz.article.grouparticle_b.search.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/****************************    (1) 建表    ****************************************/

/**
 * 用户表
 * 定义实体类User对象的数据库表为tb_user
 *
 * @DatabaseTable(tableName = "tb_user")
 * <p>
 * 让某一列属性作为表中的自增id项  每次插入新对象时不需要加入的属性
 * 该属性作为表的主键 具有唯一性
 * @DatabaseField(generatedId = true)
 * <p>
 * 让某一属性在数据库中对应的列名为tb_names
 * @DatabaseField(columnName = "tb_name")
 **/

@DatabaseTable(tableName = "tb_search")
public class Search {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "tb_content")
    private String content;
    @DatabaseField(columnName ="tb_time" )
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
