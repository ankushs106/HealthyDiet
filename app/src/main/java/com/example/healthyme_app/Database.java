package com.example.healthyme_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "Diet_Database", null, 12);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table registered_users(_id INTEGER PRIMARY KEY AUTOINCREMENT,name char,mob_no string,email string,gender char ,height float,weight float,age integer,BMI float,calories float,datetime default current_timestamp,password integer)";
        String sql1 = "create table feedback(_id INTEGER PRIMARY KEY AUTOINCREMENT,userid string,topic string,message string,datetime default current_timestamp,FOREIGN KEY(userid) REFERENCES registered_users(mob_no))";
        String sql2="create table user_consumption(_id INTEGER PRIMARY KEY AUTOINCREMENT,userid string,date default current_date, calories_consumed int,FOREIGN KEY(userid) REFERENCES registered_users(mob_no))";
        String isql1="insert into user_consumption(userid,date,calories_consumed) values('8552921269','2020-06-19',245)";
        String isql2="insert into user_consumption(userid,date,calories_consumed) values('8552921269','2020-06-20',245)";

        db.execSQL(sql2);
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(isql1);
        db.execSQL(isql2);

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
            if(db.insert("registered_users", null, contentValues)==-1)
                return false;
            else
                return true;
    }


    public boolean login(String mob,String pass) throws Exception {
        Cursor cursor;
        String decrytedpass="";
        Log.d("password", "pass1: "+mob);

        SQLiteDatabase db = this.getReadableDatabase();
        cursor=db.rawQuery("select password from registered_users where mob_no="+mob,null);
        String pass1 = null;

        if(cursor.moveToFirst())
            pass1=cursor.getString(0);

        decrytedpass = AESUtils.decrypt(pass1);
        Log.d("password", "pass1: "+pass);

        if(pass==decrytedpass)
        cursor=db.rawQuery("select * from registered_users where mob_no="+mob,null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public boolean feedback(String userid,String topic,String msg)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userid", userid);
        contentValues.put("topic", topic);
        contentValues.put("message", msg);
        if(db.insert("feedback", null, contentValues)==-1)
            return false;
        else
            return true;
    }

    public boolean InsertData(int age,float weight,float height,String gender,float bmi,float calories,String userid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("age", age);
        contentValues.put("weight", weight);
        contentValues.put("height", height);
        contentValues.put("gender", gender);
        contentValues.put("bmi", bmi);
        contentValues.put("calories", calories);


        if(db.update("registered_users", contentValues,"mob_no="+userid,null)==0)
            return false;
        else
            return true;


    }

    ArrayList<UserProgressData> data(String userid) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user_consumption where userid="+userid,null);
        ArrayList<UserProgressData> data1 = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int cal= cursor.getInt(3);
                String date=cursor.getString(2);
                data1.add(new UserProgressData(cal,date));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return data1;
    }

    public int AddCalories(String userid, int cal){
        SQLiteDatabase db=this.getReadableDatabase();
        SQLiteDatabase wdb=this.getWritableDatabase();
        Date today=new Date();
        int count=0;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String datee=format.format(today);
        Log.e("",datee);
        Cursor cursor=db.rawQuery("select calories_consumed,date from user_consumption where userid="+userid,null);
        int check=0;
       try {
           while (cursor.moveToNext())
           {
               Log.e("date sql",cursor.getString(1));
               if(datee.equals(cursor.getString(1)))
            {
                check=1;
               cal += cursor.getInt(0);
               ContentValues contentValues = new ContentValues();
               contentValues.put("calories_consumed", cal);
               try {
                   String[] args = new String[]{datee};
                   if (wdb.update("user_consumption", contentValues, "userid=" + userid + " and date= ?" , args) == 0)
                       count = 0;
                   else
                       count = 1;
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
               else
                   Log.e("","not found");
           }
           if(check==0) {
               ContentValues contentValues = new ContentValues();
               contentValues.put("calories_consumed", cal);
               contentValues.put("userid", userid);
               if(db.insert("user_consumption", null, contentValues)==-1)
                   count = 0;
               else
                   count = 1;
           }


       }
       catch (Exception e)
       {
           e.printStackTrace();;
       }
       return count;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dsql="drop table if exists registered_users";
        String dsql1="drop table if exists feedback";
        String dsql2="drop table if exists user_consumption";

        db.execSQL(dsql1);
        db.execSQL(dsql);
        db.execSQL(dsql2);
        onCreate(db);
    }
}
