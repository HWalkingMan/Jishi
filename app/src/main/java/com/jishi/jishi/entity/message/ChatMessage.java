package com.jishi.jishi.entity.message;

/**
 * @author WM
 * @description
 * @date 2020/7/4 0:01
 */
public class ChatMessage {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public ChatMessage(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
