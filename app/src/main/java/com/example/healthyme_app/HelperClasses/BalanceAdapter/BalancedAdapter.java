package com.example.healthyme_app.HelperClasses.BalanceAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyme_app.PlanData;
import com.example.healthyme_app.R;
import com.example.healthyme_app.custom_diet_plan;

import java.util.ArrayList;

public class BalancedAdapter extends RecyclerView.Adapter<BalancedAdapter.ViewHolder> {

    ArrayList<String> plandata = new ArrayList<>();
    static ArrayList<PlanData> bal=new ArrayList<PlanData>();
    String plan = null;

    public BalancedAdapter(ArrayList<PlanData> bal){
        this.bal=bal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design1,parent,false);
        ViewHolder ViewHolder= new ViewHolder(view);

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlanData b= bal.get(position);
        //holder.image.setImageResource(b.getImage());
        holder.title.setText(b.getTitle());
        holder.desc.setText(b.getPlan_name());
    }

    @Override
    public int getItemCount() {
        return bal.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView desc,title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks

            //image=itemView.findViewById(R.id.weight1);
            desc=itemView.findViewById(R.id.weighttitle);
            title=itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(title.getText().toString().equals("Balanced Diet"))
                        plan="balanced";
                    if(title.getText().toString().equals("High Fiber"))
                        plan="high-fiber"; 
                    if(title.getText().toString().equals("High Protein"))
                        plan="high-protein"; 
                    if(title.getText().toString().equals("Low Carb"))
                        plan="low-carb"; 
                    if(title.getText().toString().equals("Low Fat"))
                        plan="low-fat";
                    Context context = v.getContext();
                    plandata.clear();
                    Context c=itemView.getContext();
                    Intent intent = new Intent(c, custom_diet_plan.class);
                    intent.putExtra("plandata", plan);
                    c.startActivity(intent);
                    /* RequestQueue queue = Volley.newRequestQueue(context);

                   // cancelling all requests about this search if in queue
                    queue.cancelAll("");

                    // first StringRequest: getting items searched...txtSearch.getText().toString()
                    StringRequest stringRequest = searchNameStringRequest(title.getText().toString());
                    stringRequest.setTag("TAG_SEARCH_NAME");
                    Log.e("", String.valueOf(stringRequest));
                    // executing the request (adding to queue)
                    queue.add(stringRequest);
*/
                }
            });


        }


    }
}
