package com.example.healthyme_app;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class User_Progress extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user__progress);

            SharedPreferences sp=this.getSharedPreferences("preferences",MODE_PRIVATE);
            String userid=sp.getString("userid","");

            Database db1=new Database(getApplicationContext());
            SQLiteDatabase db=db1.getReadableDatabase();
            UserProgressData[] myListData = new UserProgressData[0];
            RecyclerView recyclerView;
           // Cursor cursor=db.rawQuery("select * from user_consumption where userid="+userid,null);

            //ArrayList<UserProgressData> data = new ArrayList<>();


            ArrayList<UserProgressData> dataa = db1.data(userid);


            if (dataa.size() > 0) {
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                UserProgressAdapter adapter = new UserProgressAdapter(dataa);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                //recyclerView.setHasFixedSize(true);
                // recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
            }
           /*
            if(cursor.getCount()>0)
                while(cursor.moveToNext())
                {

                     myListData = new UserProgressData[] {
                             new UserProgressData(cursor.getInt(3), cursor.getString(2)),

                    };*/


           


        }

}