package io.github.okrand.drivr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    private DatabaseReference mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "Here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authenticate();
        //String access_token = getIntent().getData().getQueryParameter("access_token");
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

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Handle possible errors.
            }
        });

        /*
        mDatabase = FirebaseDatabase.getInstance().getReference("Reports");

        String licenseID = "P85AXJ";
        String state = "New Jersey";
        String issue = "lights";

        //mDatabase.child(licenseID).setValue(state);
        mDatabase.child("1").child("Claim Number").setValue(1);
        mDatabase.child("1").child("License Plate").setValue(licenseID);
        mDatabase.child("1").child("State").setValue(state);
        mDatabase.child("1").child("Issue").setValue(issue);

        mDatabase.child("2").child("Claim Number").setValue(2);
        mDatabase.child("2").child("License Plate").setValue("LKM456");
        mDatabase.child("2").child("State").setValue("Virginia");
        mDatabase.child("2").child("Issue").setValue("Swerve");

        mDatabase.child("3").child("Claim Number").setValue(3);
        mDatabase.child("3").child("License Plate").setValue("ASD789");
        mDatabase.child("3").child("State").setValue("Texas");
        mDatabase.child("3").child("Issue").setValue("Tire");

        DatabaseReference lastNode = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = lastNode.child("Reports").orderByKey().limitToLast(1);
        System.out.println("lastQuery = " + mDatabase.limitToLast(1).getRef().child("Claim Number"));
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("previousClaimID = " + dataSnapshot);
                System.out.println("previousClaimID = " + dataSnapshot.getValue());
                System.out.println("newClaimID = " + dataSnapshot.child("Claim Number"));

                Integer newClaimID = (int) dataSnapshot.child("Claim Number").getValue();
                mDatabase.child(Integer.toString(newClaimID + 1)).child("License Plate").setValue("MLK123");
                mDatabase.child(Integer.toString(newClaimID + 1)).child("State").setValue("New York");
                mDatabase.child(Integer.toString(newClaimID + 1)).child("Issue").setValue("Brakes");
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


