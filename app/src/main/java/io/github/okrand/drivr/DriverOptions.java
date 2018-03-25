package io.github.okrand.drivr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class DriverOptions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_options);

        Button swerve = (Button) findViewById(R.id.swerving);
        Button speed = (Button) findViewById(R.id.speeding);
        Button pedestrian = (Button) findViewById(R.id.pedestrian);
        Button distracted = (Button) findViewById(R.id.distracted);
        Button tailgating = (Button) findViewById(R.id.tailgaiting);
        Button signal = (Button) findViewById(R.id.signal);

        swerve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        speed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        pedestrian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        distracted.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tailgating.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        signal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}
