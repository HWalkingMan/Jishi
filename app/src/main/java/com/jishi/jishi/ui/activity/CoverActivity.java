package com.jishi.jishi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jishi.jishi.R;

public class CoverActivity extends AppCompatActivity {
    private final static String MY_PRE_NAME = "preferences";
    private final static String TOKEN = "TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        // TODO: 2020/5/9 在此处添加对token的判断
        SharedPreferences sharedPreferences = this.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(TOKEN, "");
        if (!token.isEmpty()) {
            this.startActivity(new Intent(CoverActivity.this, MainActivity.class));
            this.finish();
        }
        Button btn_signin = findViewById(R.id.btn_tosignin);
        Button btn_enroll = findViewById(R.id.btn_toenroll);

        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoverActivity.this.startActivity(new Intent(CoverActivity.this, EnrollActivity.class));
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoverActivity.this.startActivity(new Intent(CoverActivity.this, LoginActivity.class));
            }
        });
    }
}
