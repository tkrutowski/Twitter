<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<%@ page import="org.sda.twitter.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.sda.twitter.database.dao.FollowersDao" %>
  Created by IntelliJ IDEA.
  User: tkrut
  Date: 13.09.2020
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tammudu+2:wght@600&display=swap" rel="stylesheet">
    <title>Twitter - użytkownicy</title>
</head>
<body>
    <%! UsersDao usersDao = new UsersDao();%>
    <%! FollowersDao followersDao = new FollowersDao();%>
    <%! int userId; %>
    <%userId = (Integer) session.getAttribute("userId");%>

<div id="header">
    <img id="logo" src="twitter-company.jpg" />
</div>
<div id="container">
    <h1 style="margin-bottom: 0px;">Lista użytkowników</h1>

    <%
        //lista wszystkich uzytkowników oprócz zalogowanego
        List<User> users = usersDao.findAll(userId);
        //lista obserwowanych przez uzytkownika zalogowanego
        List<Integer> followedList = followersDao.findFollowedByUserId(userId);
        for (User user : users) {
            //jeżeli jest na liście obsewrwowanych to aktywny przycisk usuwania
            if (followedList.contains(user.getId())) {
                out.println("<form id=\"user-form\" action=\"followerRemove\" method=\"POST\">");
                out.println("<div id=\"div-user\">");
                out.println("<img style=\"height: 70px;\"src=\"user.png\"/>");
                out.println("<p>" + user.getLogin() + "</p>");
                out.println("</div>");
                out.println("<div id=\"div-user-buttons\">");
                out.println("<a class=\"user-button\" href=\"#\" onclick=\"this.parentNode.parentNode.submit();\">Anuluj</a>");
                out.println("<input name=\"followed\" readonly hidden value=" + user.getId() + "></input>");
                out.println("<input name=\"follower\" readonly hidden value=" + userId + "></input>");
                out.println("</div>");
                out.println("</form>");
            } else {
                out.println("<form id=\"user-form\" action=\"followerAdd\" method=\"POST\">");
                out.println("<div id=\"div-user\">");
                out.println("<img style=\"height: 70px;\"src=\"user.png\"/>");
                out.println("<p>" + user.getLogin() + "</p>");
                out.println("</div>");
                out.println("<div id=\"div-user-buttons\">");
                out.println("<a class=\"user-button\" href=\"#\" onclick=\"this.parentNode.parentNode.submit();\">Obserwuj</a>");
                out.println("<input name=\"followed\" readonly hidden value=" + user.getId() + "></input>");
                out.println("<input name=\"follower\" readonly hidden value=" + userId + "></input>");
                out.println("</div>");
                out.println("</form>");
            }
        }
    %>
</div>
</body>
</html>
