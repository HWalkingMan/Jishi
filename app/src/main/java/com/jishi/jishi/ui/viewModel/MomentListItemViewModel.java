package com.jishi.jishi.ui.viewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WM
 * @description
 * @date 2020/2/25 23:33
 */
public class MomentListItemViewModel {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String id;
    private String senderNickName;
    private String sendDate;
    private int SenderAvatar;
    private int image;
    private String contentText;
    private int numReaders;
    private boolean liked;

    public MomentListItemViewModel() {
    }

    public MomentListItemViewModel(String id, String senderNickName, String sendDate, int senderAvatar, int image, String contentText, int numReaders, boolean liked) {
        this.id = id;
        this.senderNickName = senderNickName;
        this.sendDate = sendDate;
        this.SenderAvatar = senderAvatar;
        this.image = image;
        this.contentText = contentText;
        this.numReaders = numReaders;
        this.liked = liked;
    }

    public MomentListItemViewModel(String id, String senderNickName, Date sendDate, int senderAvatar, int image, String contentText, int numReaders, boolean liked) {
        this.id = id;
        this.senderNickName = senderNickName;
        this.sendDate = sdf.format(sendDate);
        this.SenderAvatar = senderAvatar;
        this.image = image;
        this.contentText = contentText;
        this.numReaders = numReaders;
        this.liked = liked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderNickName() {
        return senderNickName;
    }

    public void setSenderNickName(String senderNickName) {
        this.senderNickName = senderNickName;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sdf.format(sendDate);
    }

    public int getSenderAvatar() {
        return SenderAvatar;
    }

    public void setSenderAvatar(int senderAvatar) {
        SenderAvatar = senderAvatar;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getNumReaders() {
        return numReaders;
    }

    public void setNumReaders(int numReaders) {
        this.numReaders = numReaders;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
