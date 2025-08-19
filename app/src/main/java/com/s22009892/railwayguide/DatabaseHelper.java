package com.s22009892.railwayguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database and table information
    public static final String DATABASE_NAME = "Trains.db";
    public static final String TABLE_NAME = "train_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "STATION";
    public static final String COL_3 = "TRAIN";
    public static final String COL_4 = "ARRIVAL";
    public static final String COL_5 = "DESTINATION";
    public static final String COL_6 = "DEPARTURE";

    // Constructor to initialize the database helper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table with the specified columns
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, STATION TEXT, TRAIN TEXT, ARRIVAL TEXT, DESTINATION TEXT, DEPARTURE TEXT)");
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table if it exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert data into the database
    public boolean insertData(String station, String train, String arrival, String destination, String departure) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable instance of the database
        ContentValues contentValues = new ContentValues(); // Create a ContentValues object to hold the data
        contentValues.put(COL_2, station);
        contentValues.put(COL_3, train);
        contentValues.put(COL_4, arrival);
        contentValues.put(COL_5, destination);
        contentValues.put(COL_6, departure);
        long result = db.insert(TABLE_NAME, null, contentValues); // Insert the data into the table
        return result != -1; // Return true if the insertion was successful, false otherwise
    }

    // Method to retrieve all data from the database
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable instance of the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null); // Execute the query and return the result as a Cursor
    }

    // Method to retrieve data by station name
    public Cursor getDataByStation(String stationName) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable instance of the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ?", new String[]{stationName}); // Execute the query and return the result as a Cursor
    }

    // Method to update data in the database
    public boolean updateData(String id, String station, String train, String arrival, String destination, String departure) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable instance of the database
        ContentValues contentValues = new ContentValues(); // Create a ContentValues object to hold the data
        contentValues.put(COL_2, station);
        contentValues.put(COL_3, train);
        contentValues.put(COL_4, arrival);
        contentValues.put(COL_5, destination);
        contentValues.put(COL_6, departure);
        int rowsUpdated = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id}); // Update the data in the table
        Log.d("DatabaseHelper", "Rows updated: " + rowsUpdated); // Log the number of rows updated
        return rowsUpdated > 0; // Return true if at least one row was updated, false otherwise
    }

    // Method to delete data from the database
    public int deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable instance of the database
        int rowsDeleted = db.delete(TABLE_NAME, "ID = ?", new String[]{id}); // Delete the data from the table
        Log.d("DatabaseHelper", "Rows deleted: " + rowsDeleted); // Log the number of rows deleted
        return rowsDeleted; // Return the number of rows deleted
    }
}
