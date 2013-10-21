package no.hist.itfag.books;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import org.guut.task05.Author;
import org.guut.task05.Book;
import org.guut.task05.BookReader;

import java.util.ArrayList;

public class BookActivity extends Activity {
	private DBAdapter db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DBAdapter(this);
        db.open();
        BookReader reader = new BookReader(this);
        ArrayList<Book> books = reader.getBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            ArrayList<Author> authors = book.getAuthors();
            for (int j = 0; j < authors.size(); j++) {
                Author author =  authors.get(j);
                long id = db.insert(author.toString(), book.toString());
            }
        }

    	Cursor c = db.getAllBookAuthors();

        showAuthors(c);
        Toast.makeText(this, getAllInColumn(c,0), Toast.LENGTH_LONG).show();
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

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
