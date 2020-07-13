package com.example.healthyme_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verify extends AppCompatActivity {


    EditText otpuser;
    String codeBySystem;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String _phoneNo,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);


        //hooks
        otpuser = findViewById(R.id.otp);

        _phoneNo = getIntent().getStringExtra("phoneNo");
        userid = getIntent().getStringExtra("userid");

        sendVerificationCodeToUser(_phoneNo);
    }

    private void sendVerificationCodeToUser(String phoneNo) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        otpuser.setText(code);
                        verifycode(code);
                    }
                }

                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(Verify.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Verify.this, "verification Completed", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Verify.this,Dashboard2.class);
                            sp=getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
                            editor=sp.edit();
                            editor.putString("userid",userid);
                            editor.putBoolean("logged",true);
                            editor.commit();
                            startActivity(i);

                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(Verify.this, "Verification Not Completed! Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void callNextScreenfromOTP(View view) {

        String code = otpuser.getText().toString();
        if (!code.isEmpty()) {
            verifycode(code);

        }
    }
}
