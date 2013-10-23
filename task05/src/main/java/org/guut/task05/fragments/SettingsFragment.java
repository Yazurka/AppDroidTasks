package org.guut.task05.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import no.hist.itfag.books.R;


/**
 * Class ${CLASS_NAME}
 * @author Jørgen Lien Sellæg
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
