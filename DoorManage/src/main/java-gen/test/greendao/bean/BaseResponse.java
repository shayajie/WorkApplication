package test.greendao.bean;

/**
 * Created by shayajie on 2016/8/5.
 */
public class BaseResponse {
    protected String protocol_object;
    protected String doornum;
    protected String isoperating;
    protected String operating;
    protected String encoderpulses;
    protected String upperpulse;
    protected String lowerpulse;
    protected String phone;

    public BaseResponse() {
    }

    public BaseResponse(String protocol_object, String doornum, String isoperating, String operating, String encoderpulses, String upperpulse, String lowerpulse, String phone) {
        this.protocol_object = protocol_object;
        this.doornum = doornum;
        this.isoperating = isoperating;
        this.operating = operating;
        this.encoderpulses = encoderpulses;
        this.upperpulse = upperpulse;
        this.lowerpulse = lowerpulse;
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

    public String getIsoperating() {
        return isoperating;
    }

    public void setIsoperating(String isoperating) {
        this.isoperating = isoperating;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "protocol_object='" + protocol_object + '\'' +
                ", doornum='" + doornum + '\'' +
                ", isoperating='" + isoperating + '\'' +
                ", operating='" + operating + '\'' +
                ", encoderpulses='" + encoderpulses + '\'' +
                ", upperpulse='" + upperpulse + '\'' +
                ", lowerpulse='" + lowerpulse + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
