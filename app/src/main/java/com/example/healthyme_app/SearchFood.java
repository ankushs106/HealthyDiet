package com.example.healthyme_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchFood extends AppCompatActivity {
    ImageButton srch;
    public EditText e;
    TextView tcal;
    float k,l,m,j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        srch=(ImageButton)findViewById(R.id.srch);
               srch .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e=(EditText)findViewById(R.id.e);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                // cancelling all requests about this search if in queue
                queue.cancelAll("");

                // first StringRequest: getting items searched...txtSearch.getText().toString()
                StringRequest stringRequest = searchNameStringRequest(e.getText().toString());
                stringRequest.setTag("TAG_SEARCH_NAME");

                // executing the request (adding to queue)
                queue.add(stringRequest);

            }
        });

    }
    private StringRequest searchNameStringRequest(String nameSearch) {
        final String API = "&api_key=CUeVySx0Yd20CCukrGOhNWHx8K6AKBbKvU3M66Xv";
        final String NAME_SEARCH = "&q=";
        final String DATA_SOURCE = "&ds=Standard Reference";
        final String FOOD_GROUP = "&fg=";
        final String SORT = "&sort=r";
        final String MAX_ROWS = "&max=25";
        final String fdc = "&FDCId=72817";
        final String BEGINNING_ROW = "&offset=0";
        final String URL_PREFIX = "https://api.nal.usda.gov/ndb/search/?format=json";

        //String url = URL_PREFIX + API + NAME_SEARCH + nameSearch + DATA_SOURCE +fdc+ FOOD_GROUP + SORT + MAX_ROWS + BEGINNING_ROW;
        //String url="https://api.nal.usda.gov/fdc/v1/food/781086%20?nutrients=203&nutrients=204&nutrients=205&nutrients=206"+API;
        // String url="https://api.nal.usda.gov/fdc/v1/foods/search?query="+nameSearch+"&dataType=Branded&pageSize=25&pageNumber=2&sortBy=fdcId&sortOrder=asc&api_key=CUeVySx0Yd20CCukrGOhNWHx8K6AKBbKvU3M66Xv\n";
        //String url="https://api.nal.usda.gov/fdc/v1/foods/search?query=milk&dataType=Survey%20%28FNDDS%29&pageSize=25&pageNumber=2&sortBy=fdcId&sortOrder=asc&api_key=CUeVySx0Yd20CCukrGOhNWHx8K6AKBbKvU3M66Xv\n";
        String url = "https://api.edamam.com/api/nutrition-data?app_id=1edc0302&app_key=24162dad9e024b8ec4c0292b87177d2e&ingr=" + nameSearch+"";
        Log.e("", url);
        // 1st param => type of method (GET/PUT/POST/PATCH/etc)
        // 2nd param => complete url of the API
        // 3rd param => Response.Listener -> Success procedure
        // 4th param => Response.ErrorListener -> Error procedure

        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    // 3rd param - method onResponse lays the code procedure of success return
                    // SUCCESS
                    public void onResponse(String response) {
                        // try/catch block for returned JSON data
                        // see API's documentation for returned format
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONObject a = result.getJSONObject("totalNutrients");
                            JSONObject calories,fats,carbs,proteins;
                            for (int i = 0; i < 1; i++) {
                                calories = a.getJSONObject("ENERC_KCAL");
                                fats = a.getJSONObject("FAT");
                                carbs = a.getJSONObject("CHOCDF");
                                proteins = a.getJSONObject("PROCNT");
                                // for(int ii=0; i <=j.length(); ii++) {
                                 j = (float) calories.getDouble("quantity");
                                 k = (float) fats.getDouble("quantity");
                                 l = (float) carbs.getDouble("quantity");
                                 m = (float) proteins.getDouble("quantity");


                                int value = 0;
                                tcal = findViewById(R.id.textView9);
                                tcal.setText(String.valueOf(j));
                                Log.e("item", String.valueOf(j));
                                Log.e("item", String.valueOf(k));
                                Log.e("item", String.valueOf(l));
                                Log.e("item", String.valueOf(m));

                            }
                                final Database db= new Database(getApplicationContext());
                                SharedPreferences sp=getApplicationContext().getSharedPreferences("preferences",MODE_PRIVATE);
                                String userid=sp.getString("userid","");
                                if(db.AddCalories(userid, j,m,k,l)==1)
                                {
                                    Toast.makeText(getApplicationContext(),"Food added",Toast.LENGTH_LONG).show();
                                    Intent ii=new Intent(SearchFood.this,Dashboard2.class);
                                    startActivity(ii);
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"Try adding food again",Toast.LENGTH_LONG).show();

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
                        Toast.makeText(SearchFood.this, "Food source is not responding (USDA API)", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
