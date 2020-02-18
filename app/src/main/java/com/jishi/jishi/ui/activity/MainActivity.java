package com.jishi.jishi.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.fragment.MainFragment;
import com.jishi.jishi.ui.fragment.MeFragment;
import com.jishi.jishi.ui.fragment.MessageFragment;

/**
 * @author WM
 * @description
 * @date 2020/2/14 21:45
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mMenuMain;
    protected TextView mMenuMessage;
    protected TextView mMenuMe;
    protected MainFragment mMainFragment = new MainFragment();
    protected MessageFragment mMessageFragment = new MessageFragment();
    protected MeFragment mMeFragment = new MeFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        //获取管理类
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_content, mMainFragment)
                .hide(mMainFragment)
                .add(R.id.container_content, mMessageFragment)
                .hide(mMessageFragment)
                .add(R.id.container_content, mMeFragment)
                .hide(mMeFragment)
                .commit();

        mMenuMain.performClick();
    }

    private void initView() {
        mMenuMain = this.findViewById(R.id.menu_main);
        mMenuMessage = this.findViewById(R.id.menu_message);
        mMenuMe = this.findViewById(R.id.menu_me);

        mMenuMain.setOnClickListener(this);
        mMenuMessage.setOnClickListener(this);
        mMenuMe.setOnClickListener(this);
    }

    private void resetSelect() {
        mMenuMain.setSelected(false);
        mMenuMessage.setSelected(false);
        mMenuMe.setSelected(false);
    }

    @Override
    public void onClick(View view) {
        resetSelect();
        switch (view.getId()) {
            case R.id.menu_main:
                mMenuMain.setSelected(true);

                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMainFragment)
                        .hide(mMessageFragment)
                        .hide(mMeFragment)
                        .commit();
                break;
            case R.id.menu_message:
                mMenuMessage.setSelected(true);
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .show(mMessageFragment)
                        .hide(mMeFragment)
                        .commit();
                break;
            case R.id.menu_me:
                mMenuMe.setSelected(true);
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .hide(mMessageFragment)
                        .show(mMeFragment)
                        .commit();
                break;
            default:
                break;
        }
    }

}
