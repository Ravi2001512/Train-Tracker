package com.s22009892.railwayguide;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class TicketActivity extends AppCompatActivity {
    // Declare WebView to display the ticket
    private WebView web_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        // Initialize WebView
        web_ticket = findViewById(R.id.ticketweb);

        // Enable JavaScript in WebView settings
        web_ticket.getSettings().setJavaScriptEnabled(true);

        // Load the URL of the ticket
        web_ticket.loadUrl("https://drive.google.com/file/d/1wnJS4vq1d47kSFSYtEFucUBOZnjRNyT6/view?usp=sharing");
    }
}
