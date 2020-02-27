package com.jishi.jishi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.adapter.ContentFragmentAdapter;

public class ContentFrameFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static final int STATE_SCROLL_DONE = 2;
    protected LinearLayout mMenuHome;
    protected LinearLayout mMenuMessage;
    protected LinearLayout mMenuMoment;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    private ContentFragmentAdapter mAdapter;

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

        initAdapter();
        initView();
        initListener();

        prepareView();
    }

    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void initAdapter() {
        mAdapter = new ContentFragmentAdapter(getChildFragmentManager());
    }

    private void prepareView() {
        mMenuHome.performClick();
        toolbar.setNavigationIcon(R.mipmap.ic_default_user_avatar);
    }

    private void initView() {
        if (null == getView())
            return;
        if (null == getActivity())
            return;
        mMenuHome = getView().findViewById(R.id.ly_menu_home);
        mMenuMessage = getView().findViewById(R.id.ly_menu_message);
        mMenuMoment = getView().findViewById(R.id.ly_menu_moment);
        viewPager = getView().findViewById(R.id.container_content);
        toolbar = getActivity().findViewById(R.id.toolbar);
        drawer = getActivity().findViewById(R.id.drawer_layout);

        mMenuHome.setOnClickListener(this);
        mMenuMessage.setOnClickListener(this);
        mMenuMoment.setOnClickListener(this);

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(PAGE_ONE);
        viewPager.addOnPageChangeListener(this);
    }

    private void resetSelect() {
        mMenuHome.setSelected(false);
        mMenuMessage.setSelected(false);
        mMenuMoment.setSelected(false);
    }

    @Override
    public void onClick(View view) {
        resetSelect();
        switch (view.getId()) {
            case R.id.ly_menu_home:
                mMenuHome.setSelected(true);
                viewPager.setCurrentItem(PAGE_ONE);
                HomeFragment.toolbarUsage(toolbar);
                break;
            case R.id.ly_menu_message:
                mMenuMessage.setSelected(true);
                viewPager.setCurrentItem(PAGE_TWO);
                MessageFragment.toolbarUsage(toolbar);
                break;
            case R.id.ly_menu_moment:
                mMenuMoment.setSelected(true);
                viewPager.setCurrentItem(PAGE_THREE);
                MomentFragment.toolbarUsage(toolbar);
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
                    mMenuHome.setSelected(true);
                    HomeFragment.toolbarUsage(toolbar);
                    break;
                case PAGE_TWO:
                    mMenuMessage.setSelected(true);
                    MessageFragment.toolbarUsage(toolbar);
                    break;
                case PAGE_THREE:
                    mMenuMoment.setSelected(true);
                    MomentFragment.toolbarUsage(toolbar);
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
