package com.deepsun.wastemanagmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {


    Button submit;
    EditText email;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        submit = findViewById(R.id.etButton);
        email = findViewById(R.id.etEmail);
        mAuth = FirebaseAuth.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email= email.getText().toString().trim();
                        beginRecovery(Email);
            }

            private void beginRecovery(String email) {

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            // if isSuccessful then done message will be shown
                            // and you can change the password
                            Toast.makeText(ForgotPassword.this,"Done sent",Toast.LENGTH_LONG).show();
                            Intent i  = new Intent(ForgotPassword.this,LoginActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(ForgotPassword.this,"Error Occurred",Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ForgotPassword.this,"Error Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}