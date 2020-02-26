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
                "York", new Date(), "我想你啦", 7));
        list.add(new MessageListItemViewModel("2", R.drawable.ic_menu_camera,
                "Bark", new Date(), "在吗？", 0));
        list.add(new MessageListItemViewModel("3", R.drawable.ic_menu_camera,
                "Fork", new Date(), "你到哪了？", 4));
        list.add(new MessageListItemViewModel("4", R.drawable.ic_menu_camera,
                "Tom", new Date(), "东西在你桌子上", 1));
        list.add(new MessageListItemViewModel("5", R.drawable.ic_menu_camera,
                "Amy justinland", new Date(), "886", 150));


        return list;
    }
}
