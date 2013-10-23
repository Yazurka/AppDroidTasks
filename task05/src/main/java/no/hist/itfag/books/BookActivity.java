package no.hist.itfag.books;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import org.guut.task05.Author;
import org.guut.task05.Book;
import org.guut.task05.BookReader;

import java.util.ArrayList;

public class BookActivity extends Activity {
	private DBAdapter db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DBAdapter(this);
        db.open();
        BookReader reader = new BookReader(this);
        ArrayList<Book> books = reader.getBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            Log.d("MyDebug", book.toString());
            ArrayList<Author> authors = book.getAuthors();
            for (int j = 0; j < authors.size(); j++) {
                Author author =  authors.get(j);
                Log.d("MyDebug", author.toString());
                long id = db.insert(author.toString(), book.toString());
            }
        }
    }
    @Override
    public void onDestroy() {
    	db.close();
    	super.onDestroy();
    }
    public void showAuthors(Cursor c) {
    	ListFragment frag = (ListFragment)(this.getFragmentManager().findFragmentById(R.id.frag1));
    	String[] columns = new String[]{c.getColumnName(0),c.getColumnName(1)};
    	int[] to = new int[]{R.id.textView1,R.id.textView2};
    	
    	SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.list_layout,c, columns, to);
    	frag.setListAdapter(sca);
    }
    public String getAllInColumn(Cursor c, int column) {
    	StringBuffer res = new StringBuffer("");
    	if (c.moveToFirst()) {
	    	do {
	    		res.append(c.getString(column) + "\n");
	    		
	    	} while (c.moveToNext());
    	}
    	return res.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book, menu);
        return true;
    }

    public void openSettings(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_settings:
                Intent i = new Intent("org.guut.task05.SettingsActivity");
                startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        SharedPreferences sp = getSharedPreferences(getPackageName()+"_preferences", MODE_PRIVATE);
        String color = sp.getString("pref_color", "FFFFFF");
        Log.d("myDebug", sp.getAll().toString());
        Color c = new Color();
        rl.setBackgroundColor(Color.parseColor("#"+color));
        setContentView(rl);
        super.onResume();
    }
}
