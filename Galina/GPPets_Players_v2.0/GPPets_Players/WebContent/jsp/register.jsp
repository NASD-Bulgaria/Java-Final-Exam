<%@page import="org.json.JSONObject"%>
<%@ page import="javafx.print.PageLayout"%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="model.*" %>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+ "://" +request.getServerName() + ":" +request.getServerPort()+path+ "/";%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Player registration page</title>
</head>
<body>
	<form action="register" method="get"> 
	<% 
	JSONObject myJSON = (JSONObject) request.getAttribute("myJSON");
	if(myJSON!=null){
		String info = myJSON.toString();
		out.println(info); 
	}
	%>
	</form>
	<form action="register" method="post"> 
	Please enter the required information!<br></br> 
	First name:</br>
	<input type="text" name="firstName"><br>
	Last name:</br>
	<input type="text" name="lastName"><br>
	Login name:</br>
	<input type="text" name="loginName"><br>
	Login password:</br>
	<input type="password" name="password"><br>
	
	<input type="submit" value="Register">
	<input type="reset" value="Reset">
	</form>
</body>
</html>