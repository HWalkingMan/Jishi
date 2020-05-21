package com.jishi.jishi.business.impl;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.jishi.jishi.entity.account.Signon;
import com.jishi.jishi.entity.response.CommonReturnType;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.junit.Assert.*;

/**
 * @author WM
 * @description
 * @date 2020/5/21 23:22
 */
public class LoginBizImplTest {

    String json = "{\n" +
            "    \"data\": {\n" +
            "        \"accountId\": 10017,\n" +
            "        \"phoneNum\": \"123123123123\",\n" +
            "        \"password\": null\n" +
            "    },\n" +
            "    \"errorCode\": 0,\n" +
            "    \"errorMsg\": \"操作成功\"\n" +
            "}";

    @Test
    public void jsonToSignon() {
        Type type = new TypeToken<CommonReturnType<Signon>>() {
        }.getType();
        CommonReturnType<Signon> commonReturnType = GsonUtils.fromJson(json, type);
        System.out.println(commonReturnType.getData().getAccountId());
    }
}