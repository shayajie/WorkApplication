package door.manage.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import door.manage.com.app.AppInfo;
import door.manage.com.utils.MyAsyncTask;
import door.manage.com.utils.MyLog;

/**
 * Created by shayajie on 2016/8/4.
 */
public class DataService extends Service{
    private final String TAG = getClass().getSimpleName();

    private MyAsyncTask myAsyncTask;
    private String message;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                Intent intentBroadcast = new Intent();
                intentBroadcast.putExtra("message",message);
                intentBroadcast.setAction(AppInfo.SMS_RECEIVED);
                sendBroadcast(intentBroadcast);
                MyLog.d(TAG, "send broadcast and abort");
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        message = intent.getStringExtra("message");
        myAsyncTask = new MyAsyncTask(handler);
        myAsyncTask.execute(message);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {


    }
}
