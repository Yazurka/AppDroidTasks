package no.hist.itfag.books;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;

public class BookActivity extends Activity {
	private DBAdapter db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DBAdapter(this);
        db.open();
 /*       long id = db.insert("Wei-Meng Lee", "Beginning Android 4");
        id = db.insert("Mildrid Ljosland", "Programmering i C++");
        id = db.insert("Else Lervik", "Programmering i C++");
        id = db.insert("Mildrid Ljosland", "Algoritmer og datastrukturer");
        id = db.insert("Helge Hafting", "Algoritmer og datastrukturer");*/
 //   	Cursor c = db.getAllBookAuthors();
        Cursor c = db.getBooksByAuthor("Mildrid Ljosland");
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
