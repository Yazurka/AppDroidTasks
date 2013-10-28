package org.guut.task07;

import android.app.Activity;
import android.os.Bundle;

import org.guut.task07.server.Server;

/**
 * Class Main
 *
 * @author Jørgen Lien Sellæg
 */
public class Main extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        new Server().start();
    }
}
