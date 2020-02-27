package com.jishi.jishi.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

    private RecyclerView mRecycleViewMenu;//主菜单
    private ViewPager mVPagerHeaderAd;//广告头
    private HomeHeaderAdAdapter adapter;
    private HomeMenuAdapter mainMenuAdapter;

    protected  int [] icons={R.mipmap.header_pic_ad1,R.mipmap.header_pic_ad2,R.mipmap.header_pic_ad1};
    protected int[] menuIons = {R.mipmap.list_my_menu, R.mipmap.list_sug,
            R.mipmap.list_address, R.mipmap.menu_trav,
            R.mipmap.find_main_way, R.mipmap.list_save,
            R.mipmap.menu_nearby, R.mipmap.menu_car};
    protected String[] menus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initAdapter();
        initView();
    }

    private void initData() {
        menus = getActivity().getResources().getStringArray(R.array.main_menu);
    }

    private void initAdapter() {
        adapter = new HomeHeaderAdAdapter(getActivity(), getHeaderAddInfo(getActivity(), icons));
        mainMenuAdapter = new HomeMenuAdapter(getActivity(), getMainMenus(menuIons, menus));
    }

    private void initView() {
        if (null == getActivity() || null == getView())
            return;


        mVPagerHeaderAd = getView().findViewById(R.id.vpager_home_header_ad);
        mRecycleViewMenu = getView().findViewById(R.id.recycleview_home_menu);

        mVPagerHeaderAd.setAdapter(adapter);
        mRecycleViewMenu.setAdapter(mainMenuAdapter);
        mRecycleViewMenu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
    }

    private List<HomeMenuViewModel> getMainMenus(int[] icons, String[] names) {
        List<HomeMenuViewModel> menus = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            HomeMenuViewModel menu = new HomeMenuViewModel(icons[i], names[i]);
            menus.add(menu);
        }
        return menus;
    }

    private static List<ImageView> getHeaderAddInfo(Context context, int[] icons) {
        List<ImageView> datas = new ArrayList<>();
        for (int value : icons) {
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setImageResource(value);
            datas.add(icon);
        }
        return datas;
    }

    public static void toolbarUsage(Toolbar toolbar) {
        toolbar.setTitle("首页");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        assert getActivity() != null;
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.home_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        assert getActivity() != null;
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.home_menu, menu);
    }


}
