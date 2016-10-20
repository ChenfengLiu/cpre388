package com.example.mediaplayerpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class MediaPreference extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MediaPreferencesFragment())
                .commit();
    }

    public static class MediaPreferencesFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Apply default values
            PreferenceManager.setDefaultValues(getActivity(), R.xml.media_preferences, false);
            //load preferences from xml resourse
            addPreferencesFromResource(R.xml.media_preferences);
        }
    }
}
