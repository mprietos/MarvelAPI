package es.miguelprietos.marvelheroes.data.api;

public class ServiceMarvel {

    private static serverAPI service = null;

    public static serverAPI getService() {
        if (service == null){
            service = ServiceGenerator.createService(serverAPI.class);
        }
        return service;
    }
}
