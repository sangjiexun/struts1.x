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
 �����f�����|�����܂��đ�ϐ\���󂠂�܂���B<br>
���O�A�E�g���u���E�U����Ă����Ԃ������Ă���A�ēx���O�C�������肢�������܂��B<br>
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
