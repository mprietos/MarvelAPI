package es.miguelprietos.marvelheroes.database;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;

/**
 * Created by mprieto on 8/02/17.
 */

public class DataBaseActions {

    protected static DBHelper mDBHelper;
    protected static Context mContext;

    public DataBaseActions(Context ctx) {
        mContext = ctx;
        mDBHelper = DBHelper.getHelper(mContext);
    }

    public void closeBBDD() {

        try {
            mDBHelper.close();
        }catch (Exception e){
        }

    }

    public boolean isFavorite(int id){
        boolean ret = false;

        try {
            if (!mDBHelper.isOpen()) {
                mDBHelper = DBHelper.getHelper(mContext);
            }

            Dao dao = mDBHelper.getCharacterDao();
            QueryBuilder<CharacterItem, Integer> qb = dao.queryBuilder();
            qb.where().eq(CharacterItem.ID_FIELD, id);
            if (qb.query().size() > 0){
                ret = true;
            }

        }catch (Exception e) {

        }

        return ret;
    }


    public void removeFavorite(int id){

        try {
            if (!mDBHelper.isOpen()) {
                mDBHelper = DBHelper.getHelper(mContext);
            }

            Dao dao = mDBHelper.getCharacterDao();
            DeleteBuilder<CharacterItem, Integer> qb = dao.deleteBuilder();
            qb.where().eq(CharacterItem.ID_FIELD, id);
            qb.delete();

        }catch (Exception e) {

        }

    }

    public void saveFavorite(CharacterItem item){
        try{
            if (!mDBHelper.isOpen()) {
                mDBHelper = DBHelper.getHelper(mContext);
            }

            Dao dao = mDBHelper.getCharacterDao();
            if (!isFavorite(item.getId())) {
                dao.createOrUpdate(item);
            }
        } catch (Exception e) {
            Log.d("DataBaseActions", e.toString());
        }
    }

    public List<CharacterItem> getFavorites(){
        List<CharacterItem> ret = new ArrayList<CharacterItem>();

        try {
            if (!mDBHelper.isOpen()) {
                mDBHelper = DBHelper.getHelper(mContext);
            }

            Dao dao = mDBHelper.getCharacterDao();
            QueryBuilder<CharacterItem, Integer> qb = dao.queryBuilder();

            qb.orderBy(CharacterItem.NAME_FIELD, true);
            ret = qb.query();

        }catch (Exception e) {

        }
        return ret;
    }
}
