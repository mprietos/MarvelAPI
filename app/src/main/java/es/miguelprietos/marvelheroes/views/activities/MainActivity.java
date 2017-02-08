package es.miguelprietos.marvelheroes.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.utils.Constants;
import es.miguelprietos.marvelheroes.views.activities.search.SearchActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                initApp();
            }
        }, Constants.SPLASH_TIME);
    }

    private void initApp() {
        finish();
        startActivity(new Intent(this, SearchActivity.class));
    }
}
