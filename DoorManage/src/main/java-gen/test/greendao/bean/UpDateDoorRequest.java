package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/6/28.
 * 修改门信息的请求
 */
public class UpDateDoorRequest {
    private String protocol_object;     //代表操作类型如A、B、C
    private String doornum;             //代表门编号
    private String operating;           //代表门的操作1。是操作0是不操作
    private String encoderpulses;
    private String upperpulse;
    private String lowerpulse;
    private String password;
    private String phone;

    public UpDateDoorRequest(String doornum, String operating, String encoderpulses, String upperpulse, String lowerpulse, String password, String phone) {
        this.protocol_object = AppInfo.C_TAG;
        this.doornum = doornum;
        this.operating = operating;
        this.encoderpulses = encoderpulses;
        this.upperpulse = upperpulse;
        this.lowerpulse = lowerpulse;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UpDateDoorRequest{" +
                "protocol_object='" + protocol_object + '\'' +
                ", doornum='" + doornum + '\'' +
                ", operating='" + operating + '\'' +
                ", encoderpulses='" + encoderpulses + '\'' +
                ", upperpulse='" + upperpulse + '\'' +
                ", lowerpulse='" + lowerpulse + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
