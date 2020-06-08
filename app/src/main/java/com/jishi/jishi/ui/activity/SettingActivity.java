package com.jishi.jishi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jishi.jishi.R;

public class SettingActivity extends AppCompatActivity {

    private final static String MY_PRE_NAME = "preferences";
    private final static String TOKEN = "TOKEN";
    private final static String ACCOUNTID = "ACCOUNTID";

    private Toolbar toolbar;
    private CardView cv_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initListener();
    }

    private void initView() {
        cv_exit = findViewById(R.id.cdv_exit);
        toolbar = findViewById(R.id.toolbar_setting);
    }

    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });

        cv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = SettingActivity.this.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(TOKEN);
                editor.remove(ACCOUNTID);
                editor.apply();
                Toast.makeText(SettingActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
                SettingActivity.this.startActivity(new Intent(SettingActivity.this, CoverActivity.class));
                SettingActivity.this.finish();
            }
        });


    }


}
