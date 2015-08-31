<%-- 
    Document   : index
    Created on : Aug 13, 2015, 10:34:10 AM
    Author     : zahra
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
</head>
<body>
      <form method = "Post" action="LoginServlet">
      Username :  
      <input type="text" name="username"/> <br/> <br/>
      Password : 
      <input type="password" name="password"/> <br/> <br/>
      <input type="submit" value="Log in"/>
      </form>
    <h4> Click here <a href="http://localhost:8080/PlayersProject/registration.jsp"> to register</a> </h4>
</body>
</html>
