package com.example.healthyme_app;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import io.kommunicate.KmConversationBuilder;
//import io.kommunicate.callbacks.KmCallback;
//import io.kommunicate.users.KMUser;
import android.app.Activity;

import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.messaging.FirebaseMessagingService;


public class Register extends Activity {

    Button btn_register,btn_login;
    EditText name,email,phone,password;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        btn_register=(Button)findViewById(R.id.register);
        btn_login=(Button)findViewById(R.id.login);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.etRMobileno);
        password=(EditText)findViewById(R.id.etRpassword);

        final Database db= new Database(getApplicationContext());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                        && !phone.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if(!db.check_users(phone.getText().toString())) {
                        try {
                            if (db.insert(name.getText().toString(), email.getText().toString(),
                                    phone.getText().toString(), password.getText().toString()))
                            {
                                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), CheckBMI.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Register.this, "Something went wrong", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                        Toast.makeText(Register.this, "User already exists", Toast.LENGTH_LONG).show();
                } else {
                    name.setError("Enter NAME");
                    email.setError("Enter mobile number");
                }

            }
        });




        Intent notifyIntent = new Intent(this,Register.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (getApplicationContext(), 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                6000 , pendingIntent);

        //findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View v) {
          //      Intent i=new Intent(Register.this,ChatBot.class);
                //startActivity(i);
        //    }
       // });
    }


}
