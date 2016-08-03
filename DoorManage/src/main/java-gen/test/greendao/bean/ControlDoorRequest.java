package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/6/22.
 * 控制门的请求
 */
public class ControlDoorRequest extends BaseBean{

    private String operating;           //代表门的操作具体内容0开启门1关闭门2上升3下降4停止操纵门时请先开启或者门自己判断


    public ControlDoorRequest(String operating,String phone) {
        super(AppInfo.B_TAG, AppInfo.WRITE_TAG, phone, "");
        this.operating = operating;
    }

//    public ControlDoorRequest(String doornum, String operatingstatus, String operating, String phone) {
//        this.protocol_object = AppInfo.B_TAG;
//        this.doornum = doornum;
//        this.isoperating = operatingstatus;
//        this.operating = operating;
//        this.phone = phone;
//    }


    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }


    @Override
    public String getMessage() {
        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getIsoperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getOperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getPhone());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(AppInfo.END_TAG);
        return stringBuilder.toString();
    }
}
