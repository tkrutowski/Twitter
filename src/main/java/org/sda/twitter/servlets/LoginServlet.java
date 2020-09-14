package org.sda.twitter.servlets;


import org.sda.twitter.database.dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {

    private Random generator;
    private UsersDao usersDao;

    public void init() {
        generator = new Random();
        usersDao = new UsersDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean found = usersDao.hasUser(login, password);

        if (found) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", login);
            resp.sendRedirect("/twitter/profile.jsp");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
