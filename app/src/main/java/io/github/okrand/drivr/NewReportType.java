package io.github.okrand.drivr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class NewReportType extends AppCompatActivity{
    private final int CODE_BAD = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report_type);

        Bundle bundle = getIntent().getExtras();
        final Report r = bundle.getParcelable("report");

        Button emergency = (Button) findViewById(R.id.emergency);
        Button driver = (Button) findViewById(R.id.bad_driver);
        Button car = (Button) findViewById(R.id.bad_car);

        emergency.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewReportType.this, Emergency.class);
                startActivity(intent);
            }
        });

        driver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewReportType.this, LicensePlate.class);
                r.setClaim(""+getResources().getString(R.string.bad_driver));
                intent.putExtra("report", r);
                startActivityForResult(intent, CODE_BAD);
            }
        });

        car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewReportType.this, LicensePlate.class);
                r.setClaim(""+getResources().getString(R.string.bad_car));
                intent.putExtra("report", r);
                startActivityForResult(intent, CODE_BAD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case CODE_BAD: {
                if (resultCode == RESULT_OK && null != data){
                    Report r = data.getParcelableExtra("report");
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("report", r);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                break;
            }
        }
    }
}
