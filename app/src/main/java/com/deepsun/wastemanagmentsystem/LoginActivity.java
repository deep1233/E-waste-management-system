package com.deepsun.wastemanagmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView register,forgot;

    Button Bb;

    TextView signup;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b = findViewById(R.id.etButton);
        et = findViewById(R.id.etEmail);
        pt = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();
        Bb = findViewById(R.id.etBackbutton);
        signup = findViewById(R.id.signup);
        forgot = findViewById(R.id.forgot);


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ForgotPassword.class);
                startActivity(i);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storeemail = et.getText().toString().trim();
                String storepassword = pt.getText().toString().trim();

                Toast.makeText(LoginActivity.this, "connecting", Toast.LENGTH_SHORT).show();

                mAuth.signInWithEmailAndPassword(storeemail, storepassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, Dashboard.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to GetStartedActivity
                startActivity(new Intent(LoginActivity.this, getstartedpage.class));
                finish(); // Optional: close the current activity
            }
        });
    }
}
