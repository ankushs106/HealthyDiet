package com.example.healthyme_app.HelperClasses.LunchAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.healthyme_app.R;

import java.util.ArrayList;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.LunchViewHolder> {

    ArrayList<Lunch> Lunchlocation;

    public LunchAdapter(ArrayList<Lunch> Lunchlocation) {
        this.Lunchlocation = Lunchlocation;
    }

    @NonNull
    @Override
    public LunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lunchcard, parent, false);
        LunchViewHolder LunchViewHolder = new LunchViewHolder(view);

        return LunchViewHolder;
    }

    @Override
    public void onBindViewHolder(LunchViewHolder holder, int position) {
        Lunch Lunch = Lunchlocation.get(position);
        holder.image.setImageResource(Lunch.getImage());
        holder.title.setText(Lunch.getTitle());
        holder.description.setText(Lunch.getDescription());
    }

    @Override
    public int getItemCount() {
        return Lunchlocation.size();
    }


    public static class LunchViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public LunchViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks


            image = itemView.findViewById(R.id.lunch1);
            title = itemView.findViewById(R.id.ltitle1);
            description = itemView.findViewById(R.id.ldescription1);

        }
    }
}
