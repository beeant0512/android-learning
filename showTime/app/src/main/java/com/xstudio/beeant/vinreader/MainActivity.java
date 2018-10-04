package com.xstudio.beeant.vinreader;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView helloWorldTextView;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
            Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorldTextView = findViewById(R.id.helloWorld);
        ShowTimeHandler showTimeHandler = new ShowTimeHandler();
        Message msg = new Message();
        msg.what = 0;
        showTimeHandler.handleMessage(msg);
    }

    class ShowTimeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    // 移除所有的msg.what为0等消息，保证只有一个循环消息队列再跑
                    this.removeMessages(0);
                    // app的功能逻辑处理
                    helloWorldTextView.setText(simpleDateFormat.format(new Date()));
                    // 再次发出msg，循环更新
                    this.sendEmptyMessageDelayed(0, 1000);
                    break;
                case 1:
                    // 直接移除，定时器停止
                    this.removeMessages(0);
                    break;
                default:
                    break;
            }
        }
    }
}
