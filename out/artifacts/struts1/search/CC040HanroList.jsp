<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.CC040HanroList.HanroListBean"%>
<%@include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>

<%
	String actionURL = "e_asproLogin/CC040HanroList";
	HanroListBean bean = (HanroListBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
%>
<html>
	<head>
		<title>
			îÃòHè⁄ç◊
		</title>
		<SCRIPT type="text/javascript">
			function Close(){
				window.close();
			}
		</SCRIPT>
	</head>
	<BODY>
		<CENTER>
			<TABLE>
				<TR>
					<TD ALIGN="center" BGCOLOR="white">
						<form action="<%=actionURL%>" method="post" id="CC040Form">
							<input type="hidden" name="processType" value="<%=bean.getProcessType() %>"/>
							<input type="hidden" name="itemNo" value="<%=bean.getItemNo() %>"/>
						</form>
						<%if(bean.getDataList() != null && bean.getDataList().size() > 0){ %>
							<TABLE CLASS="lt" width="250" style="border-width:2px 2px 2px 2px;">
								<COL width="30">
								<COL width="200">
								<TR CLASS="head">
									<TH>No</TH>
									<TH>îÃòH</TH>
								</TR>
								<%for(int i=0;i<bean.getDataList().size();i++){ %>
									<%HashMap<String,String> map = (HashMap<String,String>)bean.getDataList().get(i); %>
									<TR>
										<!--No-->
										<TD align="center" title="<%=StringUtil.nvl(map.get("SC_SORT_NO"))%>">
											<nobr><%=StringUtil.nvl(map.get("SC_SORT_NO"))%></nobr>
										</TD>
										<!--îÃòH-->
										<TD align="left" title="<%=StringUtil.nvl(map.get("SC_KBN_NM"))%>">
											<nobr><%=StringUtil.nvl(map.get("SC_KBN_NM"))%></nobr>
										</TD>
									</TR>
								<%}%>
							</TABLE>
						<%} %>
						<%if(bean.getDataList().size() == 0 ){ %>
							<font size="2">
								<%=Properties.getMsg("HA.MSG.001")%>
							</font>
						<%} %>
						<app:pageinit/>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="250">
				<COL width="230">
				<TR>
					<TD  align="center">
						<input type="button" value="ï¬Ç∂ÇÈ" onclick="Close()">
					</TD>
				</TR>
			</TABLE>
		</CENTER>
	</BODY>
</html>