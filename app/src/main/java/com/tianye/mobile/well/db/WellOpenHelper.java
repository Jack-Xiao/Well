package com.tianye.mobile.well.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2015/3/20.
 */
public class WellOpenHelper extends SQLiteOpenHelper{
    public static final String CREATE_USER="create table Province(" +
            "id integer primary key autoincrement, "
            +"name text, "
            +"province_code text)";
    public static final String CREATE_="create table Favorite( " +
            "id integer primary key autoincrement, "
            +"code text,"
            +"name text)";


    //public static final String

    /**
     *
     *
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */



    public WellOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public WellOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
