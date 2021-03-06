package door.manage.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import door.manage.com.R;
import door.manage.com.app.AppInfo;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import door.manage.com.view.Dialog;
import test.greendao.bean.ControlDoorRequest;
import test.greendao.bean.Door;

/**
 * Created by shayajie on 2016/6/29.
 */
public class ManagerControlDoorActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "ManagerControlDoorActivity";
    private List<Door> doors;
    private Door door;
    private Long doorID;

    private RelativeLayout open_door_relayout,close_door_relayout,up_door_relayout,down_door_relayout,stop_relayout;
    //close_door_prompt_message,stop_prompt_message
    private TextView prompt_message,encoderpulses_textview,upperpulse_textview,lowerpulse_textview;
    private ImageView open_door_indicator_lamp_imageview,close_door_indicator_lamp_imageview,up_door_indicator_lamp_imageview,down_door_indicator_lamp_imageview;
    private boolean issend = false;
    private ControlDoorRequest request;
    private boolean isShowDialog;
    private String message;
//	private ToggleButton open_toggleButton,close_toggleButton,stop_toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_control_layout);
        mContext = this;
        doorID = getIntent().getLongExtra("doorId",0L);
        message = getIntent().getStringExtra("message");
        isShowDialog = getIntent().getBooleanExtra("isShowDialog", false);
        initdata();
        initview();
    }

    @Override
    protected void updateUI() {
        MyLog.d(TAG,"updateUI");

        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        door = mDoorService.query(doorID);
        UIData(door);


    }

    private void UIData(Door door){
        encoderpulses_textview.setText(door.getEncoderpulses());
        upperpulse_textview.setText(door.getUpperpulse());
        lowerpulse_textview.setText(door.getLowerpulse());
        if("0".equals(door.getDoorstatus())){
            open_door_relayout.setClickable(true);
            open_door_relayout.setBackgroundResource(R.drawable.gridview_item_layout_bg);

            close_door_relayout.setClickable(false);
            close_door_relayout.setBackgroundResource(R.drawable.controlpresstrue_bg);

            stop_relayout.setClickable(false);
            stop_relayout.setBackgroundResource(R.drawable.controlpresstrue_bg);

            up_door_relayout.setClickable(false);
            up_door_relayout.setBackgroundResource(R.drawable.controlpresstrue_bg);

            down_door_relayout.setClickable(false);
            down_door_relayout.setBackgroundResource(R.drawable.controlpresstrue_bg);

        }else{
            open_door_relayout.setClickable(false);
            open_door_relayout.setBackgroundResource(R.drawable.controlpresstrue_bg);

            close_door_relayout.setClickable(true);
            close_door_relayout.setBackgroundResource(R.drawable.gridview_item_layout_bg);

            stop_relayout.setClickable(true);
            stop_relayout.setBackgroundResource(R.drawable.gridview_item_layout_bg);

            up_door_relayout.setClickable(true);
            up_door_relayout.setBackgroundResource(R.drawable.gridview_item_layout_bg);

            down_door_relayout.setClickable(true);
            down_door_relayout.setBackgroundResource(R.drawable.gridview_item_layout_bg);
        }
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
        up_door_relayout = (RelativeLayout) findViewById(R.id.up_door_relayout);
        down_door_relayout = (RelativeLayout) findViewById(R.id.down_door_relayout);

        open_door_relayout.setOnClickListener(this);
        close_door_relayout.setOnClickListener(this);
        stop_relayout.setOnClickListener(this);
        up_door_relayout.setOnClickListener(this);
        down_door_relayout.setOnClickListener(this);

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
        open_door_indicator_lamp_imageview.setVisibility(View.GONE);
        close_door_indicator_lamp_imageview = (ImageView) findViewById(R.id.close_door_indicator_lamp_imageview);
        close_door_indicator_lamp_imageview.setVisibility(View.GONE);
        up_door_indicator_lamp_imageview = (ImageView) findViewById(R.id.up_door_indicator_lamp_imageview);
        up_door_indicator_lamp_imageview.setVisibility(View.GONE);
        down_door_indicator_lamp_imageview = (ImageView) findViewById(R.id.down_door_indicator_lamp_imageview);
        down_door_indicator_lamp_imageview.setVisibility(View.GONE);
// 		open_toggleButton = (ToggleButton) findViewById(R.id.open_toggleButton);
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
        door = mDoorService.query(doorID);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isShowDialog) {
            MyLog.d(TAG, "onResume");
            promoteDialog(door.getPhone(), message, true);
            isShowDialog = false;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_setting:
                Intent intent = new Intent(mContext, ManagerDoorSettingActivity.class);
                intent.putExtra("doorId", doorID);

                startActivityForResult(intent,0);
                break;
            case R.id.open_door_relayout:
                request  = new ControlDoorRequest(AppInfo.OPERATING_OPEN,door.getPhone());
                MyLog.d(TAG,request.getMessage());
//            sendMessage(door.getPhone(), request.getMessage());
                promoteDialog(door.getPhone(),request.getMessage(),false);
//			open_door_prompt_message.setText("正在开启");
//			open_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case R.id.close_door_relayout:
                request = new ControlDoorRequest(AppInfo.OPERATING_CLOSE,door.getPhone());
                MyLog.d(TAG,request.getMessage());
//                sendMessage(door.getPhone(), request.getMessage());
                promoteDialog(door.getPhone(),request.getMessage(),false);
//			close_door_prompt_message.setText("正在开启");
//			Bitmap color = BitmapFactory.decodeResource(getResources(), R.drawable.green_bg);
//			BitmapDrawable bd= new BitmapDrawable(getResources(), color);
//			close_door_indicator_lamp_imageview.setBackground(bd);
//			setFlickerAnimation(close_door_indicator_lamp_imageview);
                break;
            case R.id.stop_relayout:
                request = new ControlDoorRequest(AppInfo.OPERATING_STOP,door.getPhone());
                MyLog.d(TAG,request.getMessage());
//                sendMessage(door.getPhone(), request.getMessage());
                promoteDialog(door.getPhone(),request.getMessage(),false);
//			stop_prompt_message.setText("发送指令中");
//			open_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.white_second_text_color));
//			close_door_indicator_lamp_imageview.setBackgroundColor(getResources().getColor(R.color.white_second_text_color));
                break;
            case R.id.up_door_relayout:
                request = new ControlDoorRequest(AppInfo.OPERATING_UP,door.getPhone());
                MyLog.d(TAG,request.getMessage());
//                sendMessage(door.getPhone(), request.getMessage());
                promoteDialog(door.getPhone(),request.getMessage(),false);

                break;
            case R.id.down_door_relayout:
                request = new ControlDoorRequest(AppInfo.OPERATING_DOWN,door.getPhone());
                MyLog.d(TAG,request.getMessage());
//                sendMessage(door.getPhone(), request.getMessage());
                promoteDialog(door.getPhone(),request.getMessage(),false);
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

    private AlertDialog alertDialog;

    private void promoteDialog(final String sender, final String message, boolean fasong) {
        final Dialog mdialog = new Dialog(ManagerControlDoorActivity.this);
        mdialog.setTitle(R.string.dialog_title_promote);
        mdialog.setView(R.layout.dialog_layout);

        final TextView text = (TextView) mdialog.getView().findViewById(R.id.message1);
        text.setText("是否发送信息?");
        final Button positive = (Button) mdialog.getView().findViewById(R.id.button21);
        final Button negative = (Button) mdialog.getView().findViewById(R.id.button31);

        final CountDownTimer timer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                positive.setText(resources.getString(R.string.dialog_positive_send) + "(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                positive.setClickable(true);
                negative.setClickable(true);
                text.setText("是否重新发送?");
                positive.setText(R.string.dialog_positive_send_again);
                positive.setTextColor(resources.getColor(R.color.dialog_button_color));

            }
        };

        positive.setText(R.string.dialog_positive_send);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positive.setClickable(false);
                positive.setTextColor(resources.getColor(R.color.dialog_button_false_color));
                text.setText("已发送!请耐心等待。15秒后无消息返回可重新发送或联系管理员");
                sendMessage(sender, message);
                timer.start();
            }
        });
        negative.setText(R.string.dialog_cancel);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                mdialog.alertDialog.dismiss();
            }
        });

        mdialog.create();
        mdialog.show();
        mdialog.setCanceledOnTouchOutside(false);
        if (fasong) {
            positive.performClick();
        }
        negative.setClickable(false);
        alertDialog = mdialog.alertDialog;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
                return true;
            }else {
                finish();
                return false;
            }
        }
        return true;
    }
}
