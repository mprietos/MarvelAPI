package es.miguelprietos.marvelheroes.views.activities.search;

import android.content.Context;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.domain.UseCase;
import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.domain.usecases.GetCharacters;

/**
 * Created by miguelprieto on 7/2/17.
 */

public class SearchPresenterImpl implements  SearchContract.Presenter {


    private Context mContext;
    private SearchContract.View mView;
    private MarvelSourceImpl mRepository;

    @Override
    public void attach(Context context, SearchContract.View view, MarvelSourceImpl repository) {
        this.mContext = context;
        this.mView = view;
        this.mRepository = repository;
    }


    @Override
    public void getHeroes(String search) {
        mView.showProgressBar(true);
        GetCharacters get = new GetCharacters(mRepository);
        get.getAsync(search, new UseCase<Characters>() {
            @Override
            public void onSuccess(List<Characters> characters) {

                mView.showProgressBar(false);
                mView.showList(true);
                mView.fillData(characters);
            }

            @Override
            public void onNoResult() {
                mView.showProgressBar(false);
                mView.showMessage(mContext.getString(R.string.no_results));
            }

            @Override
            public void onNoConnection() {

                mView.showProgressBar(false);
                mView.showMessage(mContext.getString(R.string.error));
            }
        });
    }
}
