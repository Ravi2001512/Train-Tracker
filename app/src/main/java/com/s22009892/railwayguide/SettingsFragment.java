package com.s22009892.railwayguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreferenceCompat;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load preferences from XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Set up individual preferences
        SwitchPreferenceCompat darkModePreference = findPreference("dark_mode");
        SeekBarPreference brightnessPreference = findPreference("brightness");
        ListPreference orientationPreference = findPreference("screen_orientation");

        // Dark mode preference
        if (darkModePreference != null) {
            darkModePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isDarkMode = (boolean) newValue;
                applyDarkMode(isDarkMode);
                return true;
            });
        }

        // Brightness preference
        if (brightnessPreference != null) {
            brightnessPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                int brightness = (int) newValue;
                applyBrightness(brightness);
                return true;
            });
        }

        // Screen orientation preference
        if (orientationPreference != null) {
            orientationPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                String orientation = (String) newValue;
                applyScreenOrientation(orientation);
                return true;
            });
        }

        // Register listener to handle changes in preferences
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        // Apply settings based on current preferences
        applySettingsFromPreferences();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister listener to prevent memory leaks
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Handle individual preference changes
        if ("dark_mode".equals(key)) {
            boolean isDarkMode = sharedPreferences.getBoolean(key, false);
            applyDarkMode(isDarkMode);
        } else if ("brightness".equals(key)) {
            int brightness = sharedPreferences.getInt(key, 128);
            applyBrightness(brightness);
        } else if ("screen_orientation".equals(key)) {
            String orientation = sharedPreferences.getString(key, "auto");
            applyScreenOrientation(orientation);
        }
    }

    private void applySettingsFromPreferences() {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        // Apply dark mode based on saved preference
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        applyDarkMode(isDarkMode);

        // Apply brightness based on saved preference
        int brightness = sharedPreferences.getInt("brightness", 128);
        applyBrightness(brightness);

        // Apply screen orientation based on saved preference
        String orientation = sharedPreferences.getString("screen_orientation", "auto");
        applyScreenOrientation(orientation);
    }

    private void applyDarkMode(boolean isDarkMode) {
        // Set the application’s default night mode based on preference
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void applyBrightness(int brightness) {
        // Adjust screen brightness
        WindowManager.LayoutParams layoutParams = getActivity().getWindow().getAttributes();
        layoutParams.screenBrightness = brightness / 255.0f; // Brightness should be a float between 0 and 1
        getActivity().getWindow().setAttributes(layoutParams);
    }

    private void applyScreenOrientation(String orientation) {
        // Set the screen orientation based on preference
        if ("portrait".equals(orientation)) {
            getActivity().setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
        } else if ("landscape".equals(orientation)) {
            getActivity().setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
        } else {
            getActivity().setRequestedOrientation(Configuration.ORIENTATION_UNDEFINED); // Auto or undefined orientation
        }
    }
}
