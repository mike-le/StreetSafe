package io.github.okrand.drivr;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Emergency extends AppCompatActivity {
    final private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik5FWTBPVVV3TVRSRU5qUTRSVUZDTkVJd01rUTBSVEUwUVRJMFF6ZzRSVGc1T0RBMFJEWXhOUSJ9.eyJodHRwOi8vY2xpZW50LW5hbWUiOiJIQUNLX1VWQSIsImlzcyI6Imh0dHBzOi8vbG9naW4tc2FuZGJveC5zYWZldHJlay5pby8iLCJzdWIiOiJzbXN8NWFiNzEwMDhhNjgwM2E5MTkxMWU3NDA0IiwiYXVkIjpbImh0dHBzOi8vYXBpLXNhbmRib3guc2FmZXRyZWsuaW8iLCJodHRwczovL3NhZmV0cmVrLXNhbmRib3guYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTUyMTk4ODA4NywiZXhwIjoxNTIyMDI0MDg3LCJhenAiOiJtNXFYRjV6dE9kVDRjZFF0VWJaVDJnckJoRjE4N3Z3NiIsInNjb3BlIjoib3BlbmlkIHBob25lIG9mZmxpbmVfYWNjZXNzIn0.xUE5oLwjuom_HpxI3DScRDbreLKB8uj3q9_jcZ0irT2kdbzzmirUifzc-lX-qkDmSOY7Eich3kcA7ShMLDpkvEZ0gv_d_bFrC2aJ-9EDaanY96l8Jfw4HLBElWQYGqhqq7Ml6XPthNpXF8Qlz7IwW2g5mdyp07HPEsVFKy3_muZh90DBcfMqrx-VIG81qkHKTPVlYMLCbAGWHftU3fyD4oIi_NXQI-aKEys6pyEsqwhIunJ49IWnAB9yL1RYPrQK9b3XIpTovKVqjchPVH0cGj70Q13FHlFxsyPH9b4zbZzPE1peny9mAne7FepO11dMH5KcOxTISUdOlCPofZpyyg";
    private static final String[] INITIAL_PERM = {Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int INITIAL_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        Button police = findViewById(R.id.police);
        Button fire = findViewById(R.id.fire);
        Button ems = findViewById(R.id.ems);

        //LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double longitude = -78.507977;// location.getLongitude();
        final double latitude = 38.033553;// location.getLatitude();

        police.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean[] servs = {true,false,false};
                Log.d("POLICE", "ButtonPress");
                new NetworkAsync().execute();
                //contactServices(latitude,longitude,servs,token);
            }
        });

        fire.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean[] servs = {false,true,false};
                contactServices(latitude,longitude,servs,token);
            }
        });

        ems.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean[] servs = {false,false,true};
                contactServices(latitude,longitude,servs,token);
            }
        });

    }

    public void contactServices(Double lat, Double lon, Boolean[] services, String token) {
        Log.d("EMERGENCY", "contactServices");
        Boolean police = services[0];
        Boolean fire = services[1];
        Boolean medical = services[2];
        //authenticate();
        String input = "Authorization: Bearer <" + token + ">{"
                + "\"services\": {"
                    + "\"police\":" + police.toString() + ","
                    + "\"fire\":" + fire.toString() + ","
                    + "\"medical\":" + medical.toString() + "},"
                + "\"location.coordinates\": {"
                    + "\"lat\":" + lat.toString() + ","
                    + "\"lng\":" + lon.toString() + ","
                    + "\"accuracy\": 50" + "}";


        try {
            String postUrl = "https://api-sandbox.safetrek.io/v1/alarms";// put in your url
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            Log.d("SAFETREK", String.valueOf(conn.getResponseCode()));

            conn.disconnect();

        } catch (Exception e) {
            Log.d ("SAFETREK", "EXCEPTION");
            e.printStackTrace();
        }

    }

}
