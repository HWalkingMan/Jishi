package com.jishi.jishi.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jishi.jishi.ui.fragment.ContentFrameFragment;
import com.jishi.jishi.ui.fragment.HomeFragment;
import com.jishi.jishi.ui.fragment.MomentFragment;
import com.jishi.jishi.ui.fragment.MessageFragment;

/**
 * @author WM
 * @description
 * @date 2020/2/18 23:00
 */
public class ContentFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MomentFragment mMomentFragment;

    public ContentFragmentAdapter(FragmentManager fm) {
        super(fm);
        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mMomentFragment = new MomentFragment();
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
            case ContentFrameFragment.PAGE_ONE:
                fragment = mHomeFragment;
                break;
            case ContentFrameFragment.PAGE_TWO:
                fragment = mMessageFragment;
                break;
            case ContentFrameFragment.PAGE_THREE:
                fragment = mMomentFragment;
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
