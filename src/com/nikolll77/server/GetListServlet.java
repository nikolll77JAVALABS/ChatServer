package com.nikolll77.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet(name = "GetListServlet")
public class GetListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       HttpSession session = request.getSession(false);
        if (session==null) {
                System.out.print("BLYA NULL !!!!!");
        }


        String s= request.getParameter("from");
        int i = Integer.parseInt(s);
        MessageList chat = MessageList.getInstance();
        s=chat.chatToGson(i);
        if (s!=null) {
            OutputStream os = response.getOutputStream();
            os.write(s.getBytes());
            //os.close();
        }
    }
}
