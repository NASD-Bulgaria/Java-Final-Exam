<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<form method="POST" action="LoginServlet">
	<p>login Name:</p>
	<input name="loginName" type="text" />
	<br/>
	<p>Login Password</p>
	<input name="loginPassword" type="password"/>
	<br/>
	<input type="submit" value="login"/>
	</form>	
	<a href="RegisterServlet">Register Here Player</a>
</body>
</html>