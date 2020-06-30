package com.example.healthyme_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import com.android.volley.Request;

//import com.fatsecret.platform.services.android.Request;

public class Dashboard2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button brekfst,lunch,dinner,snack;
    private RequestQueue queue;
    ProgressBar pb;
    int c=0,cal=0;
    String userid,data,url;
    TextView prot,fats,carb, cal_consumed,cal_goal,bcal,dcal,scal,lcal,progress_goal;

    ArrayList<String> food=new ArrayList<>();
    ArrayList<Integer> calories=new ArrayList<>();
    ArrayList<Integer> carbs=new ArrayList<>();
    ArrayList<Integer> fat=new ArrayList<>();
    ArrayList<Integer> pro=new ArrayList<>();
    //variables
    static final float END_SCALE=0.7f;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView menuIcon;
    LinearLayout contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard2);
        /*.................hooks...............*/

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_drawer_view);
        menuIcon=findViewById(R.id.menu_icon);
        contentView=findViewById(R.id.content);
        navigationDrawer();


        brekfst=(findViewById(R.id.breakfast));
        lunch=(findViewById(R.id.lunch));
        dinner=(findViewById(R.id.dinner));
        snack=(findViewById(R.id.snack));
        pb=(ProgressBar) findViewById(R.id.pbar);
        progress_goal=(TextView)findViewById(R.id.textView16);


        cal_consumed=(TextView) (findViewById(R.id.cal));
        cal_goal=(TextView)(findViewById(R.id.cal2));
        prot=(TextView)(findViewById(R.id.protein));
        carb=(TextView)(findViewById(R.id.carbb));
        fats=(TextView)(findViewById(R.id.fat));
        bcal=(TextView)findViewById(R.id.B_cal_recommendations);
        dcal=(TextView)findViewById(R.id.d_cal_recommendations);
        lcal=(TextView)findViewById(R.id.l_cal_recommendations);
        scal=(TextView)findViewById(R.id.s_cal_recommendations);

        SharedPreferences sp=this.getSharedPreferences("preferences",MODE_PRIVATE);
         userid=sp.getString("userid","");

        Database db1=new Database(getApplicationContext());
        SQLiteDatabase db=db1.getReadableDatabase();
        Date today=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String datee=format.format(today);
        Log.e("",datee);
        try {
            Cursor cursor1 = db.rawQuery("select calories,mob_no from registered_users where mob_no=" + userid, null);
            if (cursor1.getCount() > 0)
                while (cursor1.moveToNext()) {
                      c= (int) cursor1.getFloat(0);
                     int b=(30*c)/100;
                     int l=(30*c)/100;
                     int d=(20*c)/100;
                     int s=(20*c)/100;
                     Log.e("",String.valueOf(c));
                    Log.e("",String.valueOf(b));
                    Log.e("",String.valueOf(d));

                    bcal.setText(String.valueOf(b)+" kcal");
                     dcal.setText(String.valueOf(d)+" kcal");
                     lcal.setText(String.valueOf(l)+" kcal");
                     scal.setText(String.valueOf(s)+" kcal");
                     cal_goal.setText(String.valueOf(c)+" kcal");

                }
            cursor1.close();
            Cursor cursor = db.rawQuery("select * from user_consumption where userid=" + userid, null);

            if (cursor.getCount() > 0) {
                Log.e("", "i am under if");
                while (cursor.moveToNext()) {
                    Log.e("date sql", cursor.getString(2));
                   if (datee.equals(cursor.getString(2))) {
                        prot.setText(String.valueOf(cursor.getInt(4))+" gm");
                        fats.setText(String.valueOf(cursor.getInt(5))+" gm");
                        carb.setText(String.valueOf(cursor.getInt(6))+" gm");
                        cal_consumed.setText(String.valueOf(cursor.getInt(3))+" kcal");

                       if(cursor.getInt(3)<c){
                           Log.e("progress", String.valueOf(cursor.getInt(3)));
                           pb.setProgress((cursor.getInt(3)*100)/c);
                           progress_goal.setText(String.valueOf((cursor.getInt(3)*100)/c)+"% of daily calorie goal completed. Eat more "+(c-cursor.getInt(3))+" kcal calories to complete your goal.");
                       }
                    }
                    }
                }
            else
            {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show();
                prot.setText(String.valueOf(0));
                fats.setText(String.valueOf(0));
                carb.setText(String.valueOf(0));
            }
            cursor.close();

        }catch(Exception e){
            e.printStackTrace();
        }


        brekfst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                // cancelling all requests about this search if in queue
                queue.cancelAll("");

                // first StringRequest: getting items searched...txtSearch.getText().toString()
                StringRequest stringRequest = searchNameStringRequest("milk");
                stringRequest.setTag("TAG_SEARCH_NAME");

                // executing the request (adding to queue)
                queue.add(stringRequest);
                     */
                Intent i = new Intent(Dashboard2.this, SearchFood.class);
                startActivity(i);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final String key = "fb752dda3f984c43826465a4cb2fc818";
                final String secret = "18e4eed8c4d5401c8ca93b0669bb7893";
                final String query = "milk";
                final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                Listener listener = new Listener();

                Request req = new Request(key,query,listener);

                //This response contains the list of food items at zeroth page for your query
                req.searchFoods(requestQueue, query, 1);
                //req.searchFoods(requestQueue,query);*/

                Intent i = new Intent(Dashboard2.this, SearchFood.class);
                startActivity(i);
               }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard2.this, SearchFood.class);
                startActivity(i);


            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard2.this, SearchFood.class);
                startActivity(i);
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
                navigationView.getMenu().findItem(R.id.navd_googlefit).setVisible(false);
                navigationView.getMenu().findItem(R.id.navd_googlefit_connected).setVisible(true);
                i = new Intent(this, GoogleFitTracker.class);
                startActivity(i);
                return true;
            case R.id.navd_googlefit_connected:
                navigationView.getMenu().findItem(R.id.navd_googlefit_connected).setVisible(false);
                navigationView.getMenu().findItem(R.id.navd_googlefit).setVisible(true);
            case R.id.navd_login:
                navigationView.getMenu().findItem(R.id.navd_login).setVisible(false);
                navigationView.getMenu().findItem(R.id.navd_logout).setVisible(true);
                i = new Intent(this, login.class);
                startActivity(i);
                return true;
            case R.id.navd_logout:
                SharedPreferences sp = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                Boolean value = sp.getBoolean("logged", false);
                if (value) {

                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("logged");
                    editor.remove("userid");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_LONG).show();
                    navigationView.getMenu().findItem(R.id.navd_login).setVisible(true);
                    navigationView.getMenu().findItem(R.id.navd_logout).setVisible(false);

                    i = new Intent(Dashboard2.this, login.class);
                    startActivity(i);

                }
                return true;
            case R.id.navd_help:
                i = new Intent(Dashboard2.this, ChatBot.class);
                startActivity(i);
                return true;
            case R.id.navd_bmi:
                i = new Intent(Dashboard2.this, BmiCalculator.class);
                startActivity(i);
                return true;
            case R.id.navd_fat:
                i = new Intent(Dashboard2.this, FatCalculator.class);
                startActivity(i);
                return true;
            case R.id.navd_feedback:
                i = new Intent(Dashboard2.this, FeedBack.class);
                startActivity(i);
                return true;
            case R.id.navd_profile:
                i = new Intent(Dashboard2.this, Profile.class);
                startActivity(i);
                return true;
           /* case R.id.navd_abtus:
                i = new Intent(Dashboard2.this, Plans.class);
                startActivity(i);
                return true;*/
            case R.id.navd_plan:
                i = new Intent(Dashboard2.this, Plans.class);
                startActivity(i);
                return true;
            case R.id.navd_diet_plan:
                Database db1=new Database(getApplicationContext());
                SQLiteDatabase db=db1.getReadableDatabase();
                Cursor cursor=db.rawQuery("select plan_name,calories from registered_users where mob_no="+userid,null);

                if(cursor.getCount()>0) {
                    while (cursor.moveToNext()) {
                        data=cursor.getString(0);
                        cal= (int) cursor.getFloat(1);
                    }
                    cursor.close();
                }
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                // cancelling all requests about this search if in queue
                queue.cancelAll("");

                // first StringRequest: getting items searched...txtSearch.getText().toString()
                StringRequest stringRequest = searchNameStringRequest(data,cal);
                stringRequest.setTag("TAG_SEARCH_NAME");
                Log.e("url", String.valueOf(stringRequest));
                // executing the request (adding to queue)
                queue.add(stringRequest);
                return true;
            case R.id.navd_progress:
                i = new Intent(Dashboard2.this, User_Progress.class);
                startActivity(i);
                return true;
            case R.id.navd_recipe:
                i = new Intent(Dashboard2.this, recipes.class);
                startActivity(i);
                return true;
        }

                if (id == R.id.navigation_home) {
                    i = new Intent(Dashboard2.this, Dashboard2.class);
                    startActivity(i);
                } else if (id == R.id.navigation_plan) {
                    i = new Intent(this, Plans.class);
                    startActivity(i);
                } else if (id == R.id.navigation_progress) {
                    i = new Intent(this, Progress.class);
                    startActivity(i);
                } else if (id == R.id.navigation_Recipe) {
                    i = new Intent(this, recipes.class);
                    startActivity(i);
                }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public StringRequest searchNameStringRequest(String plan,int cal) {

        // String url = "https://api.edamam.com/search?q="+plan+"&app_id=a596dba2&app_key=86bea83ee396d957017d074ee096137e&from=0&to=3&calories=59-72&health=alcohol-free&nutrients=%5BFE%5D&dietType=";
        if(plan.equals("default"))
             url="https://api.edamam.com/api/food-database/v2/parser?ingr=vegetarian&app_id=2590c23a&app_key=b9d1860d47c5a446fa688d8e8b3ee0fc&calories=100-"+cal+"&from=0&to=50";
        else
             url="https://api.edamam.com/api/food-database/v2/parser?ingr=vegetarian&app_id=2590c23a&app_key=b9d1860d47c5a446fa688d8e8b3ee0fc&calories=100-"+cal+"&dietLabels="+plan+"&from=0&to=50";

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
                            Intent i=new Intent(Dashboard2.this,MyPlans.class);
                            i.putExtra("food", food);
                            i.putExtra("calories", calories);
                            i.putExtra("pro", pro);
                            i.putExtra("fat", fat);
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