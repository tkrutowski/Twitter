package org.sda.twitter.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            req.getRequestDispatcher("/users.jsp").forward(req,resp);
        }else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
