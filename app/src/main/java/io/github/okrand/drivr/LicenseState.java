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
import java.util.Objects;

public class LicenseState extends AppCompatActivity{

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int CODE_OPTIONS_RETURN = 200;
    private static int TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        final EditText txtLicenseState = (EditText) findViewById(R.id.edit_state);
        txtLicenseState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtLicenseState.performClick();}
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
                    EditText txtSpeechInput = (EditText) findViewById(R.id.edit_state);
                    String res = result.get(0);
                    txtSpeechInput.setText(res);

                    //Get Report
                    Bundle bundle = getIntent().getExtras();
                    final Report r = bundle.getParcelable("report");
                    r.setState(res); //set license plate of report

                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Objects.equals(r.getClaim(), "" + getResources().getString(R.string.bad_driver))) {
                                Intent intent = new Intent(LicenseState.this, DriverOptions.class);
                                intent.putExtra("report", r);
                                startActivityForResult(intent, CODE_OPTIONS_RETURN);
                            }
                            else if (Objects.equals(r.getClaim(), "" + getResources().getString(R.string.bad_car))) {
                                Intent intent = new Intent(LicenseState.this, CarOptions.class);
                                intent.putExtra("report", r);
                                startActivityForResult(intent, CODE_OPTIONS_RETURN);
                            }
                        }
                    }, TIME_OUT);
                }
                break;
            }

            case CODE_OPTIONS_RETURN: { //Return from options
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
