package com.jishi.jishi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jishi.jishi.R;
import com.jishi.jishi.entity.Message.Friend;
import com.jishi.jishi.entity.Message.FriendChapter;

import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/28 23:54
 */
public class MessageFriendsAdapter extends BaseExpandableListAdapter {

    private List<FriendChapter> data;
    private LayoutInflater inflater;
    private Context context;

    public MessageFriendsAdapter(Context context, List<FriendChapter> data) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_message_friends_chapter, parent, false);
            viewHolder = new ParentViewHolder();
            viewHolder.friendCHapterName = convertView.findViewById(R.id.txv_message_friend_chapter_name);
            viewHolder.expender = convertView.findViewById(R.id.imv_message_friend_chapter_expender);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ParentViewHolder) convertView.getTag();
        }
        FriendChapter friendChapter = data.get(groupPosition);
        viewHolder.friendCHapterName.setText(friendChapter.getName());
        viewHolder.expender.setSelected(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_message_friend, parent, false);
            viewHolder = new ChildViewHolder();
            viewHolder.avatar = convertView.findViewById(R.id.imv_message_friend_avatar);
            viewHolder.nickname = convertView.findViewById(R.id.txv_message_friend_nickname);
            viewHolder.signature = convertView.findViewById(R.id.txv_message_friend_signature);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        Friend friend = data.get(groupPosition).getChildren().get(childPosition);
        viewHolder.signature.setText(friend.getSignature());
        viewHolder.nickname.setText(friend.getNickname());
        viewHolder.avatar.setImageResource(friend.getAvatar());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ParentViewHolder {
        TextView friendCHapterName;
        ImageView expender;
    }

    private static class ChildViewHolder {
        TextView nickname;
        TextView signature;
        ImageView avatar;
    }
}
