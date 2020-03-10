<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry.PasswordEntryBeanInterface"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%
PasswordEntryBeanInterface bean = (PasswordEntryBeanInterface) request.getAttribute("bean");
	String url = Constants.URL_BASE	+ "/e_asproLogin/passwordEntry";
	String img = Constants.DOC_ROOT + "/img/head_kefir_master.gif";
	String msg = StringUtil.nvl(bean.getStrErrMsg());
%>

<html>
	<head>
		<TITLE>パスワード変更</TITLE>
		<%@ include file="/taglibs.jsp"%>
		<SCRIPT type="text/javascript">
			function Update() {
				$$("_Form").action = "<%=url%>";
				$$("_Form").submit();
				btnDisabled();
			}
		</SCRIPT>
	</head>
<BODY>
	<IMG SRC="<%=img%>" ALT="" ALIGN="absmiddle" BORDER="0"/>
	<div class=pt>パスワード変更</div><br>
	<b style="color: red"><%=msg%></b><br>
	<form method="post"  action="passwordEntry" id="_Form">
		<input type="hidden" name="coNo" id="_coNo" value="<%=bean.getCoNo()%>"/>
		<input type="hidden" name="userCd" id="_userCd" value="<%=bean.getUserCd()%>"/>
		<input type="hidden" name="sessionId" id="_sessionId" value="<%=bean.getSessionId()%>"/>
		<input type="hidden" name="compString" id="_compString" value="<%=bean.getCompString()%>"/>
		<TABLE border="0">
			<TR>
				<TD ALIGN="left" NOWRAP>パスワードの有効期限が切れています。</TD>
			</TR>
			<TR>
				<TD ALIGN="left" NOWRAP>現在のパスワード及び新しいパスワードを入力してボタンをクリックしてください。</TD>
			</TR>
			<TR>
				<TD ALIGN="center" NOWRAP></TD>
			</TR>
			<TR>
				<TH ALIGN="center" NOWRAP>現在のパスワードを入力してください。</TH>
			</TR>
			<TR>
				<TD ALIGN="center">
					<input type="password" name="nowPassword" size="20" maxlength="8" Class="code" onkeypress="_chkInp()"/>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="center" NOWRAP></TD>
			</TR>
			<TR>
				<TH ALIGN="center" NOWRAP>新しいパスワードを入力して下さい。</TH>
			</TR>
			<TR>
				<TD ALIGN="center">
					<input type="password" name="newPassword" size="20" maxlength="8" Class="code" onkeypress="_chkInp()"/>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="center" NOWRAP></TD>
			</TR>
			<TR>
				<TH ALIGN="center" NOWRAP>確認の為、もう一度新しいパスワードを入力して下さい。</TH>
			</TR>
			<TR>
				<TD ALIGN="center">
					<input type="password" name="checkPassword" size="20" maxlength="8" Class="code" onkeypress="_chkInp()"/>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="center" NOWRAP></TD>
			</TR>
			<!-- パスワード変更 -->
			<TR BGCOLOR="#ffcccc">
				<TD ALIGN="center" NOWRAP>
					<input type="button" onClick="Update();" value="パスワード変更" />
				</TD>
			</TR>
		</TABLE>
	</form>
	<app:pageinit />
</BODY>
</html>