package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/6/28.
 * 修改门信息的请求
 */
public class UpDateDoorRequest extends BaseRequest {
    private String encoderpulses;
    private String upperpulse;
    private String lowerpulse;

    public UpDateDoorRequest( String phone,String encoderpulses, String upperpulse, String lowerpulse) {
        super(AppInfo.C_TAG, AppInfo.WRITE_TAG, phone, "");
        this.encoderpulses = encoderpulses;
        this.upperpulse = upperpulse;
        this.lowerpulse = lowerpulse;
    }
    //    public UpDateDoorRequest(String doornum, String operating, String encoderpulses, String upperpulse, String lowerpulse, String password, String phone) {
//        this.protocol_object = AppInfo.C_TAG;
//        this.doornum = doornum;
//        this.encoderpulses = encoderpulses;
//        this.upperpulse = upperpulse;
//        this.lowerpulse = lowerpulse;
//        this.password = password;
//        this.phone = phone;
//    }
//


    public String getEncoderpulses() {
        return encoderpulses;
    }

    public void setEncoderpulses(String encoderpulses) {
        this.encoderpulses = encoderpulses;
    }

    public String getUpperpulse() {
        return upperpulse;
    }

    public void setUpperpulse(String upperpulse) {
        this.upperpulse = upperpulse;
    }

    public String getLowerpulse() {
        return lowerpulse;
    }

    public void setLowerpulse(String lowerpulse) {
        this.lowerpulse = lowerpulse;
    }



    @Override
    public String getMessage() {
        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
//        stringBuilder.append(upDateDoorRequest.getDoornum());
//        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getIsoperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getEncoderpulses());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getUpperpulse());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getLowerpulse());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(MyUser.getInstance().getUserPhone());
//        stringBuilder.append(getPhone());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(AppInfo.END_TAG);
        return stringBuilder.toString();
    }

}
