package door.manage.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import door.manage.com.R;
import door.manage.com.utils.MyLog;

/**
 * Created by shayajie on 2016/7/27.
 */
public class FileControlActivity extends AppCompatActivity{
    private static final String TAG = "FileControlActivity";
    private  Button button_out;
    private  Button button_in;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_control_layout);
        //setting.db为apk里的一个数据库文件
        button_out = (Button) findViewById(R.id.btn_file_out);
        button_in = (Button) findViewById(R.id.btn_file_in);
        button_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
//                File File = new File(Environment.getDataDirectory() + "/data/door.manage.com/databases/doormanage.db");
//
//                    try {
//
//                        if (File.exists()) { //文件存在时
//                            int bytesum = 0;
//                            int byteread = 0;
//                            InputStream inStream = new FileInputStream(Environment.getDataDirectory() + "/data/door.manage.com/databases/doormanage.db");
//                        FileOutputStream fs = new FileOutputStream(Environment.getExternalStorageDirectory()+"/doormanage.db");
//                        byte[] buffer = new byte[1444];
//                        int length;
//                        while ( (byteread = inStream.read(buffer)) != -1) {
//                            bytesum += byteread; //字节数 文件大小
//                            System.out.println(bytesum);
//                            fs.write(buffer, 0, byteread);
//                        }
//                        inStream.close();
//                    }
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
                File dbFile = new File(Environment.getExternalStorageDirectory()+"/doormanage.db");
                //邮件接收者（数组， 可以是多位接收者）
                String[] emailReciver = new String[]{"1045070573@qq.com"};

                String  emailTitle = "项目测试";
                String emailContent = "第1次测试";
                //设置邮件地址
                email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
                //设置邮件标题
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailTitle);
                //设置发送的内容
                email.putExtra(android.content.Intent.EXTRA_TEXT, emailContent);
                //附件
                email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(dbFile));
                //邮件发送类型：带附件的邮件
                email.setType("application/octet-stream");
                //调用系统的邮件系统
                startActivity(Intent.createChooser(email, "请选择邮件发送软件"));
            }
        });
        button_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        File dbFile = new File(Environment.getDataDirectory() + "/data/door.manage.com/databases/doormanage.db");

        if(dbFile.exists()){
            MyLog.d(TAG,"数据库文件存在");



        }else {
            MyLog.d(TAG,"数据库文件不存在");
        }



    }



}
