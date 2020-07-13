package com.example.healthyme_app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class UserProgressAdapter extends RecyclerView.Adapter<UserProgressAdapter.ViewHolder>{
    private UserProgressData[] listdata;
    private  ArrayList<UserProgressData> data1;

    // RecyclerView recyclerView;
    public UserProgressAdapter(UserProgressData[] listdata) {
        this.listdata = listdata;
    }
    public UserProgressAdapter(ArrayList<UserProgressData> data) {
        this.data1 = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //final UserProgressData myListData = listdata[position];
       // UserProgressData data = data1.get(position);
        final UserProgressData data = data1.get(position);
        Log.e("",String.valueOf((int)data.getCalories()));
        holder.t1.setText( String.valueOf((int)data.getCalories()));
        holder.t2.setText(data.getDate());
       // holder.textView.setText(String.valueOf(listdata[position].getCalories()));
        //holder.textView.setText(listdata[position].getDate());

        //holder.imageView.setImageResource(listdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+data1.getDate(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data1.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       // public ImageView imageView;
        public TextView t1,t2;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.t1 = (TextView) itemView.findViewById(R.id.cal);
            this.t2 = (TextView) itemView.findViewById(R.id.date);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}