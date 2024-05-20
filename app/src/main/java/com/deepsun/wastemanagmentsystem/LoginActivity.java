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
//        forgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showRecoverPasswordDialog();
//            }
//
//            private void showRecoverPasswordDialog() {
//                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
//                builder.setTitle("Enter your Email");
//                LinearLayout linearLayout=new LinearLayout(LoginActivity.this);
//                final EditText emailet= new EditText(LoginActivity.this);
//
//                // write the email using which you registered
//                emailet.setHint("Email");
//                emailet.setMinEms(16);
//                emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//                linearLayout.addView(emailet);
//                linearLayout.setPadding(10,10,10,10);
//                builder.setView(linearLayout);
//
//                // Click on Recover and a email will be sent to your registered email id
//                builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String email=emailet.getText().toString().trim();
//                        beginRecovery(email);
//                    }
//                });
//
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.create().show();
//            }
//
//            private void beginRecovery(String email) {
//                loadingBar=new ProgressDialog(LoginActivity.this);
//                loadingBar.setMessage("Sending Email....");
//                loadingBar.setCanceledOnTouchOutside(false);
//                loadingBar.show();
//
//                // calling sendPasswordResetEmail
//                // open your email and write the new
//                // password and then you can login
//                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        loadingBar.dismiss();
//                        if(task.isSuccessful())
//                        {
//                            // if isSuccessful then done message will be shown
//                            // and you can change the password
//                            Toast.makeText(LoginActivity.this,"Done sent",Toast.LENGTH_LONG).show();
//                        }
//                        else {
//                            Toast.makeText(LoginActivity.this,"Error Occurred",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        loadingBar.dismiss();
//                        Toast.makeText(LoginActivity.this,"Error Failed",Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        });

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
