package org.guut.task07client.server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class ${CLASS_NAME}
 *
 * @author Jørgen Lien Sellæg
 */
public class Client implements Runnable{
    private final static Integer SERVER_PORT = 12345;
    private final static String IP = "10.0.0.155";
    private final static String DEBUG_TAG = "myDebug";
    private String ans;
    private String num1;
    private String num2;



    public Client(String num1, String num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try{
            s = new Socket(IP, SERVER_PORT);
            out = new PrintWriter(s.getOutputStream(), false);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println(num1+""); Log.d(DEBUG_TAG, "Printed num1 on output");
            out.println(num2+""); Log.d(DEBUG_TAG, "Printed num2 on output");
            out.flush();
            Log.d(DEBUG_TAG, "Waiting to receive ans from server");
            ans = in.readLine();
            Log.d(DEBUG_TAG, ans);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
                in.close(); Log.d(DEBUG_TAG, "input closed");
                s.close();Log.d(DEBUG_TAG, "socket closed");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Log.d(DEBUG_TAG, "run done...");
    }



    public String getAns() {
        return ans;
    }
}
