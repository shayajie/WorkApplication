package door.manage.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import door.manage.com.R;
import door.manage.com.utils.DbUtil;
import door.manage.com.utils.MyLog;
import test.greendao.bean.User;

/**
 * Created by shayajie on 2016/7/27.
 */
public class FileControlActivity extends Activity{
    private static final String TAG = "FileControlActivity";

    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    private  Button button_out;
    private  Button button_in;
    private Context context;
    private static final String dbfilepath = Environment.getDataDirectory() + "/data/door.manage.com/databases/doormanage.db";
    private static final String dbextralfilepath = Environment.getExternalStorageDirectory()+"/doormanage.db";
    private Thread thread;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    sendDBfile();
                    break;
                case 1:
                    dialog("数据导入成功!需重启应用才能生效",true);
                    break;
                default:
                    break;
            }
        }
    };

    private EditText add_user_name_edittext,add_user_phone_edittext;
    private Button add_user_button;
    private User user;
    private TextView title_textview;
    private RelativeLayout title_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_control_layout);
        context= this;
        shared = getSharedPreferences("appinfo", 0);
        editor = shared.edit();
        boolean isFirstRun = shared.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }


        initdata();
        initview();



    }

    private void initview() {

        title_textview = (TextView) findViewById(R.id.title_textview);
//        title_textview.setText(""+users.get(0).getUserId());
        title_textview.setText("用户设置");
        title_back = (RelativeLayout) findViewById(R.id.title_back);

        title_back.setVisibility(View.VISIBLE);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        add_user_name_edittext = (EditText) findViewById(R.id.add_user_name_edittext);
        add_user_phone_edittext = (EditText) findViewById(R.id.add_user_phone_edittext);
        add_user_button = (Button) findViewById(R.id.add_user_button);
        add_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedit();
            }
        });
        //setting.db为apk里的一个数据库文件
        button_out = (Button) findViewById(R.id.btn_file_out);
        button_in = (Button) findViewById(R.id.btn_file_in);
        button_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        copyDBtoExtral();
                    }
                });
                thread.start();
//
//                File dbFile = new File(dbextralfilepath);
//
//                if(dbFile.exists()){
//                    if(dbFile.isFile()){
//                        thread = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                copyDBtoExtral();
//                            }
//                        });
//                        thread.start();
//                    }
//
//                }else {
//
//                }


            }
        });
        button_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                File dbFile = new File(dbextralfilepath);
                if(dbFile.exists()){

                    dialog("导入外部数据会删除现有数据库，是否导入？",false);


                }else {
                    Toast.makeText(context,"外部数据库文件不存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        File dbFile = new File(Environment.getDataDirectory() + "/data/door.manage.com/databases/doormanage.db");
//
//        if(dbFile.exists()){
//            MyLog.d(TAG,"数据库文件存在");
//
//
//
//        }else {
//            MyLog.d(TAG,"数据库文件不存在");
//        }

        if(user!=null){
            if(user.getName()!= null){
                add_user_name_edittext.setText(user.getName());
            }
            if(user.getPhone()!=null){
                add_user_phone_edittext.setText(user.getPhone());
            }
        }


    }

    private void initdata() {
        user = DbUtil.getUserService().query(1L);
    }

    private void checkedit() {
        if(!add_user_name_edittext.getText().toString().isEmpty()){
            if(!add_user_phone_edittext.getText().toString().isEmpty()){
                String phone = add_user_phone_edittext.getText().toString().replace(" ","");
                phone = phone.trim();
                if(phone.length()==11){
                    user.setPhone(phone);
                    user.setName(add_user_name_edittext.getText().toString());
                    MyLog.d(TAG,"user"+user.toString());
                    user.update();
                }else{
                    Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"手机号为空",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"用户昵称为空",Toast.LENGTH_SHORT).show();
        }


    }

    private void sendDBfile(){
        //邮件接收者（数组， 可以是多位接收者）
        File file = new File(Environment.getExternalStorageDirectory()+"/doormanage.db");
        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        String[] emailReciver = new String[]{"1045070573@qq.com"};

        String  emailTitle = "数据库导出";
        String emailContent = "请选择发送到电脑(QQ)";
        //设置邮件地址
        email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
        //设置邮件标题
        email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailTitle);
        //设置发送的内容
        email.putExtra(android.content.Intent.EXTRA_TEXT, emailContent);
        //附件
        email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        //邮件发送类型：带附件的邮件
        email.setType("application/octet-stream");
        //调用系统的邮件系统
        startActivity(Intent.createChooser(email, "请选择邮件发送软件"));
    }


    private void copyDBtoExtral(){
        File File = new File(dbfilepath);
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            if (File.exists()) { //文件存在时
                int bytesum = 0;
                int byteread = 0;
                inStream  = new FileInputStream(dbfilepath);
                 fs = new FileOutputStream(dbextralfilepath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (inStream != null) {
                    inStream.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }
    }

    private void overDBbyExtral(){
        File File = new File(dbextralfilepath);
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            if (File.exists()) { //文件存在时
                int bytesum = 0;
                int byteread = 0;
                inStream  = new FileInputStream(dbextralfilepath);
                fs = new FileOutputStream(dbfilepath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (inStream != null) {
                    inStream.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(1);
        }
    }
    private void dialog(String message, final boolean restartApp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(restartApp){
                    restartApp();
                }else{
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            overDBbyExtral();
                        }
                    });
                    thread.start();
                }


            }
        });
        if(!restartApp){
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        builder.create().show();
    }

    private void restartApp() {
        Intent intent = getPackageManager()
                .getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
}
