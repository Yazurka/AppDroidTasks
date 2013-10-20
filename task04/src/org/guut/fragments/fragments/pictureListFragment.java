package org.guut.fragments.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
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
    public int selectedPicture;
    public ArrayList<Integer> pictures;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("myDebug", "onCreateView running");
        fillList();
        Log.d("myDebug", "onCreateView returning");
        return inflater.inflate(R.layout.picturelistfragment, container, true);
    }

    private void fillList(){
        Log.d("myDebug", "fillList running");
        ArrayList<Integer> pictureNames = pictureNames();
        pictures = pictureNames;
        this.setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pictureNames));
        Log.d("myDebug", "fillList done");
    }

    private ArrayList<Integer> pictureNames() {
        Log.d("myDebug", "pictureNames running");
        ArrayList pictureNames = new ArrayList(5);
        pictureNames.add(R.drawable.pic);
        pictureNames.add(R.drawable.pic1);
        pictureNames.add(R.drawable.pic2);
        pictureNames.add(R.drawable.pic3);
        Log.d("myDebug", "pictureNames returning");
        return pictureNames;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("myDebug", "onListItemClick running");
        changeFragments(position);
        setSelectedPicture(position);
        super.onListItemClick(l, v, position, id);
        Log.d("myDebug", "onListItemClick done");
    }

    private void changeFragments(int position) {
        Activity a = getActivity();
        ImageView iv = (ImageView)a.findViewById(R.id.picture_imageView);
        TextView tw = (TextView)a.findViewById(R.id.picture_description);
        iv.setImageResource(findPictureId(position));
        tw.setText(findTextId(position));
    }

    public void changeFragmentPictureById(Integer i){
        setSelectedPicture(i);
        changeFragments(pictures.indexOf(selectedPicture));
    }

    private void setSelectedPicture(Integer i){
        if(pictures.contains(i))
            selectedPicture = i;
        else
            selectedPicture = pictures.get(0);
    }

    /*
     *  Not a very intuitive design, but it works!
     */
    private int findPictureId(int p) {
        Log.d("myDebug", "findPictureId running");
        switch (p){
            case 0:
                Log.d("myDebug", "findPictureId returning");
                return R.drawable.pic;
            case 1:
                Log.d("myDebug", "findPictureId returning");
                return R.drawable.pic1;
            case 2:
                Log.d("myDebug", "findPictureId returning");
                return R.drawable.pic2;
            case 3:
                Log.d("myDebug", "findPictureId returning");
                return R.drawable.pic3;
            default:
                Log.d("myDebug", "findPictureId returning");
                return R.drawable.pic;
        }
    }

    private int findTextId(int p) {
        Log.d("myDebug", "findTextId running");
        switch (p){
            case 0:
                Log.d("myDebug", "findTextId returning");
                return R.string.pic_0_description;
            case 1:
                Log.d("myDebug", "findTextId returning");
                return R.string.pic_1_description;
            case 2:
                Log.d("myDebug", "findTextId returning");
                return R.string.pic_2_description;
            case 3:
                Log.d("myDebug", "findTextId returning");
                return R.string.pic_3_description;
            default:
                Log.d("myDebug", "findTextId returning");
                return R.string.pic_not_found;
        }
    }
}