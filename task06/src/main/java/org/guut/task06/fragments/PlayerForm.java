package org.guut.task06.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.guut.task06.R;

/**
 * Class ${CLASS_NAME}
 *
 * @author Jørgen Lien Sellæg
 */
public class PlayerForm extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.player_form_fragment_layout, container, false);
    }
}
