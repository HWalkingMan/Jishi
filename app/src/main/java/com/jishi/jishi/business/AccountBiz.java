package com.jishi.jishi.business;

import android.content.Context;

import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.response.CommonReturnType;

/**
 * @author WM
 * @description
 * @date 2020/6/2 11:56
 */
public interface AccountBiz {
    /*
     * put accontid  Int
     * get account Info  Account
     */
    void getAccount(Context context, Integer accountid, Callback<Account> listener);

    /*
     * put imgStr(byte[]) String
     * get imgUrl   String
     */
    void uploadUserImage(Context context, String imgStr, Callback<String> callback);

    interface Callback<T> {
        void onSuccess(CommonReturnType<T> returnType);

        void onFailed(Exception e);
    }

}
