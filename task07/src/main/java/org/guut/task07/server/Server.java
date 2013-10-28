package org.guut.task07.server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    private final static Integer SERVER_PORT = 12345;
    private final static String DEBUG_TAG = "myDebug";

    @Override
    public void run() {
        ServerSocket ss = null;
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try{
            ss = new ServerSocket(SERVER_PORT);
            Log.d(DEBUG_TAG, "Create server socket, waiting for clients...");
            s = ss.accept();
            Log.d(DEBUG_TAG,"Client connected");
            out = new PrintWriter(s.getOutputStream(), false);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ArrayList<String> nums = new ArrayList<>();
            Log.d(DEBUG_TAG, "Waiting for InputStream");
            nums.add(in.readLine());
            nums.add(in.readLine());
            Integer ans = 0;
            for (int i = 0; i < nums.size(); i++) {
                Log.d(DEBUG_TAG, "Adding numbers");
                String s1 =  nums.get(i);
                Integer num = Integer.parseInt(s1);
                ans += num;
            }
            Log.d(DEBUG_TAG, ans+"");
            out.println(ans+"");
            out.flush();

        }catch (IOException e){
            e.printStackTrace();
            Log.d(DEBUG_TAG, "Exception");
        }finally {
            try{
                out.close();
                in.close();
                s.close();
                ss.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
