package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity {
     SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Kommunicate.init(getApplicationContext(), "32c485e18efaf33bb72f0745e499faff");
        sp = getSharedPreferences("preferences",MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Boolean value=sp.getBoolean("logged",false);
                if(value){
                    Intent i=new Intent(MainActivity.this, Dashboard2.class);
                    //Intent is used to switch from one activity to another.
                    startActivity(i);
                    //invoke the SecondActivity.
                    finish();
                }
                else
                    {
                    Intent i = new Intent(MainActivity.this, login.class);
                    //Intent is used to switch from one activity to another.
                    startActivity(i);
                    //invoke the SecondActivity.
                    finish();
                    //the current activity will get finished.
                }
                  }
        }, 1000);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
        // [END retrieve_current_token]

    }
}
