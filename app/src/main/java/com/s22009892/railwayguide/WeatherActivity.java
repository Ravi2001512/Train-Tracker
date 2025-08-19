package com.s22009892.railwayguide;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Declare the WebView for displaying the weather information
        WebView web_weather;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Initialize the WebView
        web_weather = findViewById(R.id.webweather);

        // Enable JavaScript in WebView settings
        web_weather.getSettings().setJavaScriptEnabled(true);

        // Load the weather information URL
        web_weather.loadUrl("https://www.meteo.gov.lk/index.php?lang=en");
    }
}
