package com.example.erik.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Erik on 21-3-2016.
 */
public class PreferencesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener{

        private SeekBarPreference _seekBarPref;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_screen);

            // Get widgets :
            _seekBarPref = (SeekBarPreference) this.findPreference("number_of_pokemons");

            // Set listener :
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

            // Set seekbar summary :
            int radius = PreferenceManager.getDefaultSharedPreferences(this.getActivity()).getInt("number_of_pokemons", 151);
            _seekBarPref.setSummary(this.getString(R.string.settings_summary).replace("$1", ""+radius));
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            // Set seekbar summary :
            int radius = PreferenceManager.getDefaultSharedPreferences(this.getActivity()).getInt("number_of_pokemons", 151);
            _seekBarPref.setSummary(this.getString(R.string.settings_summary).replace("$1", ""+radius));
        }
    }



}
