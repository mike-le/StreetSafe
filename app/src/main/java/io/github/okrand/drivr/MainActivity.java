package io.github.okrand.drivr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int CODE_SAFETREK = 10;
    private final int CODE_NEW_REPORT = 0;
    private DatabaseReference mDatabase;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static int numberOfClaims;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "Here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //authenticate();

        final Button newReport = findViewById(R.id.button_new_report);
        newReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report r = new Report();
                Date date = new Date();
                r.setTime(sdf.format(date));
                Intent intent = new Intent(MainActivity.this, NewReportType.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("report", r);
                intent.putExtras(bundle);
                startActivityForResult(intent, CODE_NEW_REPORT);
            }
        });

        getReports("M62AYJ", "New Jersey");
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
            case CODE_NEW_REPORT: {
                if (resultCode == RESULT_OK && null != data){
                 Report r = data.getParcelableExtra("report");
                 uploadReport(r);
                 Log.d("NEW REPORT", r.getLicense() + " ---- " + r.getState() + " ---- " + r.getClaim() + ": " + r.getOption());
                }
                break;
            }
        }
    }

    public void uploadReport(final Report newReport)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query numberOfChilds = mDatabase.child("Reports").orderByKey();
        numberOfChilds.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() == 0)
                {
                    mDatabase.child("Reports").child("1").setValue(newReport);
                }
                else
                {
                    numberOfClaims = (int) dataSnapshot.getChildrenCount() + 1;
                    mDatabase.child("Reports").child(String.valueOf(numberOfClaims)).setValue(newReport);
                }
                Toast.makeText(MainActivity.this, "Report saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void getReports(final String licenseID, final String state)
    {
        final List<Report> reports = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reports");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(int i = 1; i <= (int) dataSnapshot.getChildrenCount(); i++)
                {
                    String checkLicense = dataSnapshot.child(String.valueOf(i)).child("license").getValue().toString();
                    String checkState = dataSnapshot.child(String.valueOf(i)).child("state").getValue().toString();

                    if (checkLicense.equalsIgnoreCase(licenseID) && checkState.equalsIgnoreCase(state)) {
                        String claim = dataSnapshot.child(String.valueOf(i)).child("claim").getValue().toString();
                        String time = dataSnapshot.child(String.valueOf(i)).child("time").getValue().toString();
                        String option = dataSnapshot.child(String.valueOf(i)).child("option").getValue().toString();
                        Report newReport = new Report(checkState, checkLicense, claim, option, time);
                        reports.add(newReport);
                    }
                }
                final ListView myRepsListView = findViewById(R.id.list_my_reports);
                ArrayAdapter adapter = new ArrayAdapter<Report>(MainActivity.this, R.layout.list_item_record, R.id.item_claim, reports){
                    @Override
                    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
                        final View view = super.getView(position, convertView, parent);
                        TextView itemClaim = view.findViewById(R.id.item_claim);
                        TextView itemOption = view.findViewById(R.id.item_option);
                        TextView itemDate = view.findViewById(R.id.item_date);
                        TextView itemTime = view.findViewById(R.id.item_time);
                        itemClaim.setText(reports.get(position).getClaim());
                        itemOption.setText(reports.get(position).getOption());
                        String t = reports.get(position).getTime();
                        try {
                            Date d = sdf.parse(t);
                            DateFormat daymonth = new SimpleDateFormat("MMM, d");
                            DateFormat hourMinute = new SimpleDateFormat("hh:mm aa");
                            itemDate.setText(daymonth.format(d));
                            itemTime.setText(hourMinute.format(d));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return view;
                    }
                };
                myRepsListView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
