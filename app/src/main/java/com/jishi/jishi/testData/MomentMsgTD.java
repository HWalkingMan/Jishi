package com.jishi.jishi.testData;

import com.jishi.jishi.R;
import com.jishi.jishi.entity.MomentMsg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/25 23:58
 */
public class MomentMsgTD {
    public static List<MomentMsg> getMessage() {
        List<MomentMsg> momentMsgs = new ArrayList<>();

        momentMsgs.add(new MomentMsg("1", "York", new Date(),
                R.drawable.ic_menu_camera, R.mipmap.moment_img01,
                "我好想你啊，你却不理我", 74));
        momentMsgs.add(new MomentMsg("2", "Sun", new Date(),
                R.drawable.ic_menu_camera, R.mipmap.moment_img02,
                "我想要见到你", 704));
        momentMsgs.add(new MomentMsg("3", "Smith.Li", new Date(),
                R.drawable.ic_menu_camera, R.mipmap.moment_img03,
                "我想要你的亲亲", 174));
        momentMsgs.add(new MomentMsg("4", "BigBang", new Date(),
                R.drawable.ic_menu_camera, R.mipmap.moment_img04,
                "我想要你的抱抱", 84));
        momentMsgs.add(new MomentMsg("5", "Justin", new Date(),
                R.drawable.ic_menu_camera, R.mipmap.moment_img05,
                "为什么那", 23));
        return momentMsgs;
    }
}
