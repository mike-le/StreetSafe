package io.github.okrand.drivr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class CarOptions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_options);

        Button tire = (Button) findViewById(R.id.tire);
        Button lights = (Button) findViewById(R.id.lights);

        Bundle bundle = getIntent().getExtras();
        final Report r = bundle.getParcelable("report");

        tire.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Tire");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        lights.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Lights");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        smoke.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setOption("Smoke");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("report", r);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
