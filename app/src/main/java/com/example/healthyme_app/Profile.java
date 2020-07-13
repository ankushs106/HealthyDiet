package com.example.healthyme_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView tage,tname,temail,tweight,theight,tgender,tmob,tcalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Profile.this,CheckBMI.class);
                startActivity(i);
            }
        });
        tage=(TextView)findViewById(R.id.age);
        tname=(TextView)findViewById(R.id.name);
        temail=(TextView)findViewById(R.id.email);
        tweight=(TextView)findViewById(R.id.weight);
        theight=(TextView)findViewById(R.id.height);
        tgender=(TextView)findViewById(R.id.gender);
        tmob=(TextView)findViewById(R.id.mob);
        tcalories=(TextView)findViewById(R.id.calories);

        SharedPreferences sp=this.getSharedPreferences("preferences",MODE_PRIVATE);
        String userid=sp.getString("userid","");

        Database db1=new Database(getApplicationContext());
        SQLiteDatabase db=db1.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from registered_users where mob_no="+userid,null);

        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                tage.setText(String.valueOf(cursor.getInt(7)));
                tgender.setText(cursor.getString(4));
                tname.setText(cursor.getString(1));
                tweight.setText(String.valueOf(cursor.getInt(6)));
                theight.setText(String.valueOf(cursor.getInt(5)));
                temail.setText(cursor.getString(3));
                tcalories.setText(String.valueOf(cursor.getInt(9)));
                tmob.setText(cursor.getString(2));
            }
            cursor.close();
        }
       else
           Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show();

    }


}