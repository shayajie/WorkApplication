package test.greendao.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import test.greendao.bean.User;

import test.greendao.bean.Door;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOOR".
*/
public class DoorDao extends AbstractDao<Door, Long> {

    public static final String TABLENAME = "DOOR";

    /**
     * Properties of entity Door.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property DoorId = new Property(0, Long.class, "doorId", true, "DOOR_ID");
        public final static Property Doorname = new Property(1, String.class, "doorname", false, "DOORNAME");
        public final static Property Doornum = new Property(2, String.class, "doornum", false, "DOORNUM");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property Encoderpulses = new Property(4, String.class, "encoderpulses", false, "ENCODERPULSES");
        public final static Property Upperpulse = new Property(5, String.class, "upperpulse", false, "UPPERPULSE");
        public final static Property Lowerpulse = new Property(6, String.class, "lowerpulse", false, "LOWERPULSE");
        public final static Property Password = new Property(7, String.class, "password", false, "PASSWORD");
        public final static Property Doorstatus = new Property(8, String.class, "doorstatus", false, "DOORSTATUS");
        public final static Property Doorlock = new Property(9, String.class, "doorlock", false, "DOORLOCK");
        public final static Property UserId = new Property(10, Long.class, "userId", false, "USER_ID");
    };

    private DaoSession daoSession;

    private Query<Door> user_DoorsQuery;

    public DoorDao(DaoConfig config) {
        super(config);
    }
    
    public DoorDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOOR\" (" + //
                "\"DOOR_ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: doorId
                "\"DOORNAME\" TEXT," + // 1: doorname
                "\"DOORNUM\" TEXT," + // 2: doornum
                "\"PHONE\" TEXT," + // 3: phone
                "\"ENCODERPULSES\" TEXT," + // 4: encoderpulses
                "\"UPPERPULSE\" TEXT," + // 5: upperpulse
                "\"LOWERPULSE\" TEXT," + // 6: lowerpulse
                "\"PASSWORD\" TEXT," + // 7: password
                "\"DOORSTATUS\" TEXT," + // 8: doorstatus
                "\"DOORLOCK\" TEXT," + // 9: doorlock
                "\"USER_ID\" INTEGER);"); // 10: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOOR\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Door entity) {
        stmt.clearBindings();
 
        Long doorId = entity.getDoorId();
        if (doorId != null) {
            stmt.bindLong(1, doorId);
        }
 
        String doorname = entity.getDoorname();
        if (doorname != null) {
            stmt.bindString(2, doorname);
        }
 
        String doornum = entity.getDoornum();
        if (doornum != null) {
            stmt.bindString(3, doornum);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String encoderpulses = entity.getEncoderpulses();
        if (encoderpulses != null) {
            stmt.bindString(5, encoderpulses);
        }
 
        String upperpulse = entity.getUpperpulse();
        if (upperpulse != null) {
            stmt.bindString(6, upperpulse);
        }
 
        String lowerpulse = entity.getLowerpulse();
        if (lowerpulse != null) {
            stmt.bindString(7, lowerpulse);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(8, password);
        }
 
        String doorstatus = entity.getDoorstatus();
        if (doorstatus != null) {
            stmt.bindString(9, doorstatus);
        }
 
        String doorlock = entity.getDoorlock();
        if (doorlock != null) {
            stmt.bindString(10, doorlock);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(11, userId);
        }
    }

    @Override
    protected void attachEntity(Door entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Door readEntity(Cursor cursor, int offset) {
        Door entity = new Door( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // doorId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // doorname
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // doornum
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // encoderpulses
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // upperpulse
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // lowerpulse
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // password
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // doorstatus
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // doorlock
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10) // userId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Door entity, int offset) {
        entity.setDoorId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDoorname(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDoornum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEncoderpulses(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpperpulse(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLowerpulse(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPassword(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDoorstatus(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDoorlock(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setUserId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Door entity, long rowId) {
        entity.setDoorId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Door entity) {
        if(entity != null) {
            return entity.getDoorId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "doors" to-many relationship of User. */
    public List<Door> _queryUser_Doors(Long userId) {
        synchronized (this) {
            if (user_DoorsQuery == null) {
                QueryBuilder<Door> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                user_DoorsQuery = queryBuilder.build();
            }
        }
        Query<Door> query = user_DoorsQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(" FROM DOOR T");
            builder.append(" LEFT JOIN USER T0 ON T.\"USER_ID\"=T0.\"USER_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Door loadCurrentDeep(Cursor cursor, boolean lock) {
        Door entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);

        return entity;    
    }

    public Door loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Door> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Door> list = new ArrayList<Door>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Door> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Door> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
