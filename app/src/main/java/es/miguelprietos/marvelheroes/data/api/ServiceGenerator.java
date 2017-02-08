package es.miguelprietos.marvelheroes.data.api;

import java.util.concurrent.TimeUnit;

import es.miguelprietos.marvelheroes.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder client = httpClient;
        client.connectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.readTimeout(15, TimeUnit.SECONDS);    // socket timeout);
        Retrofit retrofit = builder.client(client.build()).build();
        return retrofit.create(serviceClass);
    }
}
