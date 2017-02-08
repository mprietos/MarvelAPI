package es.miguelprietos.marvelheroes.views.activities.search;

import android.content.Context;

import java.util.List;

import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.views.bases.BasePresenter;
import es.miguelprietos.marvelheroes.views.bases.BaseView;

/**
 * Created by miguelprieto on 7/2/17.
 */

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void fillData(List<Characters> characters);
    }
    interface Presenter extends BasePresenter<Context, View> {

        void getHeroes(String search);
    }
}
