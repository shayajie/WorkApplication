package door.manage.com.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import door.manage.com.utils.DbCore;
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
        //日志打印开关
        DbCore.init(this);
        MyLog.setEnabled(true);
    }

}
