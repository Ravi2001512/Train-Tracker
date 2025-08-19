package com.s22009892.railwayguide;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetectTempActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView; // TextView to display the temperature
    private ImageView back; // ImageView for the back button
    private SensorManager sensorManager; // SensorManager to manage the sensors
    private Sensor tempSensor; // Sensor for the ambient temperature
    private Boolean isTemperatureSensorAvailable; // Boolean to check if the temperature sensor is available

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_temp); // Set the content view to the corresponding layout

        textView = findViewById(R.id.temperature); // Initialize the TextView by finding it in the layout
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // Get the SensorManager from the system service
        back = findViewById(R.id.back); // Initialize the back button by finding it in the layout

        // Check if the ambient temperature sensor is available
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE); // Get the temperature sensor
            isTemperatureSensorAvailable = true; // Set the flag to true
        } else {
            textView.setText("Temperature sensor is not Available"); // Display a message if the sensor is not available
            isTemperatureSensorAvailable = false; // Set the flag to false
        }

        // Set an OnClickListener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the HomeActivity
                Intent intent = new Intent(DetectTempActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Update the TextView with the current temperature when the sensor value changes
        textView.setText(event.values[0] + "°C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // This method is called when the sensor accuracy changes. It's not used in this example.
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener when the activity is resumed
        if (isTemperatureSensorAvailable) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener when the activity is paused
        if (isTemperatureSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }
}
