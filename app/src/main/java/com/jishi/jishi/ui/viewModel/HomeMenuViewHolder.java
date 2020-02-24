package com.jishi.jishi.ui.viewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jishi.jishi.R;

/**
 * @author WM
 * @description
 * @date 2020/2/25 1:11
 */
public class HomeMenuViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImgMenuIcon;
    private TextView mTxtMenuName;

    public HomeMenuViewHolder(View itemView) {
        super(itemView);
        mImgMenuIcon = (ImageView) itemView.findViewById(R.id.img_menu_icon);
        mTxtMenuName = (TextView) itemView.findViewById(R.id.txt_menu_name);
    }

    public ImageView getmImgMenuIcon() {
        return mImgMenuIcon;
    }

    public void setmImgMenuIcon(ImageView mImgMenuIcon) {
        this.mImgMenuIcon = mImgMenuIcon;
    }

    public TextView getmTxtMenuName() {
        return mTxtMenuName;
    }

    public void setmTxtMenuName(TextView mTxtMenuName) {
        this.mTxtMenuName = mTxtMenuName;
    }
}
