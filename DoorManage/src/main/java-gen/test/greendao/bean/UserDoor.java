package test.greendao.bean;

import test.greendao.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import test.greendao.dao.DoorDao;
import test.greendao.dao.UserDao;
import test.greendao.dao.UserDoorDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER_DOOR".
 */
public class UserDoor {

    private Long userId;
    private Long doorId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient UserDoorDao myDao;

    private User user;
    private Long user__resolvedKey;

    private Door door;
    private Long door__resolvedKey;


    public UserDoor() {
    }

    public UserDoor(Long userId, Long doorId) {
        this.userId = userId;
        this.doorId = doorId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDoorDao() : null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDoorId() {
        return doorId;
    }

    public void setDoorId(Long doorId) {
        this.doorId = doorId;
    }

    /** To-one relationship, resolved on first access. */
    public User getUser() {
        Long __key = this.userId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
            	user__resolvedKey = __key;
            }
        }
        return user;
    }

    public void setUser(User user) {
        synchronized (this) {
            this.user = user;
            userId = user == null ? null : user.getUserId();
            user__resolvedKey = userId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Door getDoor() {
        Long __key = this.doorId;
        if (door__resolvedKey == null || !door__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DoorDao targetDao = daoSession.getDoorDao();
            Door doorNew = targetDao.load(__key);
            synchronized (this) {
                door = doorNew;
            	door__resolvedKey = __key;
            }
        }
        return door;
    }

    public void setDoor(Door door) {
        synchronized (this) {
            this.door = door;
            doorId = door == null ? null : door.getDoorId();
            door__resolvedKey = doorId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
