package com.s22009892.railwayguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SheduleActivity extends AppCompatActivity {

    // Declare UI elements and database helper
    Button login, view, move;
    ImageView back;
    DatabaseHelper myDb;
    EditText stationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule);

        // Initialize the DatabaseHelper
        myDb = new DatabaseHelper(this);

        // Bind UI elements to their respective views
        login = findViewById(R.id.login);
        view = findViewById(R.id.view);
        move = findViewById(R.id.schedule);
        back = findViewById(R.id.back);
        stationName = findViewById(R.id.stationName);

        // Set up the onClick listener for the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                Intent intent = new Intent(SheduleActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Set up the onClick listener for the move button
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ViewSchduleActivity
                Intent intent = new Intent(SheduleActivity.this, ViewSchduleActivity.class);
                startActivity(intent);
            }
        });

        // Set up the onClick listener for the view button
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch and display data based on the station name
                String station = stationName.getText().toString();
                viewAll(station);
            }
        });

        // Set up the onClick listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to HomeActivity
                Intent intent = new Intent(SheduleActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to fetch and display data based on the station name
    public void viewAll(String stationName) {
        Cursor res;
        // Fetch data based on whether the station name is provided or not
        if (stationName.isEmpty()) {
            res = myDb.getAllData();
        } else {
            res = myDb.getDataByStation(stationName);
        }

        // Check if data was found
        if (res.getCount() == 0) {
            // Show a message if no data was found
            showMessage("Error", "No data found for station: " + stationName);
            return;
        }

        // Build a string of the data to be displayed
        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Station :").append(res.getString(1)).append("\n");
            buffer.append("Train :").append(res.getString(2)).append("\n");
            buffer.append("Arrival :").append(res.getString(3)).append("\n");
            buffer.append("Departure :").append(res.getString(4)).append("\n");
            buffer.append("Reason :").append(res.getString(5)).append("\n\n");
        }

        // Pass the schedule data to the ViewUpdateActivity
        Intent intent = new Intent(SheduleActivity.this, ViewUpdateActivity.class);
        intent.putExtra("schedule_data", buffer.toString());
        startActivity(intent);
    }

    // Method to show a message in an alert dialog
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
