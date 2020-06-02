package com.jishi.jishi.entity.message;

/**
 * @author WM
 * @description
 * @date 2020/2/29 0:02
 */
public class Friend {
    private String id;
    private String nickname;
    private String avatarURL;
    private String signature;

    private String chapterId;

    public Friend() {
    }

    public Friend(String id, String nickname, String avatarURL, String signature) {
        this.id = id;
        this.nickname = nickname;
        this.avatarURL = avatarURL;
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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
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
                ", avatarURL='" + avatarURL + '\'' +
                ", signature='" + signature + '\'' +
                ", chapterId='" + chapterId + '\'' +
                '}';
    }
}
