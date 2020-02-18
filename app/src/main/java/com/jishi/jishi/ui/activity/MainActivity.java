package com.jishi.jishi.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    public static final long MAX_EXIT_TIME = 2000;
    protected LinearLayout mMenuMain;
    protected LinearLayout mMenuMessage;
    protected LinearLayout mMenuMe;
    protected MainFragment mMainFragment = new MainFragment();
    protected MessageFragment mMessageFragment = new MessageFragment();
    protected MeFragment mMeFragment = new MeFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mMenuMain = this.findViewById(R.id.ly_menu_index);
        mMenuMessage = this.findViewById(R.id.ly_menu_message);
        mMenuMe = this.findViewById(R.id.ly_menu_me);

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
            case R.id.ly_menu_index:
                mMenuMain.setSelected(true);

                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMainFragment)
                        .hide(mMessageFragment)
                        .hide(mMeFragment)
                        .commit();
                break;
            case R.id.ly_menu_message:
                mMenuMessage.setSelected(true);
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .show(mMessageFragment)
                        .hide(mMeFragment)
                        .commit();
                break;
            case R.id.ly_menu_me:
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

    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > MAX_EXIT_TIME) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*小红点操作方法
    有数量
    TextView tab_menu_channel_num = getActivity ().findViewById(R.id.tab_menu_me_num);
    tab_menu_channel_num.setText("11");
    tab_menu_channel_num.setVisibility(View.VISIBLE);

    无数量
    ImageView tab_menu_setting_partner = (ImageView) getActivity ().findViewById(R.id.tab_menu_index_partner);
    tab_menu_setting_partner.setVisibility(View.VISIBLE);
     */

}
