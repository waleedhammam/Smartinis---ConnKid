package com.smartinis.connkid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpeakActivity extends AppCompatActivity {

    Button speaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        speaker = (Button) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverURL = "http://192.168.43.130:5000/speaker";
                Toast.makeText(getApplicationContext(), "Sending audio", Toast.LENGTH_SHORT).show();
                new speaker().execute(serverURL);
            }
        });
    }

    public class speaker extends AsyncTask<String, Void, String> {
        TextView speakerText = (TextView) findViewById(R.id.speakerText);
        String speakerRes;
        private ProgressDialog Dialog = new ProgressDialog(SpeakActivity.this);

        protected void onPreExecute() {

            Dialog.setMessage("Sending audio..");
            Dialog.show();
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }
                speakerRes = builder.toString();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            speakerText.setText("output: " + speakerRes);
            Dialog.dismiss();
        }
    }
}
