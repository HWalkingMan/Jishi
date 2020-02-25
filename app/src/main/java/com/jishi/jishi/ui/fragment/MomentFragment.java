package com.jishi.jishi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.jishi.jishi.R;
import com.jishi.jishi.entity.MomentMsg;
import com.jishi.jishi.testData.MomentMsgTD;
import com.jishi.jishi.ui.adapter.MomentMsgListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/18 12:07
 */
public class MomentFragment extends Fragment {
    private ListView listView;
    private List<MomentMsg> momentMsgs = new ArrayList<>();
    private MomentMsgListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = getView().findViewById(R.id.lv_moment_list);

        momentMsgs.addAll(MomentMsgTD.getMessage());
        adapter = new MomentMsgListAdapter(getContext(), momentMsgs);

        listView.setAdapter(adapter);
    }
}



