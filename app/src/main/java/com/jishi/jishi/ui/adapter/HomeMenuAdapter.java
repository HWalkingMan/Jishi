package com.jishi.jishi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.jishi.jishi.R;
import com.jishi.jishi.ui.viewModel.HomeMenuViewHolder;
import com.jishi.jishi.ui.viewModel.HomeMenuViewModel;

import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/25 0:06
 */
public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuViewHolder> {

    private Context context;
    private List<HomeMenuViewModel> menus;

    public HomeMenuAdapter(Context context, List<HomeMenuViewModel> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public HomeMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_menu, null));
    }

    @Override
    public void onBindViewHolder(HomeMenuViewHolder holder, int position) {

        HomeMenuViewModel menu = menus.get(position);
        holder.getmImgMenuIcon().setImageResource(menu.getIcon());
        holder.getmTxtMenuName().setText(menu.getMenuName());
    }

    @Override
    public int getItemCount() {
        return null != menus ? menus.size() : 0;
    }
}