package es.miguelprietos.marvelheroes.views.activities.showcharacter;

import android.content.Context;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.domain.UseCase;
import es.miguelprietos.marvelheroes.domain.classes.Comic;
import es.miguelprietos.marvelheroes.domain.usecases.GetComics;

/**
 * Created by miguelprieto on 7/2/17.
 */

public class ShowCharacterPresenterImpl implements ShowCharacterContract.Presenter {

    private Context mContext;
    private ShowCharacterContract.View mView;
    private MarvelSourceImpl mRepository;

    @Override
    public void getComics(int characterId) {
        mView.showProgressBar(true);
        GetComics get = new GetComics(mRepository);
        get.getAsync(characterId, new UseCase<Comic>(){
            @Override
            public void onSuccess(List<Comic> comics) {

                mView.showProgressBar(false);
                mView.showList(true);
                mView.fillData(comics);
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

    @Override
    public void attach(Context context, ShowCharacterContract.View view, MarvelSourceImpl repository) {
        this.mContext = context;
        this.mView = view;
        this.mRepository = repository;
    }
}