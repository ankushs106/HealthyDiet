package com.example.healthyme_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

public class recipes extends AppCompatActivity {

    EditText e;
    ArrayList<String> urldata = new ArrayList<>();
    ArrayList<String> labeldata = new ArrayList<>();
    ArrayList<String> dlabeldataa = new ArrayList<>();
    ArrayList<String> imgdataa = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        urldata.clear();
        labeldata.clear();
        dlabeldataa.clear();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urldata.clear();
                labeldata.clear();
                dlabeldataa.clear();
                e=(EditText)findViewById(R.id.ingre);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                // cancelling all requests about this search if in queue
                queue.cancelAll("");

                // first StringRequest: getting items searched...txtSearch.getText().toString()
                StringRequest stringRequest = searchNameStringRequest(e.getText().toString());
                stringRequest.setTag("TAG_SEARCH_NAME");
                Log.e("", String.valueOf(stringRequest));
                // executing the request (adding to queue)
                queue.add(stringRequest);

            }
        });
    }
    private StringRequest searchNameStringRequest(String nameSearch) {

        String url = "https://api.edamam.com/search?q="+nameSearch+"&app_id=a596dba2&app_key=86bea83ee396d957017d074ee096137e&from=0&to=20";
        Log.e("", url);

        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    // 3rd param - method onResponse lays the code procedure of success return
                    // SUCCESS
                    public void onResponse(String response) {
                        // try/catch block for returned JSON data
                        // see API's documentation for returned format
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONArray a = result.getJSONArray("hits");
                            String j=null,k=null,l=null;
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject b=a.getJSONObject(i);

                            JSONObject label,url,dlabels,proteins;
                            for (int i1 = 0; i1 < 1; i1++) {
                                JSONObject c = b.getJSONObject("recipe");
                                j = c.getString("url");
                                k = c.getString("label");
                                String im = c.getString("image");
                                l = c.getString("dietLabels");

                                Log.e("item", j);
                                Log.e("item", k);
                                Log.e("item", l);
                                urldata.add(j);
                                labeldata.add(k);
                                imgdataa.add(im);
                                dlabeldataa.add(l);

                            }
                            }
                            Intent intent = new Intent(recipes.this, ShowRecipes.class);
                            intent.putExtra("url", urldata);
                            intent.putExtra("label", labeldata);
                            intent.putExtra("dlabel", dlabeldataa);
                            intent.putExtra("img", imgdataa);
                            startActivity(intent);
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
                        Toast.makeText(recipes.this, "Food source is not responding", Toast.LENGTH_LONG).show();
                    }
                });

    }



}