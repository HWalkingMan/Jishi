package com.jishi.jishi.entity.login;

/**
 * @author WM
 * @description
 * @date 2020/5/9 16:26
 */
public class Signon {
    private Integer accountId;
    private String phoneNum;
    private String password;

    public Signon() {
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
