package door.manage.com.utils;


import java.util.List;

import door.manage.com.service.DoorService;
import door.manage.com.service.ManagerService;
import door.manage.com.service.UserService;
import test.greendao.bean.Door;
import test.greendao.bean.Manager;
import test.greendao.bean.User;
import test.greendao.dao.DoorDao;
import test.greendao.dao.ManagerDao;
import test.greendao.dao.UserDao;

public class DbUtil {
    private final static String TAG = "DbUtil";

    private static DoorService doorService;
    private static ManagerService managerService;
    private static UserService userService;

    public final static Long managerId = 1L;

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

        checkManagerExist();


        adduser();
//        List<Manager> managers =getManagerService().query("where name=?","supermanage").size()
//        if(managers.size()==1){
//            managers.get(0).set
//        }


    }

    private static void checkManagerExist() {
        List<Manager> managers =  getManagerService().query("where name=?","supermanage");
        if(managers.size()<1){
            Manager manager = new Manager();
            manager.setName("supermanage");
            manager.setPassword("admin");
            manager.setManagerId(managerId);
            getManagerService().save(manager);
        }
    }



    public static void adduser(){
        List<User> users = getUserService().query("where name=?","本机");
        if(users.size()<1){
            User mUser = new User();
            mUser.setName("本机");
//            while (getManagerId()==null){
//                checkManagerExist();
//                MyLog.d(TAG,""+getManagerId());
//            }
            mUser.setManagerId(managerId);
            getUserService().save(mUser);
        }
    }

    private static Long getManagerId() {
        List<Manager> managers =  getManagerService().query("where name=?","supermanage");
        if(managers.size() == 1){
            return managers.get(0).getManagerId();
        }
        return null;
    }


    public static boolean addDoor(Door door){
        if(!checkDoorExist(door)){
            getDoorService().save(door);
            return true;
        }else {
            return false;
        }
    }

    private static boolean checkDoorExist(Door door) {
        List<Door> doors =  getDoorService().query("where doornum=?",door.getDoornum());
        if(doors.size()>=1){
            return true;
        }else{
            return false;
        }
    }


}
