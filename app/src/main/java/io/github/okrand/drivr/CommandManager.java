//package io.github.okrand.drivr;
//
//import android.net.Uri;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Scanner;
//import java.util.List;
//import java.util.ArrayList;
//import android.content.Intent;
//
//public class CommandManager {
//
//    private CommandManager commandManager = CommandManager.getInstance();
//
//    private static CommandManager instance =
//            new CommandManager();
//
//    private CommandManager() {}
//
//    /**
//     * @return Command Manager object.
//     */
//    public static CommandManager getInstance() {
//        return instance;
//    }
//
//    /**
//     * @param input from frontend space in form of ("License Number" "State" "dec1" "dec2")
//     */
//    public void processInput(String input) {
//        String[] tokens = input.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//        String state = tokens[0];
//        String license = tokens[1];
//        String numClaim = tokens[2] + tokens[3];
//        Report report = generateReport(state, license, numClaim);
//        storeReport(report);
//    }
//
//    /**
//     * @param state of license
//     * @param license number
//     * @param claim as numbers
//     * @return new Report object
//     */
//    public Report generateReport(String state, String license, String claim, String option) {
//        Scanner scanner = new Scanner(claim);
//        List<Integer> numericClaim = new ArrayList<Integer>();
//        while (scanner.hasNextInt()) {
//            numericClaim.add(scanner.nextInt());
//        }
//        DecisionTree dTree = DecisionTree.getInstance();
//        String claimDescription = dTree.generateClaim(numericClaim);
//        return new Report(state, license, claim, option);
//    }
//
//    /**
//     * @param report to store
//     * Method that stores a report in the database
//     */
//    public void storeReport(Report report) {}
//
//    /**
//     * @param coords [lat, long]
//     * @param services true or false [police, fire, medical]
//     */
//    public String contactServices(Double[] coords, String[] services, String token) {
//        double lat = coords[0];
//        double lon = coords[1];
//        String police = services[0];
//        String fire = services[1];
//        String medical = services[2];
//        // authenticate();
//        String input = "Authorization: Bearer + " + token + "{"
//                + "\"services\": {"
//                    + "\"police\":" + police + ","
//                    + "\"fire\":" + fire + ","
//                    + "\"medical\":" + medical + "},"
//                + "\"location.coordinates\": {"
//                    + "\"lat\":" + lat + ","
//                    + "\"lng\":" + lon + ","
//                    + "\"accuracy\": 50" + "}";
//
//
//        try {
//            String postUrl = "https://api-sandbox.safetrek.io/v1";// put in your url
//            URL url = new URL(postUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(false);
//            conn.setDoInput(true);
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestMethod("POST");
//
//            OutputStream os = conn.getOutputStream();
//            os.write(input.getBytes());
//            os.flush();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
//
//            System.out.print(conn.getResponseCode());
//
//            conn.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public void authenticate() {
//        //Uri uri = getIntent().getData();
//
//        String input = "{"
//                + "\"grant_type\": " + "authorization_code" + ","
//                + "\"code\":" + "authorization_code" + ","
//                + "\"client_id\": m5qXF5ztOdT4cdQtUbZT2grBhF187vw6 ,"
//                + "\"client_secret\": 3DLoc7yAVfRYALPgby6fz5kNwgniKoHrJOi2BMDtQzFRIE1YyRvmKjb7_NyupHHE ,"
//                + "\"redirect_uri\": drivr://callback + }";
//
//        try {
//            String postUrl = "https://api-sandbox.safetrek.io/v1";// put in your url
//            URL url = new URL(postUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(false);
//            conn.setDoInput(true);
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestMethod("POST");
//
//            OutputStream os = conn.getOutputStream();
//            os.write(input.getBytes());
//            os.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
