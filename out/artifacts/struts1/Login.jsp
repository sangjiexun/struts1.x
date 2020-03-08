<%--<%@ page language="java" pageEncoding="UTF-8"%>--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page language="java" contentType="text/html; charset=windows-31j"
		 pageEncoding="windows-31j"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.Login.LoginBean"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%
	String actionURL = Constants.URL_BASE /*+ "/login"*/;
	if (!request.isRequestedSessionIdFromCookie()) {
		actionURL = response.encodeURL(actionURL);
	}
	String strForwordFlg = Constants.LOGIN_SUBMIT;
	LoginBean bean = (LoginBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
	String bgcolor = bean.getColor1();
	if(Constants.EMPTY.equals(StringUtil.nvl(bean.getCompString()))){
		bgcolor = "#004388";
	}
%>
<html>
<title><%=bean.getCompConfNm() %></title>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/ecs.js" charset="Shift_JIS"></script>
<script type="text/javascript" charset="Shift_JIS">
	function login(){
		var v_loc = window.location.href;
		var pos = v_loc.indexOf("compString=");
		if (pos!=-1){
			var start = pos + 11;
			var end = v_loc.indexOf("&",start);
			if (end == -1){
				end = v_loc.length;
			}
			var v_compValue = v_loc.substring(start, end);
			document._loginForm.compString.value = v_compValue;
			document.cookie = "compString="+v_compValue;
		}
		document._loginForm.submit();

	}

	function changeImages() {
		d = document;
		if (d.images) {
			var img;
			for (var i=0; i<changeImages.arguments.length; i+=2) {
				img = null;
				if (d.layers) {img = findElement(changeImages.arguments[i],0);}
				else {img = d.images[changeImages.arguments[i]];}
				if (img) {img.src = changeImages.arguments[i+1];}
			}
		}
	}
	function pageInit(){
		document.body.onkeydown=function(){try{if(event.srcElement.type == "password" && event.keyCode==13){login(); return};if(event.keyCode==13)event.keyCode=9;}catch(e){}}
		document.getElementById("userCd").focus();
	}
</script>
</head>
	<%--<body>
		<html:form action="/userLogin">
			&lt;%&ndash;@declare id="userName"&ndash;%&gt;<label for="userName">userName:</label><html:text property="userName" maxlength="6"/><html:errors property="userName"/><br/>
			&lt;%&ndash;@declare id="password"&ndash;%&gt;<label for="password">password:</label><html:password property="password" maxlength="6"/><html:errors property="password"/><br/>


			<span>默认用户名/密码：struts/struts</span>
			<html:reset/>&nbsp;
			<html:submit/>&nbsp;<html:cancel/>

		</html:form><html:errors></html:errors>
	</body>--%>
<BODY BACKGROUND="<%=Constants.DOC_ROOT %>/img/sub_menu_bg.gif" onload="pageInit();">
<DIV ALIGN="center">
	<form name="_loginForm" action="<%=actionURL%>" method="post">
		<%
			String strErrMsg ="000000000";
			if (bean!=null){
				strErrMsg = StringUtil.nvl(bean.getStrErrMsg());
			} else {
				bean = new LoginBean();
			}

		%>
		<b style="color: red"><%=strErrMsg%></b>
		<br>
		<CENTER>
			<TABLE WIDTH="385" BORDER="0" CELLSPACING="0" CELLPADDING="0">
				<TR HEIGHT="15">
					<TD VALIGN="top" BGCOLOR="<%=bgcolor%>" WIDTH="15" HEIGHT="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/corner11.gif" HEIGHT="15" WIDTH="15" BORDER="0"></TD>
					<TD ALIGN="center" BGCOLOR="<%=bgcolor%>" HEIGHT="15"><BR>
					</TD>
					<TD VALIGN="top" BGCOLOR="<%=bgcolor%>" WIDTH="15" HEIGHT="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/corner12.gif" HEIGHT="15" WIDTH="15" BORDER="0"></TD>
				</TR>
				<TR>
					<TD BGCOLOR="<%=bgcolor%>" WIDTH="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/spacer.gif" HEIGHT="5" WIDTH="5" BORDER="0"></TD>
					<TD ALIGN="center" BGCOLOR="<%=bgcolor%>">
						<TABLE WIDTH="350" BORDER="0" CELLSPACING="0" CELLPADDING="2">
							<TR>
								<TD ALIGN="center" BGCOLOR="white" WIDTH="150"><BR>
									<BR>
									<IMG SRC="<%=Constants.DOC_ROOT %>/img/logo.gif" ALT="" WIDTH="155" HEIGHT="45" BORDER="0"><BR>
									<BR>
									<BR>
								</TD>
								<TD ALIGN="center" BGCOLOR="white"><BR>
									<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="4">
										<TR>
											<TD><B><FONT SIZE="2">ID</FONT></B></TD>
											<TD><input type="text" istyle="3" format="*x" MODE="alphabet"
													   name="userCd" size="10" maxlength="10" id="userCd"
													<%--   value="<%=bean.getUserCd()%>"  style="ime-mode:disabled">--%>
											</TD>
										</TR>
										<TR>
											<TD><B><FONT SIZE="2">パスワード</FONT></B></TD>
											<TD><input type="password" istyle="3" format="*x" MODE="alphabet"
													   name="password" size="10" maxlength="256"></TD>
										</TR>
									</TABLE>
									<HR NOSHADE SIZE="1">
									<BR>
									<INPUT TYPE="button" NAME="submit2" VALUE="ログイン" onclick="login();"><BR>
									<BR>
								</TD>
							</TR>
						</TABLE>
						<IMG SRC="<%=Constants.DOC_ROOT %>/img/spacer.gif" HEIGHT="5" WIDTH="5" BORDER="0"></TD>
					<TD BGCOLOR="<%=bgcolor%>" WIDTH="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/spacer.gif" HEIGHT="5" WIDTH="5" BORDER="0"></TD>
				</TR>
				<TR HEIGHT="15">
					<TD BGCOLOR="<%=bgcolor%>" WIDTH="15" HEIGHT="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/corner13.gif" HEIGHT="15" WIDTH="15" BORDER="0"></TD>
					<TD BGCOLOR="<%=bgcolor%>" HEIGHT="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/spacer.gif" HEIGHT="5" WIDTH="5" BORDER="0"></TD>
					<TD BGCOLOR="<%=bgcolor%>" WIDTH="15" HEIGHT="15"><IMG SRC="<%=Constants.DOC_ROOT %>/img/corner14.gif" HEIGHT="15" WIDTH="15" BORDER="0"></TD>
				</TR>
			</TABLE>
			<FONT SIZE="2">※）ID,Passwordは半角英数で入力してください。<BR>
				また、半角大文字と半角小文字は別文字扱いとなりますので、入力時ご注意願います。<BR>
			</FONT>
		</CENTER>
		<table>
			<tr>
				<td colspan="2">
					<input type="hidden" name="coNo" value="<%=bean.getCoNo()%>">
					<input type="hidden" name="strURL" value="<%=bean.getStrURL()%>">
					<input type="hidden" name="strForwordFlg" value="<%=strForwordFlg%>">
					<input type="hidden" name="compString" value="<%=bean.getCompString()%>">
				</td>
			</tr>
		</table>
	</form>
</DIV>
</BODY>
</html>

