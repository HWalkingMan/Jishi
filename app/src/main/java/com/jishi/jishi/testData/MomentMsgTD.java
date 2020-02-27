package com.jishi.jishi.testData;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.viewModel.MomentListItemViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/25 23:58
 */
public class MomentMsgTD {
    public static List<MomentListItemViewModel> getMessage() {
        List<MomentListItemViewModel> momentListItemViewModels = new ArrayList<>();

        momentListItemViewModels.add(new MomentListItemViewModel("1", "York", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img01,
                "我好想你啊，你却不理我", 74,true));
        momentListItemViewModels.add(new MomentListItemViewModel("2", "Sun", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img02,
                "我想要见到你", 704,false));
        momentListItemViewModels.add(new MomentListItemViewModel("3", "Smith.Li", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img03,
                "我想要你的亲亲", 174,false));
        momentListItemViewModels.add(new MomentListItemViewModel("4", "BigBang", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img04,
                "我想要你的抱抱", 84,false));
        momentListItemViewModels.add(new MomentListItemViewModel("5", "Justin", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img05,
                "为什么那", 23,true));
        return momentListItemViewModels;
    }
}
