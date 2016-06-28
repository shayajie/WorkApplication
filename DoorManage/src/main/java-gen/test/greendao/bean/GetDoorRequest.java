package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/6/20.
 * 获取门信息的请求
 */
public class GetDoorRequest{
    private String protocol_object;
    private String doornum;
    private String operating;
    private String phone;

    public GetDoorRequest( String doornum, String operating, String phone) {
        this.protocol_object = AppInfo.A_TAG;
        this.doornum = doornum;
        this.operating = operating;
        this.phone = phone;
    }

    public String getProtocol_object() {
        return protocol_object;
    }

    public void setProtocol_object(String protocol_object) {
        this.protocol_object = protocol_object;
    }

    public String getDoornum() {
        return doornum;
    }

    public void setDoornum(String doornum) {
        this.doornum = doornum;
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
