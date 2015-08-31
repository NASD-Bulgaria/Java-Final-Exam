<%@page import="javafx.print.PageLayout"%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="model.*" %>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+ "://" +request.getServerName() + ":" +request.getServerPort()+path+ "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">
<title>Players list page</title>
</head>

<body>
<table border="1">
<tbody>
<tr>
	<td>LoginName</td>
	<td>Balance</td>
</tr>
<%
	List arrayList = (List)request.getAttribute("players");
	for (Iterator iter = arrayList.iterator(); iter.hasNext();) {
		Player element = (Player) iter.next();
		out.println("<tr>");
		out.println("<td>" + element.getLoginName() + "</td>");
		out.println("<td>" + element.getBalance() + "</td>");
		out.println("</tr>");
	}
%>

</tbody>
</table>
<br>
<a href="register"> Register</a>
<br>
<a href="home">Home</a></br>
</body>
</html>