package com.jishi.jishi.business;

import android.content.Context;

import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.response.BusinessException;
import com.jishi.jishi.entity.response.CommonReturnType;

/**
 * @author WM
 * @description
 * @date 2020/6/2 11:56
 */
public interface AccountBiz {
    void getAccount(Context context, Integer accountid, OnGetSuccessListener listener);

    interface OnGetSuccessListener {
        void onSuccess(CommonReturnType<Account> returnType);

        void onFailed(Exception e);
    }

}
