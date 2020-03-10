<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System Properties</title>
</head>
<body>
Just For Test System Properties:
<table border="1">
	<tr>
		<td>Scheme<br></td>
		<td><%=request.getScheme()%><br></td>
	</tr>
	<tr>
		<td>ServerName<br></td>
		<td><%=request.getServerName()%><br></td>
	</tr>
	<tr>
		<td>ServerPort<br></td>
		<td><%=request.getServerPort()%><br></td>
	</tr>
	<tr>
		<td>ContextPath<br></td>
		<td><%=request.getContextPath()%><br></td>
	</tr>
	<tr>
		<td>Protocol<br></td>
		<td><%=request.getProtocol()%><br></td>
	</tr>
	<tr>
		<td>LocalAddr<br></td>
		<td><%=request.getLocalAddr()%><br></td>
	</tr>
	<tr>
		<td>RequestURI<br></td>
		<td><%=request.getRequestURI()%><br></td>
	</tr>
	<tr>
		<td>RequestURL<br></td>
		<td><%=request.getRequestURL().toString()%><br></td>
	</tr>
<%
Properties properties = System.getProperties();
for (Enumeration e = properties.propertyNames() ; e.hasMoreElements() ;) {
     String key = (String)e.nextElement();
     String value =  properties.getProperty(key);
%>
	<tr>
		<td><%=key%><br></td>
		<td><%=value%><br></td>
	</tr>
<%
 }
%>
</table>
</body>
</html>