package door.manage.com.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import door.manage.com.utils.MyLog;
import test.greendao.dao.DaoMaster;
import test.greendao.dao.DaoSession;

/**
 * Created by Administrator on 2016/6/13.
 */
public class DoorApplication extends Application{

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
        //日志打印开关
        MyLog.setEnabled(true);
    }

    private void setupDatabase() {
        helper = new DaoMaster.DevOpenHelper(this, AppInfo.DB_NAME, null);
        //得到数据库连接对象
        db = helper.getWritableDatabase();
        //得到数据库管理者
        daoMaster =new DaoMaster(db);
        //得到daoSession，可以执行增删改查操作
        daoSession = daoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
