package com.jishi.jishi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jishi.jishi.R;
import com.jishi.jishi.business.LoginBiz;
import com.jishi.jishi.business.impl.LoginBizImpl;
import com.jishi.jishi.entity.login.Signon;
import com.jishi.jishi.entity.response.CommonReturnType;

public class LoginActivity extends AppCompatActivity {

    private final static String MY_PRE_NAME = "preferences";
    private final static String TOKEN = "TOKEN";

    private EditText edt_accountId, edt_password;
    private Button btn_login, btn_forget_pass;

    private LoginBiz loginBiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initBiz();
        initView();
        initListener();
    }

    private void initBiz() {
        loginBiz = new LoginBizImpl();
    }

    private void initView() {
        edt_password = findViewById(R.id.edt_lpassword);
        btn_login = findViewById(R.id.btn_login);
        btn_forget_pass = findViewById(R.id.btn_forget_pass);
        edt_accountId = findViewById(R.id.edt_accountid);
        Intent intent = getIntent();
        CharSequence accountid = intent.getCharSequenceExtra("accountId");
        if (accountid != null && accountid.length() > 0)
            edt_accountId.setText(accountid);
    }

    private void initListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accoutnId = edt_accountId.getText().toString();
                String password = edt_password.getText().toString();
                Signon signon = new Signon();
                signon.setAccountId(Integer.valueOf(accoutnId));
                signon.setPassword(password);
                loginBiz.login(LoginActivity.this, signon, new LoginBiz.OnLoginSuccessListener() {
                    @Override
                    public void onSuccess(CommonReturnType<String> returnType) {
                        if (returnType.getErrorCode() == 0) {
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(TOKEN, returnType.getData());
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "登录失败，因为" + returnType.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "功能尚在完善，敬请期待。", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
