package com.nikolll77.server;

import java.text.SimpleDateFormat;
import java.util.Date;


public class User {

    private String name;
    private String pass;
    private String sessionId;
    private boolean isOnline;
    private Date lastMessageDate;
    private Date lastLoginDate;

    public User (String name,String pass){
        this.name=name;
        this.pass=pass;
        this.isOnline=false;
        this.sessionId=new String();
        this.lastMessageDate= new Date();
        this.lastLoginDate= new Date();
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        StringBuilder sb = new StringBuilder().append("Name="+name+"; Status=");
        if (isOnline) sb.append("online; "); else sb.append("offline; ");

        sb.append("lastLoginDate="+dateFormat.format(lastLoginDate)+"; ");
        sb.append("LastMessageDate="+dateFormat.format(lastMessageDate)+"; ");
        sb.append("Session="+getSesseionID());

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSesseionID() {
        return sessionId;
    }

    public void setSesseionID(String sesseionID) {
        sessionId = sesseionID;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
