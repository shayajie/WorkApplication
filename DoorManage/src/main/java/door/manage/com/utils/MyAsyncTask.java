package door.manage.com.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import door.manage.com.app.AppInfo;
import door.manage.com.service.DoorService;
import test.greendao.bean.ControlDoorResponse;
import test.greendao.bean.Door;
import test.greendao.bean.GetDoorResponse;
import test.greendao.bean.UpDateDoorResponse;

/**
 * Created by shayajie on 2016/8/3.
 */
public class MyAsyncTask extends AsyncTask<String,Void,Door>{
    private static final String TAG = "MyAsyncTask";
    private DoorService mDoorService;
    private Handler handler;

    public MyAsyncTask(Handler handler) {
        this.handler = handler;
        this.mDoorService = DbUtil.getDoorService();

    }

    @Override
    protected Door doInBackground(String... params) {
        MyLog.d(TAG,"doInBackground");
        ProcessSMS(params[0]);

        return null;
    }

    @Override
    protected void onPostExecute(Door door) {
        super.onPostExecute(door);
        handler.sendEmptyMessage(0);
    }

    public void ProcessSMS(String messageContent){


        MyLog.d(TAG, "messageContent=====" + messageContent);


        String[] strings = messageContent.split(AppInfo.LAST_TAG);
        for (String s : strings) {
            MyLog.d(TAG, "strings=========" + s);
        }
        List<Door> doors;



        switch (strings[0]) {
            case AppInfo.A_TAG:
                MyLog.d(TAG, "AppInfo.A_TAG");
                GetDoorResponse doorResponse = new GetDoorResponse(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6]);
                doors = mDoorService.query("where PHONE=?", doorResponse.getPhone());
                if (doors.size() == 1) {
                    Door door = doors.get(0);
                    door.setPhone(doorResponse.getPhone());
                    door.setEncoderpulses(doorResponse.getEncoderpulses());
                    door.setUpperpulse(doorResponse.getUpperpulse());
                    door.setLowerpulse(doorResponse.getLowerpulse());
                    door.setDoorstatus(doorResponse.getOperating());
                    mDoorService.update(door);
                }

                break;
            case AppInfo.B_TAG:
                MyLog.d(TAG, "AppInfo.B_TAG");
                ControlDoorResponse controlDoorResponse = StringUtils.controlDoorResponse(strings);
                doors = mDoorService.query("where PHONE=?", controlDoorResponse.getPhone());
                if (doors.size() == 1) {
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
                MyLog.d(TAG, "AppInfo.C_TAG");
                UpDateDoorResponse upDateDoorResponse = StringUtils.upDateDoorResponse(strings);
                doors = mDoorService.query("where PHONE=?", upDateDoorResponse.getPhone());
                if (doors.size() == 1) {
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
    }

}
