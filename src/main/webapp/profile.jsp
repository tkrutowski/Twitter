<%--
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

<%--<a href="#">Lista użytkowników</a>--%>
<div>
    <form action="logout" method="get">
        <input value="Wyloguj" type="submit">
    </form>
</div>

</body>
</html>
