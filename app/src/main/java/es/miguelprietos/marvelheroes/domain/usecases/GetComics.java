package es.miguelprietos.marvelheroes.domain.usecases;

import java.util.List;

import es.miguelprietos.marvelheroes.data.source.MarvelSource;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.domain.UseCase;
import es.miguelprietos.marvelheroes.domain.classes.Comic;

/**
 * Created by mprieto on 1/02/17.
 */

public class GetComics {

    private final MarvelSourceImpl mRepository;

    public GetComics(MarvelSourceImpl mRepository) {
        this.mRepository = mRepository;
    }

    public void getAsync(int idCharacter, final UseCase<Comic> listener){
        mRepository.getComics(idCharacter, new MarvelSource.GetComicsCallback(){
            @Override
            public void onComicsLoaded(List<Comic> comics) {
                listener.onSuccess(comics);
            }

            @Override
            public void onNoResult() {
                listener.onNoResult();
            }

            @Override
            public void onNoConnection() {
                listener.onNoConnection();
            }
        });
    }
}
