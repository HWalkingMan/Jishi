package com.jishi.jishi.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.adapter.MainFragmentAdapter;
import com.jishi.jishi.ui.fragment.MainFragment;
import com.jishi.jishi.ui.fragment.MeFragment;
import com.jishi.jishi.ui.fragment.MessageFragment;

/**
 * @author WM
 * @description
 * @date 2020/2/14 21:45
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static final long MAX_EXIT_TIME = 2000;
    public static final int STATE_SCROLL_DONE = 2;
    protected LinearLayout mMenuMain;
    protected LinearLayout mMenuMessage;
    protected LinearLayout mMenuMe;
    private ViewPager viewPager;

    private MainFragmentAdapter mAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MainFragmentAdapter(getSupportFragmentManager());

        initView();

        mMenuMain.performClick();
    }

    private void initView() {
        mMenuMain = this.findViewById(R.id.ly_menu_index);
        mMenuMessage = this.findViewById(R.id.ly_menu_message);
        mMenuMe = this.findViewById(R.id.ly_menu_me);
        viewPager = this.findViewById(R.id.container_content);

        mMenuMain.setOnClickListener(this);
        mMenuMessage.setOnClickListener(this);
        mMenuMe.setOnClickListener(this);

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(PAGE_ONE);
        viewPager.addOnPageChangeListener(this);
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
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.ly_menu_message:
                mMenuMessage.setSelected(true);
                viewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.ly_menu_me:
                mMenuMe.setSelected(true);
                viewPager.setCurrentItem(PAGE_THREE);
                break;
            default:
                break;
        }
    }


    private long exitTime = 0;

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

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == STATE_SCROLL_DONE) {
            resetSelect();
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    mMenuMain.setSelected(true);
                    break;
                case PAGE_TWO:
                    mMenuMessage.setSelected(true);
                    break;
                case PAGE_THREE:
                    mMenuMe.setSelected(true);
                    break;
                default:
                    break;
            }
        }
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