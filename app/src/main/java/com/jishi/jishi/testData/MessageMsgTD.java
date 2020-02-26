package com.jishi.jishi.testData;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.viewModel.MessageListItemViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/26 19:44
 */
public class MessageMsgTD {
    public static List<MessageListItemViewModel> getMessage() {
        List<MessageListItemViewModel> list = new ArrayList<>();
        list.add(new MessageListItemViewModel("1", R.drawable.ic_menu_camera,
                "York", new Date(120, 1, 25, 1, 34, 56),
                "我想你啦", 7));
        list.add(new MessageListItemViewModel("2", R.drawable.ic_menu_camera,
                "Bark", new Date(119, 1, 24, 10, 30, 6),
                "在吗？", 0));
        list.add(new MessageListItemViewModel("3", R.drawable.ic_menu_camera,
                "Fork", new Date(120, 1, 24, 10, 30, 30),
                "你到哪了？", 4));
        list.add(new MessageListItemViewModel("4", R.drawable.ic_menu_camera,
                "Tom", new Date(120, 0, 13, 22, 34, 56),
                "东西在你桌子上", 1));
        list.add(new MessageListItemViewModel("5", R.drawable.ic_menu_camera,
                "Amy justinland", new Date(120, 1, 26, 10, 13, 34),
                "886", 150));
        list.add(new MessageListItemViewModel("5", R.drawable.ic_menu_camera,
                "Alibaba", new Date(120, 1, 25, 22, 13, 34),
                "886", 150));
        list.add(new MessageListItemViewModel("6", R.drawable.ic_menu_camera,
                "Sam", new Date(), "886", 150));


        return list;
    }
}
