package org.guut.task06;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.guut.task06.communication.Communicator;
import org.guut.task06.fragments.PlayerForm;
import org.guut.task06.validation.DataValidator;
import org.guut.task06.fragments.PlayBoard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Class Main
 *
 * @author Jørgen Lien Sellæg
 */
public class Main extends Activity{
    DataValidator vd = new DataValidator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        changeFragment(new PlayerForm());
    }

    @Override
    public void recreate() {
        super.recreate();
    }

    public void updateGame(View view){
        Button button = (Button) view;
        String buttonText = (String) button.getText();

        switch (buttonText){
            case "SEND":
                String name = ((EditText)findViewById(R.id.player_form_name)).getText().toString();
                String cardNumber = ((EditText)findViewById(R.id.player_form_credit_card_info)).getText().toString();
                changeFragment(new PlayBoard());
                changeServerSourceText(vd.getRequestFromHTTP(vd.getURL("user", name, cardNumber)));
                updateNavButtonTexts();
            break;
        }
    }

    private void updateNavButtonTexts() {
        View v = findViewById(R.id.nav_send_button);
        v.setEnabled(!v.isEnabled());
        View r = findViewById(R.id.nav_start_over_button);
        r.setEnabled(!v.isEnabled());
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.player_board_fragment_holder, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        fm.executePendingTransactions();
    }

    public void changeServerSourceText(String response){
        TextView tw = ((TextView)findViewById(R.id.board_server_source));
        tw.setText(response);
    }

    public void startOver(View view){
        recreate();
    }

    public void guessNumber(View view){
        Button button = (Button) view;
        String buttonText = (String) button.getText();
        switch (buttonText){
            case "1": doGuess(buttonText); break;
            case "2": doGuess(buttonText); break;
            case "3": doGuess(buttonText); break;
            case "4": doGuess(buttonText); break;
            case "5": doGuess(buttonText); break;
            case "6": doGuess(buttonText); break;
            case "7": doGuess(buttonText); break;
            case "8": doGuess(buttonText); break;
            case "9": doGuess(buttonText); break;
            case "10": doGuess(buttonText); break;
        }
    }

    private void doGuess(String num) {
        changeServerSourceText(vd.getRequestFromHTTP(vd.getURL("number", num)));
    }
}
