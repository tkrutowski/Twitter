package org.sda.twitter.servlets;

import org.sda.twitter.database.dao.FollowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "FollowerAddServlet", urlPatterns = {"/followerAdd"})
public class FollowerAddServlet extends HttpServlet {
    FollowersDao followersDao;

    @Override
    public void init() throws ServletException {
        followersDao=new FollowersDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int followedId = Integer.valueOf(req.getParameter("followed"));
        int followerId = Integer.valueOf(req.getParameter("follower"));

        if(followersDao.add(followerId,followedId)){
            req.getRequestDispatcher("/users.jsp").forward(req,resp);
        }
    }
}
