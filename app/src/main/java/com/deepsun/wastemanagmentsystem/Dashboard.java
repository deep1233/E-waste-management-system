package com.deepsun.wastemanagmentsystem;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
 private DatabaseReference refrence;
    private DatabaseReference refrence1; //data base refrence ko object banako
    private TextView value;

    HalfGauge halfGauge;
    ArcGauge arcGauge;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setContentView(R.layout.activity_dashboard);
        arcGauge = findViewById(R.id.arcGauge);
        //value = findViewById(R.id.textView2);
        halfGauge = findViewById(R.id.halfGauge);
        //circularFillableLoaders = findViewById(R.id.circularFillableLoaders);


        Range range = new Range();
        range.setColor(Color.parseColor("#00b20b"));
        range.setFrom(0.0);
        range.setTo(30.0);

        Range range2 = new Range();
        range2.setColor(Color.parseColor("#E3E500"));
        range2.setFrom(30.0);
        range2.setTo(50.0);

        Range range3 = new Range();
        range3.setColor(Color.parseColor("#ce0000"));
        range3.setFrom(50.0);
        range3.setTo(100.0);


        halfGauge.addRange(range3);
        halfGauge.addRange(range2);
        halfGauge.addRange(range);



        refrence = FirebaseDatabase.getInstance().getReference().child("sensor_data").child("mq");
        refrence1 = FirebaseDatabase.getInstance().getReference().child("sensor_data").child("ultrasonic");
        // firebase ko refrence ko child ma sensor banney key cha. tyo patha refrence liyera stire gardeyo

        refrence1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String val1 = snapshot.getValue(String.class);
                // yesley chai val banney ma uta bhako change bhako dtaa store gardincha.

                //updateG(Double.parseDouble(val1));


                if (val1 != null) {
                    // Update the SpeedView
                    halfGauge.setValue(Double.parseDouble(val1));
                }


            }

            private void updateG(double v) {
                halfGauge.setMinValue(0.0);
                halfGauge.setMaxValue(100.0);
                halfGauge.setValue(v);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dashboard.this, "Error"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });



        refrence.addValueEventListener(new ValueEventListener() {
            // kai a=change cha banney banney listerner rakyo

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // kai data change bhbako cha ki chaina check garcha
                String val = snapshot.getValue(String.class);

                if (val != null) {
                    // Update the SpeedView
                    arcGauge.setValue(Double.parseDouble(val));
                }




            

            }

          

           

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
