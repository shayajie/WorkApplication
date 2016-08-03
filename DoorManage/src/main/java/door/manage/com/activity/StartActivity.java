package door.manage.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import door.manage.com.R;
import door.manage.com.service.SmsService;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;

/**
 * Created by shayajie on 2016/6/16.
 */
public class StartActivity extends BaseActivity{
    private final static String TAG = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View startView = View.inflate(this, R.layout.start_layout,null);
        MyLog.d(TAG,"onCreate");
        setContentView(startView);
        shared = getSharedPreferences("appinfo", 0);
        editor = shared.edit();
        Intent intent  = new Intent(this,SmsService.class);
        startService(intent);
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        String NativePhoneNumber= telephonyManager.getLine1Number();
        MyLog.d(TAG,NativePhoneNumber);
        firstRun();
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        startView.setAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                MyLog.d(TAG,"onAnimationEnd");
//                redirectto();
                reddebug();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void updateUI() {

    }

    private void firstRun() {
        boolean isFirstRun = shared.getBoolean("isFirstRun", true);
        if (isFirstRun) {
//            long a = mDbManager.add_admin();
            MyLog.d(TAG,"==============firstrun_start");



            DbUtil.firstaddUser("");
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            MyLog.d(TAG,"==============firstrun_end");

        }
    }
    private void redirectto() {
        MyLog.d(TAG,"redirectto");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void reddebug(){
        MyLog.d(TAG,"reddebug");
        Intent intent = new Intent(this,FileControlActivity.class);
        startActivity(intent);
        finish();
    }

}
