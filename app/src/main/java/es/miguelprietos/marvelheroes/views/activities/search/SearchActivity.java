package es.miguelprietos.marvelheroes.views.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.control.adapters.CharactersRecyclerViewAdapter;
import es.miguelprietos.marvelheroes.control.callbacks.CharacterListCallBack;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.database.CharacterItem;
import es.miguelprietos.marvelheroes.database.DataBaseActions;
import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.views.activities.favorites.FavoritesActivity;
import es.miguelprietos.marvelheroes.views.activities.showcharacter.ShowCharacter;
import es.miguelprietos.marvelheroes.views.bases.BaseActivity;

public class SearchActivity  extends BaseActivity implements SearchContract.View, CharacterListCallBack {

    private static final String EXTRA_CHARACTER = "character";
    @InjectView(R.id.heroName)
    EditText mHeroName;
    @InjectView(R.id.list)
    RecyclerView mRecyclerView;

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;
    @InjectView(R.id.content_search)
    RelativeLayout mContentSearch;
    private List<Characters> characters;
    private CharactersRecyclerViewAdapter adapter;
    private SearchPresenterImpl presenter;
    private DataBaseActions mDataBase;
    private int mCountSelecteds;
    private Snackbar mSnackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        super.initToolbar(true);
        setTitle(getString(R.string.init));


        if (presenter == null)
            presenter = new SearchPresenterImpl();

        presenter.attach(this, this, new MarvelSourceImpl());

        mHeroName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String trimmedText = mHeroName.getText().toString().trim();
                if (trimmedText.length() > 1) {
                    initSearch(trimmedText);


                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        characters = new ArrayList<>();

        mDataBase = new DataBaseActions(this);
        adapter = new CharactersRecyclerViewAdapter(getBaseContext(), characters, this, mDataBase);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mRecyclerView.setAdapter(adapter);


        mSnackbar = Snackbar.make(mContentSearch.getRootView(), R.string.addToFavorite, Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(getResources().getColor(R.color.colorWhite))
                .setAction(getString(R.string.add), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addToFavorites();
                    }
                });
    }

    private void addToFavorites() {
        for (Characters item: this.characters) {
            if (item.isSelected()){
                CharacterItem characterItem = new CharacterItem(item.getId(), item.getName(), item.getDescription(), item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension());
                mDataBase.saveFavorite(characterItem);
            }
            item.setSelected(false);
        }
        adapter.notifyDataSetChanged();
    }


    public void initSearch(String trimmedText) {
        presenter.getHeroes(trimmedText);
    }

    @Override
    public void onClickCharacter(Characters item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);

        Intent i = new Intent(getBaseContext(), ShowCharacter.class);
        i.putExtra(EXTRA_CHARACTER, json);
        startActivity(i);

    }

    public List<Characters> getCharacters() {
        return characters;
    }

    @Override
    public void fillData(List<Characters> characters) {
        mCountSelecteds = 0;
        this.characters = characters;
        adapter.fillData(characters);
        adapter.notifyDataSetChanged();
        mDataBase.closeBBDD();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showList(boolean show) {
        mRecyclerView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                startActivity(new Intent(this, FavoritesActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedCharacter(int position, boolean b) {
        this.characters.get(position).setSelected(b);
        if (!b){
            mCountSelecteds--;
        }else{
            mCountSelecteds++;
        }
        showAddFavorites();
    }

    private void showAddFavorites() {
        if (mCountSelecteds == 0){
            mSnackbar.dismiss();
        }else{
            mSnackbar.show();
        }
    }
}