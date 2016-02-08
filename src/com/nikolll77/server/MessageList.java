package com.nikolll77.server;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikolll77.common.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

    private MessageList(){};
    private final List<Message> mesList = new ArrayList<Message>();

    private static final MessageList msgList = new MessageList();

    public static MessageList getInstance() {
        return msgList;
    }



    public synchronized void add(Message m){
        mesList.add(m);
    }

    public void showList(){
        for(Message mes : mesList) {
            System.out.println(mes);
        }
    }

    public synchronized String chatToGson(int n) {

        List<Message> res = new ArrayList<Message>();
        for (int i = n; i < mesList.size(); i++)
            res.add(mesList.get(i));

        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(res);

        } else
            return null;

    }
}
