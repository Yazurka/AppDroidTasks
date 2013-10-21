package org.guut.task05;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import no.hist.itfag.books.R;

public class BookReader{
    private final Context context;
    private ArrayList<String> lines;
    private ArrayList<Book> books;

    public BookReader(Context context){
        this.context = context;
        lines = new ArrayList<>();
        this.fetchSource();
        this.books = createBooks();
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
                e.printStackTrace();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToList(String line) {
        lines.add(line);
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    private ArrayList<Book> createBooks(){
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] st = line.split(";");
            Author author = new Author(st[1],st[0]);
            Book book = new Book(st[2]);
            if(!books.contains(book)){
                book.addAuthor(author);
                books.add(book);
            }else{
                books.get(books.indexOf(book)).addAuthor(author);
            }
        }
        return books;
    }

    public ArrayList<Book> getBooks(){
        return books;
    }
}
