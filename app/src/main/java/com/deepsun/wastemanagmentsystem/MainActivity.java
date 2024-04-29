package com.deepsun.wastemanagmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
private DatabaseReference refrence; //data base refrence ko object banako
    private TextView value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = findViewById(R.id.fdata);
        refrence = FirebaseDatabase.getInstance().getReference().child("sensor");
        // firebase ko refrence ko child ma sensor banney key cha. tyo patha refrence liyera stire gardeyo



        refrence.addValueEventListener(new ValueEventListener() {
            // kai a=change cha banney banney listerner rakyo

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // kai data change bhbako cha ki chaina check garcha
                String val = snapshot.getValue(String.class);
                // yesley chai val banney ma uta bhako change bhako dtaa store gardincha.
                value.setText(val);
                // hamialey  dtaa chai sndoriod ko viewcompondent ma display
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
