package com.nikolll77.common;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Message {
    private Date date = new Date();
    private String from;
    private String to;
    private String text;

    public String toGson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromGson(String s) {

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);

    }

    public int send(String s) throws IOException {

        URL url = new URL(s);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        try {
            String json = toGson();
            os.write(json.getBytes());

           return conn.getResponseCode();


        } finally {

            os.close();
        }
    }

    @Override
    public String toString() {

        return new StringBuilder().append("[").append(date.toString())
                .append(", From: ").append(from).append(", To: ").append(to)
                .append("] ").append(text).toString();

    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
