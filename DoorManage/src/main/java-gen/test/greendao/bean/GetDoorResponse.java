package test.greendao.bean;

/**
 * Created by shayajie on 2016/6/20.
 */
public class GetDoorResponse extends BaseBean{
    private String operating;
    private String encoderpulses;
    private String upperpulse;
    private String lowerpulse;



    public GetDoorResponse(String protocol_object, String isoperating, String operating, String encoderpulses, String upperpulse, String lowerpulse, String phone) {
        this.protocol_object = protocol_object;
        this.isoperating = isoperating;
        this.operating = operating;
        this.encoderpulses = encoderpulses;
        this.upperpulse = upperpulse;
        this.lowerpulse = lowerpulse;
        this.phone = phone;
    }


    public GetDoorResponse(String protocol_object, String doornum, String isoperating, String operating, String encoderpulses, String upperpulse, String lowerpulse, String phone) {
        this.protocol_object = protocol_object;
        this.doornum = doornum;
        this.isoperating = isoperating;
        this.operating = operating;
        this.encoderpulses = encoderpulses;
        this.upperpulse = upperpulse;
        this.lowerpulse = lowerpulse;
        this.phone = phone;
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

    @Override
    public String getMessage() {
        return null;
    }
}
