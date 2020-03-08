<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@include file="/taglibs.jsp" %>
<%-- added by K.Okuda 2015/04/27 start --%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.Login.LoginServlet"%>
<%@page import="java.util.Map" %>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.*" %>
<%@page import="jp.co.tcc.ecs.e_asproLogin.HA070Menu.MenuService" %>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%
	String passwordAlertMsg = (String) request.getSession().getAttribute(LoginServlet.ATTRIBUTE_KEY_PASSWORD_ALERT_MSG);
	request.getSession().removeAttribute(LoginServlet.ATTRIBUTE_KEY_PASSWORD_ALERT_MSG);
	boolean showDialogFlg = StringUtils.isNotEmpty(passwordAlertMsg);
	User user = (User) request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
	Map<String,String> dwhStatus = MenuService.getDwhStatus(Integer.parseInt(StringUtil.nvl(user.getPatternNo(),"0")));
%>
<%-- added by K.Okuda 2015/04/27 end --%>

<HTML LANG="ja">
	<HEAD>
		<TITLE>TOPメニュー</TITLE>
<%-- added by K.Okuda 2015/04/27 start --%>
		<script type="text/javascript">
			function showAlert(flg) {
				if (flg) {
					alert('<%=passwordAlertMsg%>');
				}
			}
		</script>
		<style type="text/css">
			.topTitile {
				font-size:14px;
				margin-left:10px;
				font-weight:bold;
			}
			table.topHeader{ 
				border	: none; 
				border-collapse : collapse;
				table-layout	: fixed;
			}
			table.topHeader th { 
				background-color: #ffffff;
				border-left:none; border-right:none; border-top:2px solid #000000; border-bottom:2px solid #000000; 
				font-weight: bold;
				font-size		: 16px;
				text-align		: left;
				color		: #00008B;
				padding 		: 5px; 
				
			}
			table.topHeader td { 
				font-size		: 12px;
				margin			: 0; 
				padding 		: 10px;
				background-color:white;
				border-style	: none solid solid none; 
				border-width	: 0px; 
				border-color	: #666666;
				text-overflow	: ellipsis;
				white-space 	: nowrap;
				overflow		: hidden;
			}
		</style>
<%-- added by K.Okuda 2015/04/27 end --%>
	</HEAD>
<% if (dwhStatus == null){ %>
	<BODY ONLOAD="showAlert(<%=showDialogFlg%>);" style="margin:35px">
		左メニューから選択してください。
<%}else{ %>
	<BODY ONLOAD="showAlert(<%=showDialogFlg%>);" style="margin:10px">
		
	<div style="margin-right:0px;">
		<BR>
		<TABLE WIDTH="750" class="topHeader">
			<TR>
				<TH NOWRAP>情報分析 データ取得状況</TH>
			</TR>
			<TR BGCOLOR="white">
				<TD>
					<span class="topTitile">
						<IMG SRC="<%= Constants.DOC_ROOT%>/img/swith40.gif" ALT="" ALIGN="absmiddle" BORDER="0"><%=StringUtil.nvl(dwhStatus.get("TITLE1")) %>
					</span>
					<br/><br/>
					<span style="font-size:14px;margin-left:30px;"><%=StringUtil.nvl(dwhStatus.get("COL1")) %>
						<b style="border-bottom:#33CCFF Solid 2px;"> <%=StringUtil.nvl(dwhStatus.get("YMD1")) %> </b>
					</span>
				</TD>
			</TR>
			<TR BGCOLOR="white">
				<TD></TD>
			</TR>
			<TR BGCOLOR="white">
				<TD>
					<span class="topTitile">
						<IMG SRC="<%= Constants.DOC_ROOT%>/img/swith40.gif" ALT="" ALIGN="absmiddle" BORDER="0"><%=StringUtil.nvl(dwhStatus.get("TITLE2")) %>
					</span>
					<br/><br/>
					<span style="font-size:14px;margin-left:30px;"><%=StringUtil.nvl(dwhStatus.get("COL1")) %>
						<b style="border-bottom:#33CCFF Solid 2px;"> <%=StringUtil.nvl(dwhStatus.get("YMD1")) %> </b>
					</span>
					<br/>
					<span style="font-size:14px;margin-left:30px;"><%=StringUtil.nvl(dwhStatus.get("COL2")) %>
						<b style="border-bottom:#33CCFF Solid 2px;"> <%=StringUtil.nvl(dwhStatus.get("YMD2")) %> </b>
					</span>
				</TD>
			</TR>

		</TABLE>
	</div>
<%} %>
</BODY>
</HTML>