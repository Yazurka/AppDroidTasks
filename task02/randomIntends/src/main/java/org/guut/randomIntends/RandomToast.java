package org.guut.randomIntends;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

import java.util.Random;

public class RandomToast extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_toast);
        randomToast(getIntent().getIntExtra("max", 100));
        finish();
    }

    private void randomToast(int i) {
        Toast.makeText(getApplicationContext(), String.valueOf(randomInt(i)), Toast.LENGTH_LONG).show();
    }

    private int randomInt(int i) {
        Random r = new Random();
        return r.nextInt(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.random_toast, menu);
        return true;
    }
    
}
