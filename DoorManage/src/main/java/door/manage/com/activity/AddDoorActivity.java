package door.manage.com.activity;


import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import door.manage.com.R;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import test.greendao.bean.Door;

public class AddDoorActivity extends BaseActivity implements OnClickListener {
    private static final String Tag = "DoorControlActivity";
	//add_door_id_edittext,
	private EditText add_door_name_edittext,  add_door_phone_edittext,
			add_door_encoder_pulses_edittext, add_door_pulse_upper_edittext, add_door_pulse_lower_edittext,add_door_password_edittext;
	private Button add_door_button;
//	private TextView title;
//	private RelativeLayout title_setting;
//	private RelativeLayout title_back;
//	private Context mContext;

	private boolean isrefush = false;
	private Long userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_door_layout);
		mContext = this;
		isrefush = false;
		userid = getIntent().getLongExtra("userid",0L);
		initview();
	}

	@Override
	protected void updateUI() {

	}

	private void initview() {
		inittitle(true,false);
		title_textview.setText(resources.getString(R.string.adddooractivity_title_text));
		title_back.setOnClickListener(this);
		
		
		add_door_name_edittext = (EditText) findViewById(R.id.add_door_name_edittext);
//		add_door_id_edittext = (EditText) findViewById(R.id.add_door_id_edittext);
		add_door_phone_edittext = (EditText) findViewById(R.id.add_door_phone_edittext);
		add_door_encoder_pulses_edittext = (EditText) findViewById(R.id.add_door_encoder_pulses_edittext);
		add_door_pulse_upper_edittext = (EditText) findViewById(R.id.add_door_pulse_upper_edittext);
		add_door_pulse_lower_edittext = (EditText) findViewById(R.id.add_door_pulse_lower_edittext);
		add_door_password_edittext = (EditText) findViewById(R.id.add_door_password_edittext);
		add_door_button = (Button) findViewById(R.id.add_door_button);
		add_door_button.setOnClickListener(this);
		add_door_phone_edittext.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_door_button:

			check();

			break;
		case R.id.title_back:
			if(isrefush){
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

	private void check() {
		if(!add_door_name_edittext.getText().toString().isEmpty()){
			if(!add_door_phone_edittext.getText().toString().isEmpty()){
				if(!add_door_encoder_pulses_edittext.getText().toString().isEmpty()){
					if(!add_door_pulse_upper_edittext.getText().toString().isEmpty()){
						if(!add_door_pulse_lower_edittext.getText().toString().isEmpty()){
							if(!add_door_password_edittext.getText().toString().isEmpty()){
								String phone = add_door_phone_edittext.getText().toString().replace(" ","");
								phone = phone.trim();
								if(phone.length()==11){
									Door door = new Door();
									door.setDoorname(add_door_name_edittext.getText().toString());
//           					 door.setDoornum(doorid);
									MyLog.d(Tag,add_door_phone_edittext.getText().toString());

									door.setPhone(phone);
									MyLog.d(Tag,phone);
									door.setEncoderpulses(add_door_encoder_pulses_edittext.getText().toString());
									door.setUpperpulse(add_door_pulse_upper_edittext.getText().toString());
									door.setLowerpulse(add_door_pulse_lower_edittext.getText().toString());
									door.setPassword(add_door_password_edittext.getText().toString());
									door.setUserId(userid);
									door.setDoorstatus("0");
									boolean issucceed_add = DbUtil.addDoor(door);
									if(!isrefush){
										isrefush = issucceed_add;
									}
									if(issucceed_add){
										Toast.makeText(mContext,resources.getString(R.string.add_successed),Toast.LENGTH_SHORT).show();
									}else{
										Toast.makeText(mContext,resources.getString(R.string.doorphone_exist),Toast.LENGTH_SHORT).show();
									}
								}else {
									Toast.makeText(this,resources.getString(R.string.toast_phone_erro),Toast.LENGTH_SHORT).show();
								}
							}else {
								Toast.makeText(this,resources.getString(R.string.toast_passwordisnull),Toast.LENGTH_SHORT).show();
							}
						}else {
							Toast.makeText(this,resources.getString(R.string.toast_pulse_lowerisnull),Toast.LENGTH_SHORT).show();
						}
					}else {
						Toast.makeText(this,resources.getString(R.string.toast_pulse_upperisnull),Toast.LENGTH_SHORT).show();
					}
				}else {
					Toast.makeText(this,resources.getString(R.string.toast_encoder_pulsesisnull),Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(this,resources.getString(R.string.toast_phoneisnull),Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(this,resources.getString(R.string.toast_nameisnull),Toast.LENGTH_SHORT).show();
		}

	}

}
