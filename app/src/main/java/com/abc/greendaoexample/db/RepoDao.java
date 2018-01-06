package com.abc.greendaoexample.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.abc.greendaoexample.db.Repo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "REPO".
*/
public class RepoDao extends AbstractDao<Repo, Long> {

    public static final String TABLENAME = "REPO";

    /**
     * Properties of entity Repo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Language = new Property(2, String.class, "language", false, "LANGUAGE");
        public final static Property Watchers_count = new Property(3, Integer.class, "watchers_count", false, "WATCHERS_COUNT");
        public final static Property UserId = new Property(4, long.class, "userId", false, "USER_ID");
    };

    private Query<Repo> user_UserReposQuery;

    public RepoDao(DaoConfig config) {
        super(config);
    }
    
    public RepoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"REPO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT NOT NULL ," + // 1: title
                "\"LANGUAGE\" TEXT," + // 2: language
                "\"WATCHERS_COUNT\" INTEGER," + // 3: watchers_count
                "\"USER_ID\" INTEGER NOT NULL );"); // 4: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"REPO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Repo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTitle());
 
        String language = entity.getLanguage();
        if (language != null) {
            stmt.bindString(3, language);
        }
 
        Integer watchers_count = entity.getWatchers_count();
        if (watchers_count != null) {
            stmt.bindLong(4, watchers_count);
        }
        stmt.bindLong(5, entity.getUserId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Repo readEntity(Cursor cursor, int offset) {
        Repo entity = new Repo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // language
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // watchers_count
            cursor.getLong(offset + 4) // userId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Repo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.getString(offset + 1));
        entity.setLanguage(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWatchers_count(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setUserId(cursor.getLong(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Repo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Repo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "userRepos" to-many relationship of User. */
    public List<Repo> _queryUser_UserRepos(long userId) {
        synchronized (this) {
            if (user_UserReposQuery == null) {
                QueryBuilder<Repo> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                user_UserReposQuery = queryBuilder.build();
            }
        }
        Query<Repo> query = user_UserReposQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

}
