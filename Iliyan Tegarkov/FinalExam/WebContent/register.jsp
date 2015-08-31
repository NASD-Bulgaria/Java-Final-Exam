<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		<br>Login Name:<br> <input type="text" name="loginName" />
		<br>First Name:<br> <input type="text" name="firstName" />
		<br>Last Name:<br> <input type="text" name="lastName" /> <br>Password:<br>
		<input type="password" name="password" /><br> <br>Balance:<br>
		<input type="text" name="balance" /><br> <br>
		<input type="submit" value="Register" />
	</form>
</body>
</html>