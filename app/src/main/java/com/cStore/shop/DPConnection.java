package com.cStore.shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DPConnection extends SQLiteOpenHelper {
    public final int version=1;
    public final String name = "shop";
    public DPConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dp) {
     dp.execSQL("create table if not exists users (id integer primary key,name text,picture text,password text,email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dp, int i, int i1) {
     dp.execSQL("drop table if exists users");
     onCreate(dp);
    }
    public void insertRow(String name,String picture,String password,String email){
        SQLiteDatabase dp=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("picture",picture);
        contentValues.put("password",password);
        contentValues.put("email",email);
        dp.insert("users",null,contentValues);
    }
    public ArrayList getData(){
        ArrayList arrayList=new ArrayList();
        SQLiteDatabase dp=this.getReadableDatabase();
        Cursor res=dp.rawQuery("select * from users",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            arrayList.add(res.getString(res.getColumnIndex("name")));
            arrayList.add(res.getString(res.getColumnIndex("picture")));
            arrayList.add(res.getString(res.getColumnIndex("password")));
            arrayList.add(res.getString(res.getColumnIndex("email")));
            res.moveToNext();
        }
        return arrayList;
    }
}
