<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<%@ page import="org.sda.twitter.model.User" %>
<%@ page import="java.util.List" %><%--
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
<%! UsersDao usersDao=new UsersDao();%>
<%--<%! int userId;%>--%>
<%--<% userId = (Integer) session.getAttribute("userId");%>--%>

<%--<%! List<User> users=usersDao.findAll(userId);%>--%>
<ul>
            <%
                List<User> users=usersDao.findAll((Integer) session.getAttribute("userId"));
                for (User user : users) {
                out.println("<li>" + user.getLogin() + "<button type=\"button\">Dodaj do obserwowanych</button><button type=\"button\">Usuń z obserwowanych</button></li>");
            }
//                out.println(session.getAttribute("userId"));
//                out.println(session.getAttribute("user"));
            %>

</ul>
<button type="button">Dodaj do obserwowanych</button>
<button type="button">Usuń z obserwowanych</button>
</body>
</html>
