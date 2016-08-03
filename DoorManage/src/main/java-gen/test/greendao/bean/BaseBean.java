package test.greendao.bean;

/**
 * Created by shayajie on 2016/8/3.
 */
public abstract class BaseBean {
    protected String protocol_object;//代表操作类型如A、B
    protected String isoperating;//代表门的操作1。是操作0是不操作
    protected String doornum;//代表门编号
    protected String phone;//门号码接收短信

    public BaseBean() {
    }

    public BaseBean(String protocol_object, String isoperating, String phone, String doornum) {
        this.protocol_object = protocol_object;
        this.isoperating = isoperating;
        this.phone = phone;
        if (!doornum.isEmpty()){
            this.doornum = doornum;
        }
    }


    public String getProtocol_object() {
        return protocol_object;
    }

    public void setProtocol_object(String protocol_object) {
        this.protocol_object = protocol_object;
    }

    public String getIsoperating() {
        return isoperating;
    }

    public void setIsoperating(String isoperating) {
        this.isoperating = isoperating;
    }

    public String getDoornum() {
        return doornum;
    }

    public void setDoornum(String doornum) {
        this.doornum = doornum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public abstract String getMessage();
}
