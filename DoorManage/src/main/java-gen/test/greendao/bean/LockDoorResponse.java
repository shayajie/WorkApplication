package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/7/1.
 */
public class LockDoorResponse extends BaseResponse{
    private String remotelock;

    public LockDoorResponse(String[] strings){
        super(strings[0],"",strings[1],"","","","",strings[3]);
        this.remotelock = strings[2];
    }

    public String getRemotelock() {
        return remotelock;
    }

    public void setRemotelock(String remotelock) {
        this.remotelock = remotelock;
    }

    @Override
    public String toString() {
        return "LockDoorResponse{" +
                "protocol_object='" + protocol_object + '\''+
                "isoperating='" + isoperating + '\''+
                "remotelock='" + remotelock + '\'' +
                "phone='" + phone + '\''+
                '}';
    }
}
