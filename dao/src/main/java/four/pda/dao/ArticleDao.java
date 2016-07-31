package four.pda.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import four.pda.dao.Article;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ARTICLE".
*/
public class ArticleDao extends AbstractDao<Article, Long> {

    public static final String TABLENAME = "ARTICLE";

    /**
     * Properties of entity Article.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Date = new Property(1, java.util.Date.class, "date", false, "DATE");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Description = new Property(3, String.class, "description", false, "DESCRIPTION");
        public final static Property Category = new Property(4, String.class, "category", false, "CATEGORY");
        public final static Property Image = new Property(5, String.class, "image", false, "IMAGE");
        public final static Property PublishedDate = new Property(6, java.util.Date.class, "publishedDate", false, "PUBLISHED_DATE");
        public final static Property CommentsCount = new Property(7, Integer.class, "commentsCount", false, "COMMENTS_COUNT");
        public final static Property AuthorId = new Property(8, Long.class, "authorId", false, "AUTHOR_ID");
        public final static Property AuthorName = new Property(9, String.class, "authorName", false, "AUTHOR_NAME");
    };


    public ArticleDao(DaoConfig config) {
        super(config);
    }
    
    public ArticleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ARTICLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DATE\" INTEGER," + // 1: date
                "\"TITLE\" TEXT," + // 2: title
                "\"DESCRIPTION\" TEXT," + // 3: description
                "\"CATEGORY\" TEXT," + // 4: category
                "\"IMAGE\" TEXT," + // 5: image
                "\"PUBLISHED_DATE\" INTEGER," + // 6: publishedDate
                "\"COMMENTS_COUNT\" INTEGER," + // 7: commentsCount
                "\"AUTHOR_ID\" INTEGER," + // 8: authorId
                "\"AUTHOR_NAME\" TEXT);"); // 9: authorName
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ARTICLE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Article entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(2, date.getTime());
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(5, category);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(6, image);
        }
 
        java.util.Date publishedDate = entity.getPublishedDate();
        if (publishedDate != null) {
            stmt.bindLong(7, publishedDate.getTime());
        }
 
        Integer commentsCount = entity.getCommentsCount();
        if (commentsCount != null) {
            stmt.bindLong(8, commentsCount);
        }
 
        Long authorId = entity.getAuthorId();
        if (authorId != null) {
            stmt.bindLong(9, authorId);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(10, authorName);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Article readEntity(Cursor cursor, int offset) {
        Article entity = new Article( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // date
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // description
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // category
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // image
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // publishedDate
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // commentsCount
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // authorId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // authorName
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Article entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCategory(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setImage(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPublishedDate(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setCommentsCount(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setAuthorId(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setAuthorName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Article entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Article entity) {
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
    
}
