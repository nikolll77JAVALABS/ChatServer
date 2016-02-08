package com.nikolll77.client;

import com.nikolll77.server.UserList;


public class test {
    public static void main(String[] args) {
        UserList usrList = UserList.getInstance();
        System.out.print(usrList);
    }
}
