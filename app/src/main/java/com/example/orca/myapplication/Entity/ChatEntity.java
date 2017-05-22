package com.example.orca.myapplication.Entity;

/**
 * Created by orca on 2017/5/18.
 */
public class ChatEntity {

    private int userImage;
    private String content;
    private String chatTime;
    private boolean isComeMsg;
//    ChatEntity(int userImage,String content,String chatTime,boolean isComeMsg){
//        this.userImage=userImage;
//        this.content=content;
//        this.chatTime=chatTime;
//        this.isComeMsg=isComeMsg;
//    }

    public int getUserImage() {
        return userImage;
    }
    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getChatTime() {
        return chatTime;
    }
    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }
    public boolean isComeMsg() {
        return isComeMsg;
    }
    public void setComeMsg(boolean isComeMsg) {
        this.isComeMsg = isComeMsg;
    }

}