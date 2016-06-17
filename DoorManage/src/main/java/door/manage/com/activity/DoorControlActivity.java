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
import door.manage.com.utils.DbUtil;
import test.greendao.bean.Door;

public class DoorControlActivity extends BaseActivity implements OnClickListener {
	private static final String Tag = "DoorControlActivity";
	private List<Door> doors;
	private Door door;
	private Long doorID;

	private RelativeLayout open_door_relayout,close_door_relayout,stop_relayout;
	private TextView open_door_prompt_message,close_door_prompt_message,stop_prompt_message;
	private ImageView open_door_indicator_lamp_imageview,close_door_indicator_lamp_imageview;
	private boolean issend = false;
	
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
		
		open_door_prompt_message = (TextView) findViewById(R.id.open_door_prompt_message);
		close_door_prompt_message = (TextView) findViewById(R.id.close_door_prompt_message);
		stop_prompt_message = (TextView) findViewById(R.id.stop_prompt_message);
		
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
			open_door_prompt_message.setText("正在开启");
			open_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.green));
			break;
		case R.id.close_door_relayout:
//			close_door_prompt_message.setText("正在开启");
//			Bitmap color = BitmapFactory.decodeResource(getResources(), R.drawable.green_bg);
//			BitmapDrawable bd= new BitmapDrawable(getResources(), color);
//			close_door_indicator_lamp_imageview.setBackground(bd);
//			setFlickerAnimation(close_door_indicator_lamp_imageview);
			break;
		case R.id.stop_relayout:
			stop_prompt_message.setText("发送指令中");
			open_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.white_second_text_color));
			close_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.white_second_text_color));
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
