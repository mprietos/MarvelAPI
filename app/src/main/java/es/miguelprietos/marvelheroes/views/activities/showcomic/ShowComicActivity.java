package es.miguelprietos.marvelheroes.views.activities.showcomic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.domain.classes.Comic;
import es.miguelprietos.marvelheroes.domain.classes.Image;
import es.miguelprietos.marvelheroes.views.activities.search.SearchPresenterImpl;
import es.miguelprietos.marvelheroes.views.bases.BaseActivity;

public class ShowComicActivity extends BaseActivity implements ShowComicContract.View {
    private static final String EXTRA_COMIC = "comic";
    private static final long DELAY_IMAGES = 2000;
    private int idComic;

    @InjectView(R.id.titleComic)
    TextView mTitleComic;

    @InjectView(R.id.descComic)
    TextView mDescComic;

    @InjectView(R.id.moreInfo)
    TextView mMoreInfo;

    @InjectView(R.id.imageView)
    ImageView mImageView;


    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;
    private ShowComicPresenterImpl presenter;
    private List<Image> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comic);
        ButterKnife.inject(this);
        super.initToolbar(false);

        idComic = getIntent().getExtras().getInt(EXTRA_COMIC);
        setTitle("");

        if (presenter == null)
            presenter = new ShowComicPresenterImpl();

        presenter.attach(this, this, new MarvelSourceImpl());

        presenter.getComic(idComic);


    }

    @Override
    public void fillData(Comic comic) {
        mTitleComic.setText(comic.getTitle());
        setTitle(comic.getTitle());
        mDescComic.setText(comic.getDescription());
        mMoreInfo.setText(comic.getFormat() + " " + comic.getPageCount() + " " + getString(R.string.pages));

        mImages = comic.getImages();
        Log.d("ShowComic", "Images size " + mImages.size());

        showRandonImage();

    }


    private void showRandonImage(){
        Random ran = new Random();
        int position = ran.nextInt(mImages.size());
        Log.d("ShowComic", "Position " + position);

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        mImageView.startAnimation(inFromRight);
        Glide.with(this).load(mImages.get(position).getPath() + "." + mImages.get(position).getExtension()).into(mImageView);

    }

    @Override
    public void setPresenter(ShowComicContract.Presenter presenter) {

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showList(boolean show) {

    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

