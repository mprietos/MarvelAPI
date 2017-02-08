package es.miguelprietos.marvelheroes.domain.classes;

import java.util.List;

public class ResourceList {

    private int available;
    private int returned;
    private String collectionURI;
    private List<ResumeEntity> items;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<ResumeEntity> getItems() {
        return items;
    }

    public void setItems(List<ResumeEntity> items) {
        this.items = items;
    }
}

