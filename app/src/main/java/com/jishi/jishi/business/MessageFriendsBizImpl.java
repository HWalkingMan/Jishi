package com.jishi.jishi.business;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.blankj.utilcode.util.GsonUtils;
import com.jishi.jishi.entity.CommonReturnType;
import com.jishi.jishi.entity.Friend;
import com.jishi.jishi.entity.FriendChapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author WM
 * @description
 * @date 2020/2/29 11:34
 */
public class MessageFriendsBizImpl implements MessageFriendsBiz {

//    public static final String FRIENDS_LIST_GET_URL = "http://192.168.171.1:8090/api/test";

    public static final String FRIENDS_LIST_GET_URL = "http://localhost:8090/api/test";
    private OkHttpClient client;

    public MessageFriendsBizImpl() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void loadFriendsList(final Context context, boolean useCache, final OnSuccessListener listener) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Boolean, Void, List<FriendChapter>> asyncTask = new AsyncTask<Boolean, Void, List<FriendChapter>>() {

            private Exception ex;

            @Override
            protected List<FriendChapter> doInBackground(Boolean... booleans) {
                boolean isUseCache = booleans[0];
                List<FriendChapter> chapters = new ArrayList<>();
                try {
                    if (isUseCache) {
                        //TODO load from DB
                    }
                    if (chapters.isEmpty()) {
                        //TODO load from web
                        List<FriendChapter> chaptersFromWeb = null;//loadFriendsListFromWeb(context);

                        //TODO cache to DB
                        chapters.addAll(chaptersFromWeb);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.ex = e;
                }

                return chapters;
            }

            @Override
            protected void onPostExecute(List<FriendChapter> chapters) {
                if (ex != null) {
                    listener.onFailed(ex);
                    return;
                }
                listener.onSuccess(chapters);
            }
        };
        asyncTask.execute(useCache);
    }

    public String loadFriendsListFromWeb(Context context) throws Exception {
        Request request = new Request.Builder()
                .url(FRIENDS_LIST_GET_URL)
                .build();
        Response response = client.newCall(request).execute();

        String friendsList_json = Objects.requireNonNull(response.body()).string();
        CommonReturnType returnType = GsonUtils.fromJson(friendsList_json, CommonReturnType.class);
        if (returnType.getErrorCode() == 0) {
            String tempJson = GsonUtils.toJson(returnType.getData());
//            List<FriendChapter> chapters= (List<FriendChapter>) GsonUtils.fromJson(tempJson,FriendChapter.class);
            return friendsList_json;
        } else {
            throw new Exception("Server Error!\n Error Code: " + returnType.getErrorCode()
                    + "\n Error Msg: " + returnType.getErrorMsg());
        }
    }


//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(json, JSON);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
}

