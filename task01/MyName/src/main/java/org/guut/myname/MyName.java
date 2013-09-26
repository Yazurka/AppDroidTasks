package org.guut.myname;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_name, menu);
        return true;
    }
    
}
