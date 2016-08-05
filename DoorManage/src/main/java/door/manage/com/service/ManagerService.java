package door.manage.com.service;

import test.greendao.bean.Manager;
import test.greendao.dao.ManagerDao;

/**
 * Created by Administrator on 2016/6/15.
 */
public class ManagerService extends BaseService<Manager,Long>{
    public ManagerService(ManagerDao dao) {
        super(dao);
    }
}
