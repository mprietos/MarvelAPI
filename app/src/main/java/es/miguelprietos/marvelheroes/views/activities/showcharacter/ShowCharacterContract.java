package es.miguelprietos.marvelheroes.views.activities.showcharacter;

import android.content.Context;

import java.util.List;

import es.miguelprietos.marvelheroes.domain.classes.Comic;
import es.miguelprietos.marvelheroes.views.bases.BasePresenter;
import es.miguelprietos.marvelheroes.views.bases.BaseView;

/**
 * Created by miguelprieto on 7/2/17.
 */

public interface ShowCharacterContract {
    interface View extends BaseView<Presenter> {
        void fillData(List<Comic> list);
    }
    interface Presenter extends BasePresenter<Context, View> {
        void getComics(int characterId);
    }
}
