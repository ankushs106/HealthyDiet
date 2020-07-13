package com.example.healthyme_app;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecipeDataAdapter extends RecyclerView.Adapter<RecipeDataAdapter.ViewHolder>{
    private UserProgressData[] listdata;
    private  ArrayList<RecipesData> data1;
    static String url=null;
    // RecyclerView recyclerView;
    public RecipeDataAdapter(ArrayList<RecipesData> data) {
        this.data1 = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recipes_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //final UserProgressData myListData = listdata[position];
        // UserProgressData data = data1.get(position);
        final RecipesData data = data1.get(position);
        Log.e("url of recipe ",String.valueOf(data.getUrl()));
        holder.t1.setText( String.valueOf(data.getLabel()));
        holder.t2.setText(data.getUrl());
       /* URL imgurl = null;
        try {
            imgurl = new URL(data.getImgurl());
        Bitmap bmp = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream());
        holder.imageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // holder.textView.setText(String.valueOf(listdata[position].getCalories()));
        //holder.textView.setText(listdata[position].getDate());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+data1.getDate(),Toast.LENGTH_LONG).show();
                Context c=view.getContext();
                url=data.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                c.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data1.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView t1,t2;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            relativeLayout=itemView.findViewById(R.id.ll);
            this.t1 = (TextView) itemView.findViewById(R.id.dtitle1);
            this.t2 = (TextView) itemView.findViewById(R.id.ddescription1);

        }
    }
}