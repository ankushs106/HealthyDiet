package com.example.healthyme_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button brekfst, lunch, dinner;
    private RequestQueue queue;

    //variables
    static final float END_SCALE = 0.7f;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView menuIcon;
    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard2);
        /*.................hooks...............*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_drawer_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        navigationDrawer();


        final String key = "fb752dda3f984c43826465a4cb2fc818";
        final String secret = "18e4eed8c4d5401c8ca93b0669bb7893";

        final String query = "milk";


        brekfst = (findViewById(R.id.breakfast));
        lunch = (findViewById(R.id.lunch));
        dinner = (findViewById(R.id.dinner));
        final String url = "http://platform.fatsecret.com/rest/server.api";


        brekfst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                Listener listener = new Listener();

                Request req = new Request(key, secret, listener);

                //This response contains the list of food items at zeroth page for your query
                req.searchFoods(requestQueue, query, 1);
                //req.searchRecipes(requestQueue,query);
                //req.getFood(requestQueue,query);
            }
        });

    }


    private void navigationDrawer() {
        /*...............Navigation Drawe Menu......*/
        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.navd_home);
        navigationView.setNavigationItemSelectedListener(this);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

// Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i;
        int id = item.getItemId();
        switch (id) {

            case R.id.navd_home:
                i = new Intent(this, Dashboard2.class);
                startActivity(i);
                return true;

            case R.id.navd_googlefit:
                i = new Intent(this, GoogleFitTracker.class);
                startActivity(i);
                return true;

            case R.id.navd_logout:
                SharedPreferences sp = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                Boolean value = sp.getBoolean("logged", false);
                if (value) {

                    item.setTitle("Login");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("logged");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_LONG).show();
                    i = new Intent(Dashboard2.this, Dashboard2.class);
                    startActivity(i);
                } else {
                    item.setTitle("Logout");
                    i = new Intent(Dashboard2.this, login.class);
                    startActivity(i);
                }

            case R.id.navd_help:
                i = new Intent(Dashboard2.this, ChatBot.class);
                startActivity(i);
        }


        if (id == R.id.navigation_home) {
            i = new Intent(this, Dashboard2.class);
            startActivity(i);
        } else if (id == R.id.navigation_plan) {
            i = new Intent(this, GoogleFitTracker.class);
            startActivity(i);
        } else if (id == R.id.navigation_progress) {
            i = new Intent(this, GoogleFitTracker.class);
            startActivity(i);
        } else if (id == R.id.navigation_Recipe) {
            i = new Intent(this, GoogleFitTracker.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getResponse(String response) {
        try{
            JSONObject jsonObject = null;
            try{jsonObject = new JSONObject(response);}catch(JSONException e){e.printStackTrace();}
            final JSONObject jsonObjectResult = jsonObject.optJSONObject("result");
            final JSONObject jsonObjectFoods = jsonObjectResult.optJSONObject("foods");
            final JSONArray jsonArrayFood = jsonObjectFoods.optJSONArray("food");
            if(jsonArrayFood!=null && jsonArrayFood.length()>0){
                for (int i = 0 ;i < jsonArrayFood.length();i++){
                    final JSONObject jsonObjectFood = jsonArrayFood.optJSONObject(i);
                    if(jsonObjectFood!=null && jsonObjectFood.length()>0){
                        Log.e("",jsonObjectFood.getString("food_description"));
                    }
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}