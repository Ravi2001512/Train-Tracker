package com.s22009892.railwayguide;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewSchduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Declare WebView to display the schedule
        WebView schedule;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schdule);

        // Initialize WebView
        schedule = findViewById(R.id.schedule);

        // Enable JavaScript in WebView settings
        schedule.getSettings().setJavaScriptEnabled(true);

        // Load the URL of the train schedule
        schedule.loadUrl("https://trainschedule.lk/");
    }
}
