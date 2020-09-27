<!DOCTYPE html>
<html lang="pl">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Twitter - rejestracja"/>
</jsp:include>
<body>
    <div id="header">
        <img id="logo" src="twitter-company.jpg" />
    </div>
<br/>
<div id="container">
    <div id="login-form">
        <P style="font-size: 2em;">Utwórz użytkownika</P>
        <form action="register" method="POST">
            <label for="login">Login:</label>
            <input type="text" name="login" id="login" placeholder="wpisz tutaj..."/>
        
            <br/>
            <label for="password">Hasło:</label>
            <input type="password" id="password" name="password" placeholder="wpisz tutaj..."/>
            <br/>
            <br/>
            <label for="password">Potwierdź hasło:</label>
            <input type="password" id="password2" name="password2" placeholder="wpisz tutaj..."/>
            <br/>
            <a class="login-button" href="#" onclick="this.parentNode.submit();"> Zarejestruj się</a>
        </form>
    </div>
</div>

</body>
</html>