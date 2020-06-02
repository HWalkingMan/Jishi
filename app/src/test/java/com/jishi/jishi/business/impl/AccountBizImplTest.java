package com.jishi.jishi.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.response.CommonReturnType;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author WM
 * @description
 * @date 2020/6/2 17:54
 */
public class AccountBizImplTest {

    @Test
    public void getAccount() {

    }

    @Test
    public void JsonToAccount() {
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"accountId\": 10021,\n" +
                "        \"nickName\": \"unnamed\",\n" +
                "        \"signature\": \"nothing said\",\n" +
                "        \"avatarURL\": null\n" +
                "    },\n" +
                "    \"errorCode\": 0,\n" +
                "    \"errorMsg\": \"操作成功\"\n" +
                "}";
        CommonReturnType<Account> commonReturnType = JSON.parseObject(json, new TypeReference<CommonReturnType<Account>>() {
        });
        System.out.println(commonReturnType);
        Account account = commonReturnType.getData();
        System.out.println(account.getSignature());
    }
}