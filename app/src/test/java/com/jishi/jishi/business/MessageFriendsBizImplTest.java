package com.jishi.jishi.business;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jishi.jishi.entity.CommonReturnType;
import com.jishi.jishi.entity.Friend;
import com.jishi.jishi.entity.FriendChapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/29 17:26
 */
public class MessageFriendsBizImplTest {
    MessageFriendsBizImpl bussiness;

    @Before
    public void setUp() throws Exception {
        bussiness = new MessageFriendsBizImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadFriendsListFromWeb() throws Exception {
//        String json = bussiness.loadFriendsListFromWeb(null);

//        Gson gson = new Gson();
//        Type type = new TypeToken<CommonReturnType<List<FriendChapter>>>() {
//        }.getType();
//
//        CommonReturnType<List<FriendChapter>> result = gson.fromJson(json, type);
//
//        System.out.println(json + '\n');
//        System.out.println(result + "\n");
//        String json2 = "{\"id\":\"2\",\"name\":\"boy friend\",\"children\":[" +
//                "{\"id\":\"2\",\"nickname\":\"Tom\",\"signature\":\"evil is love!\"," +
//                "\"chapterId\":\"2\",\"avatar\":1500004}," +
//                "{\"id\":\"3\",\"nickname\":\"Amyson\",\"signature\":\"evil love is love\"," +
//                "\"chapterId\":\"2\",\"avatar\":1500004}]}";
//
//        final FriendChapter friendChapter = gson.fromJson(json2, new TypeToken<FriendChapter>() {
//        }.getType());
//        System.out.println(friendChapter);

//        System.out.println(chapters.get(0));
//        System.out.println(chapters.get(0).getClass().getName());

        String json = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": \"1\",\n" +
                "            \"name\": \"be forver\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": \"1\",\n" +
                "                    \"nickname\": \"Jack\",\n" +
                "                    \"signature\": \"Love is evil!\",\n" +
                "                    \"chapterId\": \"1\",\n" +
                "                    \"avatar\": 1500004\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"2\",\n" +
                "            \"name\": \"boy friend\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": \"2\",\n" +
                "                    \"nickname\": \"Tom\",\n" +
                "                    \"signature\": \"evil is love!\",\n" +
                "                    \"chapterId\": \"2\",\n" +
                "                    \"avatar\": 1500004\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"3\",\n" +
                "                    \"nickname\": \"Amyson\",\n" +
                "                    \"signature\": \"evil love is love\",\n" +
                "                    \"chapterId\": \"2\",\n" +
                "                    \"avatar\": 1500004\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"3\",\n" +
                "            \"name\": \"best friend\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": \"4\",\n" +
                "                    \"nickname\": \"Adam\",\n" +
                "                    \"signature\": \"evil is not love!\",\n" +
                "                    \"chapterId\": \"3\",\n" +
                "                    \"avatar\": 1500004\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"5\",\n" +
                "                    \"nickname\": \"Joun\",\n" +
                "                    \"signature\": \"evil love is not love\",\n" +
                "                    \"chapterId\": \"3\",\n" +
                "                    \"avatar\": 1500004\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"6\",\n" +
                "                    \"nickname\": \"Amzason\",\n" +
                "                    \"signature\": \"love is evil love\",\n" +
                "                    \"chapterId\": \"3\",\n" +
                "                    \"avatar\": 1500004\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"errorCode\": 0,\n" +
                "    \"errorMsg\": \"success\"\n" +
                "}";

        final CommonReturnType<List<FriendChapter>> fcFromJson = getFCFromJson(json);
        System.out.println(fcFromJson);
    }

    private CommonReturnType<List<FriendChapter>> getFCFromJson(String json) {
        CommonReturnType<List<FriendChapter>> returnType = new CommonReturnType();

        try {
            JSONObject root = new JSONObject(json);
            int errorcode = root.optInt("errorCode");
            String errorMsg = root.optString("errorMsg");
            List<FriendChapter> chapters = new ArrayList<>();

            JSONArray dataJsonArray = root.optJSONArray("data");
            for (int i = 0; i < dataJsonArray.length(); i++) {
                JSONObject chapterJsonObj = dataJsonArray.getJSONObject(i);
                String id = chapterJsonObj.optString("id");
                String name = chapterJsonObj.optString("name");
                FriendChapter friendChapter = new FriendChapter(id, name);
                JSONArray childrenArray = chapterJsonObj.getJSONArray("children");
                for (int j = 0; j < childrenArray.length(); j++) {
                    JSONObject friendObj = childrenArray.getJSONObject(j);
                    String fid = friendObj.getString("id");
                    String nickname = friendObj.getString("nickname");
                    String signature = friendObj.getString("signature");
                    int avatar = friendObj.getInt("avatar");
                    friendChapter.addChild(new Friend(fid, name, avatar, signature));
                }
                chapters.add(friendChapter);
            }
            returnType.setErrorCode(errorcode);
            returnType.setErrorMsg(errorMsg);
            returnType.setData(chapters);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnType;
    }

    @Test
    public void loadFriendsList() {
        bussiness.loadFriendsList(null, false, new MessageFriendsBiz.OnSuccessListener() {
            @Override
            public void onSuccess(List<FriendChapter> chapters) {
                System.out.println(chapters);
            }

            @Override
            public void onFailed(Exception e) {
                System.out.println(e);
            }
        });
    }
}