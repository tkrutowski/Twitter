<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<%@ page import="org.sda.twitter.database.dao.FollowersDao" %>
<%@ page import="java.util.List" %>
<%@ page import="org.sda.twitter.database.dao.TweetDao" %>
<%@ page import="org.sda.twitter.model.Tweet" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: tkrut
  Date: 13.09.2020
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tammudu+2:wght@600&display=swap" rel="stylesheet">

    <title>Twitter - profil użytkownika</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div id="header">
    <!-- <h1>Twitter</h1> -->
    <img id="logo" src="twitter-company.jpg"/>
</div>
<br/>
<div id="container">
    <div id="profile-form">
        <div id="user-form" style="width: 90%; border: none;">
            <div id="div-user">
                <p style="font-size: 2.7em;">Witaj <%= session.getAttribute("user")%></p>
            </div>

            <div class="div-user-buttons" style="width: 60%;">
                <form class="div-user-buttons">
                    <a class="user-button" style="width: 90%;" href="newTweet.html">Nowy tweet</a>
                </form>

                <form class="div-user-buttons" action="users" method="get">
                    <a class="user-button" style="width: 90%;" href="#" onclick="this.parentNode.submit();">Użytkownicy</a>
                </form>

                <form class="div-user-buttons" action="logout" method="get">
                    <a class="user-button" style="width: 60%;" href="#" onclick="this.parentNode.submit();">Wyloguj</a>
                </form>

            </div>

        </div>
        <p style="font-size: 2em; margin-top: 60px; align-self: center;">Tweety obserwowanych użytkowników</p>

        <%! UsersDao usersDao = new UsersDao();%>
        <%! FollowersDao followersDao = new FollowersDao();%>
        <%! TweetDao tweetDao = new TweetDao();%>

        <%! int userId; %>
        <%userId = (Integer) session.getAttribute("userId");%>
        <%
            //lista obserwowanych przez uzytkownika zalogowanego
            List<Integer> followedList = followersDao.findFollowedByUserId(userId);

            //lista tweetów obserwowanych użytkowników
            List<Tweet> followedTweets = tweetDao.getFollowedTweet(followedList);
            Collections.reverse(followedTweets);
            for (Tweet tweet : followedTweets) {
                out.println("<div class=\"tweet\">");
                out.println("<div class=\"tweet-header\">");
                out.println("<p>" + usersDao.getUserName(tweet.getAuthorId()) + "</p>");
                out.println("<p>" + tweet.getPublishDate().toLocalDate() + "  " + tweet.getPublishDate().toLocalTime() + "</p>");
                out.println("</div>");

                out.println("<div>");
                out.println("<p class=\"tweet-message\">" + tweet.getMessage() + "</p>");
                out.println("</div>");
                out.println("</div>");
            }
        %>
    </div>
</div>
</body>
</html>
