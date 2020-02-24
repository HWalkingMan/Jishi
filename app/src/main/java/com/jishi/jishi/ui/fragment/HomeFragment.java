package com.jishi.jishi.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.adapter.HomeHeaderAdAdapter;
import com.jishi.jishi.ui.adapter.HomeMenuAdapter;
import com.jishi.jishi.ui.viewModel.HomeMenuViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/18 12:06
 */
public class HomeFragment extends Fragment {

    protected  int [] icons={R.mipmap.header_pic_ad1,R.mipmap.header_pic_ad2,R.mipmap.header_pic_ad1};
    protected ViewPager mVPagerHeaderAd;//广告头

    //菜单图标

    protected int[] menuIons = {R.mipmap.list_my_menu, R.mipmap.list_sug,
            R.mipmap.list_address, R.mipmap.menu_trav,
            R.mipmap.find_main_way, R.mipmap.list_save,
            R.mipmap.menu_nearby, R.mipmap.menu_car};
    protected  String [] menus;
    protected RecyclerView mRecycleViewMenu;//主菜单


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menus=this.getActivity().getResources().getStringArray(R.array.main_menu);

        mVPagerHeaderAd = (ViewPager) getView().findViewById(R.id.vpager_home_header_ad);
        mRecycleViewMenu = (RecyclerView) getView().findViewById(R.id.recycleview_home_menu);


        HomeHeaderAdAdapter adapter = new HomeHeaderAdAdapter(getActivity(), getHeaderAddInfo(getActivity(), icons));
        mVPagerHeaderAd.setAdapter(adapter);

        //菜单
        //布局样式
        mRecycleViewMenu.setLayoutManager(new GridLayoutManager(getActivity(),4));
        HomeMenuAdapter mainMenuAdapter = new HomeMenuAdapter(getActivity(), getMainMenus(menuIons, menus));
        mRecycleViewMenu.setAdapter(mainMenuAdapter);
    }

    private List<HomeMenuViewModel> getMainMenus(int icons[], String names[]) {
        List<HomeMenuViewModel> menus = new ArrayList<>();

        for (int i = 0; i < icons.length; i++) {
            HomeMenuViewModel menu = new HomeMenuViewModel(icons[i], names[i]);
            menus.add(menu);
        }
        return menus;
    }

    private static List<ImageView> getHeaderAddInfo(Context context, int icons[]) {
        List<ImageView> datas = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setImageResource(icons[i]);
            datas.add(icon);
        }
        return datas;
    }


}
