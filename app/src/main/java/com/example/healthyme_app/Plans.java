package com.example.healthyme_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyme_app.HelperClasses.BalanceAdapter.BalancedAdapter;

import java.util.ArrayList;

public class Plans extends AppCompatActivity {
    RecyclerView recyclerView,balanced,high_fiber,high_protein,low_carb,low_fat,low_sodium;
    RecyclerView.Adapter a1,a2,a3,a4,a5,a6;
    ArrayList<String> data=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);


        ArrayList<Integer > cal = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> des = new ArrayList<>();
        ArrayList<Integer> image = new ArrayList<>();


        cal.add(100);
        cal.add(100);
        cal.add(100);
        cal.add(100);
        cal.add(100);

        title.add("Balanced Diet");
        title.add("High Fiber");
        title.add("High Protein");
        title.add("Low Carb");
        title.add("Low Fat");

        des.add("Enjoy Balanced  Diet");
        des.add("Stay healthy with high fiber");
        des.add("More protein to improve yoyr health");
        des.add("Low carb diet helps in weight loss");
        des.add("Way to limit your fat consumption");

        image.add(R.drawable.lowcalorie2);
        image.add(1);
        image.add(0);
        image.add(0);
        image.add(0);


        ArrayList<PlanData> data = new ArrayList<PlanData>();
        for (int i=0;i<cal.size();i++)
            data.add(new PlanData(cal.get(i),title.get(i),R.drawable.lowcalorie4,des.get(i)));
        Log.e("data", String.valueOf(data));

        if (data.size() > 0) {
            recyclerView = (RecyclerView) findViewById(R.id.rec);
            BalancedAdapter adapter = new BalancedAdapter(data);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            //recyclerView.setHasFixedSize(true);
            // recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }

/*
        //hooks
        balanced = findViewById(R.id.balanced);
        balancedRecycler();
        high_fiber = findViewById(R.id.high_fiber);
        highfiberRecycler();
        high_protein = findViewById(R.id.high_protein);
        highproteinRecycler();
        low_carb = findViewById(R.id.low_carb);
        carbRecycler();
        low_fat = findViewById(R.id.low_fat);
        fatRecycler();
        low_sodium = findViewById(R.id.low_sodium);
        //lowsodiumRecycler();

    }
    private void balancedRecycler() {
        //data.add("ok");
        balanced.setHasFixedSize(true);
        balanced.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PlanData> bal = new ArrayList<>();
        bal.add(new PlanData(100, "Enjoy Balanced  Diet",R.drawable.lowcalorie4, "Balanced Diet"));
        a1 = new BalancedAdapter(bal);
        balanced.setAdapter(a1);
    }

    private void carbRecycler() {
        low_carb.setHasFixedSize(true);
        low_carb.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PlanData> carbb = new ArrayList<>();
        carbb.add(new PlanData(100, "Enjoy Balanced  Diet",R.drawable.weight1, "Low carbs"));

        a2 = new BalancedAdapter(carbb);
        low_carb.setAdapter(a2);

    }
    private void highfiberRecycler() {
        high_fiber.setHasFixedSize(true);
        high_fiber.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PlanData> fibr = new ArrayList<>();
        fibr.add(new PlanData(100, "Enjoy Balanced  Diet",R.drawable.weight2, "Low carbs"));
        a3 = new BalancedAdapter(fibr);
        high_fiber.setAdapter(a3);

    }
    private void highproteinRecycler() {

        high_protein.setHasFixedSize(true);
        high_protein.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PlanData> pro = new ArrayList<>();
        pro.add(new PlanData(100, "Enjoy Balanced  Diet",R.drawable.lowcalorie4, "high protein"));
        a4 = new BalancedAdapter(pro);
        high_protein.setAdapter(a4);

    }
    private void lowsodiumRecycler() {

        low_sodium.setHasFixedSize(true);
        low_sodium.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PlanData> sodium = new ArrayList<>();
        sodium.add(new PlanData(100, "Enjoy Balanced  Diet",R.drawable.lowcalorie2, "Low sodium"));
        a5 = new BalancedAdapter(sodium);
        low_sodium.setAdapter(a5);

    }
    private void fatRecycler() {
        low_fat.setHasFixedSize(true);
        low_fat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PlanData> fats = new ArrayList<>();
        fats.add(new PlanData(100, "Enjoy Balanced  Diet",R.drawable.lowcalorie1, "Low fat"));
        a6 = new BalancedAdapter(fats);
        low_fat.setAdapter(a6);

    }*/

    }
}