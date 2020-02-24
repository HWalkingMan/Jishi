package com.jishi.jishi.ui.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author WM
 * @description
 * @date 2020/2/25 0:53
 */
public class HomeMenuViewModel {
    int icon;
    String menuName;

    public HomeMenuViewModel() {
    }

    public HomeMenuViewModel(int icon, String menuName) {
        this.icon = icon;
        this.menuName = menuName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "HomeMenuViewModel{" +
                "icon=" + icon +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}
