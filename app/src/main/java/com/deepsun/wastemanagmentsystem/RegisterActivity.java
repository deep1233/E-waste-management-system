package com.deepsun.wastemanagmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    EditText etFull, etEmail, etP1, etP2;
    Button Register;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView Loginhere;

    Button backb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Binding views of front end in in-app backend
        Loginhere= findViewById(R.id.Lo_up);
        etFull = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etRegEmail);
        etP1 = findViewById(R.id.etpw1);
        etP2 = findViewById(R.id.etpw2);
        Register = findViewById(R.id.btnReg);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pBar);
        backb = findViewById(R.id.etBackbutton2);

        Loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = etFull.getText().toString().trim();
                String Email = etEmail.getText().toString().trim();
                String Password = etP1.getText().toString().trim();
                String Password1 = etP2.getText().toString().trim();

                if (TextUtils.isEmpty(Name)) {
                    etFull.setError("Full name is required");
                    return;
                }

                if (TextUtils.isEmpty(Email)) {
                    etEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    etP1.setError("Password is required");
                    return;
                }

                if (!Password.equals(Password1)) {
                    etP1.setError("Password does not match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish(); // Close the current activity
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE); // Hide progress bar on error
                        }
                    }
                });
            }
        });

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to GetStartedActivity
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish(); // Optional: close the current activity
            }
        });
    }
}
