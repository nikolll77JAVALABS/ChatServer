package com.nikolll77.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class UserList {
    private UserList() {

        usrList.put("nikolll",new User("nikolll","123"));
        usrList.put("serg",new User("serg","123"));
        usrList.put("egor",new User("egor","123"));

    }
    private static HashMap<String,User> usrList = new HashMap<>();

    public static UserList getInstance() {
        return userList;
    }

    private static final UserList userList=new UserList();

    public boolean checkRights(String name,String pass) {
        boolean result=false;
        User usr=usrList.get(name);
        if (usr !=null)
            if (usr.getPass().equals(pass)) result=true;

        return result;

    }

    public synchronized void add(String name,String pass){
        User usr = new User(name,pass);
        usrList.put(usr.getName(),usr);
    }
    public synchronized boolean sessionPresent(String userName,String sessionID) {
        boolean result=false;
        User usr = usrList.get(userName);
        if (usr!=null){
            result=usr.getSesseionID().equals(sessionID);
        }
        return result;
    }
    public synchronized void setSession(String userName,String sessionID){
        User usr = usrList.get(userName);
        usr.setSesseionID(sessionID);
        usr.setLastLoginDate(new Date());
        usr.setOnline(true);

    }

    public synchronized void setLastMessageDate(String userName){
        usrList.get(userName).setLastMessageDate(new Date());
    }

    public synchronized String toString() {
        String s=new String();
        for (User user:usrList.values()) {
           s+=user.toString()+"\n";
        }
        return s;
    }


}
