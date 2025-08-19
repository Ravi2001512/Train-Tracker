package com.s22009892.railwayguide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText editTxtStart, editTxtDestination;
    private ImageView back;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize UI elements
        editTxtStart = findViewById(R.id.editTxtStart);
        editTxtDestination = findViewById(R.id.editTxtDestination);
        btnSearch = findViewById(R.id.btnSearch);
        back = findViewById(R.id.back);

        // Set onClickListener for the button
        btnSearch.setOnClickListener(v -> {
            // Get the text from EditText fields
            String startingPoint = editTxtStart.getText().toString();
            String endpoint = editTxtDestination.getText().toString();

            // Check if any of the fields are empty
            if (startingPoint.equals("") || endpoint.equals("")) {
                Toast.makeText(this, "Please Enter Starting location & destination", Toast.LENGTH_SHORT).show();
            } else {
                // Call the method to get the path
                getPath(startingPoint, endpoint);
            }
        });

        // Set onClickListener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to HomeActivity
                Intent intent = new Intent(MapActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to get the path using Google Maps
    private void getPath(String startingPoint, String endpoint) {
        try {
            // Construct the URI for the directions
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + startingPoint + "/" + endpoint);
            // Create an Intent to view the directions
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            // Handle case where Google Maps is not installed
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en&gl=US");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
