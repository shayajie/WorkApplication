package door.manage.com.utils;

import door.manage.com.app.AppInfo;
import test.greendao.bean.ControlDoorRequest;
import test.greendao.bean.ControlDoorResponse;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorRequest;
import test.greendao.bean.GetDoorResponse;
import test.greendao.bean.LockDoorRequest;
import test.greendao.bean.LockDoorResponse;
import test.greendao.bean.UpDateDoorRequest;
import test.greendao.bean.UpDateDoorResponse;

/**
 * Created by shayajie on 2016/6/20.
 */
public class StringUtils {
    private static final String TAG = "StringUtils";
    public static String getDoorMessage(GetDoorRequest getDoorRequest){

        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(getDoorRequest.getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getDoorRequest.getDoornum());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getDoorRequest.getOperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getDoorRequest.getPhone());

        return stringBuilder.toString();
    }

    public static String controlDoorRequest(ControlDoorRequest controlDoorRequest){

        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(controlDoorRequest.getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(controlDoorRequest.getDoornum());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(controlDoorRequest.getOperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(controlDoorRequest.getOperatingstatus());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(controlDoorRequest.getPhone());

        return stringBuilder.toString();
    }

    public static String upDateDoorRequest(UpDateDoorRequest upDateDoorRequest){

        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(upDateDoorRequest.getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getDoornum());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getOperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getEncoderpulses());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getUpperpulse());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getLowerpulse());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getPassword());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(upDateDoorRequest.getPhone());

        return stringBuilder.toString();
    }



    public static String lockDoorRequest(LockDoorRequest lockDoorRequest){
        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(lockDoorRequest.getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(lockDoorRequest.getDoornum());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(lockDoorRequest.getOperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(lockDoorRequest.getRemotelock());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(lockDoorRequest.getPhone());

        return stringBuilder.toString();
    }


    public static GetDoorResponse getDoorResponse(String[] strings){
        if(strings.length>1){
            MyLog.d(TAG,""+strings.length);
            GetDoorResponse getDoorResponse = new GetDoorResponse(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
            return getDoorResponse;
        }
        return null;
    }

    public static ControlDoorResponse controlDoorResponse(String[] strings){
        if(strings.length>1){
            MyLog.d(TAG,""+strings.length);
            ControlDoorResponse controlDoorResponse = new ControlDoorResponse(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
            return controlDoorResponse;
        }
        return null;
    }

    public static UpDateDoorResponse upDateDoorResponse(String[] strings) {
        if(strings.length>1){
            MyLog.d(TAG,""+strings.length);
            UpDateDoorResponse upDateDoorResponse = new UpDateDoorResponse(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
            return upDateDoorResponse;
        }
        return null;
    }

    public static LockDoorResponse lockDoorResponse(String[] strings){
        if(strings.length>1){
            MyLog.d(TAG,""+strings.length);
            LockDoorResponse lockDoorResponse = new LockDoorResponse(strings[0],strings[1],strings[2],strings[3],strings[4]);
            return lockDoorResponse;
        }
        return null;
    }
}
