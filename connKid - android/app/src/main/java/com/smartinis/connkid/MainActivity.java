package com.smartinis.connkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button kidLocationButton, kidTempButton, liveStreamButton, speakButton, kidHeartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kidLocationButton = (Button) findViewById(R.id.kidLocationButton);
        kidTempButton = (Button) findViewById(R.id.kidTempButton);
        liveStreamButton = (Button) findViewById(R.id.liveStreamButton);
        speakButton = (Button) findViewById(R.id.speakButton);
        kidHeartButton = (Button) findViewById(R.id.kidHeartButton);

        kidLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent location = new Intent(MainActivity.this, KidLocation.class);
                startActivity(location);
            }
        });

        kidTempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent health = new Intent(MainActivity.this, KidHealth.class);
                startActivity(health);
            }
        });

        liveStreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stream = new Intent(MainActivity.this, LiveStram.class);
                startActivity(stream);
            }
        });

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent speak = new Intent(MainActivity.this, SpeakActivity.class);
                startActivity(speak);
            }
        });

        kidHeartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent heart = new Intent(MainActivity.this, KidHeart.class);
                startActivity(heart);
            }
        });
    }
}
