package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/7/1.
 */
public class LockDoorRequest {
    private String protocol_object;     //代表操作类型如A、B、C
    private String doornum;             //代表门编号
    private String operating;
    private String remotelock;
    private String phone;

    public LockDoorRequest(String doornum, String operating, String phone) {
        this.protocol_object = AppInfo.D_TAG;
        this.doornum = doornum;
        this.operating = operating;
        this.phone = phone;
    }

    public LockDoorRequest(String doornum, String operating, String remotelock, String phone) {
        this.protocol_object = AppInfo.D_TAG;
        this.doornum = doornum;
        this.operating = operating;
        this.remotelock = remotelock;
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

    public String getRemotelock() {
        return remotelock;
    }

    public void setRemotelock(String remotelock) {
        this.remotelock = remotelock;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
