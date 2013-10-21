package org.guut.fragments.run;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import org.guut.fragments.R;
import org.guut.fragments.fragments.pictureListFragment;

/**
 * Called when the activity is first created.
 */
public class main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.d("myDebug", "menu item "+item+" clicked");

        FragmentManager fm = getFragmentManager();
        pictureListFragment lf = (pictureListFragment) fm.findFragmentById(R.id.picture_list_fragment);

        switch (item.toString()) {
            case "Prev":
                lf.changeFragmentPictureById((lf.selectedPicture)-1);
                break;
            case "Next":
                lf.changeFragmentPictureById((lf.selectedPicture)+1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
