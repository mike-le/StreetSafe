package io.github.okrand.drivr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private static int numberOfClaims;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Example upload
        Report newReport = new Report("new state", "new license", "new claim");
        uploadReport(newReport);

        //Populate List
        List<Report> theList = new ArrayList<>();

        ListView myRepsListView = findViewById(R.id.list_my_reports);
        ArrayAdapter adapter = new ArrayAdapter<Report>(MainActivity.this, R.layout.list_item_record, R.id.list_my_reports, theList){

        };
        myRepsListView.setAdapter(adapter);

        //On Click go to New Report.java
        Intent intent = new Intent(this, NewReport.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    //If User calls app and successfully provides information to upload, use this function
    public void uploadReport(final Report newReport)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query numberOfChilds = mDatabase.child("Reports").orderByKey();
        numberOfChilds.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getChildrenCount());
                numberOfClaims = (int) dataSnapshot.getChildrenCount() + 1;
                //Prompt the User License and store into report

                mDatabase.child("Reports").child(String.valueOf(numberOfClaims)).setValue(newReport);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Handle possible errors.
            }
        });
    }
}


