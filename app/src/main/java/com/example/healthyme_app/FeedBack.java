package com.example.healthyme_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FeedBack extends AppCompatActivity {
    Button feedback_btn;
    EditText topic,msg;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        feedback_btn=(Button)findViewById(R.id.btn);
        topic=(EditText)findViewById(R.id.topic);
        msg=(EditText)findViewById(R.id.msg);
        final Database db= new Database(getApplicationContext());

        sp=getSharedPreferences("preferences",MODE_PRIVATE);
        String id=sp.getString("userid","");
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.feedback(id,topic.getText().toString(),msg.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Feedback added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Oops!Something went wrong.",Toast.LENGTH_LONG).show();
            }
        });
    }
}