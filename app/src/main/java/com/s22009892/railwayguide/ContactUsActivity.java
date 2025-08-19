package com.s22009892.railwayguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    // Declare ImageView variables for the social media buttons and the back button
    ImageView fb, insta, twitter, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us); // Set the content view to the corresponding layout

        // Initialize ImageView variables by finding them in the layout
        fb = findViewById(R.id.btnfb);
        insta = findViewById(R.id.btninsta);
        twitter = findViewById(R.id.btnx);
        back = findViewById(R.id.back);

        // Set an OnClickListener for the Facebook button
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Facebook profile URL
                gotoUrl("https://www.facebook.com/profile.php?id=100079929871912");
            }
        });

        // Set an OnClickListener for the Instagram button
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Instagram profile URL
                gotoUrl("https://www.instagram.com/deshan_raviii/");
            }
        });

        // Set an OnClickListener for the Twitter button
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Twitter profile URL
                gotoUrl("https://x.com/deshan_ravi");
            }
        });

        // Set an OnClickListener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the HomeActivity
                Intent intent = new Intent(ContactUsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to open a URL in a web browser
    private void gotoUrl(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
