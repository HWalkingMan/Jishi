package com.jishi.jishi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * @author WM
 * @description
 * @date 2020/5/13 11:27
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "jishiac.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table signon (accountid integer  primary key autoincrement,phonenum text not null unique, password text not null);");
        db.execSQL("create table account (accountid integer primary key autoincrement,nickname text default \"unnamed\",signature text default \"nothing said\",img64 blob);");
        db.execSQL("insert into signon values(10001,\"13212341234\",\"********\");");
        db.execSQL("insert into account values (10001,\"unname\",\"nothing to said\",null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
