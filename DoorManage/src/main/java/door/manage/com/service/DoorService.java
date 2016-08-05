package door.manage.com.service;


import test.greendao.bean.Door;
import test.greendao.dao.DoorDao;

public class DoorService extends BaseService<Door,Long> {
    public DoorService(DoorDao dao) {
        super(dao);
    }
}
