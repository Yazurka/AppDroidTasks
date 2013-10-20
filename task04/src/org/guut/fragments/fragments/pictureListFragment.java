package org.guut.fragments.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.guut.fragments.R;

import java.util.ArrayList;

public class pictureListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fillList();
        return inflater.inflate(R.layout.pictureListFragment, container, false);
    }

    private void fillList(){
        ArrayList<Integer> pictureNames = pictureNames();
        this.setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pictureNames));
    }

    private ArrayList<Integer> pictureNames() {
        ArrayList pictureNames = new ArrayList(5);
        pictureNames.add(R.drawable.pic);
        pictureNames.add(R.drawable.pic1);
        pictureNames.add(R.drawable.pic2);
        pictureNames.add(R.drawable.pic3);
        return pictureNames;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Activity a = getActivity();
        ImageView iv = (ImageView)a.findViewById(R.id.picture_imageView);
        TextView tw = (TextView)a.findViewById(R.id.picture_description);
        iv.setImageResource(position);
        tw.setText(findTextId(position));
        super.onListItemClick(l, v, position, id);
    }

    private int findTextId(int p) {
        switch (p){
            case 0:
                return R.string.pic_0_description;
            case 1:
                return R.string.pic_1_description;
            case 2:
                return R.string.pic_2_description;
            case 3:
                return R.string.pic_3_description;
            default:
                return R.string.pic_not_found;
        }
    }
}