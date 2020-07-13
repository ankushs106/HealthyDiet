package com.example.healthyme_app;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Progress extends AppCompatActivity {
    TextView tcal,tpro,tfat,tcarb,tdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        tcal=(TextView)findViewById(R.id.cal);
        tpro=(TextView)findViewById(R.id.protein);
        tfat=(TextView)findViewById(R.id.fat);
        tcarb=(TextView)findViewById(R.id.carbb);
        tdate=(TextView)findViewById(R.id.date);

        SharedPreferences sp=this.getSharedPreferences("preferences",MODE_PRIVATE);
        String userid=sp.getString("userid","");

        Database db1=new Database(getApplicationContext());
        SQLiteDatabase db=db1.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user_consumption where userid="+userid,null);

        if(cursor.getCount()>0)
            while(cursor.moveToNext())
            {

                tcal.setText(String.valueOf(cursor.getInt(3)));
                //tpro.setText(String.valueOf(cursor.getString()));
                //tfat.setText(String.valueOf(cursor.getString()));
                tdate.setText(String.valueOf(cursor.getString(2)));
                //tpro.setText(String.valueOf(cursor.getString()));
            }
        cursor.close();
    }
}