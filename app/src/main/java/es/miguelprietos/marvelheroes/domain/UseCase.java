package es.miguelprietos.marvelheroes.domain;

import java.util.List;

public interface UseCase<T> {
    void onSuccess(List<T> items);
    void onNoResult();
    void onNoConnection();
}
