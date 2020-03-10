<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@ include file="/taglibs.jsp" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.CommonService"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP.ShipFixUPBean"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.tag.SelectTag.Option"%>

<%
	ShipFixUPBean bean = (ShipFixUPBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
	String strErrMsg = StringUtil.nvl(bean.getStrErrMsg());
%>
<html>
	<head>
		<TITLE>
			�o�׎���CSV�o�^
		</TITLE>
		<SCRIPT type="text/javascript">

			//�A�b�v���[�h
			function upload(){
				if($("#csvFile").val().length == 0){
					$("#errMsg").html("<%=Properties.getMsg("HA.ERROR.001", new String[] {"�t�@�C��"}, true)%>");
					return;
				}
				//�t�@�C���i�t�@�C���g���q�j
				if($("#csvFile").val().length < 5){
					$("#errMsg").html("<%=Properties.getMsg("HA.ERROR.104",false)%>");
					return;
				}else{
					var fileType = $("#csvFile").val().substr($("#csvFile").val().length -4);
					if(fileType.toUpperCase() != ".CSV"){
						$("#errMsg").html("<%=Properties.getMsg("HA.ERROR.104",false)%>");
						return;
					}
				}

				if(confirm("<%=Properties.getMsg("HA.MSG.027")%>")){
					$("#csvUploadLayout").hide();
					$("#csvUploadWaitLayout").show();

					$("#_processType").val("<%=Constants.PAGE_UPLOAD%>");
		 	    	$("#_searchKbn").val("2");
					$("#_Form").prop("action","<%=Constants.URL_BASE%>/e_asproShip/SY141ShipFixUP");
					$("#_Form").submit();
					btnDisabled();
				}
			}
		</SCRIPT>
	</head>
	<BODY>
		<img src="<%=Constants.DOC_ROOT%>/img/head_kefir_syukka.gif" alt="" align="absmiddle" border="0">
		<div class=pt>�o�׎���CSV�o�^</div>
		<br><br>
		<b style="color:red" id="errMsg"><%=strErrMsg%></b>

		<div id="csvUploadLayout">
			<FORM action="" name="_From" id="_Form" method="post" enctype="multipart/form-data">
				<input type="hidden" name="processType" id="_processType">
				<input type="hidden" name="searchKbn" id="_searchKbn" value="2"/>

				<app:div hiddenFlagWidth="356">
					<TABLE class="nb" style="width:400">
						<TR>
							<TD>
								<TABLE class="sf" style="width:400">
									<TR>
										<TH class="must" style="width: 110">�q�ɖ�</TH>
										<TD style="width:290">
											<%if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {%>
												<%=CommonService.getSoukoNm(StringUtil.nvl(bean.getLoginUser().getCoNo()), bean.getWhCd())%>
											<%} else { %>
												<% List<Option> resultWhNm = CommonService.getSoukoOptionList(StringUtil.nvl(bean.getLoginUser().getCoNo()), true);%>
												<app:select
													name="whCd"
													id="whCd"
													options="<%=resultWhNm%>"
													selectedValues="<%=bean.getWhCd() %>"/>
											<%} %>
										</TD>
									</TR>
									<TR>
										<TH class="must" style="width: 110">�p�^�[���I��</TH>
										<TD style="width:290">
											<app:select2 name="patternNo"
												id="patternNo"
												list = "<%=bean.getPatternNoList() %>"
												value="<%=bean.getPatternNo() %>"
												listKey ="KEY"
												listValue = "VALUE"
											/>
										</TD>
									</TR>
									<TR>
										<TH class="must" style="width: 110">�t�@�C��</TH>
										<TD style="width:290">
											<input type="file" name="csvFile" id="csvFile" size="45" style="width:250"/>
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>

					<TABLE class="nb" style="width: 408">
						<TR BGCOLOR="#ffcccc">
							<TD ALIGN="center">
								<%-- �A�b�v���[�h --%>
								<input type="button" onClick="upload();" value="�o�^����" <app:PowerCheck baseId="SY141" value="<%=Constants.POWER_CSVUPLOAD%>"/>/>
							</TD>
						</TR>
					</TABLE>
				</app:div>
			</form>

			<%if("2".equals(bean.getSearchKbn())){%>
				<%Vector list = bean.getList(); %>
				<%if(list.size() > 0){%>
					<TABLE CLASS="lt" width="620">
							<COL width="30">
							<COL width="120">
							<COL width="80">
							<COL width="100">
							<COL width="100">
							<COL width="150">
						<TR CLASS="head">
							<TH>NO</TH>
							<TH>�o�^��������</TH>
							<TH>�o�^����</TH>
							<TH>�q�ɖ�</TH>
							<TH>�p�^�[����</TH>
							<TH>�t�@�C����</TH>
						</TR>
					<%for(int i=0;i<list.size();i++){
						HashMap item = (HashMap) list.get(i);
					%>
						<TR>
							<!--No-->
							<TD align="center">
								<%=String.valueOf(i+1) %></TD>
							<%-- �o�^�������� --%>
							<TD title="<%=StringUtil.nvl(item.get("MAKE_END_DATE"))%>">
								<nobr><%=StringUtil.nvl(item.get("MAKE_END_DATE"), "&nbsp;")%></nobr></TD>
							<%-- �o�^���� --%>
							<TD align="right" title="<%=StringUtil.nvl(item.get("MEI_CNT"))%>">
								<nobr><%=StringUtil.nvl(item.get("MEI_CNT"))%></nobr></TD>
							<%-- �q�ɖ�--%>
							<TD title="<%=StringUtil.nvl(item.get("WH_NM"), "&nbsp;")%>">
								<nobr><%=StringUtil.nvl(item.get("WH_NM"), "&nbsp;")%></nobr></TD>
							<%-- �p�^�[����--%>
							<TD title="<%=StringUtil.nvl(item.get("PATTERN_NM"), "&nbsp;")%>">
								<nobr><%=StringUtil.nvl(item.get("PATTERN_NM"), "&nbsp;")%></nobr></TD>
							<%-- �t�@�C���� --%>
							<TD title="<%=StringUtil.nvl(item.get("UP_FILE_NM"), "&nbsp;")%>">
								<nobr><%=StringUtil.nvl(item.get("UP_FILE_NM"), "&nbsp;")%></nobr></TD>
						</TR>
					<%} %>
					</TABLE>
				<%} %>
				<%if(list.size() == 0){%>
					<font size="2">
						<%=Properties.getMsg("HA.MSG.001")%>
					</font>
				<%} %>
			<%} %>
		</div>
		<div id="csvUploadWaitLayout" style="display:none;">
			<div class="pt">�f�[�^����荞�ݒ��ł��B</div>
			<img src="<%=Constants.DOC_ROOT %>/img/searching1.gif" border="0" height="35" width="35" align="middle">
		</div>
	<app:pageinit tableInit="0"/>
	</BODY>
</html>