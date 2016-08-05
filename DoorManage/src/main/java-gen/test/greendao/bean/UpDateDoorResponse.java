package test.greendao.bean;

/**
 * Created by shayajie on 2016/6/28.
 */
public class UpDateDoorResponse extends BaseResponse{
    public UpDateDoorResponse(String[] strings){
        super(strings[0],"",strings[1],strings[2],strings[3],strings[4],strings[5],strings[6]);
    }

    @Override
    public String toString() {
        return "UpDateDoorResponse{"+
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
