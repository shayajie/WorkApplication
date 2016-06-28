package door.manage.com.activity;

import android.os.Bundle;

import java.util.List;

import door.manage.com.R;
import door.manage.com.utils.DbUtil;
import test.greendao.bean.User;

/**
 * Created by shayajie on 2016/6/28.
 */
public class ManagerUsersActivity extends BaseActivity{
    private static final String Tag = "ManagerUsersActivity";
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
        initview();
    }

    private void initview() {
        inittitle(true,true);

    }

    private void initdata() {
        users =  mUserService.query("where managerId=?", ""+DbUtil.managerId);
    }

    @Override
    protected void updateUI() {

    }
}
