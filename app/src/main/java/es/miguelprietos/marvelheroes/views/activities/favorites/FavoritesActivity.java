package es.miguelprietos.marvelheroes.views.activities.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.control.adapters.FavoritesAdapter;
import es.miguelprietos.marvelheroes.database.CharacterItem;
import es.miguelprietos.marvelheroes.database.DataBaseActions;
import es.miguelprietos.marvelheroes.views.bases.BaseActivity;

public class FavoritesActivity extends BaseActivity {

    private RecyclerView mRecycler;
    private List<CharacterItem> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        super.initToolbar(false);
        setTitle(getString(R.string.favorites));

        mRecycler = (RecyclerView)findViewById(R.id.listFavorites);
        DataBaseActions mActions = new DataBaseActions(this);
        favorites = mActions.getFavorites();

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        FavoritesAdapter adapter = new FavoritesAdapter(this, favorites);
        mRecycler.setAdapter(adapter);
        mActions.closeBBDD();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
