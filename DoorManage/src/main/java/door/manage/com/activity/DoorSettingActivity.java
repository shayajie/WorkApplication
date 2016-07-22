package door.manage.com.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import door.manage.com.R;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.Door;
import test.greendao.bean.UpDateDoorRequest;

/**
 * Created by shayajie on 2016/6/28.
 */
public class DoorSettingActivity extends BaseActivity implements View.OnClickListener {
    private static final String Tag = "DoorSettingActivity";
    private boolean ispass = false;
    private boolean isupdate = false;
    private Long doorID;
    private Door door;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_setting_layout);
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
        update_door_button.setOnClickListener(this);
        password_button.setOnClickListener(this);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.password_button:
                if(door.getPassword().equals(password_edittext.getText().toString())){
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
                                MyLog.d(Tag,StringUtils.upDateDoorRequest(request));
                                sendMessage(door.getPhone(), StringUtils.upDateDoorRequest(request));
                                isupdate = true;
                                Toast.makeText(mContext,resources.getString(R.string.updata_done),Toast.LENGTH_SHORT).show();
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


}
