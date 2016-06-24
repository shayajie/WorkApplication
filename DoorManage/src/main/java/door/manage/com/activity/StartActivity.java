package door.manage.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import door.manage.com.R;
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
        setContentView(startView);
        shared = getSharedPreferences("appinfo", 0);
        editor = shared.edit();
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
                redirectto();
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
            DbUtil.firstaddUser();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            MyLog.d(TAG,"==============firstrun_end");
        }
    }
    private void redirectto() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
