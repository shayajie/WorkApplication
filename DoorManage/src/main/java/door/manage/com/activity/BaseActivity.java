package door.manage.com.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import door.manage.com.R;
import door.manage.com.app.AppInfo;
import door.manage.com.service.DoorService;
import door.manage.com.service.ManagerService;
import door.manage.com.service.UserService;
import door.manage.com.utils.DbUtil;

/**
 * Created by shayajie on 2016/6/14.
 */
public class BaseActivity extends Activity {
//    protected RelativeLayout title_back,title_setting;
//    protected TextView title_text;
    protected Resources resources;
    protected Context mContext;
    protected SharedPreferences shared;
    protected SharedPreferences.Editor editor;
    protected UserService mUserService;
    protected DoorService mDoorService;
    protected ManagerService mManagerService;

    protected RelativeLayout title_setting,title_back;
    protected TextView title_textview;

    protected boolean title_back_visible = false;
    protected boolean title_setting_visible = false;

    //短信
    private SmsManager mSmsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        shared = getSharedPreferences("appinfo", 0);
        editor = shared.edit();
        mSmsManager = SmsManager.getDefault();
        mUserService = DbUtil.getUserService();
        mDoorService = DbUtil.getDoorService();
        mManagerService = DbUtil.getManagerService();
//        title_back = (RelativeLayout) findViewById(R.id.title_back);
//        title_setting = (RelativeLayout) findViewById(R.id.title_setting);
//        title_text = (TextView) findViewById(R.id.title_textview);
    }

    protected void inittitle(boolean... check) {
        title_back_visible = check[0];
        title_setting_visible = check[1];
        title_textview = (TextView) findViewById(R.id.title_textview);
//        title_textview.setText(""+users.get(0).getUserId());
        title_textview.setText(resources.getString(R.string.mainactivity_title_text));
        title_back = (RelativeLayout) findViewById(R.id.title_back);
        title_setting = (RelativeLayout) findViewById(R.id.title_setting);
        if(title_back_visible){
            title_back.setVisibility(View.VISIBLE);
//            title_back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext,"asdljkhfhdsakjhfkads",Toast.LENGTH_LONG).show();
////                    MyLog.d(TAG,"========="+users.size());
//                }
//            });
        }else{
            title_back.setVisibility(View.GONE);
        }

        if(title_setting_visible){
            title_setting.setVisibility(View.VISIBLE);

//            title_setting.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }else {
            title_setting.setVisibility(View.GONE);
        }
    }

    protected void sendMessage(String sender,String message){
         PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new
                 Intent(AppInfo.SMS_SEND_ACTIOIN), 0);
         PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0,
         new Intent(AppInfo.SMS_DELIVERED_ACTION), 0);

         mSmsManager.sendTextMessage(sender, null, message, sentIntent,
         deliveryIntent);
    }
}
