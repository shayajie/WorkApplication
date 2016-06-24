package door.manage.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import door.manage.com.R;
import door.manage.com.adapter.GridView_Adapter;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorRequest;
import test.greendao.bean.User;

/**
 * Created by shayajie on 2016/6/14.
 */
public class MainActivity extends BaseActivity {
    private final static String TAG = "MainActivity";
    //标题栏

    private List<User> users;
    private List<Door> doors;

    private GridView mGridView;
    private GridView_Adapter mGridView_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initdata();
        initview();
    }

    @Override
    protected void updateUI() {

    }


    private void initdata() {
        MyLog.d(TAG, "==============initdata_start");
        users = mUserService.query("where name=?", "本机");
        if (users.size() == 1) {
            doors = mDoorService.query("where USER_ID=?", "" + users.get(0).getUserId());

        }
        MyLog.d(TAG, "==============initdata_end");
        MyLog.d(TAG, "" + users.get(0).getUserId());
    }

    private void initview() {
        inittitle(false, true);

        title_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.d(TAG,"我是设置按钮");
            }
        });

        mGridView = (GridView) findViewById(R.id.door_gridview);

        mGridView_adapter = new GridView_Adapter(doors, mContext);
        mGridView.setAdapter(mGridView_adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (doors.size() == position) {
                    Intent intent = new Intent(mContext, AddDoorActivity.class);
                    intent.putExtra("userid", users.get(0).getUserId());
                    startActivityForResult(intent, 0);
                } else {

                    Door door = doors.get(position);
                    GetDoorRequest getDoorRequest = new GetDoorRequest(door.getDoornum(), AppInfo.READ_TAG,door.getPhone());
                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));
                    sendMessage(door.getPhone(), StringUtils.getDoorMessage(getDoorRequest));
//                    String message = AppInfo.A_TAG+AppInfo.LAST_TAG+door.getDoornum()+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+door.getPhone();
//                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));
//                    sendMessage(door.getPhone(), message);
                    Intent intent = new Intent(mContext, DoorControlActivity.class);
                    intent.putExtra("doorId", doors.get(position).getDoorId());
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                doors = mDoorService.query("where USER_ID=?", "" + users.get(0).getUserId());
                MyLog.d(TAG,"================"+doors.size());
                mGridView_adapter = new GridView_Adapter(doors, mContext);
                mGridView.setAdapter(mGridView_adapter);
                break;

            default:
                break;
        }
    }
}
