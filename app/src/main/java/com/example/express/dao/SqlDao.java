package com.example.express.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqlDao {

    // 创建数据库
    private SqlHelper sqlHelper;

    // 通知栏信息表
    private String t_notify = "t_notify";


    //单例模式
    //不能让每一个类都能new一个  那样就不是同一个对象了 所以首先构造函数要私有化    以上下文作为参数
    private SqlDao(Context ctx) {

        //由于数据库只需要调用一次，所以在单例中建出来
        sqlHelper = new SqlHelper(ctx, "t_notify.db", null, 1);
    }

    //public static 为静态类型  要调用就要有一个静态的变量    为私有的
    private static SqlDao sqlDao;


    //既然BlackDao类是私有的  那么别的类就不能够调用    那么就要提供一个public static（公共的  共享的）的方法
    //要加上一个synchronized（同步的）
    //如果同时有好多线程 同时去调用getInstance()方法  就可能会出现一些创建（new）多个BlackDao的现象  所以要加上synchronized
    public static synchronized SqlDao getInstance(Context ctx) {

        //就可以判断  如果为空 就创建一个， 如果不为空就还用原来的  这样整个应用程序中就只能获的一个实例
        if (sqlDao == null) {
            sqlDao = new SqlDao(ctx);

        }
        return sqlDao;
    }

    //常用方法  增删改查

    /**
     * 添加通知信息记录至数据库
     *
     * @param text
     */
    public void addNotify(String text) {

        //获得一个可写的数据库的一个引用
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("text", text);// KEY 是列名，vlaue 是该列的值

        // 参数一：表名，参数三，是插入的内容
        // 参数二：只要能保存 values中是有内容的，第二个参数可以忽略
        db.insert(t_notify, null, values);

    }

    /**
     * 查询通知信息
     */
    public String[] pageNotify() {

        SQLiteDatabase db = sqlHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from t_notify", null);

        List<String> list = new ArrayList<>();

        cursor.moveToFirst();
        do {
            int i = cursor.getColumnIndex("text");
            list.add(cursor.getString(i));
        } while (cursor.moveToNext());

        return (String[]) list.toArray(new String[list.size()]);
    }


}
