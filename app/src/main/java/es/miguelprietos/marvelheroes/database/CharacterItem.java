package es.miguelprietos.marvelheroes.database;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

import es.miguelprietos.marvelheroes.domain.classes.Image;
import es.miguelprietos.marvelheroes.domain.classes.Url;

/**
 * Created by mprieto on 8/02/17.
 */
public class CharacterItem {

    public static final String ID_FIELD= "id";
    public static final String NAME_FIELD  = "name";
    @DatabaseField(generatedId = true)
    private int keyId;
    @DatabaseField(index = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField
    private String thumbnail;


    public CharacterItem(int id, String name, String description, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public CharacterItem() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
