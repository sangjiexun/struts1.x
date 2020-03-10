<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.io.*"%>
<HTML>
 <HEAD>
  <TITLE>ERROR</TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">
 </HEAD>
 <BODY>
  <br>
<font color="red">
 ご迷惑をお掛けしまして大変申し訳ありません。<br>
ログアウトしブラウザを閉じてお時間をおいてから、再度ログインをお願いいたします。<br>
</font>
<pre width="600">
<%
	
	Exception exp = (Exception) request.getAttribute("error");
	
	PrintWriter ps = response.getWriter();
	ps.write("<!--");
	exp.printStackTrace(ps);
	ps.write("-->");
%>
</pre>
 </BODY>
</HTML>
