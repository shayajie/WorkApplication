package door.manage.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

import door.manage.com.R;
import door.manage.com.adapter.GridView_Adapter;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import door.manage.com.view.Dialog;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorRequest;
import test.greendao.bean.LockDoorRequest;
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
        MyLog.d(TAG,"onCreate");
        initdata();
        initview();
    }

    @Override
    protected void updateUI() {
        doors = mDoorService.query("where USER_ID=?", "" + users.get(0).getUserId());
        mGridView_adapter = new GridView_Adapter(doors, mContext);
        mGridView.setAdapter(mGridView_adapter);
    }


    private void initdata() {
        MyLog.d(TAG, "==============initdata_start");
        users = mUserService.query("where name=?", "本机");
        if (users.size() == 1) {
            doors = mDoorService.query("where USER_ID=?", "" + users.get(0).getUserId());
        }else {
            Toast.makeText(mContext,"数据出错",Toast.LENGTH_SHORT).show();
        }
        MyLog.d(TAG, "==============initdata_end");
        MyLog.d(TAG, "" + users.get(0).getUserId());
    }

    private void initview() {
        MyLog.d(TAG,"initview");
        inittitle(false, true);

        title_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.d(TAG,"我是设置按钮");
                Intent intent = new Intent(mContext,ManagerUsersActivity.class);
                startActivityForResult(intent,0);
            }
        });

        mGridView = (GridView) findViewById(R.id.door_gridview);

        mGridView_adapter = new GridView_Adapter(doors, mContext);
        mGridView.setAdapter(mGridView_adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyLog.d(TAG,"onItemClick");
                if (doors.size() == position) {
                    MyLog.d(TAG,"adddoor");
                    Intent intent = new Intent(mContext, AddDoorActivity.class);
                    intent.putExtra("userid", users.get(0).getUserId());
                    startActivityForResult(intent, 0);
                } else {

                    Door door = doors.get(position);
                    GetDoorRequest getDoorRequest = new GetDoorRequest(door.getDoornum(), AppInfo.READ_TAG,door.getPhone());
                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));

                    promoteDialog(door.getPhone(),StringUtils.getDoorMessage(getDoorRequest));

                    MyLog.d(TAG,"controldoor");

//                    sendMessage(door.getPhone(), StringUtils.getDoorMessage(getDoorRequest));
//                    String message = AppInfo.A_TAG+AppInfo.LAST_TAG+door.getDoornum()+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+door.getPhone();
//                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));
//                    sendMessage(door.getPhone(), message);
//                    Intent intent = new Intent(mContext, DoorControlActivity.class);
//                    intent.putExtra("doorId", doors.get(position).getDoorId());
//                    startActivity(intent);
                }

            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyLog.d(TAG,"setOnItemLongClickListener");
                if (doors.size() == position) {
                } else {
                    dialog(doors.get(position));
//                    Door door = doors.get(position);
//                    GetDoorRequest getDoorRequest = new GetDoorRequest(door.getDoornum(), AppInfo.READ_TAG,door.getPhone());
//                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));
//                    sendMessage(door.getPhone(), StringUtils.getDoorMessage(getDoorRequest));
////                    String message = AppInfo.A_TAG+AppInfo.LAST_TAG+door.getDoornum()+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+door.getPhone();
////                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));
////                    sendMessage(door.getPhone(), message);
//                    Intent intent = new Intent(mContext, DoorControlActivity.class);
//                    intent.putExtra("doorId", doors.get(position).getDoorId());
//                    startActivity(intent);
                }

                return true;
            }
        });
    }
    private void promoteDialog(final String sender, final String message) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("这是测试标题");
//        dialog.setView();
//        dialog.setMessage("我是一条测试文本");
        dialog.setView(R.layout.dialog_layout);

        final TextView text = (TextView) dialog.getView().findViewById(R.id.text);
        text.setText("是否发送消息获取门信息!");
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"发送中"+ which,Toast.LENGTH_SHORT).show();
                Field field = null;
                try {
                    field = dialog.getClass().getSuperclass().getSuperclass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(dialog, false);

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.setPositiveButton("发送", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"发送中"+ which,Toast.LENGTH_SHORT).show();
                Field field = null;
                try {
                    field = dialog.getClass().getSuperclass().getSuperclass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(dialog, false);
                    text.setText("已发送,正在获取门信息...");
                    Toast.makeText(MainActivity.this,"发送中",Toast.LENGTH_SHORT).show();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
//                sendMessage(sender,message);
            }
        });
        dialog.setNeutralButton("测试", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this,"wcesghugh"+which,Toast.LENGTH_SHORT).show();
            }
        });

        dialog.create();
        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
               updateUI();
                break;

            default:
                break;
        }
    }

    protected void dialog(final Door door) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否删除？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyLog.d(TAG,"door删除");
                mDoorService.delete(door);
                updateUI();
                Toast.makeText(mContext,resources.getString(R.string.delete_done),Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
    private boolean isExit = false;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                isExit = false;
            }

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(!isExit){
                isExit = true;
                Toast.makeText(mContext,"再按一次退出",Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(0,3000);
                return true;
            }else {
                finish();
                return false;
            }
        }
        return true;
    }
}
