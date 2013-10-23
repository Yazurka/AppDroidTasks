package org.guut.task05;

import android.app.Activity;
import android.os.Bundle;

import org.guut.task05.fragments.SettingsFragment;

public class SettingsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }


}