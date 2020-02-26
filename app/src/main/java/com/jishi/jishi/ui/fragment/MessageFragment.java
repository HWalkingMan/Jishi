package com.jishi.jishi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jishi.jishi.R;
import com.jishi.jishi.testData.MessageMsgTD;
import com.jishi.jishi.ui.adapter.MessageListAdapter;
import com.jishi.jishi.ui.viewModel.MessageListItemViewModel;
import com.jishi.jishi.ui.viewModel.MomentListItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/18 12:07
 */
public class MessageFragment extends Fragment {
    private ListView listView;
    private List<MessageListItemViewModel> messageListItemViewModels = new ArrayList<>();
    private MessageListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.lv_message_list);

        //TODO messageListItemViewModels need data
        messageListItemViewModels.addAll(MessageMsgTD.getMessage());

        adapter = new MessageListAdapter(getContext(), messageListItemViewModels);
        listView.setAdapter(adapter);
    }
}
