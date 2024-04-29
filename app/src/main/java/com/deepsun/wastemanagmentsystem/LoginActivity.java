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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button b;
    EditText et;
    EditText pt;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b=findViewById(R.id.etButton);
        et=findViewById(R.id.etEmail);
        pt=findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String storeemail =et.getText().toString().trim();
                String storepassword =pt.getText().toString().trim();

                Toast.makeText(LoginActivity.this, "connecting", Toast.LENGTH_SHORT).show();

                mAuth.signInWithEmailAndPassword(storeemail,storepassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }

                        else{

                            Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });


    }
}