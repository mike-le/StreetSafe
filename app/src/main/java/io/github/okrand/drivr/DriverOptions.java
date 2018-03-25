package io.github.okrand.drivr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        Bundle bundle = getIntent().getExtras();
        final Report r = bundle.getParcelable("report");

        swerve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Swerve");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        speed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Speed");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        pedestrian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Pedestrian");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        distracted.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Distracted");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        tailgating.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Tailgating");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        signal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Signal");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
