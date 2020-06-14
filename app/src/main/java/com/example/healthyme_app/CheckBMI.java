package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CheckBMI extends Activity {
    EditText eage,eweight,eheight;
    Spinner s1,s2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_b_m_i);

        eage=(EditText)findViewById(R.id.age);
        eweight=(EditText)findViewById(R.id.weight);
        eheight=(EditText)findViewById(R.id.height);

        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner3);
        b1=(Button)findViewById(R.id.submit);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age=Integer.parseInt(eage.getText().toString());
                Log.d(TAG, "age: "+age);
                int weight=Integer.parseInt(eweight.getText().toString());
                int height=Integer.parseInt(eheight.getText().toString());

                double bmi = ( (double) weight / ( (double) height * (double) height)) ;

                Intent i=new Intent(CheckBMI.this,CaloriesDisplay.class);
                Bundle bundle = new Bundle();
                bundle.putInt("age",age);
                bundle.putFloat("bmi", (float) bmi);
                bundle.putFloat("weight",(float)weight);
                bundle.putFloat("height",(float)height);
                bundle.putString("gender", (String) s2.getSelectedItem());
                bundle.putString("activity", (String) s1.getSelectedItem());
                i.putExtras(bundle);
                startActivity(i);

            }
        });

    }
}
