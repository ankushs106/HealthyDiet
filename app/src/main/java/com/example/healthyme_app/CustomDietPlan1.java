package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomDietPlan1 extends Activity {
    String plandata;
    CheckBox c3, c1, c2, c4;
    Button b;
    String d1,d2,d3,d4;
    ArrayList<String> food=new ArrayList<>();
    ArrayList<Integer> calories=new ArrayList<>();
    ArrayList<Integer> carbs=new ArrayList<>();
    ArrayList<Integer> fat=new ArrayList<>();
    ArrayList<Integer> pro=new ArrayList<>();
    String foodtype,cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_diet_plan1);

        c1=(CheckBox)findViewById(R.id.checkBox);
        c2=(CheckBox)findViewById(R.id.checkBox1);
        c3=(CheckBox)findViewById(R.id.checkBox2);
        c4=(CheckBox)findViewById(R.id.checkBox3);

        b=(Button)findViewById(R.id.button);

        Bundle bundle = getIntent().getExtras();

         plandata = bundle.getString("plandata");
         cal = bundle.getString("calories");
         foodtype = bundle.getString("foodtype");
         Log.e("ooooooooooo",""+cal);
        Log.e("ooooooooooo",""+foodtype);

        if (c1.isChecked()) {
            d1 = "gluten-free";
        }
        if (c2.isChecked()) {
            d2 = "dairy-free";
        }
        if (c3.isChecked()) {
            d3 = "sugar-Conscious";
        }
        if (c4.isChecked()) {
            d4 = "soy-free";
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                // cancelling all requests about this search if in queue
                queue.cancelAll("");

                // first StringRequest: getting items searched...txtSearch.getText().toString()
                StringRequest stringRequest = searchNameStringRequest(plandata);
                stringRequest.setTag("TAG_SEARCH_NAME");
                Log.e("url", String.valueOf(stringRequest));
                // executing the request (adding to queue)
                queue.add(stringRequest);
            }
        });
    }


    public StringRequest searchNameStringRequest(String plan) {

       // String url = "https://api.edamam.com/search?q="+plan+"&app_id=a596dba2&app_key=86bea83ee396d957017d074ee096137e&from=0&to=3&calories=59-72&health=alcohol-free&nutrients=%5BFE%5D&dietType=";
        String url="https://api.edamam.com/api/food-database/v2/parser?ingr="+foodtype+"&app_id=2590c23a&app_key=b9d1860d47c5a446fa688d8e8b3ee0fc&healthLabels="+d1+"&healthLabels="+d2+"&healthLabels="+d3+"&healthLabels="+d4+"&calories=100-"+cal+"&dietLabels="+plan+"&from=0&to=30";
        Log.e("", url);

        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    // 3rd param - method onResponse lays the code procedure of success return
                    // SUCCESS
                    public void onResponse(String response) {
                        // try/catch block for returned JSON data
                        // see API's documentation for returned format
                        try {
                            JSONObject label,url,dlabels,proteins;
                            JSONObject result = new JSONObject(response);
                            JSONArray a = result.getJSONArray("hints");
                            for (int iii = 0; iii < a.length(); iii++) {
                                JSONObject r = a.getJSONObject(iii);

                                for (int i = 0; i < 1; i++) {
                                    JSONObject c = r.getJSONObject("food");
                                    Log.e("", String.valueOf(c));
                                    for (int i2 = 0; i2 < 1; i2++) {
                                        String jj = c.getString("label");
                                        food.add(jj);
                                        JSONObject c1 = c.getJSONObject("nutrients");
                                        for (int i1 = 0; i1 < 1; i1++) {
                                            int j = c1.getInt("ENERC_KCAL");
                                            int k = c1.getInt("PROCNT");
                                            int l = c1.getInt("FAT");
                                            int m = c1.getInt("CHOCDF");

                                       Log.e("item", String.valueOf(jj));

                                        Log.e("item", String.valueOf(j));
                                        Log.e("item", String.valueOf(k));
                                        Log.e("item", String.valueOf(l));
                                        calories.add(j);
                                        pro.add(k);
                                        fat.add(l);
                                        carbs.add(m);
                                    }}
                                }
                            }
                            Intent i=new Intent(CustomDietPlan1.this,ShowPlanDetails.class);
                            i.putExtra("cal",cal);
                            i.putExtra("food", food);
                            i.putExtra("calories", calories);
                            i.putExtra("pro", pro);
                            i.putExtra("fat", fat);
                            i.putExtra("plan", plandata);
                            i.putExtra("carbs", carbs);
                            startActivity(i);
                           /*  Intent intent = new Intent(, ShowRecipes.class);
                            intent.putExtra("url", urldata);
                            intent.putExtra("label", labeldata);
                            intent.putExtra("dlabel", dlabeldataa);
                            startActivity(intent);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Log.e("ooo", e.getMessage());
                        }
                    } // public void onResponse(String response)
                }, // Response.Listener<String>()
                new Response.ErrorListener() {
                    // 4th param - method onErrorResponse lays the code procedure of error return
                    // ERROR

                    public void onErrorResponse(VolleyError error) {
                        // display a simple message on the screen
                        Log.e("", "Food source is not responding");
                    }
                });

    }
}