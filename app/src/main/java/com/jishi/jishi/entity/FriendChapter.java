package com.jishi.jishi.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WM
 * @description
 * @date 2020/2/29 0:02
 */
public class FriendChapter {

    private String id;
    private String name;
    private List<Friend> children = new ArrayList<>();

    public FriendChapter() {
    }

    public FriendChapter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(Friend friend) {
        children.add(friend);
        friend.setChapterId(this.id);
    }

    public List<Friend> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "FriendChapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
