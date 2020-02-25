package com.jishi.jishi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jishi.jishi.R;
import com.jishi.jishi.entity.MomentMsg;

import java.util.List;
import java.util.Random;

/**
 * @author WM
 * @description
 * @date 2020/2/26 0:17
 */
public class MomentMsgListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<MomentMsg> data;

    public MomentMsgListAdapter(Context context, List<MomentMsg> data) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MomentMsg getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_moment_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.avatar = convertView.findViewById(R.id.imv_moment_item_avatar);
            viewHolder.image = convertView.findViewById(R.id.imv_moment_item_img);
            viewHolder.like = convertView.findViewById(R.id.imv_moment_item_like);
            viewHolder.numReader = convertView.findViewById(R.id.txv_moment_item_readers);
            viewHolder.sendDate = convertView.findViewById(R.id.txv_moment_item_date);
            viewHolder.senderNickName = convertView.findViewById(R.id.txv_moment_item_nickname);
            viewHolder.text = convertView.findViewById(R.id.txv_moment_item_text);
            viewHolder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setSelected(!v.isSelected());
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MomentMsg msg = data.get(position);

        viewHolder.text.setText(msg.getContentText());
        viewHolder.senderNickName.setText(msg.getSenderNickName());
        viewHolder.sendDate.setText(msg.getSendDate());
        viewHolder.numReader.setText(String.valueOf(msg.getNumReaders()));
        viewHolder.like.setSelected(new Random().nextBoolean());
        viewHolder.image.setImageResource(msg.getImage());
        viewHolder.avatar.setImageResource(msg.getSenderAvatar());


        return convertView;
    }

    private static class ViewHolder {
        ImageView avatar;
        TextView senderNickName;
        TextView sendDate;
        ImageView image;
        TextView text;
        TextView numReader;
        ImageView like;
    }
}
