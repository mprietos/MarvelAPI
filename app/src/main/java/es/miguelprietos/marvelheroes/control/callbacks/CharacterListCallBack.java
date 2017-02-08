package es.miguelprietos.marvelheroes.control.callbacks;


import es.miguelprietos.marvelheroes.domain.classes.Characters;

public interface CharacterListCallBack {

    void onClickCharacter(Characters item);

    void selectedCharacter(int position, boolean b);
}
