package es.miguelprietos.marvelheroes.data.source;

import java.util.List;

import es.miguelprietos.marvelheroes.data.SendRequest;
import es.miguelprietos.marvelheroes.data.api.ServiceMarvel;
import es.miguelprietos.marvelheroes.data.api.serverAPI;
import es.miguelprietos.marvelheroes.domain.classes.BaseResponse;
import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.domain.classes.Comic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mprieto on 31/01/17.
 */

public class MarvelSourceImpl implements MarvelSource {


    @Override
    public void getCharacters(String starts, final GetCharactersCallback callback) {
        serverAPI service = ServiceMarvel.getService();
        SendRequest request = SendRequest.create();
        Call<BaseResponse<Characters>> call = service.getCharactersByStart(starts, String.valueOf(request.getTimeStamp()), request.getPublicKey(),request.getHashSignature());
        call.enqueue(new Callback<BaseResponse<Characters>>() {
            @Override
            public void onResponse(Call<BaseResponse<Characters>> call, Response<BaseResponse<Characters>> response) {
                if (response.isSuccessful()) {
                    List<Characters> characters = response.body().data.results;
                    callback.onCharactersLoaded(characters);
                } else {
                    callback.onNoResult();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Characters>> call, Throwable t) {
                callback.onNoConnection();
            }
        });
    }

    @Override
    public void getComics(int idCharacter, final GetComicsCallback callback) {
        serverAPI service = ServiceMarvel.getService();
        SendRequest request = SendRequest.create();
        Call<BaseResponse<Comic>> call = service.getComicsByCharacter(idCharacter, String.valueOf(request.getTimeStamp()), request.getPublicKey(),request.getHashSignature());
        call.enqueue(new Callback<BaseResponse<Comic>>() {
            @Override
            public void onResponse(Call<BaseResponse<Comic>> call, Response<BaseResponse<Comic>> response) {
                if (response.isSuccessful()) {
                    List<Comic> comics = response.body().data.results;
                    callback.onComicsLoaded(comics);
                } else {
                    callback.onNoResult();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Comic>> call, Throwable t) {
                callback.onNoConnection();
            }
        });
    }

    @Override
    public void getComicsById(int idComic, final GetComicsCallback callback) {
        serverAPI service = ServiceMarvel.getService();
        SendRequest request = SendRequest.create();
        Call<BaseResponse<Comic>> call = service.getComicById(idComic, String.valueOf(request.getTimeStamp()), request.getPublicKey(),request.getHashSignature());
        call.enqueue(new Callback<BaseResponse<Comic>>() {
            @Override
            public void onResponse(Call<BaseResponse<Comic>> call, Response<BaseResponse<Comic>> response) {
                if (response.isSuccessful()) {
                    List<Comic> comics = response.body().data.results;
                    callback.onComicsLoaded(comics);
                } else {
                    callback.onNoResult();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Comic>> call, Throwable t) {
                callback.onNoConnection();
            }
        });
    }
}
