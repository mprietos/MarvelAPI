package es.miguelprietos.marvelheroes.views.activities.showcomic;

import android.content.Context;

import java.util.List;

import es.miguelprietos.marvelheroes.domain.classes.Comic;
import es.miguelprietos.marvelheroes.views.activities.showcharacter.ShowCharacterContract;
import es.miguelprietos.marvelheroes.views.bases.BasePresenter;
import es.miguelprietos.marvelheroes.views.bases.BaseView;

/**
 * Created by miguelprieto on 7/2/17.
 */

public interface ShowComicContract {
    interface View extends BaseView<ShowComicContract.Presenter> {
        void fillData( Comic comic);
    }
    interface Presenter extends BasePresenter<Context, ShowComicContract.View> {
        void getComic(int comicId);
    }
}
