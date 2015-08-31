<%-- 
    Document   : registration
    Created on : Aug 13, 2015, 10:36:36 AM
    Author     : zahra
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
 <form method = "Post" action="MainServlet">
      Please enter your first name :  
      <input type="text" name="firstName"/> <br/> <br/>
      Please enter your last name :  
      <input type="text" name="lastName"/> <br/> <br/>
      Username :
      <input type="text" name="username"/> <br/> <br/>
      Password :  
      <input type="password" name="password"/> <br/> <br/>
      <input type="submit" value="OK"/>
      </form>
           
</body>
</html>