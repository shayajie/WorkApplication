package door.manage.com.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import door.manage.com.R;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.Door;
import test.greendao.bean.LockDoorRequest;
import test.greendao.bean.Manager;
import test.greendao.bean.UpDateDoorRequest;

/**
 * Created by shayajie on 2016/6/29.
 */
public class ManagerDoorSettingActivity extends BaseActivity implements View.OnClickListener {
    private static final String Tag = "ManagerDoorSettingActivity";
    private boolean ispass = false;
    private boolean isupdate = false;
    private Long doorID;
    private Door door;
    private Manager manager;

    private LinearLayout verification_layout, door_setting_layout;
    private TextView password_prompt_text;
    private EditText update_door_name_edittext,
            update_door_phone_edittext,
            update_door_encoder_pulses_edittext,
            update_door_pulse_upper_edittext,
            update_door_pulse_lower_edittext,
            update_door_password_edittext,
            password_edittext;
    private Button update_door_button,password_button;
    private Switch onekey_close_door_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_door_setting_layout);
        mContext = this;
        doorID = getIntent().getLongExtra("doorId", 0L);
        initdata();
        initview();
    }

    private void initview() {
        inittitle(true,false);
        title_textview.setText(resources.getString(R.string.doorsettingactivity_title_text));
        title_back.setOnClickListener(this);

        verification_layout = (LinearLayout) findViewById(R.id.verification_layout);
        door_setting_layout = (LinearLayout) findViewById(R.id.door_setting_layout);

        password_prompt_text = (TextView) findViewById(R.id.password_prompt_text);

        update_door_button = (Button) findViewById(R.id.update_door_button);
        password_button = (Button) findViewById(R.id.password_button);
        onekey_close_door_button = (Switch) findViewById(R.id.one_key_lock_switch);
        update_door_button.setOnClickListener(this);
        password_button.setOnClickListener(this);
        onekey_close_door_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LockDoorRequest request = new LockDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG,door.getPhone());
                //测试发现 界面显示true  点击后变false 这里ischecked应该是点击后状态改变获取的
                if(onekey_close_door_button.isChecked()){
                    MyLog.d(Tag,"true");
                    request.setRemotelock("1");

                }else{
                    MyLog.d(Tag,"flase");
                    request.setRemotelock("0");
                }

                dialog(request);
            }
        });

//        onekey_close_door_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //isChecked=true 门已锁 doorlock = 1
//
//
//            }
//        });

        update_door_name_edittext = (EditText) findViewById(R.id.update_door_name_edittext);
        update_door_phone_edittext = (EditText) findViewById(R.id.update_door_phone_edittext);
        update_door_encoder_pulses_edittext = (EditText) findViewById(R.id.update_door_encoder_pulses_edittext);
        update_door_pulse_upper_edittext = (EditText) findViewById(R.id.update_door_pulse_upper_edittext);
        update_door_pulse_lower_edittext = (EditText) findViewById(R.id.update_door_pulse_lower_edittext);
        update_door_password_edittext = (EditText) findViewById(R.id.update_door_password_edittext);
        password_edittext = (EditText) findViewById(R.id.password_edittext);
        isPass();
    }

    private void isPass() {
        if(ispass){
            verification_layout.setVisibility(View.GONE);
            door_setting_layout.setVisibility(View.VISIBLE);
            updateUI();
        }else {
            verification_layout.setVisibility(View.VISIBLE);
            door_setting_layout.setVisibility(View.GONE);

        }
    }

    private void initdata() {
        door = mDoorService.query(doorID);
        manager = mManagerService.query(DbUtil.managerId);
    }



    @Override
    protected void updateUI() {
        door = mDoorService.query(doorID);
        update_door_name_edittext.setText(door.getDoorname());
        update_door_phone_edittext.setText(door.getPhone());
        update_door_encoder_pulses_edittext.setText(door.getEncoderpulses());
        update_door_pulse_lower_edittext.setText(door.getLowerpulse());
        update_door_pulse_upper_edittext.setText(door.getUpperpulse());
        update_door_password_edittext.setText(door.getPassword());
        if("1".equals(door.getDoorlock())){
            onekey_close_door_button.setChecked(true);
        }else {
            onekey_close_door_button.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
            case R.id.update_door_button:

                checkEditText();

                break;
//            case R.id.s:
//
//                LockDoorRequest request = new LockDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG);
//
//                Toast.makeText(mContext,"这是测试文本呢",Toast.LENGTH_SHORT).show();
//
//                break;
            case R.id.title_back:
                if(isupdate){
                    setResult(1);
                }else {
                    setResult(0);
                }
                finish();
                break;
            default:
                break;
        }
    }
    private void checkEditText(){
        if(!update_door_name_edittext.getText().toString().isEmpty()){

            if(!update_door_phone_edittext.getText().toString().isEmpty()){

                if(!update_door_encoder_pulses_edittext.getText().toString().isEmpty()){

                    if(!update_door_pulse_upper_edittext.getText().toString().isEmpty()){

                        if(!update_door_pulse_lower_edittext.getText().toString().isEmpty()){

                            if(!update_door_password_edittext.getText().toString().isEmpty()){

                                //判断完毕
                                Door door = mDoorService.query(doorID);
                                //门号码修改？？？
                                door.setDoorname(update_door_name_edittext.getText().toString());
                                door.setPhone(update_door_phone_edittext.getText().toString());
                                door.setEncoderpulses(update_door_encoder_pulses_edittext.getText().toString());
                                door.setUpperpulse(update_door_pulse_upper_edittext.getText().toString());
                                door.setLowerpulse(update_door_pulse_lower_edittext.getText().toString());
                                door.setPassword(update_door_password_edittext.getText().toString());
                                MyLog.d(Tag,door.toString());
                                mDoorService.update(door);
                                UpDateDoorRequest request = new UpDateDoorRequest(door.getDoornum(), AppInfo.WRITE_TAG,door.getEncoderpulses(),door.getUpperpulse(),door.getLowerpulse(),door.getPassword(),door.getPhone());
//
                                MyLog.d(Tag,StringUtils.upDateDoorRequest(request));
                                sendMessage(door.getPhone(), StringUtils.upDateDoorRequest(request));
                                isupdate = true;
                            }else{
                                Toast.makeText(mContext,resources.getString(R.string.toast_passwordisnull),Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(mContext,resources.getString(R.string.toast_pulse_lowerisnull),Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(mContext,resources.getString(R.string.toast_pulse_upperisnull),Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(mContext,resources.getString(R.string.toast_encoder_pulsesisnull),Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(mContext,resources.getString(R.string.toast_phoneisnull),Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(mContext,resources.getString(R.string.toast_nameisnull),Toast.LENGTH_SHORT).show();
        }
    }

    protected void dialog(final LockDoorRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ManagerDoorSettingActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否要发送远程锁信息？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MyLog.d(Tag,StringUtils.lockDoorRequest(request));
                sendMessage(door.getPhone(),StringUtils.lockDoorRequest(request));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onekey_close_door_button.setChecked(!onekey_close_door_button.isChecked());
            }
        });

        builder.create().show();
    }
}
