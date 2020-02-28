package com.jishi.jishi.entity;

/**
 * @author WM
 * @description
 * @date 2020/2/29 0:02
 */
public class Friend {
    private String id;
    private String nickname;
    private int Avatar;
    private String signature;

    private String chapterId;

    public Friend() {
    }

    public Friend(String id, String nickname, int avatar, String signature) {
        this.id = id;
        this.nickname = nickname;
        Avatar = avatar;
        this.signature = signature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", Avatar=" + Avatar +
                ", signature='" + signature + '\'' +
                ", chapterId='" + chapterId + '\'' +
                '}';
    }
}
