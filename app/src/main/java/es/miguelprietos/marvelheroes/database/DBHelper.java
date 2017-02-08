package es.miguelprietos.marvelheroes.database;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "miguelprietosmarvel.db";
    private static final int DATABASE_VERSION = 1;
    private static DBHelper helper = null;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private Dao<CharacterItem, Integer> UserDao;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getHelper(Context context) {

        if (helper == null) {

            helper = new DBHelper(context);
        }
        usageCounter.incrementAndGet();
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{

            TableUtils.createTable(connectionSource, CharacterItem.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<CharacterItem, Integer> getCharacterDao() throws SQLException {
        if (UserDao == null) {
            UserDao = getDao(CharacterItem.class);
        }
        return UserDao;
    }


    @Override
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            UserDao = null;
            helper = null;
        }
    }
}
