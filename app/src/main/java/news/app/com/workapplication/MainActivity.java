package news.app.com.workapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String Tag = "MainActivity=============";
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(Tag+"onCreate");
        button = (Button) findViewById(R.id.button_to_second);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(Tag+"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(Tag+"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(Tag+"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(Tag+"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(Tag+"onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(Tag+"onDestroy");
    }
}
