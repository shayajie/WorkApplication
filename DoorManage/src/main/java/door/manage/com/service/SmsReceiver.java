package door.manage.com.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import door.manage.com.app.AppInfo;
import door.manage.com.utils.MyLog;

public class SmsReceiver extends BroadcastReceiver {
	 	private static final String TAG = "SmsReceiver";
	    private String sender = "";

		public void setSender(String sender) {
			this.sender = sender;
		}

	@Override
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        MyLog.d(TAG,"action: "+action);
	        if (AppInfo.SMS_RECEIVED_ACTION.equals(action)||AppInfo.SMS_DELIVER_ACTION.equals(action)) {
	            Bundle bundle = intent.getExtras();
	            StringBuffer messageContent = new StringBuffer();
	            if (bundle != null) {
	                Object[] pdus = (Object[]) bundle.get("pdus");
	                for (Object pdu : pdus) {
	                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
	                    String sender = message.getOriginatingAddress();
	                    MyLog.d(TAG,"sender: "+sender);
	                    if (this.sender.equals(sender)) {
	                        messageContent.append(message.getMessageBody());
	                    }
	                }
	                if(!messageContent.toString().isEmpty()) {
	                    MyLog.d(TAG,"send message broadcast.");
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
