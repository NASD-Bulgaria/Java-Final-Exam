<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body bgcolor="blue">

 <form method = "Post" action="UserServlet">
      <p>User name:</p>
      <br/>
      <input type="text" name="username"/>
       <p>Password:</p>
      <br/>
         <input type="password" name="password"/>
         <br/>
            <input type="submit" value="Register"/>
           </form>

</body>
</html>