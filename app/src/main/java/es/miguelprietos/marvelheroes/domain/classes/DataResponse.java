package es.miguelprietos.marvelheroes.domain.classes;

import java.util.List;

public class DataResponse<T> {
    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<T> results;

}
