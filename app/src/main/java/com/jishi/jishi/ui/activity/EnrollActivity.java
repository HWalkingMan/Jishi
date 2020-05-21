package com.jishi.jishi.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jishi.jishi.R;
import com.jishi.jishi.business.LoginBiz;
import com.jishi.jishi.business.impl.LoginBizImpl;
import com.jishi.jishi.entity.account.Signon;
import com.jishi.jishi.entity.response.CommonReturnType;

public class EnrollActivity extends AppCompatActivity {

    private EditText edt_phone, edt_password, edt_password_r;
    private TextView txv_enroll_success_tip, txv_accountIdShow;
    private Button btn_enroll, btn_tologin;
    private ImageView img_enrollsuccess;

    private LoginBiz loginBiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        initBiz();
        initView();
        initListener();
    }

    private void initBiz() {
        loginBiz = new LoginBizImpl();
    }

    private void initView() {
        btn_enroll = findViewById(R.id.btn_enroll);
        edt_phone = findViewById(R.id.edt_phone);
        edt_password = findViewById(R.id.edt_spassword);
        edt_password_r = findViewById(R.id.edt_spassword_r);

        img_enrollsuccess = findViewById(R.id.imv_enrollsuccess);
        btn_tologin = findViewById(R.id.btn_tologin);
        txv_accountIdShow = findViewById(R.id.txv_accountIdShow);
        txv_enroll_success_tip = findViewById(R.id.txv_enroll_success_tip);

    }

    private void initListener() {
        // FIXME: 2020/5/13 缺少数据校验
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = edt_phone.getText().toString();
                String password = edt_password.getText().toString();
                final Signon signon = new Signon();
                signon.setPhoneNum(phoneNum);
                signon.setPassword(password);
                loginBiz.register(EnrollActivity.this, signon, new LoginBiz.OnRegisterSuccessListener() {
                    @Override
                    public void onSuccess(CommonReturnType<Signon> returnType) {
                        if (returnType.getErrorCode() == 0) {
                            fadeOut(btn_enroll);
                            fadeOut(edt_password);
                            fadeOut(edt_password_r);
                            fadeOut(edt_phone);
                            txv_accountIdShow.setText(returnType.getData().getAccountId().toString());
                            fadeIn(img_enrollsuccess);
                            fadeIn(txv_accountIdShow);
                            fadeIn(txv_enroll_success_tip);
                            fadeIn(btn_tologin);
                            btn_tologin.setEnabled(true);
                        } else {
                            Toast.makeText(EnrollActivity.this, "注册失败，因为" + returnType.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        Toast.makeText(EnrollActivity.this, "注册失败，因为" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollActivity.this, LoginActivity.class);
                intent.putExtra("accountId", txv_accountIdShow.getText());
                EnrollActivity.this.startActivity(intent);
            }
        });
    }

    public static void fadeOut(View view) {
        if (view.getVisibility() != View.VISIBLE) return;

        // Since the button is still clickable before fade-out animation
        // ends, we disable the button first to block click.
        view.setEnabled(false);
        Animation animation = new AlphaAnimation(1F, 0F);
        animation.setDuration(800);
        view.startAnimation(animation);
        view.setVisibility(View.GONE);
    }

    public static void fadeIn(View view) {
        if (view.getVisibility() == View.VISIBLE) return;

        view.setVisibility(View.VISIBLE);
        Animation animation = new AlphaAnimation(0F, 1F);
        animation.setDuration(800);
        view.startAnimation(animation);
    }
}
