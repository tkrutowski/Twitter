package org.sda.twitter.servlets;


import org.sda.twitter.database.dao.UsersDao;
import org.sda.twitter.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class RegisterServlet extends HttpServlet {

    private UsersDao usersDao;

    public void init() {
        usersDao = new UsersDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        if (password.equals(password2) && !login.isEmpty() && !password.isEmpty()) {
            User user = new User(login, password);
            if (usersDao.createUser(user)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.sendRedirect("/twitter/registerSuccess.html");
            } else
                resp.sendRedirect("/twitter/registerFailed.html");
        } else
            resp.sendRedirect("/twitter/registerFailed.html");
    }
}
