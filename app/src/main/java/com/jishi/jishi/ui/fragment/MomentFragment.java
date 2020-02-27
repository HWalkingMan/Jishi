package com.jishi.jishi.ui.fragment;

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
import android.widget.ListView;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.viewModel.MomentListItemViewModel;
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
    private List<MomentListItemViewModel> momentListItemViewModels = new ArrayList<>();
    private MomentMsgListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO messageListItemViewModels need real data
        momentListItemViewModels.addAll(MomentMsgTD.getMessage());
        adapter = new MomentMsgListAdapter(getContext(), momentListItemViewModels);

        initView();
    }

    private void initView() {
        if (null == getView())
            return;
        listView = getView().findViewById(R.id.lv_moment_list);

        listView.setAdapter(adapter);
    }

    public static void toolbarUsage(Toolbar toolbar) {
        toolbar.setTitle("圈子");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        assert getActivity() != null;
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.moment_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        assert getActivity() != null;
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.moment_menu, menu);
    }
}
