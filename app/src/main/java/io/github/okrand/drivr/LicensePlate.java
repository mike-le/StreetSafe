package io.github.okrand.drivr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Krando67 on 3/24/18.
 */

public class LicensePlate extends AppCompatActivity{

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int CODE_STATE_RETURN = 200;
    private static int TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);

        final EditText txtLicensePlate = (EditText) findViewById(R.id.edit_plate);
        txtLicensePlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
            txtLicensePlate.performClick();}
        }, TIME_OUT);
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak now!");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Don't Speak, I know what you're thinking",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @SuppressLint("ShowToast")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: { // Result of speech to text
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditText txtSpeechInput = (EditText) findViewById(R.id.edit_plate);
                    String res = result.get(0).replaceAll("\\s+","").toUpperCase();
                    txtSpeechInput.setText(res);

                    //Get Report
                    Bundle bundle = getIntent().getExtras();
                    final Report r = bundle.getParcelable("report");
                    r.setLicense(res); //set license plate of report

                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LicensePlate.this, LicenseState.class);
                            intent.putExtra("report", r);
                            startActivityForResult(intent, CODE_STATE_RETURN);
                        }
                    }, TIME_OUT);
                }
                break;
            }
            case CODE_STATE_RETURN: { //Return from state
                if (resultCode == RESULT_OK && null != data) {
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
