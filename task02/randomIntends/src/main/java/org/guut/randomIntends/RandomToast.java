package org.guut.randomIntends;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class RandomToast extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyDebug", "oncreate randomtoast");
        super.onCreate(savedInstanceState);
        randomToast((int)getIntent().getDoubleExtra("max", 100));
        finish();
    }

    private void randomToast(int i) {
        int o = randomInt(i);
        Toast.makeText(getApplicationContext(), String.valueOf(o), Toast.LENGTH_LONG).show();
        Intent irra = new Intent();
        irra.putExtra("num", (double) o);
        setResult(RESULT_OK, irra);
    }

    private int randomInt(int i) {
        Random r = new Random();
        return r.nextInt(i);
    }
}
