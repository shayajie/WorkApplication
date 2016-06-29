package door.manage.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import door.manage.com.R;
import door.manage.com.adapter.GridView_Adapter;
import door.manage.com.utils.DbUtil;
import test.greendao.bean.Manager;
import test.greendao.bean.User;

/**
 * Created by shayajie on 2016/6/28.
 */
public class ManagerUsersActivity extends BaseActivity implements View.OnClickListener {
    private static final String Tag = "ManagerUsersActivity";
    private List<User> users;
    private Manager manager;

    private GridView mGridView;
    private GridView_Adapter mGridView_adapter;
    private LinearLayout verification_layout;
    private TextView password_prompt_text;
    private EditText password_edittext;
    private Button password_button;

    private boolean ispass = false;
    private boolean isupdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_users_layout);
        mContext = this;
        initdata();
        initview();
    }

    private void initview() {
        inittitle(true,false);
        title_textview.setText(resources.getString(R.string.managerusersactivity_title_text));
        title_back.setOnClickListener(this);

        mGridView = (GridView) findViewById(R.id.user_gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (users.size() == position) {
                    Intent intent = new Intent(mContext, AddUserActivity.class);
//                    intent.putExtra("userid", users.get(0).getUserId());
                    startActivityForResult(intent, 0);
                } else {
                    Intent intent = new Intent(mContext, UserDoorActivity.class);
                    intent.putExtra("userid", users.get(position).getUserId());
                    startActivityForResult(intent, 0);

                }

            }
        });
        verification_layout = (LinearLayout) findViewById(R.id.verification_layout);

        password_prompt_text = (TextView) findViewById(R.id.password_prompt_text);

        password_button = (Button) findViewById(R.id.password_button);
        password_button.setOnClickListener(this);

        password_edittext = (EditText) findViewById(R.id.password_edittext);

        isPass();
    }

    private void initdata() {
        users =  mUserService.query("where MANAGER_ID=?", ""+DbUtil.managerId);
        manager = mManagerService.query(DbUtil.managerId);
    }

    @Override
    protected void updateUI() {
        users =  mUserService.query("where MANAGER_ID=?", ""+DbUtil.managerId);
        mGridView_adapter = new GridView_Adapter(mContext,users);
        mGridView.setAdapter(mGridView_adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                if(isupdate){
                    setResult(1);
                }else {
                    setResult(0);
                }
                finish();
                break;
            case R.id.password_button:
                if(manager.getPassword().equals(password_edittext.getText().toString())){
                    ispass = true;
                    password_prompt_text.setText(null);
                }else{
                    ispass = false;
                    password_prompt_text.setText(resources.getString(R.string.password_erro));
                }
                isPass();
                break;
            default:
                break;
        }
    }
    private void isPass() {
        if(ispass){
            verification_layout.setVisibility(View.GONE);
            mGridView.setVisibility(View.VISIBLE);
            updateUI();
        }else {
            verification_layout.setVisibility(View.VISIBLE);
            mGridView.setVisibility(View.GONE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                updateUI();
                break;

            default:
                break;
        }
    }
}
