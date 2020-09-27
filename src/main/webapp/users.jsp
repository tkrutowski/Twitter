<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<%@ page import="org.sda.twitter.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.sda.twitter.database.dao.FollowersDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%! UsersDao usersDao;
    FollowersDao followersDao;
    int userId; %>
<%! public void jspInit() {
    usersDao = new UsersDao();
    followersDao = new FollowersDao();
}
%>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tammudu+2:wght@600&display=swap" rel="stylesheet">
    <title>Twitter - użytkownicy</title>
</head>
<body>

<div id="header">
    <a href="profile.jsp">
        <img id="logo" src="twitter-company.jpg"/>
    </a>
</div>
<div id="container">
    <h1 style="margin-bottom: 0px;">Lista użytkowników</h1>

    <%
        userId = (Integer) session.getAttribute("userId");
        //lista wszystkich uzytkowników oprócz zalogowanego
        List<User> users = usersDao.findAllExceptLoggedIn(userId);
        //lista obserwowanych przez uzytkownika zalogowanego
        List<Integer> followedList = followersDao.findFollowedByUserId(userId);
        for (User user : users) {
            //jeżeli jest na liście obsewrwowanych to aktywny przycisk usuwania
            if (followedList.contains(user.getId())) {
    %>
    <form id="user-form" action="followerRemove" method="POST">
        <div id="div-user">
            <img style="height: 70px;" src="user.png"/>
            <p><%=user.getLogin()%></p>
        </div>
        <div class="div-user-buttons">
            <a class="user-button" href="#" onclick="this.parentNode.parentNode.submit();">Anuluj</a>
            <input name="followed" readonly hidden value="<%= user.getId()%>"></input>
            <input name="follower" readonly hidden value="<%= userId %>"></input>
        </div>
    </form>
    <% } else { %>
    <form id="user-form" action="followerAdd" method="POST">
            <div id="div-user">
                <img style="height: 70px;" src="user.png"/>
                <p><%= user.getLogin() %></p>
            </div>
            <div class="div-user-buttons">
                <a class="user-button" href="#" onclick="this.parentNode.parentNode.submit();">Obserwuj</a>
                <input name="followed" readonly hidden value="<%=user.getId()%>"></input>
                <input name="follower" readonly hidden value="<%= userId %>"></input>
            </div>
    </form>
    <% }
    }
    %>
</div>
</body>
</html>
