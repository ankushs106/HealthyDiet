package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CaloriesDisplay extends Activity implements AdapterView.OnItemSelectedListener {
    TextView tbmi,tcal,bmi_result;
    float bmr,calories = 0;
    Button btn;
    Spinner s;
    SharedPreferences sp1;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_display);

        tbmi=(TextView)findViewById(R.id.textView2);
        tcal=(TextView)findViewById(R.id.textView5);
        bmi_result=(TextView)findViewById(R.id.textView6);
        s=(Spinner)findViewById(R.id.spinner2);
        btn=(Button)findViewById(R.id.button3);

        SharedPreferences sp=this.getSharedPreferences("preferences",MODE_PRIVATE);
        String userid=sp.getString("userid","");


        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("name");
        float bmi=bundle.getFloat("bmi", (int) 0.0);
        int age=bundle.getInt("age",0);
        float weight=bundle.getFloat("weight", (float) 0.0);
        float height= (float) (bundle.getFloat("height", (float) 0.0));
        String gender=bundle.getString("gender");
        String activity=bundle.getString("activity");


        if(gender.equals("Female"))
        {
            bmr= (float) ((10*weight)+(6.25*height)-(5*age)-161);
        }
        else{
            bmr= (float) ((10*weight)+(6.25*height)-(5*age)+5);
        }

        if(activity.equals("Sedentary (little or no exercise)"))
            calories= (float) (1.2*bmr);
        else
        if(activity.equals("Lightly active (light exercise/sports 1-3 days/week)"))
            calories= (float) (1.375*bmr);
          else if(activity.equals("Moderately active (moderate exercise/sports 3-5 days/week)"))
            calories= (float) (1.55*bmr);
          else if(activity.equals("Very active (hard exercise/sports 6-7 days a week)"))
            calories= (float) (1.725*bmr);
          else if(activity.equals("If you are extra active (very hard exercise/sports & a physical job) "))
            calories= (float) (1.9*bmr);



        tbmi.setText(String.valueOf(bmi));

        String bmiLabel;
        if (Float.compare(bmi, 2f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmi_result.setText(bmiLabel);

        s.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Maintain weight");
        categories.add("Lose weight");
        categories.add("Gain weight");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s.setAdapter(dataAdapter);


       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Database db= new Database(getApplicationContext());
                Log.e("aaaaaa", String.valueOf(age));
                Log.e("aaaaaa", String.valueOf(weight));
                Log.e("aaaaaa", String.valueOf(height));
                Log.e("aaaaaa", String.valueOf(calories));

                if(db.InsertData(age,weight,height,gender,bmi,calories,userid)) {
                    Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_LONG).show();
                    /*sp1=getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
                    editor=sp1.edit();
                    editor.putString("userid",userid);
                    editor.putBoolean("logged",true);*/
                    Intent i=new Intent(CaloriesDisplay.this,Dashboard2.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"Try adding data again",Toast.LENGTH_LONG).show();

            }
        });




    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String choice = parent.getItemAtPosition(position).toString();
        Log.e("asdfghj",choice);
        if(choice.equals("Lose weight"))
        {
            calories-=500;
            tcal.setText(String.valueOf(calories));

        }
        else if(choice.equals("Gain weight")){
            calories+=500;
            tcal.setText(String.valueOf(calories));
        }
        else if(choice.equals("Maintain weight"))
            tcal.setText(String.valueOf(calories));

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + choice, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
