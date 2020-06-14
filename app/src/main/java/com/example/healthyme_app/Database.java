package com.example.healthyme_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "Diet_Database", null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table registered_users(_id INTEGER PRIMARY KEY AUTOINCREMENT,name char,mob_no string,email string,gender char ,heignt float,weight float,age integer,datetime default current_timestamp,password integer)";
        db.execSQL(sql);
    }
    public boolean check_users(String s2){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db.rawQuery("select * from registered_users where mob_no="+s2,null).getCount()>0)
            return true;
        else
            return false;

    }
    public boolean insert(String s, String s1,String s2,String s3) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            s3 = AESUtils.encrypt(s3);
            contentValues.put("name", s);
            contentValues.put("email", s1);
            contentValues.put("mob_no", s2);
            contentValues.put("password", s3);
            db.insert("registered_users", null, contentValues);
            return true;
    }


    public boolean login(String mob,String pass) throws Exception {
        Cursor cursor;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor=db.rawQuery("select password from registered_users where mob_no="+mob,null);
        String pass1 = null;
        Log.d(TAG, "pass1: "+pass1);

        if(cursor.moveToFirst())
            pass1=cursor.getString(0);
        pass = AESUtils.decrypt(pass1);
        cursor=db.rawQuery("select * from registered_users where password="+pass+" and mob_no="+mob,null);
        int count=cursor.getCount();
        if(cursor.moveToFirst())
            return true;
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dsql="drop table if exists registered_users";

        db.execSQL(dsql);
    }
}
