package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/6/22.
 * 控制门的请求
 */
public class ControlDoorRequest {

    private String protocol_object;     //代表操作类型如A、B
    private String doornum;             //代表门编号
    private String operatingstatus;     //代表门的操作具体内容0开启门1关闭门2上升3下降4停止操纵门时请先开启或者门自己判断
    private String operating;           //代表门的操作1。是操作0是不操作
    private String phone;               //门号码接收短信

    public ControlDoorRequest(String doornum, String operatingstatus, String operating, String phone) {
        this.protocol_object = AppInfo.B_TAG;
        this.doornum = doornum;
        this.operatingstatus = operatingstatus;
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

    public String getOperatingstatus() {
        return operatingstatus;
    }

    public void setOperatingstatus(String operatingstatus) {
        this.operatingstatus = operatingstatus;
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
