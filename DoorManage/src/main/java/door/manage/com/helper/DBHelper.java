package door.manage.com.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import test.greendao.dao.DaoMaster;

/**
 * Created by Administrator on 2016/6/13.
 */
public class DBHelper extends DaoMaster.DevOpenHelper{
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

    }
}
