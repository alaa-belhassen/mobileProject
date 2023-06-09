package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class db extends SQLiteOpenHelper {

    public db( Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create Table users ( username Text primary key , password Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {
        mydb.execSQL("drop Table if exists users ");
    }

    public boolean insertUser(String username , String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = mydb.insert("users", null , contentValues);
        if ( result == -1 ){
            return false ;
        }else {
            return true;
        }
    }

    public boolean checkUser ( String username){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where username == ?",new String[] {username});
        if ( cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkLogin ( String username,String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where username == ? and password = ?",new String[] {username,password});
        if ( cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
