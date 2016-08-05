package door.manage.com.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import door.manage.com.utils.MyLog;

/**
 * Created by shayajie on 2016/7/29.
 */
public class MyRestartReceiver extends BroadcastReceiver{
    private static final String TAG = "MyRestartReceiver";
    private static final String action1 = "com.door.sms.restart";
    private static final String action2 = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLog.d(TAG,"onReceive action "+intent.getAction());
        if(intent.getAction().equals(action1)||intent.getAction().equals(action2)){

            intent = new Intent(context,SmsService.class);
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.startService(intent);
        }
    }
}
