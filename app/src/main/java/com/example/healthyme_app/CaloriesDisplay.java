package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CaloriesDisplay extends Activity {
    TextView tbmi,tcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_display);

        tbmi=(TextView)findViewById(R.id.textView2);
        tcal=(TextView)findViewById(R.id.textView5);


        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("name");
        float bmi=bundle.getFloat("bmi", (float) 0.0);
        int age=bundle.getInt("age",0);
        float weight=bundle.getFloat("weight", (float) 0.0);
        float height= (float) ((bundle.getFloat("height", (float) 0.0))*30.48);
        String gender=bundle.getString("gender");
        String activity=bundle.getString("activity");
        float bmr,calories = 0;
        if(gender.equals("Female"))
        {
            bmr= (float) ((655.1+(9.6*weight)+(1.9*height))/4.7*age);
        }
        else{
            bmr= (float) ((66.5+(13.8*weight)+(5*height))/6.6*age);
        }

        if(activity.equals("Low Activity"))
            calories= (float) (1.2*bmr);
        else
        if(activity.equals("Moderate Activity"))
            calories= (float) (1.3*bmr);
          else if(activity.equals("High Activity"))
            calories= (float) (1.4*bmr);

          tbmi.setText(String.valueOf(bmi));
           tcal.setText(String.valueOf(calories));


        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CaloriesDisplay.this,Dashboard2.class);
                startActivity(i);
            }
        });


    }
}
