package com.jishi.jishi.util;

import com.jishi.jishi.entity.account.Account;

/**
 * @author WM
 * @description
 * @date 2020/5/13 15:27
 */
public class TokenUtils {
    public static String createToken(Account account) {
        return account.getAccountId().toString();
    }
}
