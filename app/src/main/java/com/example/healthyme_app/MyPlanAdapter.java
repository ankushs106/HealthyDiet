package com.example.healthyme_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPlanAdapter extends RecyclerView.Adapter<MyPlanAdapter.ViewHolder> {

    ArrayList<String> plandata = new ArrayList<>();
    static ArrayList<PlanData> data=new ArrayList<PlanData>();


    public MyPlanAdapter(ArrayList<PlanData> data){
        this.data=data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_details,parent,false);
        ViewHolder ViewHolder= new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlanData b= data.get(position);
        holder.food_name.setText(b.getFood_name());
        holder.calories.setText(String.valueOf(b.getCal())+" kcal");
        holder.carbs.setText(String.valueOf(b.getCarbs())+" gm");
        holder.fat.setText(String.valueOf(b.getFats())+" gm");
        holder.pro.setText(String.valueOf(b.getProteins())+" gm");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView food_name,calories,pro,fat,carbs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            food_name=itemView.findViewById(R.id.label);
            calories=itemView.findViewById(R.id.cal);
            pro=itemView.findViewById(R.id.protein);
            fat=itemView.findViewById(R.id.fat);
            carbs=itemView.findViewById(R.id.carbb);

        }
    }

}
