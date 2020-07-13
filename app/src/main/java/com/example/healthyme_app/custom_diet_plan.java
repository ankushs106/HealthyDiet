package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class custom_diet_plan extends Activity {

    EditText cal;
    Spinner s;
    Button btn;
    String calories;
    String foodtype;
    String plandata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_diet_plan);

        cal=(EditText)findViewById(R.id.ecall);
        s=(Spinner)findViewById(R.id.spinner) ;
        btn=(Button)findViewById(R.id.btn);

        Bundle bundle = getIntent().getExtras();

        plandata = bundle.getString("plandata");
        Log.e("dataaaaa",plandata);

         calories=cal.getText().toString();
        Log.e("calories here",calories);
        if(s.getSelectedItem().equals("Vegetarian"))
            foodtype=(String)s.getSelectedItem();
        else if(s.getSelectedItem().equals("Non-Vegetarian"))
            foodtype="chicken";
        else if(s.getSelectedItem().equals("Eggetarian"))
            foodtype="eggs";
        Log.e("dataaaaa", String.valueOf(calories));
        Log.e("foodtype",foodtype);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(custom_diet_plan.this,CustomDietPlan1.class);
                i.putExtra("calories", cal.getText().toString());
                i.putExtra("foodtype", foodtype);
                i.putExtra("plandata", plandata);
                startActivity(i);
            }
        });
    }
}