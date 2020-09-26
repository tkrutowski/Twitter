package org.sda.twitter.servlets;


import org.sda.twitter.database.dao.TweetDao;
import org.sda.twitter.model.Tweet;
import org.sda.twitter.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(name = "PublishTweetServlet", urlPatterns = {"/publishTweet"})
public class PublishTweetServlet extends HttpServlet {

    private TweetDao tweetDao;

    @Override
    public void init() throws ServletException {
        tweetDao = new TweetDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);//jeżeli nie ma to nie tworzymy nowej sesji
        req.setCharacterEncoding("UTF-8");
        if (session != null) {
            int id = (Integer) session.getAttribute("userId");
            String message = req.getParameter("tweetMessage");
            LocalDateTime publishedTime = LocalDateTime.now();

            boolean success = tweetDao.publishTweet(new Tweet(message, id, publishedTime));

            if (success) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

