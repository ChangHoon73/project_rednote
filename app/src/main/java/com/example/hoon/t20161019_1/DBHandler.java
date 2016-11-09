package com.example.hoon.t20161019_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Hoon on 2016-10-19.
 */

public class DBHandler {
    DBHelper helper;
    SQLiteDatabase db;

    private DBHandler(Context ctx) {
         helper = new DBHelper(ctx);
         db = helper.getWritableDatabase();
    }

    public static DBHandler open(Context ctx){
        DBHandler handler = new DBHandler(ctx);
        return handler;
    }

    public long insert(String name, String tel){
        ContentValues cv = new ContentValues();
        cv.put("_name",name);
        cv.put("_tel",tel);
        return db.insert("telbook", null, cv);
    }

    public Cursor getSelectAll(){
        Cursor cursor = db.rawQuery( "select _id, _name, _tel from telbook order by _id desc ", null);
        if( cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete(int _id){
        db.execSQL("delete from telbook where _id='"+_id+"' ");

    }

    public void update(int _id, String _name, String _tel){
        db.execSQL("update telbook set _name='"+_name+"' , _tel='"+_tel+"' where _id= '"+_id+"' ");
        db.execSQL(String.format(" update telbook set _name='%s' , _tel='%s' where _id='%d' ",
                                                           _name,       _tel,          _id ));
    }
}
