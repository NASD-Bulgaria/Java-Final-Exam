<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		<br>Login name: <input type="text" name="loginName" /> 
		<br>Password:<input type="password" name="loginPassword" /> 
		<br>Balance: <input type="text" name="balance" /> 
		
		<br>First name: <input type="text" name="firstName" />
		<br>Last name: <input type="text" name="lastName" />
		
		<br><input type="submit" value="Register" />
	</form>
</body>
</html>