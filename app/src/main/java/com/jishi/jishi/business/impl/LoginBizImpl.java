package com.jishi.jishi.business.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.jishi.jishi.business.LoginBiz;
import com.jishi.jishi.dao.DatabaseHelper;
import com.jishi.jishi.dao.LoginDao;
import com.jishi.jishi.entity.login.Account;
import com.jishi.jishi.entity.login.Signon;
import com.jishi.jishi.entity.response.CommonReturnType;
import com.jishi.jishi.util.TokenUtil;

/**
 * @author WM
 * @description
 * @date 2020/5/12 15:55
 */
public class LoginBizImpl implements LoginBiz {
    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login(final Context context, Signon signon, final OnLoginSuccessListener successListener) {

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Signon, Void, Account> asyncTask = new AsyncTask<Signon, Void, Account>() {

            private Exception ex;

            @Override
            protected Account doInBackground(Signon... signons) {
                Signon signon = signons[0];
                try {
                    return loginWithDB(signon);
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

            @Override
            protected void onPostExecute(Account account) {
                if (ex != null) {
                    successListener.onFailed(ex);
                    return;
                }
                successListener.onSuccess(CommonReturnType.okResult(TokenUtil.createToken(account)));
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
                    return registerWithDB(signon);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.ex = e;
                }
                return null;
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
