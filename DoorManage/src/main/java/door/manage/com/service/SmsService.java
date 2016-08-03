package door.manage.com.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import door.manage.com.app.AppInfo;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.ControlDoorResponse;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorResponse;
import test.greendao.bean.UpDateDoorResponse;

/**
 * Created by shayajie on 2016/7/28.
 */
public class SmsService extends Service {
    private static final String TAG = "SmsService";
    private static final String action = "com.door.sms.restart";


    private static final String permission = "android.permission.BROADCAST_SMS";

    private static final String receiverTAG = "SmsReceiver";
    private DoorService mDoorService;
    private List<Door> doors;

//    private Thread thread;
//
//
//
//
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MyLog.d(TAG, "action: " + action);
            mDoorService = DbUtil.getDoorService();
            if (AppInfo.SMS_RECEIVED_ACTION.equals(action) || AppInfo.SMS_DELIVER_ACTION.equals(action)) {
                Bundle bundle = intent.getExtras();
                StringBuffer messageContent = new StringBuffer();

                doors = mDoorService.queryAll();
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    for (Object pdu : pdus) {
//						SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu,);
                        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = message.getOriginatingAddress();
                        String senderfinal = sender.substring(sender.length() - 11);
                        MyLog.d(TAG, "sender: " + sender);
                        MyLog.d(TAG, "senderfinal: " + senderfinal);
                        for (Door door : doors) {
                            if (door.getPhone().equals(senderfinal)) {
                                MyLog.d(TAG, "messgeappend====" + replaceBlank(message.getMessageBody()));
                                messageContent.append(message.getMessageBody());
                            }
                        }


                    }
                    if (!messageContent.toString().isEmpty()) {

                        Intent intentBroadcast = new Intent();
                        intentBroadcast.putExtra("message", replaceBlank(messageContent.toString()));
                        intentBroadcast.setAction(AppInfo.SMS_RECEIVED);
                        context.sendBroadcast(intentBroadcast);
                        MyLog.d(TAG, "send broadcast and abort");
//	                    abortBroadcast();
                    }
                }
            }
        }
    };



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        MyLog.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.d(TAG, "onStartCommand");
        System.out.println("w ziy kas");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppInfo.SMS_DELIVER_ACTION);
        intentFilter.addAction(AppInfo.SMS_RECEIVED_ACTION);
        registerReceiver(mReceiver, intentFilter, permission, null);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        MyLog.d(TAG, "onDestroy");
        stopForeground(true);
        Intent intent = new Intent(action);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}

