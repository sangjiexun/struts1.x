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
		<TITLE>�p�X���[�h�ύX</TITLE>
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
	<div class=pt>�p�X���[�h�ύX</div><br>
	<b style="color: red"><%=msg%></b><br>
	<form method="post"  action="passwordEntry" id="_Form">
		<input type="hidden" name="coNo" id="_coNo" value="<%=bean.getCoNo()%>"/>
		<input type="hidden" name="userCd" id="_userCd" value="<%=bean.getUserCd()%>"/>
		<input type="hidden" name="sessionId" id="_sessionId" value="<%=bean.getSessionId()%>"/>
		<input type="hidden" name="compString" id="_compString" value="<%=bean.getCompString()%>"/>
		<TABLE border="0">
			<TR>
				<TD ALIGN="left" NOWRAP>�p�X���[�h�̗L���������؂�Ă��܂��B</TD>
			</TR>
			<TR>
				<TD ALIGN="left" NOWRAP>���݂̃p�X���[�h�y�ѐV�����p�X���[�h����͂��ă{�^�����N���b�N���Ă��������B</TD>
			</TR>
			<TR>
				<TD ALIGN="center" NOWRAP></TD>
			</TR>
			<TR>
				<TH ALIGN="center" NOWRAP>���݂̃p�X���[�h����͂��Ă��������B</TH>
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
				<TH ALIGN="center" NOWRAP>�V�����p�X���[�h����͂��ĉ������B</TH>
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
				<TH ALIGN="center" NOWRAP>�m�F�ׁ̈A������x�V�����p�X���[�h����͂��ĉ������B</TH>
			</TR>
			<TR>
				<TD ALIGN="center">
					<input type="password" name="checkPassword" size="20" maxlength="8" Class="code" onkeypress="_chkInp()"/>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="center" NOWRAP></TD>
			</TR>
			<!-- �p�X���[�h�ύX -->
			<TR BGCOLOR="#ffcccc">
				<TD ALIGN="center" NOWRAP>
					<input type="button" onClick="Update();" value="�p�X���[�h�ύX" />
				</TD>
			</TR>
		</TABLE>
	</form>
	<app:pageinit />
</BODY>
</html>