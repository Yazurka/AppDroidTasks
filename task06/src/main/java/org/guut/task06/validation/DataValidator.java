package org.guut.task06.validation;

import android.content.Context;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.guut.task06.R;
import org.guut.task06.communication.Communicator;

import java.util.ArrayList;

/**
 * Class ${CLASS_NAME}
 *
 * @author Jørgen Lien Sellæg
 */
public class DataValidator {
    Context context;
    HttpClient client = new DefaultHttpClient();

    public DataValidator(Context context) {
        this.context = context;
    }

    public boolean isNumber(String toTest){
        try {
            Integer.parseInt(toTest);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private ArrayList<BasicNameValuePair> prepareHtmlGET(String string, String integer){
        ArrayList<BasicNameValuePair> res = new ArrayList<>(2);
        res.add(new BasicNameValuePair(context.getString(R.string.http_get_firstname), string));
        res.add(new BasicNameValuePair(context.getString(R.string.http_get_card_number), integer));
        return res;
    }

    private ArrayList<BasicNameValuePair> prepareHtmlGET(String string){
        ArrayList<BasicNameValuePair> res = new ArrayList<>(1);
        res.add(new BasicNameValuePair(context.getString(R.string.http_get_number), string));
        return res;
    }

    public String getURL(String s, String... params){
        switch (s){
            case "user":
                return context.getString(R.string.base_url)+ URLEncodedUtils.format(prepareHtmlGET(params[0], params[1]), null);
            case "number":
                return context.getString(R.string.base_url)+ URLEncodedUtils.format(prepareHtmlGET(params[0]), null);
        }
    return null;
    }

    public String getRequestFromHTTP(String url) {
        String res = "";
        Communicator com = new Communicator(client);
         com.execute(url);
        try {
            res = com.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}