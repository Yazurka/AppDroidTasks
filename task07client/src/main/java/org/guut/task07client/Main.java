package org.guut.task07client;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.guut.task07client.server.Client;

/**
 * Class Main
 *
 * @author Jørgen Lien Sellæg
 */
public class Main extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }


    public void calculate(View view) {

        TextView tv = (TextView)findViewById(R.id.sum);
        String num1, num2;
        num1 = ((EditText)findViewById(R.id.firstNumber)).getText().toString();
        num2 = ((EditText)findViewById(R.id.secondNumber)).getText().toString();
        if(num1 == null || num2 == null){
            num1 = "403";
            num2 = "1";
        }
        Client cl = new Client(num1, num2);
        Thread t = new Thread(cl);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(cl.getAns() == null){
            Log.d("myDebug", "ans is null...");
        }else{
            Log.d("myDebug", cl.getAns());
        }
        tv.setText(cl.getAns());
    }

}
