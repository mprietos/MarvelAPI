package es.miguelprietos.marvelheroes.views.bases;

/**
 * Created by miguelprieto on 7/2/17.
 */

public interface BaseView<T> {
    void setPresenter (T presenter);
    void showMessage(String message);
    void showList(boolean show);
    void showProgressBar(boolean show);
}