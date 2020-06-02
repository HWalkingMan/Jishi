package com.jishi.jishi.business.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.jishi.jishi.business.LoginBiz;
import com.jishi.jishi.dao.DatabaseHelper;
import com.jishi.jishi.dao.LoginDao;
import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.account.Signon;
import com.jishi.jishi.entity.response.CommonReturnType;

import java.lang.reflect.Type;
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
 * @date 2020/5/12 15:55
 */
public class LoginBizImpl implements LoginBiz {
    private final String REGISTER_URL = "http://192.168.56.2:8080/account/v1/enroll";
    private final String LOGIN_URL = "http://192.168.56.2:8080/account/v1/login";
    private final String ONLINE_URL = "http://192.168.56.2:8080/account/v1/online";
    private OkHttpClient client;

    public LoginBizImpl() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void online() {

    }

    @Override
    public void login(final Context context, final Signon signon, final OnLoginSuccessListener successListener) {

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Signon, Void, String> asyncTask = new AsyncTask<Signon, Void, String>() {

            private Exception ex;

            @Override
            protected String doInBackground(Signon... signons) {
                Signon signon = signons[0];
                try {
                    return loginWithWeb(signon);
//                    return TokenUtil.createToken(loginWithDB(signon));
                } catch (Exception e) {
                    e.printStackTrace();
                    this.ex = e;
                }
                return null;
            }

            private Account loginWithDB(Signon signon) throws Exception {
                DatabaseHelper helper = new DatabaseHelper(context);
                SQLiteDatabase db = helper.getReadableDatabase();
                final Account account = LoginDao.login(db, signon);
                db.close();
                if (account == null) {
                    throw new Exception("username or password is wrong.");
                }
                return account;
            }

            private String loginWithWeb(Signon signon) throws Exception {
                RequestBody formBody = new FormBody.Builder()
                        .add("accountId", String.valueOf(signon.getAccountId()))
                        .add("password", EncryptUtils.encryptSHA1ToString(signon.getPassword()))
                        .build();
                Request request = new Request.Builder()
                        .url(LOGIN_URL)
                        .post(formBody)
                        .build();
                Response response = client.newCall(request).execute();
                String json = Objects.requireNonNull(response.body()).string();
                System.out.println(">>>" + json);
                Type type = new TypeToken<CommonReturnType<String>>() {
                }.getType();
                CommonReturnType<String> commonReturnType = GsonUtils.fromJson(json, type);
                if (commonReturnType.getErrorCode() != 0) {
                    throw new Exception(commonReturnType.getErrorMsg());
                }
                System.out.println(">>>" + commonReturnType.getData());
                return commonReturnType.getData();
            }

            @Override
            protected void onPostExecute(String string) {
                if (ex != null) {
                    successListener.onFailed(ex);
                    return;
                }
                successListener.onSuccess(CommonReturnType.okResult(string));
            }
        };

        asyncTask.execute(signon);

    }

    @Override
    public void register(final Context context, Signon signon, final OnRegisterSuccessListener successListener) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Signon, Void, Signon> asyncTask = new AsyncTask<Signon, Void, Signon>() {
            private Exception ex;

            @Override
            protected Signon doInBackground(Signon... signons) {
                Signon signon = signons[0];
                try {
                    return registerWithWeb(signon);
//                    return registerWithDB(signon);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.ex = e;
                }
                return null;
            }

            private Signon registerWithWeb(Signon signon) throws Exception {
                RequestBody formBody = new FormBody.Builder()
                        .add("phoneNum", signon.getPhoneNum())
                        .add("password", EncryptUtils.encryptSHA1ToString(signon.getPassword()))
                        .build();
                Request request = new Request.Builder()
                        .url(REGISTER_URL)
                        .post(formBody)
                        .build();
                Response response = client.newCall(request).execute();
                String json = Objects.requireNonNull(response.body()).string();
                System.out.println(">>>" + json);
                Type type = new TypeToken<CommonReturnType<Signon>>() {
                }.getType();
                CommonReturnType<Signon> commonReturnType = GsonUtils.fromJson(json, type);
                if (commonReturnType.getErrorCode() != 0) {
                    throw new Exception(commonReturnType.getErrorMsg());
                }
                System.out.println(">>>" + commonReturnType.getData().getAccountId());
                return commonReturnType.getData();
            }

            private Signon registerWithDB(Signon signon) throws Exception {
                DatabaseHelper helper = new DatabaseHelper(context);
                SQLiteDatabase db = helper.getReadableDatabase();
                final Signon signonWithId = LoginDao.register(db, signon);
                db.close();
                return signonWithId;
            }

            @Override
            protected void onPostExecute(Signon signon) {
                if (ex != null) {
                    successListener.onFailed(ex);
                    return;
                }
                successListener.onSuccess(CommonReturnType.okResult(signon));
            }
        };

        asyncTask.execute(signon);
    }


}
