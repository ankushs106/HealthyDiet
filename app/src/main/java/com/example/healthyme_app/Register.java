package com.example.healthyme_app;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import io.kommunicate.KmConversationBuilder;
//import io.kommunicate.callbacks.KmCallback;
//import io.kommunicate.users.KMUser;


public class Register extends Activity {

    Button btn_register,btn_login;
    EditText name,email,phone,password;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

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
                                sp=getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
                                editor=sp.edit();
                                editor.putString("userid",phone.getText().toString());
                                editor.putBoolean("logged",true);
                                editor.commit();
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




        //findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View v) {
          //      Intent i=new Intent(Register.this,ChatBot.class);
                //startActivity(i);
        //    }
       // });
    }


}
