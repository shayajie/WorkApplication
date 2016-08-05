package test.greendao.bean;

/**
 * Created by shayajie on 2016/6/22.
 */
public class ControlDoorResponse extends BaseResponse {
    public ControlDoorResponse(String[] strings) {
        super(strings[0], "", strings[1], strings[2], strings[3], strings[4], strings[5], strings[6]);
    }

    @Override
    public String toString() {
        return "ControlDoorResponse{"+
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
