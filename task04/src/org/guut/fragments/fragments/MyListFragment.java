package org.guut.fragments.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.guut.fragments.R;

import java.util.ArrayList;

public class MyListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fillList();
        return inflater.inflate(R.layout.mylistfragment, container, false);
    }

    private void fillList(){
        ArrayList<String> picnames = new ArrayList<>(findViewById(R.array.picture_names));
        ArrayAdapter<String> list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  );

    }
}