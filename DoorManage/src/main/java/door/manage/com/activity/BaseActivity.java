package door.manage.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import door.manage.com.R;

/**
 * Created by Administrator on 2016/6/14.
 */
public class BaseActivity extends Activity {
//    protected RelativeLayout title_back,title_setting;
//    protected TextView title_text;
    protected Resources resources;
    protected Context mContext;
    protected SharedPreferences shared;
    protected SharedPreferences.Editor editor;


    protected boolean title_back_visible = false;
    protected boolean title_setting_visible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
//        title_back = (RelativeLayout) findViewById(R.id.title_back);
//        title_setting = (RelativeLayout) findViewById(R.id.title_setting);
//        title_text = (TextView) findViewById(R.id.title_textview);
    }


}
