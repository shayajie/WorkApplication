package door.manage.com.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import door.manage.com.R;
import door.manage.com.app.AppInfo;
import door.manage.com.service.DoorService;
import door.manage.com.service.ManagerService;
import door.manage.com.service.SmsService;
import door.manage.com.service.UserService;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;

/**
 * Created by shayajie on 2016/6/14.
 */
public abstract class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity";
    private  final String simpleName   = getClass().getSimpleName();
    //    protected RelativeLayout title_back,title_setting;
//    protected TextView title_text;
    protected Resources resources;
    protected Context mContext;
    protected SharedPreferences shared;
    protected SharedPreferences.Editor editor;
    protected UserService mUserService;
    protected DoorService mDoorService;
    protected ManagerService mManagerService;

    protected RelativeLayout title_setting, title_back;
    protected TextView title_textview;

    protected boolean title_back_visible = false;
    protected boolean title_setting_visible = false;

    //短信
    private SmsManager mSmsManager;
    private SmsStatusReceiver mSmsStatusReceiver;
    private SmsDeliveryStatusReceiver mSmsDeliveryStatusReceiver;
    private MSmsReceiver mSmsReceiver;
//    private SmsService.SmsReceiver smsReceiver;

//    protected String getmessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        shared = getSharedPreferences("appinfo", 0);
        editor = shared.edit();
        //短信
        mSmsManager = SmsManager.getDefault();


        mUserService = DbUtil.getUserService();
        mDoorService = DbUtil.getDoorService();
        mManagerService = DbUtil.getManagerService();


        mSmsStatusReceiver = new SmsStatusReceiver();
        registerReceiver(mSmsStatusReceiver, new IntentFilter(AppInfo.SMS_SEND_ACTIOIN));

        mSmsDeliveryStatusReceiver = new SmsDeliveryStatusReceiver();
        registerReceiver(mSmsDeliveryStatusReceiver, new IntentFilter(AppInfo.SMS_DELIVERED_ACTION));

        mSmsReceiver = new MSmsReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sms_received");
        registerReceiver(mSmsReceiver, intentFilter);

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
        if (title_back_visible) {
            title_back.setVisibility(View.VISIBLE);
//            title_back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext,"asdljkhfhdsakjhfkads",Toast.LENGTH_LONG).show();
////                    MyLog.d(TAG,"========="+users.size());
//                }
//            });
        } else {
            title_back.setVisibility(View.GONE);
        }

        if (title_setting_visible) {
            title_setting.setVisibility(View.VISIBLE);

//            title_setting.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        } else {
            title_setting.setVisibility(View.GONE);
        }
    }

    protected void sendMessage(String sender, String message) {
        if(AppInfo.isSendMessage){
            PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new
                    Intent(AppInfo.SMS_SEND_ACTIOIN), 0);
            PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0,
                    new Intent(AppInfo.SMS_DELIVERED_ACTION), 0);

            mSmsManager.sendTextMessage(sender, null, message, sentIntent,
                    deliveryIntent);
        }

    }

    protected abstract void updateUI();

    public class SmsStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            MyLog.d(TAG, "SmsStatusReceiver onReceive.");
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    MyLog.d(TAG, "SmsStatusReceiver onReceive.Activity.RESULT_OK");
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    MyLog.d(TAG, "RESULT_ERROR_GENERIC_FAILURE");
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    MyLog.d(TAG, "RESULT_ERROR_NO_SERVICE");
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    MyLog.d(TAG, "RESULT_ERROR_NULL_PDU");
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    MyLog.d(TAG, "RESULT_ERROR_RADIO_OFF");
                    break;
            }
//            abortBroadcast();
        }
    }

    public class SmsDeliveryStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            MyLog.d(TAG, "SmsDeliveryStatusReceiver onReceive.");
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    MyLog.i(TAG, "RESULT_OK");
                    break;
                case Activity.RESULT_CANCELED:
                    MyLog.i(TAG, "RESULT_CANCELED");

                    break;
            }
//            abortBroadcast();
        }
    }


    public class MSmsReceiver extends BroadcastReceiver {
        public static final String SMS_RECEIVED_ACTION = "sms_received";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != getTopActivity()){
                MyLog.d(TAG,"topactivity===="+getTopActivity());
                MyLog.d(TAG,"getSimpleName===="+simpleName);
                if(getTopActivity().endsWith(simpleName)){
                    String action = intent.getAction();
                    MyLog.d(TAG, "action: " + action);
                    if (SMS_RECEIVED_ACTION.equals(action)) {
                        Bundle bundle = intent.getExtras();
//                        getmessage = bundle.getString("message");
//                        MyLog.d(TAG, getmessage);
                        updateUI();


                    }
                }
            }

//            abortBroadcast();
        }
    }
    private String getTopActivity()
    {
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if(runningTaskInfos != null)

            return runningTaskInfos.get(0).topActivity.getClassName();
        else
            return null ;
    }
    @Override
    protected void onStart() {
        super.onStart();
        MyLog.d(TAG,"onStart");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.d(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSmsReceiver);
        unregisterReceiver(mSmsStatusReceiver);
        unregisterReceiver(mSmsDeliveryStatusReceiver);
    }

}
