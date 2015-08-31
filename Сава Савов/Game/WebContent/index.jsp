<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body bgcolor="yellow">

      <form method = "Post" action="LoginPage">
      <p>User name:</p>
      <br/>
      <input type="text" name="username"/>
       <p>Password:</p>
      <br/>
         <input type="password" name="password"/>
         <br/>
            <input type="submit" value="Login"/>
            </form>
            <a href="http://localhost:8080/Game/registration.jsp">Register here</a>

</body>
</html>