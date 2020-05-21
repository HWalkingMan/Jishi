package com.jishi.jishi.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;
import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.account.Signon;

/**
 * @author WM
 * @description
 * @date 2020/5/13 15:30
 */
public class LoginDao {
    private static final String selectSignonByPhone = "select * from signon where phonenum = ?";
    private static final String selectSignonByAP = "select * from signon where accountid = ? and password = ?";
    private static final String selectAccountByAccountid = "select * from account where accountid = ?";
    private static final String insertSignon = "insert into signon values(null,?,?);";
    private static final String insertAccount = "insert into account values(?,null,null,null);";

    public static Signon register(SQLiteDatabase db, Signon signon) throws Exception {
        ContentValues signonValue = new ContentValues();
        signonValue.put("phonenum", signon.getPhoneNum());
        String enPW = EncryptUtils.encryptSHA1ToString(signon.getPassword());//使用SHA1加密
        signonValue.put("password", enPW);
        long result = db.insert("signon", null, signonValue);
        if (result == -1)
            throw new Exception("手机号已经被注册");
        final Cursor signonCur = db.rawQuery(selectSignonByPhone, new String[]{signon.getPhoneNum()});
        if (signonCur.getCount() != 1)
            throw new Exception("插入操作失败");
        signonCur.moveToFirst();
        Signon signonWithId = new Signon();
        signonWithId.setAccountId(signonCur.getInt(signonCur.getColumnIndex("accountid")));
        signonWithId.setPassword(signonCur.getString(signonCur.getColumnIndex("password")));
        signonWithId.setPhoneNum(signonCur.getString(signonCur.getColumnIndex("phonenum")));
        db.execSQL(insertAccount, new Object[]{signonWithId.getAccountId()});
        return signonWithId;
    }

    public static Account login(SQLiteDatabase db, Signon signon) throws Exception {
        String enPW = EncryptUtils.encryptSHA1ToString(signon.getPassword());
        final Cursor cursor = db.rawQuery(selectSignonByAP, new String[]{signon.getAccountId().toString(), enPW});
        if (cursor.getCount() == 0)
            return null;
        final Cursor cursor1 = db.rawQuery(selectAccountByAccountid, new String[]{signon.getAccountId().toString()});
        if (cursor1.getCount() != 1)
            throw new Exception("账户信息不存在");
        cursor1.moveToFirst();
        Account account = new Account();
        account.setAccountId(cursor1.getInt(cursor1.getColumnIndex("accountid")));
        account.setNickName(cursor1.getString(cursor1.getColumnIndex("nickname")));
        account.setSignature(cursor1.getString(cursor1.getColumnIndex("signature")));
        account.setImg64(cursor1.getString(cursor1.getColumnIndex("img64")));
        Log.d("Login", account.toString());
        return account;
    }
}
