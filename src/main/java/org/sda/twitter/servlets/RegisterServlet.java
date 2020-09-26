package org.sda.twitter.servlets;


import org.sda.twitter.database.dao.UsersDao;
import org.sda.twitter.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class RegisterServlet extends HttpServlet {

    private UsersDao usersDao;

    public void init() {
        usersDao = new UsersDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        if (login != null && password != null && password2 != null) {
            if (!login.isEmpty() && !password.isEmpty() && password.equals(password2)) {
                User user = new User(login, password);
                if (usersDao.createUser(user)) {
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    req.getRequestDispatcher("/registerSuccess.html").forward(req,resp);
                }
            }else
                req.getRequestDispatcher("/registerFailed.html").forward(req,resp);
        } else
            req.getRequestDispatcher("/registerFailed.html").forward(req,resp);
    }
}
