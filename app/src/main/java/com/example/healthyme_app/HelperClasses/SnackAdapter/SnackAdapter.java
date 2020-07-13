package com.example.healthyme_app.HelperClasses.SnackAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.healthyme_app.R;

import java.util.ArrayList;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.SnackViewHolder> {

    ArrayList<Snack> Snacklocation;

    public SnackAdapter(ArrayList<Snack> Snacklocation) {
        this.Snacklocation = Snacklocation;
    }

    @NonNull
    @Override
    public SnackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.snackcard, parent, false);
        SnackViewHolder SnackViewHolder = new SnackViewHolder(view);

        return SnackViewHolder;
    }

    @Override
    public void onBindViewHolder(SnackViewHolder holder, int position) {
        Snack Snack = Snacklocation.get(position);
        holder.image.setImageResource(Snack.getImage());
        holder.title.setText(Snack.getTitle());
        holder.description.setText(Snack.getDescription());
    }

    @Override
    public int getItemCount() {
        return Snacklocation.size();
    }


    public static class SnackViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public SnackViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks


            image = itemView.findViewById(R.id.snack1);
            title = itemView.findViewById(R.id.stitle1);
            description = itemView.findViewById(R.id.sdescription1);

        }
    }
}
