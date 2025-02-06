package com.daniibarra.ast3roides;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new
                        PreferencesFragment())
                .commit();

    }


}