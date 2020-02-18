package com.jishi.jishi.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jishi.jishi.ui.activity.MainActivity;
import com.jishi.jishi.ui.fragment.MainFragment;
import com.jishi.jishi.ui.fragment.MeFragment;
import com.jishi.jishi.ui.fragment.MessageFragment;

/**
 * @author WM
 * @description
 * @date 2020/2/18 23:00
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    protected MainFragment mMainFragment = null;
    protected MessageFragment mMessageFragment = null;
    protected MeFragment mMeFragment = null;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        mMainFragment = new MainFragment();
        mMessageFragment = new MessageFragment();
        mMeFragment = new MeFragment();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = mMainFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = mMessageFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = mMeFragment;
                break;
            default:
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
