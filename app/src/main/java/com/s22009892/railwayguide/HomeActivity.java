package com.s22009892.railwayguide;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    // Declare buttons for different activities
    private Button shedule;
    private Button tickets;
    private Button stationNumbers;
    private Button weather;
    private Button settings;
    private Button contactUs;
    private Button map;
    private Button temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize buttons by finding them in the layout
        shedule = findViewById(R.id.shedule);
        tickets = findViewById(R.id.tickets);
        stationNumbers = findViewById(R.id.stationNumbers);
        weather = findViewById(R.id.weather);
        settings = findViewById(R.id.settings);
        contactUs = findViewById(R.id.contactUs);
        map = findViewById(R.id.map);
        temperature = findViewById(R.id.temperature);

        // Set OnClickListener for the schedule button
        shedule.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                // Start the SheduleActivity
                Intent intent = new Intent(HomeActivity.this, SheduleActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the tickets button
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the TicketActivity
                Intent intent = new Intent(HomeActivity.this, TicketActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the station numbers button
        stationNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ContactNumberActivity
                Intent intent = new Intent(HomeActivity.this, ContactNumberActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the weather button
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the WeatherActivity
                Intent intent = new Intent(HomeActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the settings button
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SettingsActivity
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the contact us button
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ContactUsActivity
                Intent intent = new Intent(HomeActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the map button
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MapActivity
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the temperature button
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the DetectTempActivity
                Intent intent = new Intent(HomeActivity.this, DetectTempActivity.class);
                startActivity(intent);
            }
        });
    }
}
