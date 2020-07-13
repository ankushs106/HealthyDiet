package com.example.healthyme_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPlans extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tplan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plans);

        tplan=(TextView)findViewById(R.id.textView10);
        SharedPreferences sp=this.getSharedPreferences("preferences",MODE_PRIVATE);
        String userid=sp.getString("userid","");

        Database db1=new Database(getApplicationContext());
        SQLiteDatabase db=db1.getReadableDatabase();
        Cursor cursor=db.rawQuery("select plan_name from registered_users where mob_no="+userid,null);

        if(cursor.getCount()>0)
            while(cursor.moveToNext())
                tplan.setText("Your Plan: "+cursor.getString(0));
        cursor.close();

        Bundle bundle = getIntent().getExtras();

        ArrayList<String> food = (ArrayList<String>) getIntent().getSerializableExtra("food");
        ArrayList<Integer> pro = (ArrayList<Integer>) getIntent().getSerializableExtra("pro");
        ArrayList<Integer> fat = (ArrayList<Integer>) getIntent().getSerializableExtra("fat");
        ArrayList<Integer> carbs = (ArrayList<Integer>) getIntent().getSerializableExtra("carbs");
        ArrayList<Integer> calories = (ArrayList<Integer>) getIntent().getSerializableExtra("calories");

        String plan=bundle.getString("plan");
        ArrayList<PlanData> data = new ArrayList<PlanData>();
        for (int i=0;i<food.size();i++) {
            data.add(new PlanData(food.get(i),calories.get(i), fat.get(i), carbs.get(i), pro.get(i)));
            Log.e("", String.valueOf(Integer.valueOf(calories.get(i))));

        } Log.e("data", String.valueOf(data));


        if (data.size() > 0) {
            Log.e("","i am here");
            recyclerView = (RecyclerView) findViewById(R.id.rec);
            ShowPlanAdapter adapter = new ShowPlanAdapter(data);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            //recyclerView.setHasFixedSize(true);
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }

        findViewById(R.id.button22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent i=new Intent(MyPlans.this,Plans.class);
                    startActivity(i);


            }
        });
    }




}