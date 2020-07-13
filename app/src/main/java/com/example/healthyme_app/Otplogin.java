package com.example.healthyme_app;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class Otplogin extends AppCompatActivity {


    EditText phoneNumber;
    CountryCodePicker countryCodePicker;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_login);


        countryCodePicker=findViewById(R.id.coountry_code_picker);
        phoneNumber=findViewById(R.id.signup_phone_number);
    }


    public void callVerifyScreen(View view){

        //Validate fields
        if(!validatePhoneNumber()){
            return;
        }//Validation succeded and now move on to the next screen to verify phone number and save data

        String _getUserEnteredPhoneNumber=phoneNumber.getText().toString().trim();
        String _phoneNo= "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;
        Intent intent= new Intent(getApplicationContext(),Verify.class);

        //Pass all fields to the next activity
        intent.putExtra("phoneNo",_phoneNo);
        intent.putExtra("userid",_getUserEnteredPhoneNumber);


        Pair[] pairs = new Pair[1];

        pairs[0]= new Pair<View,String>(findViewById(R.id.signup_next_btn),"transition_verify");
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Otplogin.this,pairs);
        startActivity(intent,options.toBundle());
    }

    private boolean validatePhoneNumber() {

        String val = phoneNumber.getText().toString();

        if (val.isEmpty()) {
            phoneNumber.setError("Field cannot be empty");
            return false;
        } else {
            phoneNumber.setError(null);
            return true;
        }


    }
}