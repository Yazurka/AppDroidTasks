package org.guut.task07.server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            out.println("Welcome client...");//send text to client
            String res = in.readLine();//receive text from client
            Log.d(DEBUG_TAG,res);
                        
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
