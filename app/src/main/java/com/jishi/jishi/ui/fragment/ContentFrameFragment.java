package com.jishi.jishi.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.adapter.MainFragmentAdapter;

public class ContentFrameFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static final int STATE_SCROLL_DONE = 2;
    protected LinearLayout mMenuMain;
    protected LinearLayout mMenuMessage;
    protected LinearLayout mMenuMe;
    private ViewPager viewPager;

    private MainFragmentAdapter mAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    public static ContentFrameFragment newInstance() {
        return new ContentFrameFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.content_frame, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new MainFragmentAdapter(getChildFragmentManager());
        initView();
        mMenuMain.performClick();
    }

    private void initView() {
        if (null == getView())
            return;
        mMenuMain = getView().findViewById(R.id.ly_menu_index);
        mMenuMessage = getView().findViewById(R.id.ly_menu_message);
        mMenuMe = getView().findViewById(R.id.ly_menu_me);
        viewPager = getView().findViewById(R.id.container_content);

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
