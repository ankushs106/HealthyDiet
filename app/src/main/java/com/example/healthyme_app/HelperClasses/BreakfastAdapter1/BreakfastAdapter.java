package com.example.healthyme_app.HelperClasses.BreakfastAdapter1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthyme_app.R;

import java.util.ArrayList;



    public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastViewHolder> {

        ArrayList<Breakfast> Breakfastlocation;

        public BreakfastAdapter(ArrayList<Breakfast> Breakfastlocation) {
            this.Breakfastlocation = Breakfastlocation;
        }

        @NonNull
        @Override
        public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfastcard,parent,false);
            BreakfastViewHolder BreakfastViewHolder= new BreakfastViewHolder(view);

            return BreakfastViewHolder;
        }

        @Override
        public void onBindViewHolder(BreakfastViewHolder holder, int position) {
            Breakfast   Breakfast= Breakfastlocation.get(position);
            holder.image.setImageResource(Breakfast.getImage());
            holder.title.setText(Breakfast.getTitle());
            holder.description.setText(Breakfast.getDescription());
        }

        @Override
        public int getItemCount() {
            return Breakfastlocation.size();
        }


        public static class BreakfastViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView title;
            TextView description;

            public BreakfastViewHolder(@NonNull View itemView) {
                super(itemView);

                //Hooks


                image=itemView.findViewById(R.id.breakfast1);
                title=itemView.findViewById(R.id.btitle1);
                description=itemView.findViewById(R.id.bdescription1);

            }
        }
    }

