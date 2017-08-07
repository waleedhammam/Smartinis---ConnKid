package com.smartinis.connkid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class KidLocation extends AppCompatActivity implements OnMapReadyCallback {

    TextView loc;
    Button GetServerData;
    double[] latlong = new double[]{31.270169, 30.003249};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_location);

        loc = (TextView) findViewById(R.id.loc);
        GetServerData = (Button) findViewById(R.id.getData);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);

        GetServerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String serverURL = "http://192.168.43.130:5000/gps";
                new GetData().execute(serverURL);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap map) {
        System.out.println("lat is" + latlong[0] + "long is" + latlong[1]);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latlong[0], latlong[1]))
                .title("Kid Location"));
    }

    private void processValue(String val)
    {
        if (val.charAt(0) == '$'){
            // $GPGLL,3126.87,N,3000.62,E*77
            if (val.substring(1, 6) == ("GPGLL")){
                String[] parts = val.split(",");
                latlong[0] = Double.parseDouble(parts[2]); // 3723.465874
                String p3 = parts[3]; // N
                latlong[1] = Double.parseDouble(parts[4]); // 12202.26954
                String p5 = parts[5]; // W
            }
            else {
                Toast.makeText(getApplicationContext(), "getting location", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Receiving data", Toast.LENGTH_SHORT).show();
        }
    }

    public class GetData extends AsyncTask<String, Void, String> {
        TextView loc = (TextView) findViewById(R.id.loc);
        String res;
        private ProgressDialog Dialog = new ProgressDialog(KidLocation.this);

        protected void onPreExecute() {

            Dialog.setMessage("Getting Location..");
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
                res = builder.toString();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loc.setText("output: " + res);
            Dialog.dismiss();
            System.out.println("res is" + res);
            processValue(res);
        }
    }

}