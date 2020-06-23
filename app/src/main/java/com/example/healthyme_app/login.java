package com.example.healthyme_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login extends Activity {

    Button btn_login,btn_forgot_pass,btn_account;
    EditText e_mob,e_pass;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Database db= new Database(getApplicationContext());
        btn_login=(Button)findViewById(R.id.btnLogin) ;
        btn_forgot_pass=(Button)findViewById(R.id.btnLogin2);
        btn_account=(Button)findViewById(R.id.btnLogin3) ;

        btn_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(i);
            }
        });
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_mob=(EditText)findViewById(R.id.etMobileNumber);
                e_pass=(EditText)findViewById(R.id.etPassword);

                if(!e_mob.getText().toString().isEmpty() && !e_pass.getText().toString().isEmpty())
                {
                    try {
                        if(db.login(e_mob.getText().toString(),e_pass.getText().toString())){
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_LONG).show();
                            sp=getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
                            editor=sp.edit();
                            editor.putString("userid",e_mob.getText().toString());
                            editor.putBoolean("logged",true);
                            Intent i = new Intent(login.this, Dashboard2.class);
                            startActivity(i);
                            editor.commit();

                        }
                        else
                            Toast.makeText(getApplicationContext(),"Check Mobile Number or password ",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else
                    Toast.makeText(getApplicationContext(),"Fill details",Toast.LENGTH_LONG).show();
            }
        });
    }
}
