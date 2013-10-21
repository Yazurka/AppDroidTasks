package org.guut.task05;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import no.hist.itfag.books.R;

public class BookReader{
    private final Context context;
    private ArrayList<String> books = new ArrayList<>();

    public BookReader(Context context){
        this.context = context;
        this.fetchSource();
    }

    private void fetchSource(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.books)));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    addToList(line.trim());
                }
            } catch (IOException e) {
                System.out.println("Can't read file...");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToList(String line) {
        books.add(line);
    }

    public ArrayList<String> getLines() {
        return books;
    }
}
