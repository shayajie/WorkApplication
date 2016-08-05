package test.greendao.bean;

import door.manage.com.utils.DbUtil;

/**
 * Created by shayajie on 2016/8/5.
 */
public class MyUser {
    private volatile static MyUser Instance;
    private User user;
    private MyUser() {
        user = DbUtil.getUserService().query(1L);
    }

    public String getUserPhone() {
        return user.getPhone();
    }

    public static MyUser getInstance() {
        if (Instance == null) {
            synchronized (MyUser.class) {
                if (Instance == null) {
                    Instance = new MyUser();
                }
            }
        }
        return Instance;
    }

}
