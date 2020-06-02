package com.jishi.jishi.ui.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.LinearInterpolator;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.jishi.jishi.R;
import com.jishi.jishi.business.MessageFriendsBiz;
import com.jishi.jishi.business.impl.MessageFriendsBizImpl;
import com.jishi.jishi.entity.message.FriendChapter;
import com.jishi.jishi.testData.MessageMsgTD;
import com.jishi.jishi.ui.adapter.MessageFriendsAdapter;
import com.jishi.jishi.ui.adapter.MessageListAdapter;
import com.jishi.jishi.ui.viewModel.MessageListItemViewModel;


import java.util.ArrayList;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/18 12:07
 */
public class MessageFragment extends Fragment {
    private LinearLayout btn_message_chat, btn_message_friendslist;
    private TextView txv_chat, txv_friendList;
    private ListView listView;
    private ExpandableListView expandableListView;

    private List<MessageListItemViewModel> messageListItemViewModels = new ArrayList<>();
    private List<FriendChapter> friendChapters = new ArrayList<>();

    private MessageListAdapter listAdapter;
    private MessageFriendsAdapter expandableListAdapter;

    private MessageFriendsBiz messageFriendsBiz;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initBiz();

        initAdapter();

        initData();

        initView();

        initListener();

        perpareView();
    }

    private void initData() {
        //TODO messageListItemViewModels need real data
        messageListItemViewModels.addAll(MessageMsgTD.getMessage());
        listAdapter.notifyDataSetChanged();
        friendChapters.addAll(MessageMsgTD.getfriendchapter());
        expandableListAdapter.notifyDataSetChanged();
//        messageFriendsBiz.loadFriendsList(getContext(), false, new MessageFriendsBiz.OnSuccessListener() {
//            @Override
//            public void onSuccess(List<FriendChapter> chapters) {
//                friendChapters.addAll(chapters);
//                expandableListAdapter.notifyDataSetChanged();
//                System.out.println(chapters);
//            }
//
//            @Override
//            public void onFailed(Exception e) {
//
//            }
//        });
    }

    private void initBiz() {
        messageFriendsBiz = new MessageFriendsBizImpl();
    }

    private void initAdapter() {
        listAdapter = new MessageListAdapter(getContext(), messageListItemViewModels);
        expandableListAdapter = new MessageFriendsAdapter(getContext(), friendChapters);
    }

    private void initView() {
        assert getView() != null;
        listView = getView().findViewById(R.id.lv_message_list);
        expandableListView = getView().findViewById(R.id.elv_friends_list);
        btn_message_chat = getView().findViewById(R.id.btn_message_chat);
        btn_message_friendslist = getView().findViewById(R.id.btn_message_friendslist);
        txv_chat = getView().findViewById(R.id.txv_message_chat);
        txv_friendList = getView().findViewById(R.id.txv_message_friendlist);

        listView.setAdapter(listAdapter);
        expandableListView.setAdapter(expandableListAdapter);


    }

    private void initListener() {
        final LinearLayout.LayoutParams bigLayoutParams = new LinearLayout
                .LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        final LinearLayout.LayoutParams smailLayoutParams = new LinearLayout
                .LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);

        final ValueAnimator animatorChat = ValueAnimator.ofFloat(2, 8);
        animatorChat.setInterpolator(new LinearInterpolator());
        animatorChat.setDuration(500);
        animatorChat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bigLayoutParams.weight = (float) animation.getAnimatedValue();
                btn_message_chat.setLayoutParams(bigLayoutParams);
                smailLayoutParams.weight = 10 - (float) animation.getAnimatedValue();
                btn_message_friendslist.setLayoutParams(smailLayoutParams);
                if (animation.getAnimatedFraction() > 0.9) {
                    txv_chat.setVisibility(View.VISIBLE);
                    txv_friendList.setVisibility(View.GONE);
                }
            }
        });

        final ValueAnimator animatorFriend = ValueAnimator.ofFloat(2, 8);
        animatorFriend.setInterpolator(new LinearInterpolator());
        animatorFriend.setDuration(500);
        animatorFriend.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bigLayoutParams.weight = (float) animation.getAnimatedValue();
                btn_message_friendslist.setLayoutParams(bigLayoutParams);
                smailLayoutParams.weight = 10 - (float) animation.getAnimatedValue();
                btn_message_chat.setLayoutParams(smailLayoutParams);
                if (animation.getAnimatedFraction() > 0.9) {
                    txv_chat.setVisibility(View.GONE);
                    txv_friendList.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_message_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigLayoutParams.setMargins(0, 0, 2, 0);
                smailLayoutParams.setMargins(2, 0, 0, 0);
                if (!v.isSelected()) {
                    animatorChat.start();
                    v.setSelected(true);
                    btn_message_friendslist.setSelected(false);
                    listView.setVisibility(View.VISIBLE);
                    expandableListView.setVisibility(View.GONE);
                }
            }
        });

        btn_message_friendslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigLayoutParams.setMargins(2, 0, 0, 0);
                smailLayoutParams.setMargins(0, 0, 2, 0);
                if (!v.isSelected()) {
                    animatorFriend.start();
                    v.setSelected(true);
                    btn_message_chat.setSelected(false);
                    listView.setVisibility(View.GONE);
                    expandableListView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void perpareView() {
        int totalNewMsg = 0;
        for (MessageListItemViewModel vo : messageListItemViewModels) {
            totalNewMsg += vo.getNewMsgNum();
        }
        TextView numNewMsg = getActivity().findViewById(R.id.tab_menu_message_num);
        if (totalNewMsg == 0) {
            numNewMsg.setVisibility(View.GONE);
            numNewMsg.setText("");
        } else if (totalNewMsg > 99) {
            numNewMsg.setText("99+");
            numNewMsg.setVisibility(View.VISIBLE);
        } else {
            numNewMsg.setText(String.valueOf(totalNewMsg));
            numNewMsg.setVisibility(View.VISIBLE);
        }

        btn_message_chat.setSelected(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        assert getActivity() != null;
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.message_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        assert getActivity() != null;
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.message_menu, menu);
    }

    public static void toolbarUsage(Toolbar toolbar) {
        toolbar.setTitle("消息");
    }
}
