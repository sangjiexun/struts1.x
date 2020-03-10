<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<%
User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
int intCoNo = user.getCoNo();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>main</title>
</head>
<body>
<%if (0==intCoNo || 3==intCoNo){ %>
該当ユーザーは出荷データ検索画面を使用する権限がありません。
<%} else {%>
該当ユーザーは出荷指示データ入力画面を使用する権限がありません。
<%} %>
</body>
</html>