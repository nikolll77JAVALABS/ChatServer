package com.nikolll77.server;

import com.nikolll77.common.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/*        HttpSession session = req.getSession(false);
        if (session==null) {
            System.out.print("BLYA NULL !!!!!");
        }*/


        InputStream is = req.getInputStream();
        byte[] buf=new byte[req.getContentLength()];
        MessageList ml = MessageList.getInstance();

        is.read(buf);
        String s= new String(buf);
        Message mes = Message.fromGson(s);

        if (mes==null) {
            resp.setStatus(400);
            return;
        }

/*        UserList ul = UserList.getInstance();
        if (ul.sessionPresent(mes.getFrom(),session.getId())){
            resp.setStatus(0);
            return;
        }*/

        ml.add(mes);
       // System.out.print(mes);

    }
}
