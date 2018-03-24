package io.github.okrand.drivr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Report> theList = new ArrayList<>();

        ListView myRepsListView = findViewById(R.id.list_my_reports);
        ArrayAdapter adapter = new ArrayAdapter<Report>(MainActivity.this, R.layout.list_item_record, R.id.list_my_reports, theList){

        };
        myRepsListView.setAdapter(adapter);
    }
}
