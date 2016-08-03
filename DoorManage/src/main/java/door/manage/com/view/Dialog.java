package door.manage.com.view;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by shayajie on 2016/7/26.
 */
public class Dialog {
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private AlertDialog.Builder builder;
    private Button button;
    public AlertDialog alertDialog;
    public  Dialog(Context context){
        this.context = context;

        this.builder = new AlertDialog.Builder(context);
    }

    public  Dialog(Context context,int theme) {
        this.context = context;

        this.builder = new AlertDialog.Builder(context,theme);
    }



    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        builder.setTitle(title);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(int title){
        //设置标题
        builder.setTitle(title);
        //设置信息
//        builder.setMessage(title);
//        builder.setMessage("s");
//        //no
//        builder.setNegativeButton();
//        //yes
//        builder.setPositiveButton();
//        //中立
//        builder.setNeutralButton();
//        builder.setAdapter();

//        builder.setCancelable();
//        builder.setCursor();
        //titleview
//        builder.setCustomTitle();
        //dialog icon
//        builder.setIcon();
        //Set an icon as supplied by a theme attribute. e.g.
//        builder.setIconAttribute();
//        builder.setItems();
//        builder.setInverseBackgroundForced();
//        builder.setMultiChoiceItems();
        //自定义view
//        builder.setView();
//        builder.setSingleChoiceItems();
//        builder.setRecycleOnMeasureEnabled();
        //监听返回键
//        builder.setOnKeyListener();
//        builder.setOnItemSelectedListener();
//        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//
//            }
//        });
//        builder.setOnCancelListener();

    }

    /**
     * 返回/取消
     * @param buttonname 按钮名称
     * @param listener 按钮点击监听器
     */

    public void setNegativeButton(String buttonname,DialogInterface.OnClickListener listener){
        builder.setNegativeButton(buttonname, listener);
    }

    /**
     * 确定
     * @param buttonname 按钮名称
     * @param listener 按钮点击监听器
     */
    public void setPositiveButton(String buttonname,DialogInterface.OnClickListener listener){
        builder.setPositiveButton(buttonname,listener);
    }

    /**
     * 中立按钮
     * @param buttonname
     * @param listener
     */
    public void setNeutralButton(String buttonname,DialogInterface.OnClickListener listener){
        builder.setNeutralButton(buttonname,listener);
    }

    public void setMessage(String message){
        builder.setMessage(message);
    }

    public void setView(int resId){
        this.inflater = LayoutInflater.from(context);
        this.view = inflater.inflate(resId,null,false);
        builder.setView(view);
    }
    public View getView(){
        return view;
    }
    public void create(){
       builder.create();
    }
    public void show(){
        alertDialog =  builder.show();

    }
    public void setCancelable(boolean cancelable){
        builder.setCancelable(cancelable);
    }
    public void setCanceledOnTouchOutside(boolean cancelable){
        alertDialog.setCanceledOnTouchOutside(cancelable);
    }


    public Button getPositiveButton(){
        return alertDialog.getButton(-1);
    }

}
