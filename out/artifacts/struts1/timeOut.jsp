<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@ include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
	.b1,.b2,.b3,.b4,.b1b,.b2b,.b3b,.b4b,.b{display:block;overflow:hidden;}
	.b1,.b2,.b3,.b1b,.b2b,.b3b{height:1px;}
	.b2,.b3,.b4,.b2b,.b3b,.b4b,.b{border-left:1px solid #CCCCCC;border-right:1px solid #CCCCCC;}
	.b1,.b1b{margin:0 5px;background:#CCCCCC;}
	.b2,.b2b{margin:0 3px;border-width:2px;}
	.b3,.b3b{margin:0 2px;}
	.b4,.b4b{height:2px;margin:0 1px;}
	.d1{background:#CCCCCC;}
	</style>
    <title>メンテナンス中です</title>
  </head>
<body background="<%=Constants.DOC_ROOT%>/img/sub_menu_bg.gif">
    <div>
        <b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b>
        <div class="b d1" align="center">
            <font style="font-size:26px;color:red; margin:0px 10px;">ただいまの時間はメンテナンス時間帯となっており、ログインすることはできません。</font>
            <%User user = (User) request.getSession().getAttribute(
					jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);%>
            <br><font style="font-size:26px;color:blue; margin:0px 10px;">ログイン時間帯:　<%=user.getUseValidTimeBegin() %>～<%=user.getUseValidTimeEnd() %></font>
        </div>
        <b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
    </div>
</body>
</html>
