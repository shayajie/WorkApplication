package test.greendao.bean;

/**
 * Created by shayajie on 2016/6/20.
 */
public class GetDoorResponse {
    private String protocol_object;
    private String doornum;
    private String operatingstatus;
    private String operating;
    private String encoderpulses;
    private String upperpulse;
    private String lowerpulse;
    private String phone;

    public GetDoorResponse(String protocol_object, String doornum, String operatingstatus, String operating, String encoderpulses, String upperpulse, String lowerpulse, String phone) {
        this.protocol_object = protocol_object;
        this.doornum = doornum;
        this.operatingstatus = operatingstatus;
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
}
