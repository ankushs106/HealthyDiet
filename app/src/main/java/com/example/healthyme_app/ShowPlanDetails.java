package com.example.healthyme_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowPlanDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    String plan;
    String cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan_details);

        Bundle bundle = getIntent().getExtras();

        ArrayList<String> food = (ArrayList<String>) getIntent().getSerializableExtra("food");
        ArrayList<Integer> pro = (ArrayList<Integer>) getIntent().getSerializableExtra("pro");
        ArrayList<Integer> fat = (ArrayList<Integer>) getIntent().getSerializableExtra("fat");
        ArrayList<Integer> carbs = (ArrayList<Integer>) getIntent().getSerializableExtra("carbs");
        ArrayList<Integer> calories = (ArrayList<Integer>) getIntent().getSerializableExtra("calories");

        plan=bundle.getString("plan");
        cal=bundle.getString("cal");
        Log.e("plannn",plan);
        Log.e("received", String.valueOf(cal));

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

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db=new Database(getApplicationContext());
                SharedPreferences sp=getSharedPreferences("preferences",MODE_PRIVATE);
                String userid=sp.getString("userid","");


                if(db.updatePlan(plan,userid,cal)==1){
                    Toast.makeText(getApplicationContext(), "Plan Updated", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(ShowPlanDetails.this,Dashboard2.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();


            }
        });
    }




}