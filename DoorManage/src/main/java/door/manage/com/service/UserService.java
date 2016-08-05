package door.manage.com.service;


import test.greendao.bean.User;
import test.greendao.dao.UserDao;

public class UserService extends BaseService<User,Long> {
    public UserService(UserDao dao) {
        super(dao);
    }
}
