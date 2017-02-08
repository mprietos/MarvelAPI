package es.miguelprietos.marvelheroes.views.bases;

import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;

/**
 * Created by miguelprieto on 7/2/17.
 */

public interface BasePresenter<T,V> {
    void attach(T context, V view, MarvelSourceImpl repository);
}
