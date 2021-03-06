package door.manage.com.utils;

import android.content.Context;

import de.greenrobot.dao.query.QueryBuilder;
import test.greendao.dao.DaoMaster;
import test.greendao.dao.DaoSession;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-09-01
 * Time: 10:47
 */
public class DbCore {
    private static final String DEFAULT_DB_NAME = "doormanage.db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static Context mContext;
    private static String DB_NAME;

    public static void init(Context context) {
        MyLog.d("DbCore","init(context) ");
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String dbName) {
        MyLog.d("DbCore","init(context,string) ");
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }

    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog(){

        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
