package org.guut.birthdayfriend;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import org.guut.person.Person;

public class BirthdayList extends ListActivity {
    private ArrayList<Person> friends = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent g = getIntent();
        switch (g.getStringExtra("source")){
            case "showFriend":
                Log.d("MyDebugApp", "Case showFriend");
                friends = (ArrayList<Person>)g.getSerializableExtra("friends");
                setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends));
            break;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        Log.d("MyD", "OnRestore called");
        super.onRestoreInstanceState(state);
        friends = (ArrayList<Person>)state.getSerializable("friends");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("MyD", "OnSave called");
        outState.putSerializable("friends", friends);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("myDebug", position+"");
        Toast.makeText(getApplicationContext(), friends.get(position).getDob(), Toast.LENGTH_LONG).show();
    }
}