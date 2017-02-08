package es.miguelprietos.marvelheroes.data.source;


import java.util.List;

import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.domain.classes.Comic;

/**
 * Created by mprieto on 31/01/17.
 */


public interface MarvelSource {

    interface GetCharactersCallback {
        void onCharactersLoaded(List<Characters> characters);
        void onNoResult();
        void onNoConnection();
    }

    interface GetComicsCallback {
        void onComicsLoaded(List<Comic> comics);
        void onNoResult();
        void onNoConnection();
    }


    void getCharacters(String starts, GetCharactersCallback callback);

    void getComics(int idCharacter, GetComicsCallback callback);

    void getComicsById(int idComic, GetComicsCallback callback);


}
