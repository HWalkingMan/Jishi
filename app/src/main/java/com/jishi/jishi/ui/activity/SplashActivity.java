package com.jishi.jishi.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jishi.jishi.R;

import java.lang.ref.WeakReference;

/**
 * @description
 * @author WM
 * @date 2020/2/15 20:45
 */
public class SplashActivity extends AppCompatActivity {
    public static final int COUNTDOWN_TIME_CODE = 10001;
    public static final int JUMP_OUT_CODE = 10002;
    public static final int DELAY_MILLIS = 1000;
    public static final int COUNTDOWN_TIME = 3;

    private boolean clickFlag = false;

    protected CountdownHandler mHandler = new CountdownHandler(this);
    private TextView txv_countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txv_countdown = findViewById(R.id.txv_countdown);

        Message message = Message.obtain();
        message.what = COUNTDOWN_TIME_CODE;
        message.arg1 = COUNTDOWN_TIME;

        mHandler.sendMessageDelayed(message, DELAY_MILLIS);

        txv_countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFlag = true;
                Message jmpMsg = Message.obtain();
                jmpMsg.what = JUMP_OUT_CODE;
                mHandler.sendMessage(jmpMsg);

            }
        });
    }

    public static class CountdownHandler extends Handler {

        final WeakReference<SplashActivity> mWeakReference;

        private CountdownHandler(SplashActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mWeakReference.get();
            int value = msg.arg1;
            switch (msg.what) {
                case COUNTDOWN_TIME_CODE:
                    activity.txv_countdown.setText(activity.getString(R.string.skip) + " " + (value--));
                    if (value >= 0) {
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_CODE;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    } else if (!activity.clickFlag) {
                        Message message = Message.obtain();
                        message.what = JUMP_OUT_CODE;
                        sendMessage(message);
                    }
                    break;
                case JUMP_OUT_CODE:
                    activity.startActivity(new Intent(activity, CoverActivity.class));
                    activity.finish();
                    break;

                default:
            }
        }
    }
}
