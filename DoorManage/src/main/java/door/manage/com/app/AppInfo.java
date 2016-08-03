package door.manage.com.app;

import door.manage.com.utils.MyLog;

/**
 * Created by Administrator on 2016/6/14.
 */
public class AppInfo {
    public static final String  DB_NAME = "doormanage";


    public static final String SMS_SEND_ACTIOIN = "SMS_SEND";
    public static final String SMS_DELIVERED_ACTION = "SMS_DELIVERED";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SMS_DELIVER_ACTION="android.provider.Telephony.SMS_DELIVER";
    public static final String SMS_RECEIVED = "sms_received";
    /**
     * LAST_TAG:已‘z’结尾
     */
    public static final String LAST_TAG = "z";

    /**
     * 已“Z”收尾
     */
    public static final String END_TAG = "ZZ";
    //门读取请求
    public static final String READ_TAG = "0";
    //门写入请求
    public static final String WRITE_TAG = "1";

    //们操作状态0关闭1开启2上升3下降4停止
    public static final String OPERATING_CLOSE = "0";
    public static final String OPERATING_OPEN = "1";
    public static final String OPERATING_UP = "2";
    public static final String OPERATING_DOWN = "3";
    public static final String OPERATING_STOP = "4";
    //获取门状态标记请求返回标记
    public static final String A_TAG = "A";
    //控制门
    public static final String B_TAG = "B";
    //修改门
    public static final String C_TAG = "C";
    public static final String D_TAG = "D";
    public static final String F_TAG = "F";

    public static boolean isSendMessage = false;

    public static void setIsSendMessage(boolean sendMessage){

        MyLog.d("AppInfo","setIsSendMessage ");

        isSendMessage = sendMessage;
    }

}
