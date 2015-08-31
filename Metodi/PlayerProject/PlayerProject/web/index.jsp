
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Title Here</title>
    </head>
    <body>
        <form method="POST" action="LoginPage">
            <p>UserName</p>
            <br/>
            <input type="text" name="username"/>
            <p>PassWord</p>
            <br/>
            <input type="password" name="password"/>
            <br/>
            <input type="submit" value="Login"/>
        </form>
        <a href="http://localhost:8080/PlayerProject/faces/registration.jsp">Register here</a>
    </body>
</html>
