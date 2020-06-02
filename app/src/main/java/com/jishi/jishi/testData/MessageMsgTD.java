package com.jishi.jishi.testData;

import com.jishi.jishi.R;
import com.jishi.jishi.entity.message.Friend;
import com.jishi.jishi.entity.message.FriendChapter;
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

        list.add(new MessageListItemViewModel("1", R.mipmap.ic_default_user_avatar,
                "York", new Date(120, 1, 25, 1, 34, 56),
                "我想你啦", 7));
        list.add(new MessageListItemViewModel("2", R.mipmap.ic_default_user_avatar,
                "Bark", new Date(119, 1, 24, 10, 30, 6),
                "在吗？", 0));
        list.add(new MessageListItemViewModel("3", R.mipmap.ic_default_user_avatar,
                "Fork", new Date(120, 1, 24, 10, 30, 30),
                "你到哪了？", 4));
        list.add(new MessageListItemViewModel("4", R.mipmap.ic_default_user_avatar,
                "Tom", new Date(120, 0, 13, 22, 34, 56),
                "东西在你桌子上", 1));
        list.add(new MessageListItemViewModel("5", R.mipmap.ic_default_user_avatar,
                "Amy justinland", new Date(120, 1, 26, 10, 13, 34),
                "886", 10));
        list.add(new MessageListItemViewModel("5", R.mipmap.ic_default_user_avatar,
                "Alibaba", new Date(120, 1, 25, 22, 13, 34),
                "886", 5));
        list.add(new MessageListItemViewModel("6", R.mipmap.ic_default_user_avatar,
                "Sam", new Date(), "886", 15));
        return list;
    }

    public static List<FriendChapter> getfriendchapter() {
        final String url = "https://bkimg.cdn.bcebos.com/pic/63d0f703918fa0ec08faacc53fde4eee3d6d54fb6deb?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg";
        List<FriendChapter> list = new ArrayList<>();
        FriendChapter fc = new FriendChapter("1", "be forver");
        Friend a = new Friend("1", "Jack", url, "Love is evil!");
        fc.addChild(a);
        list.add(fc);

        fc = new FriendChapter("2", "boy friend");
        a = new Friend("2", "Tom", url, "evil is love!");
        fc.addChild(a);
        a = new Friend("3", "Amyson", url, "evil love is love");
        fc.addChild(a);
        list.add(fc);

        fc = new FriendChapter("3", "best friend");
        a = new Friend("4", "Adam", url, "evil is not love!");
        fc.addChild(a);
        a = new Friend("5", "Joun", url, "evil love is not love");
        fc.addChild(a);
        a = new Friend("6", "Amzason", url, "love is evil love");
        fc.addChild(a);
        list.add(fc);
        return list;
    }
}
