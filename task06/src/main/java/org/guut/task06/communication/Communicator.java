package org.guut.task06.communication;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Class ${CLASS_NAME}
 *
 * @author Jørgen Lien Sellæg
 */
public class Communicator extends AsyncTask<String, Integer, String> {
    HttpClient client;
    public Communicator(HttpClient client){
        this.client = client;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            HttpGet request = new HttpGet(params[i]);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            try {
                response = client.execute(request,responseHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
