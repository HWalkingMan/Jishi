package com.jishi.jishi.entity.account;


/**
 * @author WM
 * @description
 * @date 2020/5/9 11:23
 */
public class Account {
    private Integer accountId;
    private String nickName;
    private String signature;
    private String img64;

    public Account() {
    }


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImg64() {
        return img64;
    }

    public void setImg64(String img64) {
        this.img64 = img64;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", img64='" + img64 + '\'' +
                '}';
    }
}
