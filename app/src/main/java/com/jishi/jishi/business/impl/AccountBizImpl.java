package com.jishi.jishi.business.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blankj.utilcode.util.EncryptUtils;
import com.jishi.jishi.business.AccountBiz;
import com.jishi.jishi.dao.DatabaseHelper;
import com.jishi.jishi.dao.LoginDao;
import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.account.Signon;
import com.jishi.jishi.entity.response.BusinessException;
import com.jishi.jishi.entity.response.CommonError;
import com.jishi.jishi.entity.response.CommonReturnType;
import com.jishi.jishi.entity.response.EmBusinessErr;
import com.jishi.jishi.ui.activity.LoginActivity;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author WM
 * @description
 * @date 2020/6/2 12:43
 */
public class AccountBizImpl implements AccountBiz {

    private final static String MY_PRE_NAME = "preferences";
    private final static String TOKEN = "TOKEN";

    private final String GET_ACCOUNT_URL = "http://192.168.56.2:8080/account/v1/get";

    private OkHttpClient client;

    public AccountBizImpl() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void getAccount(final Context context, final Integer accountid, final OnGetSuccessListener listener) {

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Integer, Void, Account> asyncTask = new AsyncTask<Integer, Void, Account>() {
            Exception ex = null;

            @Override
            protected Account doInBackground(Integer... integers) {
                try {
                    Account account = getAccountByDB(integers[0]);
                    if (account == null) {
                        account = getAccountByWeb();
                        if (account == null) {
                            throw new BusinessException(EmBusinessErr.DATA_NOT_EXIST);
                        } else {
                            updateAccountDB(account);
                        }
                    }
                    return account;
                } catch (Exception e) {
                    e.printStackTrace();
                    ex = e;
                }
                return null;
            }

            private Account getAccountByDB(Integer accountid) throws Exception {
                DatabaseHelper helper = new DatabaseHelper(context);
                SQLiteDatabase db = helper.getReadableDatabase();
                final Account account = LoginDao.getAccount(db, accountid);
                db.close();
                return account;
            }

            private Account getAccountByWeb() throws Exception {
                SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
                String token = sharedPreferences.getString(TOKEN, null);
                if (token == null) {
                    return null;
                }
                Log.i("getAccountByWeb", accountid.toString());
                Request request = new Request.Builder()
                        .url(GET_ACCOUNT_URL + "?accountId=" + accountid.toString())
                        .addHeader("Authorization", token)
                        .get()
                        .build();
                Response response = client.newCall(request).execute();
                String json = Objects.requireNonNull(response.body()).string();
                Log.i("json", json);
                CommonReturnType<Account> commonReturnType = JSON.parseObject(json, new TypeReference<CommonReturnType<Account>>() {
                });
                if (commonReturnType.getErrorCode() != 0) {
                    throw new Exception(commonReturnType.getErrorMsg());
                }

                return commonReturnType.getData();
            }

            private void updateAccountDB(Account account) {
                DatabaseHelper helper = new DatabaseHelper(context);
                SQLiteDatabase db = helper.getReadableDatabase();
                LoginDao.updateAccount(db, account);
                db.close();
            }

            @Override
            protected void onPostExecute(Account account) {
                if (ex != null) {
                    listener.onFailed(ex);
                } else if (account == null) {
                    listener.onFailed(new Exception("nothing get"));
                } else {
                    listener.onSuccess(CommonReturnType.okResult(account));
                }
            }
        };
        asyncTask.execute(accountid);

    }
}
