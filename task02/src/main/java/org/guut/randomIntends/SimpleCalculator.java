package org.guut.randomIntends;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleCalculator extends Activity {
    private double num1;
    private double num2;
    private double ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);
        onCreateInit();
    }

    private void onCreateInit(){
        num1 = 0;
        num2 = 0;
        ans = 0;
        setTextViews(num1, num2, ans);
    }

    private void setTextViews(double num1, double num2, double ans) {
        EditText num10 = (EditText) findViewById(R.id.num1_text);
        EditText num20 = (EditText) findViewById(R.id.num2_text);
        TextView ans1 = (TextView) findViewById(R.id.ans_text);
        Log.d("MyDebug", "Set text view start");
        num10.setText(String.valueOf(num1));
        num20.setText(String.valueOf(num2));
        ans1.setText(String.valueOf(ans));
        Log.d("MyDebug", "Set text view stop");
    }

    public void calculate(View v){
        Bundle bun = new Bundle();
        Log.d("MyDebug", "Calculate get string view start");
        bun.putString("multi", getString(R.string.option_multiplication));
        bun.putString("sub", getString(R.string.option_substract));
        bun.putString("add", getString(R.string.option_add));
        this.showDialog(0, bun);
        Log.d("MyDebug", "Calculate get string view stop");
    }

    public void getNum1(View v){
        Log.d("MyDebug", "getnum1 start");
        Intent i = new Intent("org.guut.RandomToast");
        Log.d("MyDebug", "getnum1 create intent");
        i.putExtra("max", 10.0);
        startActivityForResult(i, 1);
        Log.d("MyDebug", "getnum1 stop");
    }

    public void getNum2(View v){
        Log.d("MyDebug", "getnum2 start");
        Intent i = new Intent("org.guut.RandomToast");
        i.putExtra("max", 10.0);
        startActivityForResult(i, 2);
        Log.d("MyDebug", "getnum2 stop");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyDebug", "onActivityResult started");
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                Double o = Double.parseDouble(((EditText)findViewById(R.id.num2_text)).getText().toString());
                setTextViews(data.getDoubleExtra("num", 0.0), o, 0.0);
                break;
            case 2:
                Double i = Double.parseDouble(((EditText)findViewById(R.id.num1_text)).getText().toString());
                setTextViews(i, data.getDoubleExtra("num", 0.0),  0.0);
                break;
        }
    }



    @Override
    protected Dialog onCreateDialog(int id, Bundle bun) {
        super.onCreateDialog(id, bun);
        String[] s = new String[3];
        Log.d("MyDebug", "Fetch bun start");
        s[0] = bun.getString("multi");
        s[1] = bun.getString("sub");
        s[2] = bun.getString("add");
        Log.d("MyDebug", "Fetch bun end");
        switch (id){
            case 0:
            return new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher)
                .setTitle(getString(R.string.choose_operator_title))
                .setItems(s, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Case 1",i+"");
                        switch (i){

                            case 0:
                                multiply();
                                Log.d("MyDebug", "multiply");
                                break;
                            case 1:
                                subtract();
                                Log.d("MyDebug", "substract");
                                break;
                            case 2:
                                add();
                                Log.d("MyDebug", "add");
                                break;
                        }
                    }
                })
                .create();
        }
        return null;
    }

    private void multiply() {
        Log.d("MyDebug", "multiply start");
        Double o = Double.parseDouble(((EditText)findViewById(R.id.num1_text)).getText().toString());
        Double i = Double.parseDouble(((EditText)findViewById(R.id.num2_text)).getText().toString());
        setTextViews(o, i, o*i);
        Log.d("MyDebug", "multiply end");
    }

    private void add() {
        Log.d("MyDebug", "add start");
        Double o = Double.parseDouble(((EditText)findViewById(R.id.num1_text)).getText().toString());
        Double i = Double.parseDouble(((EditText)findViewById(R.id.num2_text)).getText().toString());
        setTextViews(o, i, o+i);
        Log.d("MyDebug", "add stop");
    }

    private void subtract() {
        Log.d("MyDebug", "substract start");
        Double o = Double.parseDouble(((EditText)findViewById(R.id.num1_text)).getText().toString());
        Double i = Double.parseDouble(((EditText)findViewById(R.id.num2_text)).getText().toString());
        setTextViews(o, i, o-i);
        Log.d("MyDebug", "substract stop");
    }
}
