package test.greendao.bean;

import door.manage.com.app.AppInfo;

/**
 * Created by shayajie on 2016/6/20.
 * 获取门信息的请求
 */
public class GetDoorRequest extends BaseRequest {

    public GetDoorRequest(String phone) {
        super(AppInfo.A_TAG,AppInfo.READ_TAG,phone,"");
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder=  new StringBuilder();
        stringBuilder.append(getProtocol_object());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(getIsoperating());
        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(MyUser.getInstance().getUserPhone());
//        stringBuilder.append(getPhone());

        stringBuilder.append(AppInfo.LAST_TAG);
        stringBuilder.append(AppInfo.END_TAG);
        return stringBuilder.toString();
    }

}
