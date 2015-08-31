<%@page import="javafx.print.PageLayout"%>
<%@ page import="org.json.JSONObject"%>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+ "://" +request.getServerName() + ":" +request.getServerPort()+path+ "/";%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Player login page</title>
</head>
<body>
	<form action="login" method="get"> 
	<% 
	JSONObject myJSON = (JSONObject) request.getAttribute("myJSON");
	if(myJSON!=null){
		String info = myJSON.toString();
		out.println(info); 
	}
	%>
	</form>

	<form action="login" method="post">  
	Please enter the required information!<br></br>
	Login name:</br>
	<input type="text" name="loginName"><br>
	Login password:</br>
	<input type="password" name="password"><br>
	
	<input type="submit" value="Login">
	<input type="reset" value="Reset">
	</form>
	<br>
	<a style="font-style: italic; font-weight: bold; font-family: Arial; text-align: left; color: navy" href = "register"> 
	    Click here to register! </a>
</body>
</html>