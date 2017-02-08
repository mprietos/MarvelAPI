package es.miguelprietos.marvelheroes.views.activities.showcharacter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.control.adapters.ComicsRecyclerViewAdapter;
import es.miguelprietos.marvelheroes.control.callbacks.ComicListCallBack;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.database.CharacterItem;
import es.miguelprietos.marvelheroes.database.DataBaseActions;
import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.domain.classes.Comic;
import es.miguelprietos.marvelheroes.views.activities.showcomic.ShowComicActivity;
import es.miguelprietos.marvelheroes.views.bases.BaseActivity;

public class ShowCharacter extends BaseActivity implements ShowCharacterContract.View, ComicListCallBack {
    private static final String EXTRA_CHARACTER = "character";
    private static final String EXTRA_COMIC = "comic";
    private Characters mCharacter;

    @InjectView(R.id.avatar)
    ImageView mAvatar;
    @InjectView(R.id.name)
    TextView mName;
    @InjectView(R.id.subname)
    TextView mDescription;
    @InjectView(R.id.list)
    RecyclerView mRecyclerView;
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;
    @InjectView(R.id.btnFavorite)
    Button mBtnFavorite;
    private ShowCharacterPresenterImpl presenter;
    private ComicsRecyclerViewAdapter adapter;
    private DataBaseActions mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_character);

        ButterKnife.inject(this);
        super.initToolbar(false);
        Gson gson = new Gson();
        String json = getIntent().getExtras().getString(EXTRA_CHARACTER);
        mCharacter = gson.fromJson(json, Characters.class);
        setTitle(mCharacter.getName());
        String urlImage = mCharacter.getThumbnail().getPath() + "." + mCharacter.getThumbnail().getExtension();
        Glide.with(this).load(urlImage).into(mAvatar);

        setTitle(mCharacter.getName());
        mName.setText(mCharacter.getName());
        mDescription.setText(mCharacter.getDescription());

        mTitle.setText(mCharacter.getComics().getAvailable() + " " +  getString(R.string.comics));
        if (presenter == null)
            presenter = new ShowCharacterPresenterImpl();

        presenter.attach(this, this, new MarvelSourceImpl());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Comic> entities = new ArrayList<>();
        adapter = new ComicsRecyclerViewAdapter(this, entities, this);
        mRecyclerView.setAdapter(adapter);

        this.presenter.getComics(mCharacter.getId());
        mDataBase = new DataBaseActions(this);

        formatButtonFavorite();


    }

    private void formatButtonFavorite() {
        final boolean isFavorite =mDataBase.isFavorite(mCharacter.getId());
        if (isFavorite){
            mBtnFavorite.setText(getString(R.string.removefavorite));
            Drawable img = getResources().getDrawable( R.drawable.ic_favorite );
            mBtnFavorite.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);

        }else{
            mBtnFavorite.setText(getString(R.string.addToFavorite));
            Drawable img = getResources().getDrawable( R.drawable.ic_favorite_border );
            mBtnFavorite.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);

        }
        mBtnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite){
                    mDataBase.removeFavorite(mCharacter.getId());
                }else{
                    CharacterItem characterItem = new CharacterItem(mCharacter.getId(), mCharacter.getName(), mCharacter.getDescription(), mCharacter.getThumbnail().getPath() + "." + mCharacter.getThumbnail().getExtension());
                    mDataBase.saveFavorite(characterItem);
                }

                formatButtonFavorite();
            }
        });
    }

    @Override
    protected void onStop() {
        mDataBase.closeBBDD();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillData(List<Comic> list) {
        adapter.fillData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ShowCharacterContract.Presenter presenter) {

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
    public void onClickComic(Comic item) {
        Intent i = new Intent(getBaseContext(), ShowComicActivity.class);
        i.putExtra(EXTRA_COMIC, item.getId());
        startActivity(i);
    }
}
