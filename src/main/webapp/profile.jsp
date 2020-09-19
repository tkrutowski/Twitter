<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<%@ page import="org.sda.twitter.database.dao.FollowersDao" %>
<%@ page import="org.sda.twitter.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.sda.twitter.database.dao.TweetDao" %>
<%@ page import="org.sda.twitter.model.Tweet" %><%--
  Created by IntelliJ IDEA.
  User: tkrut
  Date: 13.09.2020
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User profile</title>
</head>
<body>
<h1>Welcome <%= session.getAttribute("user")%></h1>
<div>
    <form action="users" method="get">
        <input value="Lista użytkowników" type="submit">
    </form>
</div>

<div>
    <%! UsersDao usersDao = new UsersDao();%>
    <%! FollowersDao followersDao = new FollowersDao();%>
    <%! TweetDao tweetDao=new TweetDao();%>

    <%! int userId; %>
    <%userId = (Integer) session.getAttribute("userId");%>
    <ul>
        <%
            //lista obserwowanych przez uzytkownika zalogowanego
            List<Integer> followedList = followersDao.findFollowedByUserId(userId);


            //lista tweetów obserwowanych użytkowników
            List<Tweet> followedTweet = tweetDao.getFollowedTweet(followedList);

            for(Tweet tweet:followedTweet){
               out.println("<div>");
                out.println("<li>");
                out.println("<p>uzytkownik "+tweet.getAuthorId()+"</p>");
                out.println("<p>"+tweet.getMessage()+"</p>");
                out.println("</li>");
                out.println("</div>");
            }

        %>

    </ul>
</div>

<div>
    <form action="logout" method="get">
        <input value="Wyloguj" type="submit">
    </form>
</div>
<div>
    <form action="publishTweet" method="post">
            <textarea id="tweetArea" name="tweetMessage" rows="5" cols="50">
            Place your message here
            </textarea>
        <br/>
        <input type="submit" value="Publish">
    </form>
</div>

</body>
</html>
