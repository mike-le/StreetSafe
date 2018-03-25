package io.github.okrand.drivr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Emergency extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        Button police = (Button) findViewById(R.id.police);
        Button fire = (Button) findViewById(R.id.fire);
        Button ems = (Button) findViewById(R.id.ems);

        police.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        fire.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        ems.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

}
