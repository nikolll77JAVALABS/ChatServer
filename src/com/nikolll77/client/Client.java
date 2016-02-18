package com.nikolll77.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nikolll77.common.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

class GetChatThread extends Thread {

    int n=0;

    private String usrName="";
    private String usrRoom="";

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public void setUsrRoom(String usrRoom) {
        this.usrRoom = usrRoom;
       // n=0; //Отдадим все сообщения из комнаты
    }

    @Override
    public void run() {
        try {

            while (!isInterrupted()) {

                URL url = new URL("http://localhost:8080/getchat?from="+n+"&user="+usrName+"&room="+usrRoom);
                HttpURLConnection httpUC = (HttpURLConnection) url.openConnection();
                //httpUC.setRequestMethod("GET");
                InputStream is = httpUC.getInputStream();

                int contLen=httpUC.getContentLength();
                if (contLen>0) {
                    byte[] buf = new byte[contLen];
                    is.read(buf);
                    String chatInGson = new String(buf);


                    Gson gson = new GsonBuilder().create();
                    //Message[] res =gson.fromJson(chatInGson,Message[].class);
                    List<Message> res = gson.fromJson(chatInGson, new TypeToken<List<Message>>() {
                    }.getType());
                    for (Message mes : res) {
                        System.out.println(mes.toString());
                        n++;
                    }
                    System.out.print("room:"+usrRoom+":"+usrName+">");
                }

                sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }
}

public class Client {

    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);
        Message mes =new Message();

        String userRoom="";

        //Login process
        System.out.print("Login:");
        String usrName = scanner.nextLine();
        System.out.print("Password:");
        String usrPass = scanner.nextLine();

       if (login(usrName,usrPass)==0) {
            System.out.print("Access denied");
            return;
        }
        else
       {
           System.out.println("Welcome");
           System.out.println("To send private message - use #u userName");
           System.out.println("To move to other room - use #r roomName");
           System.out.println("For exit from the chat - use #exit");
       }

        GetChatThread chatThread = new GetChatThread();
        chatThread.setDaemon(true);
        chatThread.start();
        chatThread.setUsrName(usrName);

        mes.setFrom(usrName);
        //chat loop
        String text="";
        String[] stringList;
        while (!text.equals("#exit")) {


            text = scanner.nextLine();

            stringList = text.split(" ");

            mes.setTo("");
            if (stringList[0].equals("#u")) {          //to user
                if (stringList[1]!=null)  mes.setTo(stringList[1]);
            }

            if (stringList[0].equals("#r")) {          //go to room
                if (stringList.length>=2) userRoom=stringList[1]; else userRoom="";
                chatThread.setUsrRoom(userRoom);
                continue;
            }

            mes.setRoom(userRoom);
            mes.setText(text);

            Integer respCode = mes.send("http://localhost:8080/add");
            if (respCode!=200) System.out.print("Error = "+respCode);
        }



    }

    private static int login(String name,String pass) throws IOException {
        URL url=new URL("http://localhost:8080/login?name="+name+"&pass="+pass);
        HttpURLConnection httpUC= (HttpURLConnection) url.openConnection();
        return httpUC.getResponseCode();
    }
}
