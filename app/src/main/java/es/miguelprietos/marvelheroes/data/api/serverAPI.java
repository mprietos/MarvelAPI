package es.miguelprietos.marvelheroes.data.api;


import es.miguelprietos.marvelheroes.domain.classes.BaseResponse;
import es.miguelprietos.marvelheroes.domain.classes.Characters;

import es.miguelprietos.marvelheroes.domain.classes.Comic;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface serverAPI {

    @GET("/v1/public/characters")
    Call<BaseResponse<Characters>> getCharacters(@Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);



    @GET("/v1/public/characters")
    Call<BaseResponse<Characters>> getCharactersByStart(@Query("nameStartsWith") String nameStartsWith
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);

    @GET("/v1/public/characters/{characterId}/comics")
    Call<BaseResponse<Comic>> getComicsByCharacter(@Path("characterId") int characterId
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);

    @GET("/v1/public/comics/{comicId}")
    Call<BaseResponse<Comic>> getComicById(@Path("comicId") int comicId
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);

}
