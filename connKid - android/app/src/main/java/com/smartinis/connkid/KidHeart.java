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

public class KidHeart extends AppCompatActivity {

    Button getHeart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_heart);

        getHeart = (Button) findViewById(R.id.getHeart);
        getHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverURL = "http://192.168.43.130:5000/heart";
                Toast.makeText(getApplicationContext(), "Getting Heart State", Toast.LENGTH_SHORT).show();
                new GetHeart().execute(serverURL);
            }
        });
    }
    public class GetHeart extends AsyncTask<String, Void, String> {
        TextView heartState = (TextView) findViewById(R.id.heartState);
        String heart;
        private ProgressDialog Dialog = new ProgressDialog(KidHeart.this);

        protected void onPreExecute() {

            Dialog.setMessage("Getting Heart State..");
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
                heart = builder.toString();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Dialog.dismiss();
            heartState.setText("output: " + heart);
        }
    }
}
