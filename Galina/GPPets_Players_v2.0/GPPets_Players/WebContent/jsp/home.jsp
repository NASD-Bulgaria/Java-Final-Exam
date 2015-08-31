<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+
request.getServerPort()+path+"/";%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="org.json.JSONObject"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Player home page</title>
</head>
<body bgcolor="FFFFCC" topmargin="0">
	<form action="home" method="get"> 
	Retrieve information from myJSON:</br> 
	<% 
	JSONObject myJSON = (JSONObject) request.getAttribute("myJSON");
	if(myJSON!=null){
		String info = myJSON.toString();
		out.println(info); 
	}
	%>
	</form>
	
	</br>
	<form action="deposit" method="post">  
	Please enter amount to deposit:</br>
	<input type="number" name="amountDeposit" min = "0"><br>
	<input type="submit" value="Deposit">
	<input type="reset" value="Reset">
	<input type="hidden" name="token" value="<%=myJSON.get("token")%>">
	</form>
	
	<form action="withdraw" method="post">  
	Please enter amount to withdraw:</br>
	<input type="number" name="amountWithdraw" min = "0" step="100" max="<%=session.getAttribute("balance")%>"><br>
	<input type="submit" value="Withdraw">
	<input type="reset" value="Reset">
	<input type="hidden" name="token" value="<%=myJSON.get("token")%>">
	</form>
	<br>
	<form action="logout" method="get">  
	<input type="submit" value="Logout">
	<input type="hidden" name="token" value="<%=myJSON.get("token")%>">
	</form>

</body>
</html>