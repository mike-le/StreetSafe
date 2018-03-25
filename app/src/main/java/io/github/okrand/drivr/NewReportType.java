package io.github.okrand.drivr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class NewReportType extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report_type);

        Button emergency = (Button) findViewById(R.id.emergency);
        Button driver = (Button) findViewById(R.id.bad_driver);
        Button car = (Button) findViewById(R.id.bad_car);

        emergency.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewReportType.this, LicensePlate.class);
            }
        });

        driver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewReportType.this, LicensePlate.class);
            }
        });

        car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewReportType.this, LicensePlate.class);
            }
        });
    }

}
