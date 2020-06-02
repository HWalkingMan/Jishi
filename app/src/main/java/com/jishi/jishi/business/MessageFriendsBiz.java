package com.jishi.jishi.business;

import android.content.Context;

import com.jishi.jishi.entity.message.FriendChapter;

import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/29 11:28
 */
public interface MessageFriendsBiz {

    void loadFriendsList(Context context, boolean useCache, OnSuccessListener listener);


    interface OnSuccessListener {
        void onSuccess(List<FriendChapter> chapters);

        void onFailed(Exception e);
    }
}

