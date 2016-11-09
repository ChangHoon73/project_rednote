package com.example.hoon.t20161019_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hoon on 2016-10-19.
 */

public class DBHelper extends SQLiteOpenHelper {


    //    public DBHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, "addr.db", null , 1 );
//    }
    public DBHelper(Context context) {
        super(context, "addr.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table telbook(" +
                "    _id integer primary key autoincrement," +
                "    _name text not null," +
                "    _tel  text not null" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists telbook");
        onCreate(db);
    }
}
