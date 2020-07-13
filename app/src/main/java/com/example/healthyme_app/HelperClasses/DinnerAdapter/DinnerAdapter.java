package com.example.healthyme_app.HelperClasses.DinnerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyme_app.R;

import java.util.ArrayList;

public class DinnerAdapter extends RecyclerView.Adapter<DinnerAdapter.DinnerViewHolder> {


    ArrayList<Dinner> Dinnerlocation;

    public DinnerAdapter(ArrayList<Dinner> Dinnerlocation) {
        this.Dinnerlocation = Dinnerlocation;
    }

    @NonNull
    @Override
    public DinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dinnercard, parent, false);
        DinnerViewHolder DinnerViewHolder = new DinnerViewHolder(view);

        return DinnerViewHolder;
    }

    @Override
    public void onBindViewHolder(DinnerViewHolder holder, int position) {
        Dinner Dinner = Dinnerlocation.get(position);
        holder.image.setImageResource(Dinner.getImage());
        holder.title.setText(Dinner.getTitle());
        holder.description.setText(Dinner.getDescription());
    }

    @Override
    public int getItemCount() {
        return Dinnerlocation.size();
    }


    public static class DinnerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public DinnerViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks


            image = itemView.findViewById(R.id.dinner1);
            title = itemView.findViewById(R.id.dtitle1);
            description = itemView.findViewById(R.id.ddescription1);

        }
    }
}
