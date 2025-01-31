package com.jishi.jishi.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/25 0:40
 */
public class HomeHeaderAdAdapter extends PagerAdapter {

    protected Context context;
    protected List<ImageView> images;

    public HomeHeaderAdAdapter(Context context, List<ImageView> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return null != images ? images.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(images.get(position));
        return images.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));
    }
}
