package es.miguelprietos.marvelheroes.domain.classes;



import java.util.List;

public class Characters {

    private int id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private List<Url> urls;
    private Image thumbnail;
    private ResourceList comics;
    private ResourceList stories;
    private ResourceList events;
    private ResourceList series;

    private boolean isSelected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (description == null)
            return  "";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ResourceList getComics() {
        return comics;
    }

    public void setComics(ResourceList comics) {
        this.comics = comics;
    }

    public ResourceList getStories() {
        return stories;
    }

    public void setStories(ResourceList stories) {
        this.stories = stories;
    }

    public ResourceList getEvents() {
        return events;
    }

    public void setEvents(ResourceList events) {
        this.events = events;
    }

    public ResourceList getSeries() {
        return series;
    }

    public void setSeries(ResourceList series) {
        this.series = series;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
