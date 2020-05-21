package com.jishi.jishi.business;

import android.content.Context;

import com.jishi.jishi.entity.account.Signon;
import com.jishi.jishi.entity.response.CommonReturnType;

/**
 * @author WM
 * @description
 * @date 2020/5/9 11:11
 */
public interface LoginBiz {
    boolean isLogined();

    void login(Context context, Signon signon, OnLoginSuccessListener successListener);

    void register(Context context, Signon signon, OnRegisterSuccessListener successListener);

    interface OnLoginSuccessListener {
        void onSuccess(CommonReturnType<String> returnType);

        void onFailed(Exception e);
    }

    interface OnRegisterSuccessListener {
        void onSuccess(CommonReturnType<Signon> returnType);

        void onFailed(Exception e);
    }
}
