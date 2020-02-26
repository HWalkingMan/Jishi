package com.jishi.jishi.ui.viewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author WM
 * @description
 * @date 2020/2/26 11:40
 */
public class MessageListItemViewModel {


    private String id;
    private int SenderAvatar;
    private String senderNickName;
    private Date latestMsgDate;
    private String latestMsgPart;
    private int newMsgNum;

    public MessageListItemViewModel() {
    }


    public MessageListItemViewModel(String id, int senderAvatar, String senderNickName, Date latestMsgDate, String latestMsgPart, int newMsgNum) {
        this.id = id;
        SenderAvatar = senderAvatar;
        this.senderNickName = senderNickName;
        this.latestMsgDate = latestMsgDate;
        this.latestMsgPart = format(latestMsgPart);
        this.newMsgNum = newMsgNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSenderAvatar() {
        return SenderAvatar;
    }

    public void setSenderAvatar(int senderAvatar) {
        SenderAvatar = senderAvatar;
    }

    public String getSenderNickName() {
        return senderNickName;
    }

    public void setSenderNickName(String senderNickName) {
        this.senderNickName = senderNickName;
    }

    public Date getLatestMsgDate() {
        return latestMsgDate;
    }

    public String getLastestMsgDateStr(Date currentDate) {
        SimpleDateFormat sdf;
        //latestMsgDateStr = sdf.format(latestMsgDate);
        //TODO 更改时间显示模式
        int day = (int) ((currentDate.getTime() - latestMsgDate.getTime()) / (1000 * 60 * 60 * 24));
        System.out.println("date:" + latestMsgDate + ",day=" + day);
        if (day == 0) {
            return new SimpleDateFormat("HH:mm", Locale.CHINA).format(latestMsgDate);
        } else if (day == 1) {
            return "昨天";
        } else if (day < 7) {
            return new SimpleDateFormat("E", Locale.CHINA).format(latestMsgDate);
        } else {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(latestMsgDate);
        }
    }

    public void setLatestMsgDate(Date latestMsgDate) {
        this.latestMsgDate = latestMsgDate;
    }

    public int getNewMsgNum() {
        return newMsgNum;
    }

    public void setNewMsgNum(int newMsgNum) {
        this.newMsgNum = newMsgNum;
    }

    public String getLatestMsgPart() {
        return latestMsgPart;
    }

    public void setLatestMsgPart(String latestMsgPart) {
        this.latestMsgPart = format(latestMsgPart);
    }

    @Override
    public String toString() {
        return "MessageListItemViewModel{" +
                "id='" + id + '\'' +
                ", SenderAvatar=" + SenderAvatar +
                ", senderNickName='" + senderNickName + '\'' +
                ", latestMsgDate='" + latestMsgDate + '\'' +
                ", latestMsgPart='" + latestMsgPart + '\'' +
                '}';
    }

    private String format(String latestMsg) {
        if (latestMsg.length() > 20)
            return latestMsg.substring(0, 20) + "···";
        else return latestMsg;
    }
}
