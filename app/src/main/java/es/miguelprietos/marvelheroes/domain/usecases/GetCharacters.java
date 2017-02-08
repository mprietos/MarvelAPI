package es.miguelprietos.marvelheroes.domain.usecases;

import java.util.List;

import es.miguelprietos.marvelheroes.data.source.MarvelSource;
import es.miguelprietos.marvelheroes.data.source.MarvelSourceImpl;
import es.miguelprietos.marvelheroes.domain.UseCase;
import es.miguelprietos.marvelheroes.domain.classes.Characters;

/**
 * Created by mprieto on 1/02/17.
 */

public class GetCharacters {

    private final MarvelSourceImpl mRepository;

    public GetCharacters(MarvelSourceImpl mRepository) {
        this.mRepository = mRepository;
    }

    public void getAsync(String search, final UseCase<Characters> listener){
        mRepository.getCharacters(search, new MarvelSource.GetCharactersCallback(){
            @Override
            public void onCharactersLoaded(List<Characters> characters) {
                listener.onSuccess(characters);
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
