<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>

<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<%@ include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%
User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
//
%>
<html>
	<head>
		<title><%=user.getTitleName() %></title>
		<app:AjaxInitTag/>
		<script type="text/javascript">
		function change_cnt(){
 	    	var url = "<%=Constants.URL_BASE%>/e_asproLogin/menu";
			var parms = "strForwordFlg="+<%=Constants.PAGE_AJAX%>;
				parms = parms+"&ajaxKbn=1";
			<app:AjaxSendRequest2Action params="parms" url="url" debug="0"/>
 	    }
		
		function setCnt(_cnt){
		try{
			window.frames[0].setCnt(_cnt);
		}catch(e){}
		}
		
	</script>
		
	</head>
	<FRAMESET rows="50,44,*" border="0" frameborder="no" framespacing="0" id="headframeset">
		<FRAME name="head" src="<%=Constants.URL_BASE%>/e_asproLogin/menu/MenuHead.jsp" noresize scrolling="no">
		<FRAME name="splitter_v" src="<%=Constants.URL_BASE%>/e_asproLogin/menu/MenuSplitterV.jsp" noresize scrolling="no">
		<FRAME name="sub" src="<%=Constants.URL_BASE%>/e_asproLogin/menu/SubFrameset.jsp" noresize>
	</FRAMESET>
</html>