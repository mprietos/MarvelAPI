package es.miguelprietos.marvelheroes.views.bases;

/**
 * Created by miguelprieto on 7/2/17.
 */
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import es.miguelprietos.marvelheroes.R;

public class BaseActivity extends AppCompatActivity {
    public void initToolbar(boolean forClose) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (forClose){
            toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        }

    }
}
