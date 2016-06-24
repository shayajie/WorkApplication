package door.manage.com.activity;

import java.util.List;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import door.manage.com.R;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.ControlDoorRequest;
import test.greendao.bean.Door;

public class DoorControlActivity extends BaseActivity implements OnClickListener {
	private static final String Tag = "DoorControlActivity";
	private List<Door> doors;
	private Door door;
	private Long doorID;

	private RelativeLayout open_door_relayout,close_door_relayout,stop_relayout;
	//close_door_prompt_message,stop_prompt_message
	private TextView prompt_message,encoderpulses_textview,upperpulse_textview,lowerpulse_textview;
	private ImageView open_door_indicator_lamp_imageview,close_door_indicator_lamp_imageview,up_door_indicator_lamp_imageview,down_door_indicator_lamp_imageview;
	private boolean issend = false;
    private ControlDoorRequest request;
//	private ToggleButton open_toggleButton,close_toggleButton,stop_toggleButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.door_control_layout);
		mContext = this;
		doorID = getIntent().getLongExtra("doorId",0L);
		initdata();
		initview();
	}

	@Override
	protected void updateUI() {
		MyLog.d(Tag,"updateUI");
		door = DbUtil.getDoorService().query(doorID);
		UIData(door);

	}

	private void UIData(Door door){
		encoderpulses_textview.setText(door.getEncoderpulses());
		upperpulse_textview.setText(door.getUpperpulse());
		lowerpulse_textview.setText(door.getLowerpulse());
		switch (door.getDoorstatus()){
//			状态
//			已关闭：0
//			已开启：1
//			已下降：2
//			停止：  3
//			下降中：4
//			上升中：5

			case "0":
				prompt_message.setText(resources.getString(R.string.door_closed_text));
				break;
			case "1":
				prompt_message.setText(resources.getString(R.string.door_opened_text));
				break;
			case "2":
				prompt_message.setText(resources.getString(R.string.door_falling_text));
				break;
			case "3":
				prompt_message.setText(resources.getString(R.string.door_stoped_text));
				break;
			case "4":
				prompt_message.setText(resources.getString(R.string.door_closing_text));
				break;
			case "5":
				prompt_message.setText(resources.getString(R.string.door_openning_text));
				break;
		}

	}

	private void initview() {
		inittitle(true,true);

		title_textview.setText(resources.getString(R.string.doorcontrolactivity_title_text));
		title_back.setOnClickListener(this);
		title_setting.setOnClickListener(this);

//		title = (TextView) findViewById(R.id.title_textview);
//		title_back = (RelativeLayout) findViewById(R.id.title_back);
//		title_setting = (RelativeLayout) findViewById(R.id.title_setting);
//		title_setting.setVisibility(View.VISIBLE);
//		title_setting.setOnClickListener(this);
//		title.setText("用户门管理");
//		title_back.setVisibility(View.VISIBLE);
//		title_back.setOnClickListener(this);
		
		open_door_relayout = (RelativeLayout) findViewById(R.id.open_door_relayout);
		close_door_relayout = (RelativeLayout) findViewById(R.id.close_door_relayout);
		stop_relayout = (RelativeLayout) findViewById(R.id.stop_relayout);
		open_door_relayout.setOnClickListener(this);
		close_door_relayout.setOnClickListener(this);
		stop_relayout.setOnClickListener(this);

		prompt_message = (TextView) findViewById(R.id.prompt_message);


//		open_door_prompt_message = (TextView) findViewById(R.id.open_door_prompt_message);
//		close_door_prompt_message = (TextView) findViewById(R.id.close_door_prompt_message);
//		stop_prompt_message = (TextView) findViewById(R.id.stop_prompt_message);

		encoderpulses_textview = (TextView) findViewById(R.id.encoderpulses_textview);
		upperpulse_textview = (TextView) findViewById(R.id.upperpulse_textview);
		lowerpulse_textview = (TextView) findViewById(R.id.lowerpulse_textview);
//		encoderpulses_textview.setText(door.getEncoderpulses());
//		upperpulse_textview.setText(door.getUpperpulse());
//		lowerpulse_textview.setText(door.getLowerpulse());

		open_door_indicator_lamp_imageview = (ImageView) findViewById(R.id.open_door_indicator_lamp_imageview);
		close_door_indicator_lamp_imageview = (ImageView) findViewById(R.id.close_door_indicator_lamp_imageview);
//		open_toggleButton = (ToggleButton) findViewById(R.id.open_toggleButton);
//		close_toggleButton = (ToggleButton) findViewById(R.id.close_toggleButton);
//		stop_toggleButton = (ToggleButton) findViewById(R.id.stop_toggleButton);
//		open_toggleButton.setChecked(true);
//		open_toggleButton.setClickable(false);
//		open_toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				if(isChecked){
//					
//				}else {
//
//				}
//			}
//		});
		UIData(door);
	}

	private void initdata() {
		door = DbUtil.getDoorService().query(doorID);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.title_back:
			finish();
			break;
		case R.id.title_setting:
//			Intent intent = new Intent(mContext, DoorSettingActivity.class);
//			startActivity(intent);
			break;
		case R.id.open_door_relayout:
            request  = new ControlDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG,AppInfo.OPERATING_OPEN,door.getPhone());

            sendMessage(door.getPhone(),StringUtils.controlDoorRequest(request));
//			open_door_prompt_message.setText("正在开启");
//			open_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.green));
			break;
		case R.id.close_door_relayout:
            request = new ControlDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG,AppInfo.OPERATING_CLOSE,door.getPhone());

            sendMessage(door.getPhone(),StringUtils.controlDoorRequest(request));
//			close_door_prompt_message.setText("正在开启");
//			Bitmap color = BitmapFactory.decodeResource(getResources(), R.drawable.green_bg);
//			BitmapDrawable bd= new BitmapDrawable(getResources(), color);
//			close_door_indicator_lamp_imageview.setBackground(bd);
//			setFlickerAnimation(close_door_indicator_lamp_imageview);
			break;
		case R.id.stop_relayout:
			request = new ControlDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG,AppInfo.OPERATING_STOP,door.getPhone());

			sendMessage(door.getPhone(),StringUtils.controlDoorRequest(request));
//			stop_prompt_message.setText("发送指令中");
//			open_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.white_second_text_color));
//			close_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.white_second_text_color));
			break;
        case R.id.up_door_relayout:
			request = new ControlDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG,AppInfo.OPERATING_UP,door.getPhone());

			sendMessage(door.getPhone(),StringUtils.controlDoorRequest(request));
                break;
        case R.id.down_door_relayout:
			request = new ControlDoorRequest(door.getDoornum(),AppInfo.WRITE_TAG,AppInfo.OPERATING_DOWN,door.getPhone());

			sendMessage(door.getPhone(),StringUtils.controlDoorRequest(request));
                break;
		default:
			break;
		}
	}
	private void setFlickerAnimation(ImageView iv_chat_head) {  
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible  
        animation.setDuration(500); // duration - half a second  
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate  
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely  
        animation.setRepeatMode(Animation.REVERSE); //   
        iv_chat_head.setAnimation(animation);  
}
}
