package com.smartinis.connkid;

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

public class KidHealth extends AppCompatActivity {

    Button getTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_health);
        getTemp = (Button) findViewById(R.id.getTemp);
        getTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverURL = "http://192.168.43.130:5000/temp";
                Toast.makeText(getApplicationContext(), "Getting Temp", Toast.LENGTH_SHORT).show();
                new GetTemp().execute(serverURL);
            }
        });
    }

    public class GetTemp extends AsyncTask<String, Void, String> {
        TextView kidTemp = (TextView) findViewById(R.id.kidTemp);
        String temp;

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
                temp = builder.toString();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            kidTemp.setText("output: " + temp);
        }
    }

}
