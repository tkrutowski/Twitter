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
<%! List<User> users=usersDao.findAll();%>
        <ul>
            <% for (User user : users) {
                out.println("<li>" + user.getLogin() + "</li>");
            }
            %>

</ul>
</body>
</html>
