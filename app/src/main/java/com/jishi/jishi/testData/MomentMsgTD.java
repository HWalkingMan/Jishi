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
                "大学物理要记得知识点都在这里了！", 74, true));
        momentListItemViewModels.add(new MomentListItemViewModel("2", "Sun", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img02,
                "中南周边，夏天必去的8个地方", 704, false));
        momentListItemViewModels.add(new MomentListItemViewModel("3", "Smith.Li", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img03,
                "五一拿到的新的资格证书！！炫耀~", 174, false));
        momentListItemViewModels.add(new MomentListItemViewModel("4", "BigBang", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img04,
                "并夕夕帮砍！急求！谁还不是个宝宝", 84, false));
        momentListItemViewModels.add(new MomentListItemViewModel("5", "Justin", new Date(),
                R.mipmap.ic_default_user_avatar, R.mipmap.moment_img05,
                "爷笑了", 23, true));
        return momentListItemViewModels;
    }
}
