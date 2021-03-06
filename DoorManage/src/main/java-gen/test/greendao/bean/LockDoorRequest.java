package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/7/1.
 */
public class LockDoorRequest extends BaseRequest{
    private String remotelock;

    public LockDoorRequest(String phone, String remotelock) {
        super(AppInfo.D_TAG, AppInfo.WRITE_TAG, phone, "");
        this.remotelock = remotelock;
    }

    public String getRemotelock() {
        return remotelock;
    }

    public void setRemotelock(String remotelock) {
        this.remotelock = remotelock;
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getIsoperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getRemotelock());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(MyUser.getInstance().getUserPhone());
//        stringBuilder.append(getPhone());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(AppInfo.END_TAG);
        return stringBuilder.toString();
    }
}
