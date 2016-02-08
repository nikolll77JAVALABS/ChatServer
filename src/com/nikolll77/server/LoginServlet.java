package com.nikolll77.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by MY-PC on 08.02.2016.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass= req.getParameter("pass");
        int status=0;
        UserList userList = UserList.getInstance();

        //check password
        if (userList.checkRights(name,pass)) {
            status=1;
        } else  status=0;

        //get session
        if (status==1) {
            HttpSession session = req.getSession(true);
            String sessionId= session.getId();
            userList.setSession(name,sessionId);
           // System.out.print(userList.toString());
        }


        resp.setStatus(status);
    }


}
