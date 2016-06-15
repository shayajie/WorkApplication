package door.manage.com.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import door.manage.com.R;

/**
 * Created by Administrator on 2016/6/14.
 */
public class MainActivity extends BaseActivity{
    private final static String TAG = "MainActivity";
    //标题栏
    private RelativeLayout title_setting,title_back;
    private TextView title_textview;


    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
        initview();
    }

    private void initdata() {

    }

    private void initview() {
        inittitle();
        mGridView = (GridView) findViewById(R.id.door_gridview);
    }

    private void inittitle() {
        title_back_visible = true;
        title_setting_visible = true;
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText(resources.getString(R.string.mainactivity_title_text));
        title_back = (RelativeLayout) findViewById(R.id.title_back);
        title_setting = (RelativeLayout) findViewById(R.id.title_setting);
        if(title_back_visible){
            title_back.setVisibility(View.VISIBLE);
            title_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else{
            title_back.setVisibility(View.GONE);
        }

        if(title_setting_visible){
            title_setting.setVisibility(View.VISIBLE);
            title_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else {
            title_setting.setVisibility(View.GONE);
        }
    }
}
