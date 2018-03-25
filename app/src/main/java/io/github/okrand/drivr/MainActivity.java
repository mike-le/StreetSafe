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

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
private final int CODE_SAFETREK = 10;
    private DatabaseReference mDatabase;
    private static int numberOfClaims;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "Here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authenticate();
        //String access_token = getIntent().getData().getQueryParameter("access_token");
        //Example upload
        Report newReport = new Report("new state", "new license", "new claim");
        uploadReport(newReport);
        //DatabaseTest();
    }

    void authenticate() {
        String client_id = "m5qXF5ztOdT4cdQtUbZT2grBhF187vw6";
        String redirectUri = "https://drivr-report.herokuapp.com/callback";
        String url = "https://account-sandbox.safetrek.io/authorize" + "?client_id=" + client_id + "&scope=openid%20phone%20offline_access&response_type=code&redirect_uri=" + redirectUri;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        System.out.println(url);
        startActivityForResult(intent, CODE_SAFETREK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case CODE_SAFETREK: {
                String token = data.getData().getQueryParameter("access_token");
                System.out.print(token);
                break;
            }
        }
    }

    void DatabaseTest(){
        String licenseID = "P85AXJ";
        String state = "New Jersey";
        String issue = "lights";

        mDatabase2 = FirebaseDatabase.getInstance().getReference();
        mDatabase2.child("1").child("Claim Number").setValue(1);
        mDatabase2.child("1").child("License Plate").setValue(licenseID);
        mDatabase2.child("1").child("State").setValue(state);
        mDatabase2.child("1").child("Issue").setValue(issue);

        mDatabase2.child("2").child("Claim Number").setValue(2);
        mDatabase2.child("2").child("License Plate").setValue("LKM456");
        mDatabase2.child("2").child("State").setValue("Virginia");
        mDatabase2.child("2").child("Issue").setValue("Swerve");

        mDatabase2.child("3").child("Claim Number").setValue(3);
        mDatabase2.child("3").child("License Plate").setValue("ASD789");
        mDatabase2.child("3").child("State").setValue("Texas");
        mDatabase2.child("3").child("Issue").setValue("Tire");

        Query lastQuery = mDatabase2.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
            }

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
<<<<<<< HEAD
=======
        */
    }
}


