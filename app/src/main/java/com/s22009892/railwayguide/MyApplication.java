package com.s22009892.railwayguide;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Retrieve the dark mode preference from SharedPreferences
        boolean isDarkMode = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark_mode", false);

        // Set the default night mode based on the saved preference
        if (isDarkMode) {
            // Enable dark mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Disable dark mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
