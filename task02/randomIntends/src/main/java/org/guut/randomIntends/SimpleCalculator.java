package org.guut.randomIntends;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        num10.setText(String.valueOf(num1));
        num20.setText(String.valueOf(num2));
        ans1.setText(String.valueOf(ans));
    }

    public void calculate(View v){
        Bundle bun = new Bundle();
        bun.putString("multi", getString(R.string.option_multiplication));
        bun.putString("sub", getString(R.string.option_substract));
        bun.putString("add", getString(R.string.option_add));
        this.showDialog(0, bun);

    }

    @Override
    protected Dialog onCreateDialog(int id, Bundle bun) {
        super.onCreateDialog(id, bun);
        String[] s = new String[3];
        s[0] = bun.getString("multi");
        s[1] = bun.getString("sub");
        s[2] = bun.getString("add");
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
                                break;
                            case 1:
                                subtract();
                                break;
                            case 2:
                                add();
                                break;
                        }
                    }
                })
                .create();
        }
        return null;
    }

    private void multiply() {
        Double o = Double.parseDouble(getString(R.id.num1_text));
        Double i= Double.parseDouble(getString(R.id.num2_text));
        setTextViews(o, i, o*i);
    }

    private void add() {
        Double o = Double.parseDouble(getString(R.id.num1_text));
        Double i= Double.parseDouble(getString(R.id.num2_text));
        setTextViews(o, i, o+i);
    }

    private void subtract() {
        Double o = Double.parseDouble(getString(R.id.num1_text));
        Double i= Double.parseDouble(getString(R.id.num2_text));
        setTextViews(o, i, o-i);
    }
}
