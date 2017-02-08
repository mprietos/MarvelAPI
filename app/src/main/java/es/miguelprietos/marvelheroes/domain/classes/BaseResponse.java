package es.miguelprietos.marvelheroes.domain.classes;

public class BaseResponse<T> {
    public int code;
    public String status;
    public String etag;
    public DataResponse<T> data;
}
