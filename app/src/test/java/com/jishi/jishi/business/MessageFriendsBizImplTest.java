package com.jishi.jishi.business;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jishi.jishi.entity.CommonReturnType;
import com.jishi.jishi.entity.FriendChapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
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
        String json = bussiness.loadFriendsListFromWeb(null);
        Gson gson = new Gson();
        Type type = new TypeToken<CommonReturnType<List<FriendChapter>>>() {
        }.getType();

        CommonReturnType<List<FriendChapter>> result = gson.fromJson(json, type);

        System.out.println(json + '\n');
        System.out.println(result + "\n");
        String json2 = "{\"id\":\"2\",\"name\":\"boy friend\",\"children\":[" +
                "{\"id\":\"2\",\"nickname\":\"Tom\",\"signature\":\"evil is love!\"," +
                "\"chapterId\":\"2\",\"avatar\":1500004}," +
                "{\"id\":\"3\",\"nickname\":\"Amyson\",\"signature\":\"evil love is love\"," +
                "\"chapterId\":\"2\",\"avatar\":1500004}]}";

        final FriendChapter friendChapter = gson.fromJson(json2, new TypeToken<FriendChapter>() {
        }.getType());
        System.out.println(friendChapter);

//        System.out.println(chapters.get(0));
//        System.out.println(chapters.get(0).getClass().getName());
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