package com.jishi.jishi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jishi.jishi.R;
import com.jishi.jishi.ui.viewModel.MessageListItemViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author WM
 * @description
 * @date 2020/2/26 15:34
 */
public class MessageListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<MessageListItemViewModel> data;

    public MessageListAdapter(Context context, List<MessageListItemViewModel> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_message_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.avatar = convertView.findViewById(R.id.imv_message_item_avatar);
            viewHolder.numNewMsg = convertView.findViewById(R.id.txv_message_item_numnewmsg);
            viewHolder.sendDate = convertView.findViewById(R.id.txv_message_item_lastestmsgdate);
            viewHolder.senderNickName = convertView.findViewById(R.id.txv_message_item_nickname);
            viewHolder.msgPart = convertView.findViewById(R.id.txv_message_item_lastestmsgpart);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MessageListItemViewModel msg = data.get(position);

        viewHolder.avatar.setImageResource(msg.getSenderAvatar());
        viewHolder.senderNickName.setText(msg.getSenderNickName());
        viewHolder.sendDate.setText(msg.getLatestMsgDate());
        viewHolder.msgPart.setText(msg.getLatestMsgPart());

        if (msg.getNewMsgNum() == 0) {
            viewHolder.numNewMsg.setVisibility(View.GONE);
            viewHolder.numNewMsg.setText("");
        } else if (msg.getNewMsgNum() > 99) {
            viewHolder.numNewMsg.setText("99+");
            viewHolder.numNewMsg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.numNewMsg.setText(String.valueOf(msg.getNewMsgNum()));
            viewHolder.numNewMsg.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView avatar;
        TextView senderNickName;
        TextView sendDate;
        TextView msgPart;
        TextView numNewMsg;
    }


}
