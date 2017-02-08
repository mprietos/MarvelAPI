package es.miguelprietos.marvelheroes.views.activities.showcomic;

import android.content.Context;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.domain.UseCase;
import es.miguelprietos.marvelheroes.domain.classes.Comic;
import es.miguelprietos.marvelheroes.domain.usecases.GetComicById;
/**
 * Created by miguelprieto on 7/2/17.
 */

public class ShowComicPresenterImpl implements ShowComicContract.Presenter {
    private Context mContext;
    private ShowComicContract.View mView;
    private MarvelSourceImpl mRepository;

    @Override
    public void getComic(int comicId) {
        mView.showProgressBar(true);
        GetComicById get = new GetComicById(mRepository);
        get.getAsync(comicId, new UseCase<Comic>(){
            @Override
            public void onSuccess(List<Comic> comics) {

                mView.showProgressBar(false);
                mView.showList(true);
                if (comics.size() > 0)
                    mView.fillData(comics.get(0));
                else
                    mView.fillData(new Comic());
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
    public void attach(Context context, ShowComicContract.View view, MarvelSourceImpl repository) {
        this.mContext = context;
        this.mView = view;
        this.mRepository = repository;
    }

}
