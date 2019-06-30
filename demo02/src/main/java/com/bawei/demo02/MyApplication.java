package com.bawei.demo02;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.demo02.greendao.DaoMaster;
import com.bawei.demo02.greendao.DaoSession;

/*
 *@Auther:陈浩
 *@Date: 2019/6/23
 *@Time:21:00
 *@Description:${DESCRIPTION}
 * */public class MyApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"db-demo02",null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoseession(){
        return daoSession;
    }
}
