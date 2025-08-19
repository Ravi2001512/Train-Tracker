package com.s22009892.railwayguide;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Declare WebView variable
        WebView contact_Numbers;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_number); // Set the content view to the corresponding layout

        // Initialize the WebView by finding it in the layout
        contact_Numbers = findViewById(R.id.contactNumber);

        // Enable JavaScript in the WebView settings
        contact_Numbers.getSettings().setJavaScriptEnabled(true);

        // Load the specified URL into the WebView
        contact_Numbers.loadUrl("https://drive.google.com/file/d/1I7Tpsu2RhKw-2XEn6iMnLKAEWkzlgKC9/view?usp=drive_link");
    }
}
