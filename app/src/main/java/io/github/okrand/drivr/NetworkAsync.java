package io.github.okrand.drivr;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkAsync extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        final double longitude = -78.507977;// location.getLongitude();
        final double latitude = 38.033553;// location.getLatitude();
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik5FWTBPVVV3TVRSRU5qUTRSVUZDTkVJd01rUTBSVEUwUVRJMFF6ZzRSVGc1T0RBMFJEWXhOUSJ9.eyJodHRwOi8vY2xpZW50LW5hbWUiOiJIQUNLX1VWQSIsImlzcyI6Imh0dHBzOi8vbG9naW4tc2FuZGJveC5zYWZldHJlay5pby8iLCJzdWIiOiJzbXN8NWFiNzEwMDhhNjgwM2E5MTkxMWU3NDA0IiwiYXVkIjpbImh0dHBzOi8vYXBpLXNhbmRib3guc2FmZXRyZWsuaW8iLCJodHRwczovL3NhZmV0cmVrLXNhbmRib3guYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTUyMTk4ODA4NywiZXhwIjoxNTIyMDI0MDg3LCJhenAiOiJtNXFYRjV6dE9kVDRjZFF0VWJaVDJnckJoRjE4N3Z3NiIsInNjb3BlIjoib3BlbmlkIHBob25lIG9mZmxpbmVfYWNjZXNzIn0.xUE5oLwjuom_HpxI3DScRDbreLKB8uj3q9_jcZ0irT2kdbzzmirUifzc-lX-qkDmSOY7Eich3kcA7ShMLDpkvEZ0gv_d_bFrC2aJ-9EDaanY96l8Jfw4HLBElWQYGqhqq7Ml6XPthNpXF8Qlz7IwW2g5mdyp07HPEsVFKy3_muZh90DBcfMqrx-VIG81qkHKTPVlYMLCbAGWHftU3fyD4oIi_NXQI-aKEys6pyEsqwhIunJ49IWnAB9yL1RYPrQK9b3XIpTovKVqjchPVH0cGj70Q13FHlFxsyPH9b4zbZzPE1peny9mAne7FepO11dMH5KcOxTISUdOlCPofZpyyg";
        Boolean[] servs = {true,false,false};
        contactServices(latitude,longitude,servs,token);
        return null;


    }

    private void contactServices(Double lat, Double lon, Boolean[] services, String token) {
        Log.d("EMERGENCY", "contactServices");
        Boolean police = services[0];
        Boolean fire = services[1];
        Boolean medical = services[2];
        String input = "Authorization: Bearer <" + token + "> Content-Type: application/json {"
                + "'services': {"
                + "'police':" + police + ","
                + "'fire':" + fire + ","
                + "'medical':" + medical + "},"
                + "'location.coordinates': {"
                + "'lat':" + lat + ","
                + "'lng':" + lon + ","
                + "'accuracy': 5" + "}";


        try {
            String postUrl = "https://api-sandbox.safetrek.io/v1/alarms";// put in your url
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            //conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }

            Log.d("SAFETREK", String.valueOf(conn.getResponseCode()));

            conn.disconnect();

        } catch (Exception e) {
            Log.d ("SAFETREK", "EXCEPTION");
            e.printStackTrace();
        }

    }
}
