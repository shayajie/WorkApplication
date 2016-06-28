package door.manage.com.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.List;

import door.manage.com.app.AppInfo;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.ControlDoorResponse;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorResponse;
import test.greendao.bean.UpDateDoorResponse;

public class SmsReceiver extends BroadcastReceiver {
	 	private static final String TAG = "SmsReceiver";
	    private String sender = "";
		private DoorService mDoorService;
		private List<Door> doors;
		public void getsender(String sender) {
			mDoorService = DbUtil.getDoorService();

			this.sender = sender;
		}

	@Override
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        MyLog.d(TAG,"action: "+action);
            mDoorService = DbUtil.getDoorService();
	        if (AppInfo.SMS_RECEIVED_ACTION.equals(action)||AppInfo.SMS_DELIVER_ACTION.equals(action)) {
	            Bundle bundle = intent.getExtras();
	            StringBuffer messageContent = new StringBuffer();

				doors = mDoorService.queryAll();
	            if (bundle != null) {
	                Object[] pdus = (Object[]) bundle.get("pdus");
	                for (Object pdu : pdus) {
	                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
	                    String sender = message.getOriginatingAddress();
	                    MyLog.d(TAG,"sender: "+sender);

						for (Door door :doors){
							if (door.getPhone().equals(sender)) {
								messageContent.append(message.getMessageBody());
							}
						}


	                }
	                if(!messageContent.toString().isEmpty()) {
	                    MyLog.d(TAG,"send message broadcast.");
                        String[] strings = messageContent.toString().split(AppInfo.LAST_TAG);
						for (String s : strings){
							MyLog.d(TAG,"strings========="+s);
						}
						List<Door> doors;
						switch (strings[0]){
                            case AppInfo.A_TAG:
								MyLog.d(TAG,"AppInfo.A_TAG");
                                GetDoorResponse doorResponse = StringUtils.getDoorResponse(strings);
								doors = mDoorService.query("where doornum=?",doorResponse.getDoornum());
                                if(doors.size()==1){
                                    Door door = doors.get(0);
                                    door.setPhone(doorResponse.getPhone());
                                    door.setEncoderpulses(doorResponse.getEncoderpulses());
                                    door.setUpperpulse(doorResponse.getUpperpulse());
                                    door.setLowerpulse(doorResponse.getLowerpulse());
                                    door.setDoorstatus(doorResponse.getOperatingstatus());
                                    mDoorService.update(door);
                                }

                                break;
                            case AppInfo.B_TAG:
								MyLog.d(TAG,"AppInfo.B_TAG");
								ControlDoorResponse controlDoorResponse = StringUtils.controlDoorResponse(strings);
								doors = mDoorService.query("where doornum=?",controlDoorResponse.getDoornum());
								if(doors.size()==1){
									Door door = doors.get(0);
									door.setPhone(controlDoorResponse.getPhone());
									door.setEncoderpulses(controlDoorResponse.getEncoderpulses());
									door.setUpperpulse(controlDoorResponse.getUpperpulse());
									door.setLowerpulse(controlDoorResponse.getLowerpulse());
									door.setDoorstatus(controlDoorResponse.getOperatingstatus());
									mDoorService.update(door);
								}
                                break;
                            case AppInfo.C_TAG:
								MyLog.d(TAG,"AppInfo.C_TAG");
								UpDateDoorResponse upDateDoorResponse = StringUtils.upDateDoorResponse(strings);
								doors = mDoorService.query("where doornum=?",upDateDoorResponse.getDoornum());
								if(doors.size()==1){
									Door door = doors.get(0);
									door.setPhone(upDateDoorResponse.getPhone());
									door.setEncoderpulses(upDateDoorResponse.getEncoderpulses());
									door.setUpperpulse(upDateDoorResponse.getUpperpulse());
									door.setLowerpulse(upDateDoorResponse.getLowerpulse());
									door.setDoorstatus(upDateDoorResponse.getOperatingstatus());
									mDoorService.update(door);
								}
                                break;
                            case AppInfo.D_TAG:

                                break;
                            case AppInfo.F_TAG:

                                break;
                            default:
                                break;
                        }
	                    Intent intentBroadcast = new Intent();
	                    intentBroadcast.putExtra("message", messageContent.toString());
	                    intentBroadcast.setAction(AppInfo.SMS_RECEIVED);
	                    context.sendBroadcast(intentBroadcast);
						MyLog.d(TAG, "send broadcast and abort");
//	                    abortBroadcast();
	                }
	            }
	        }
	    }



}
