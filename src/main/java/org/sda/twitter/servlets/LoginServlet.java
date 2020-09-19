package org.sda.twitter.servlets;


import org.sda.twitter.database.dao.UsersDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {

    private UsersDao usersDao;

    public void init() {
        usersDao = new UsersDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

       int userId = usersDao.hasUser(login, password);

        if (userId > 0) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", login);
            session.setAttribute("userId", userId);
            resp.sendRedirect("/twitter/profile.jsp");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.sendRedirect("/twitter/loginFailed.html");
        }
    }
}
