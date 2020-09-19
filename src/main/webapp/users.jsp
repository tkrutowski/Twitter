<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<%@ page import="org.sda.twitter.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.sda.twitter.database.dao.FollowersDao" %>
<%@ page import="javax.swing.*" %>
<%@ page import="static javax.swing.text.html.FormSubmitEvent.MethodType.POST" %><%--
  Created by IntelliJ IDEA.
  User: tkrut
  Date: 13.09.2020
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Twitter - użytkownicy</title>
</head>
<body>
<h1>Użytkownicy</h1>
<%! UsersDao usersDao = new UsersDao();%>
<%! FollowersDao followersDao = new FollowersDao();%>
<%! int userId; %>
<%userId = (Integer) session.getAttribute("userId");%>
<ul>
    <%
        //lista wszystkich uzytkowników oprócz zalogowanego
        List<User> users = usersDao.findAll(userId);
        //lista obserwowanych przez uzytkownika zalogowanego
        List<Integer> followedList = followersDao.findFollowedByUserId(userId);
        for (User user : users) {
            //jeżeli jest na liście obsewrwowanych to aktywny przycisk usuwania
            if (followedList.contains(user.getId())) {
                out.println("<form action=\"followerRemove\" method=\"POST\">");
                out.println("<li>" + user.getLogin() + "<input type=\"submit\" value=\"Dodaj do obserwowanych\" disabled></input><input type=\"submit\" value = \"Usuń z obserwowanych\"></input></li>");
                out.println("<input name=\"followed\" readonly value=" + user.getId() + "></input>");
                out.println("<input name=\"follower\" readonly value=" + userId + "></input>");
                out.println("</form>");
            } else {
                out.println("<form action=\"followerAdd\" method=\"POST\">");
                out.println("<li>" + user.getLogin() + "<input type=\"submit\" value=\"Dodaj do obserwowanych\"></input><input type=\"submit\" value = \"Usuń z obserwowanych\" disabled></input></li>");
                out.println("<input name=\"followed\" readonly value=" + user.getId() + "></input>");
                out.println("<input name=\"follower\" readonly value=" + userId + "></input>");
                out.println("</form>");
            }
        }
    %>

</ul>
</body>
</html>
