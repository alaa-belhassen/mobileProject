package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class deliverydb  extends SQLiteOpenHelper {
    public deliverydb( Context context) {
        super(context,"delivery.db",null,1);
    }
//inserting delivery.

    public boolean insertUser( String usercode, String vehiculeT , double receipt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usercode",usercode);
        contentValues.put("vehicule",vehiculeT);
        contentValues.put("receipt",receipt);
        long result = db.insert("delivery", null , contentValues);
        if ( result == -1 ){
            return false ;
        }else {
            return true;
        }
    }
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from delivery ", null);
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table delivery ( deliverycode INTEGER primary key autoincrement , usercode Text , vehicule Text , receipt REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users ");
    }
}
