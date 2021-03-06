package door.manage.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import door.manage.com.R;
import door.manage.com.adapter.GridView_Adapter;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorRequest;

/**
 * Created by shayajie on 2016/6/29.
 */
public class UserDoorActivity extends BaseActivity{
    private static final String TAG = "UserDoorActivity";

    private Long userId;

    private List<Door> doors;

    private GridView_Adapter mGridView_adapter;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_door_layout);
        mContext = this;
        userId = getIntent().getLongExtra("userid",0L);
        initdata();
        initview();
    }

    private void initview() {
        inittitle(true,false);
        title_textview.setText(resources.getString(R.string.userdooractivity_title_text));
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

        mGridView = (GridView) findViewById(R.id.user_door_gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (doors.size() == position) {
                    Intent intent = new Intent(mContext, AddDoorActivity.class);
                    intent.putExtra("userid", userId);
                    startActivityForResult(intent, 0);
                } else {

                    Door door = doors.get(position);
                    GetDoorRequest getDoorRequest = new GetDoorRequest(door.getPhone());
                    MyLog.d(TAG,getDoorRequest.getMessage());
//                    sendMessage(door.getPhone(), getDoorRequest.getMessage());
//                    String message = AppInfo.A_TAG+AppInfo.LAST_TAG+door.getDoornum()+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"0"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+"111"+AppInfo.LAST_TAG+door.getPhone();
//                    MyLog.d(StringUtils.getDoorMessage(getDoorRequest));
//                    sendMessage(door.getPhone(), message);
                    Intent intent = new Intent(mContext, ManagerControlDoorActivity.class);
                    intent.putExtra("doorId", doors.get(position).getDoorId());
                    intent.putExtra("message",getDoorRequest.getMessage());
                    intent.putExtra("isShowDialog",true);
                    startActivity(intent);
                }
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyLog.d(TAG,"长按");
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
        updateUI();
    }

    private void initdata() {
        doors = mDoorService.query("where USER_ID=?",""+userId);
    }

    @Override
    protected void updateUI() {
        doors = mDoorService.query("where USER_ID=?",""+userId);
        mGridView_adapter = new GridView_Adapter(doors,mContext);
        mGridView.setAdapter(mGridView_adapter);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDoorActivity.this);
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
}
