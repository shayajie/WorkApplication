package door.manage.com.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import door.manage.com.app.AppInfo;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import door.manage.com.utils.StringUtils;
import test.greendao.bean.ControlDoorResponse;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorResponse;
import test.greendao.bean.UpDateDoorResponse;

public class SSSmsReceiver extends BroadcastReceiver {
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
//						SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu,);
	                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
	                    String sender = message.getOriginatingAddress();
						String senderfinal = sender.substring(sender.length()-11);
	                    MyLog.d(TAG,"sender: "+sender);
						MyLog.d(TAG,"senderfinal: "+senderfinal);
						for (Door door :doors){
							if (door.getPhone().equals(senderfinal)) {
								MyLog.d(TAG,"messgeappend===="+replaceBlank(message.getMessageBody()));
								messageContent.append(message.getMessageBody());
							}
						}


	                }
	                if(!messageContent.toString().isEmpty()) {
	                    MyLog.d(TAG,"send message broadcast.");

						String strfinal =  replaceBlank(messageContent.toString());

						MyLog.d(TAG,"messageContent====="+strfinal);


                        String[] strings = strfinal.split(AppInfo.LAST_TAG);
						for (String s : strings){
							MyLog.d(TAG,"strings========="+s);
						}
						List<Door> doors;
//						switch (strings[0]){
//                            case AppInfo.A_TAG:
//								MyLog.d(TAG,"AppInfo.A_TAG");
//                                GetDoorResponse doorResponse = StringUtils.getDoorResponse(strings);
//								doors = mDoorService.query("where doornum=?",doorResponse.getDoornum());
//                                if(doors.size()==1){
//                                    Door door = doors.get(0);
//                                    door.setPhone(doorResponse.getPhone());
//                                    door.setEncoderpulses(doorResponse.getEncoderpulses());
//                                    door.setUpperpulse(doorResponse.getUpperpulse());
//                                    door.setLowerpulse(doorResponse.getLowerpulse());
//                                    door.setDoorstatus(doorResponse.getOperating());
//                                    mDoorService.update(door);
//                                }
//
//                                break;
//                            case AppInfo.B_TAG:
//								MyLog.d(TAG,"AppInfo.B_TAG");
//								ControlDoorResponse controlDoorResponse = StringUtils.controlDoorResponse(strings);
//								doors = mDoorService.query("where doornum=?",controlDoorResponse.getDoornum());
//								if(doors.size()==1){
//									Door door = doors.get(0);
//									door.setPhone(controlDoorResponse.getPhone());
//									door.setEncoderpulses(controlDoorResponse.getEncoderpulses());
//									door.setUpperpulse(controlDoorResponse.getUpperpulse());
//									door.setLowerpulse(controlDoorResponse.getLowerpulse());
//									door.setDoorstatus(controlDoorResponse.getOperatingstatus());
//									mDoorService.update(door);
//								}
//                                break;
//                            case AppInfo.C_TAG:
//								MyLog.d(TAG,"AppInfo.C_TAG");
//								UpDateDoorResponse upDateDoorResponse = StringUtils.upDateDoorResponse(strings);
//								doors = mDoorService.query("where doornum=?",upDateDoorResponse.getDoornum());
//								if(doors.size()==1){
//									Door door = doors.get(0);
//									door.setPhone(upDateDoorResponse.getPhone());
//									door.setEncoderpulses(upDateDoorResponse.getEncoderpulses());
//									door.setUpperpulse(upDateDoorResponse.getUpperpulse());
//									door.setLowerpulse(upDateDoorResponse.getLowerpulse());
//									door.setDoorstatus(upDateDoorResponse.getOperatingstatus());
//									mDoorService.update(door);
//								}
//                                break;
//                            case AppInfo.D_TAG:
//
//                                break;
//                            case AppInfo.F_TAG:
//
//                                break;
//                            default:
//                                break;
//                        }
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

//	/**
//	 * 字符串转换unicode
//	 */
//	public static String string2Unicode(String string) {
//		MyLog.d(TAG,"字符串转换unicode"+string);
//		StringBuffer unicode = new StringBuffer();
//
//		for (int i = 0; i < string.length(); i++) {
//
//			// 取出每一个字符
//			char c = string.charAt(i);
//
//			// 转换为unicode
//			unicode.append("\\u" + Integer.toHexString(c));
//		}
//
//		return unicode.toString();
//	}
//	public static String unicode2String(String unicode) {
//
//		StringBuffer string = new StringBuffer();
//
//		String[] hex = unicode.split("\\\\u");
//
//		for (int i = 1; i < hex.length; i++) {
//
//			// 转换出每一个代码点
//			int data = Integer.parseInt(hex[i], 16);
//
//			// 追加成string
//			string.append((char) data);
//		}
//
//		return string.toString();
//	}

	//去空格换行
		public static String replaceBlank(String str) {
			String dest = "";
			if (str!=null) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(str);
				dest = m.replaceAll("");
			}
			return dest;
		}
}
