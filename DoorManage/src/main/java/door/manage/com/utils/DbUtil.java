package door.manage.com.utils;


import java.util.List;

import door.manage.com.service.DoorService;
import door.manage.com.service.ManagerService;
import door.manage.com.service.UserService;
import test.greendao.bean.Manager;
import test.greendao.bean.User;
import test.greendao.dao.DoorDao;
import test.greendao.dao.ManagerDao;
import test.greendao.dao.UserDao;

public class DbUtil {
    private static DoorService doorService;
    private static ManagerService managerService;
    private static UserService userService;

    private static UserDao getUserDao() {
        return DbCore.getDaoSession().getUserDao();
    }

    private static DoorDao getDoorDao() {
        return DbCore.getDaoSession().getDoorDao();
    }

    private static ManagerDao getManagerDao() {
        return DbCore.getDaoSession().getManagerDao();
    }

    public static DoorService getDoorService() {
        if (doorService == null) {
            doorService = new DoorService(getDoorDao());
        }
        return doorService;
    }

    public static ManagerService getManagerService() {
        if (managerService == null) {
            managerService = new ManagerService(getManagerDao());
        }
        return managerService;
    }
    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService(getUserDao());
        }
        return userService;
    }

    public static void firstaddUser(){
        List<User> users =  getUserService().query("where name in (?,?)","supermanage","本机");
        if(users.size()<1){
//            User mUser1 = new User();
//            mUser1.setName("supermanage");
//            mUser1.setPassword("admin");
//
//            User mUser2 = new User();
//            mUser2.setName("本机");
//            mUser2.setPassword("123456");
//
//            getUserService().save(mUser1,mUser2);
        }



    }
}
