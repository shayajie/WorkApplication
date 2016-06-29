package door.manage.com.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import door.manage.com.R;
import door.manage.com.utils.DbUtil;
import test.greendao.bean.User;

/**
 * Created by shayajie on 2016/6/29.
 */
public class AddUserActivity extends BaseActivity{
    private static final String TAG = "AddUserActivity";
    private EditText add_user_name_edittext,add_user_phone_edittext;
    private Button add_user_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_layout);
        mContext  = this;
        initdata();
        initview();
    }

    private void initdata() {

    }

    private void initview() {
        inittitle(true,false);
        title_textview.setText(resources.getString(R.string.adduseractivity_title_text));
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });
        add_user_name_edittext = (EditText) findViewById(R.id.add_user_name_edittext);
        add_user_phone_edittext = (EditText) findViewById(R.id.add_user_phone_edittext);
        add_user_button = (Button) findViewById(R.id.add_user_button);
        add_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {
        if(!add_user_name_edittext.getText().toString().isEmpty()){
            if(!add_user_phone_edittext.getText().toString().isEmpty()){

                User user = new User();
                user.setName(add_user_name_edittext.getText().toString());
                user.setPhone(add_user_phone_edittext.getText().toString());
                user.setManagerId(DbUtil.managerId);

                boolean issucceed_add = DbUtil.addUser(user);
                if(issucceed_add){
                    Toast.makeText(mContext,resources.getString(R.string.add_successed),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,resources.getString(R.string.userphone_exist),Toast.LENGTH_SHORT).show();
                }


            }else {
                Toast.makeText(mContext,resources.getString(R.string.user_phone_null),Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(mContext,resources.getString(R.string.user_name_null),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void updateUI() {

    }
}
