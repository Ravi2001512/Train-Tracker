package com.s22009892.railwayguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewUpdateActivity extends AppCompatActivity {

    // Declare the back button ImageView
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update);

        // Initialize the TextView for displaying the schedule data
        TextView scheduleTextView = findViewById(R.id.scheduleTextView);
        // Initialize the back button ImageView
        back = findViewById(R.id.back);

        // Get the data from the intent
        String scheduleData = getIntent().getStringExtra("schedule_data");
        // Display the schedule data
        scheduleTextView.setText(scheduleData);

        // Set an onClickListener for the back button to navigate to SheduleActivity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewUpdateActivity.this, SheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}
