package com.example.healthyme_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowRecipes extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipes);


        ArrayList<String> urldata = (ArrayList<String>) getIntent().getSerializableExtra("url");
        ArrayList<String> labeldata = (ArrayList<String>) getIntent().getSerializableExtra("label");
        ArrayList<String> dlabeldata = (ArrayList<String>) getIntent().getSerializableExtra("dlabel");
        ArrayList<String> imgdata = (ArrayList<String>) getIntent().getSerializableExtra("img");

        ArrayList<RecipesData> data = new ArrayList<RecipesData>();
        for (int i=0;i<urldata.size();i++)
            data.add(new RecipesData(urldata.get(i),labeldata.get(i),dlabeldata.get(i),imgdata.get(i)));
        Log.e("data", String.valueOf(data));

        if (data.size() > 0) {
            recyclerView = (RecyclerView) findViewById(R.id.rec);
            RecipeDataAdapter adapter = new RecipeDataAdapter(data);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            //recyclerView.setHasFixedSize(true);
            // recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }
    }
}