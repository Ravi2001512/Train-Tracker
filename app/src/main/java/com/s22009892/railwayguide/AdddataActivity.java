package com.s22009892.railwayguide;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdddataActivity extends AppCompatActivity {
    DatabaseHelper myDb;  // Database helper object to interact with the database
    EditText editTextStation, editTexttrain, editTextrainTime, editTextdeparture, editTextreason, editTextupdate; // EditText fields for user input
    Button btnAdd, btnview, btnupdate, btndelete;  // Buttons for different operations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        myDb = new DatabaseHelper(this);  // Initialize the database helper

        // Linking EditText fields with their respective UI elements
        editTextStation = findViewById(R.id.station);
        editTexttrain = findViewById(R.id.train);
        editTextrainTime = findViewById(R.id.trainArive);
        editTextdeparture = findViewById(R.id.trainDeparture);
        editTextreason = findViewById(R.id.reason);
        editTextupdate = findViewById(R.id.updateDelete);

        // Linking Buttons with their respective UI elements
        btnAdd = findViewById(R.id.add);
        btnview = findViewById(R.id.view);
        btnupdate = findViewById(R.id.update);
        btndelete = findViewById(R.id.delete);

        // Setting up the button click listeners
        insertData();
        viewAll();
        updateData();
        deleteData();
    }

    // Method to insert data into the database
    public void insertData() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Attempt to insert data and show a toast message based on the result
                boolean isInserted = myDb.insertData(editTextStation.getText().toString(),
                        editTexttrain.getText().toString(),
                        editTextrainTime.getText().toString(),
                        editTextdeparture.getText().toString(),
                        editTextreason.getText().toString());

                if (isInserted)
                    Toast.makeText(AdddataActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AdddataActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Method to view all data from the database
    public void viewAll() {
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();  // Retrieve all data from the database
                if (res.getCount() == 0) {
                    // Show an error message if no data is found
                    showMessage("Error", "Nothing found");
                    return;
                }

                // StringBuffer to build the display message
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Station :" + res.getString(1) + "\n");
                    buffer.append("Train :" + res.getString(2) + "\n");
                    buffer.append("Arrival :" + res.getString(3) + "\n");
                    buffer.append("Departure :" + res.getString(4) + "\n");
                    buffer.append("Reason :" + res.getString(5) + "\n\n");
                }

                // Display the data using an AlertDialog
                showMessage("Data", buffer.toString());
            }
        });
    }

    // Method to update data in the database
    public void updateData() {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextupdate.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(AdddataActivity.this, "Please enter a valid ID", Toast.LENGTH_LONG).show();
                    return;
                }
                // Attempt to update data and show a toast message based on the result
                boolean isUpdate = myDb.updateData(id,
                        editTextStation.getText().toString(),
                        editTexttrain.getText().toString(),
                        editTextrainTime.getText().toString(),
                        editTextdeparture.getText().toString(),
                        editTextreason.getText().toString());

                if (isUpdate) {
                    Toast.makeText(AdddataActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AdddataActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Method to delete data from the database
    public void deleteData() {
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextupdate.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(AdddataActivity.this, "Please enter a valid ID", Toast.LENGTH_LONG).show();
                    return;
                }
                // Attempt to delete data and show a toast message based on the result
                int deletedRows = myDb.deleteData(id);

                if (deletedRows > 0) {
                    Toast.makeText(AdddataActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AdddataActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Method to display a message using an AlertDialog
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
