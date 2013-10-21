package org.guut.birthdayfriend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.guut.person.Person;

import java.util.ArrayList;

public class main extends Activity {
    ArrayList<Person> friends = new ArrayList<>();
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void addFriend(View view) {
        String friendName = ((EditText)findViewById(R.id.person_name)).getText().toString();
        String friendBirthday = ((EditText)findViewById(R.id.date_selector)).getText().toString();
        friends.add(new Person(friendName, friendBirthday));
        Toast.makeText(getApplicationContext(), R.string.add_birthday_success, Toast.LENGTH_LONG).show();
    }

    public void showFriends(View view) {
        Intent intent = new Intent("org.guut.BirthdayList");
        intent.putExtra("friends", friends);
        intent.putExtra("source", "showFriend");
        Log.d("MyDebugApp", "ShowFriends before");
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        if (d.getWidth() > d.getHeight()) {
            //Landscape
            if(friends != null){
                ArrayAdapter la = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);
                ListView lw = (ListView)findViewById(R.id.vertical_list);
                lw.setAdapter(la);
                lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(
                                getApplicationContext(),
                                friends.get(i).getDob(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(this, "List is empty", Toast.LENGTH_LONG).show();
            }
        }
        super.onResume();
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
}
