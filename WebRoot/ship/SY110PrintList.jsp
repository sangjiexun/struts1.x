<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproShip.SY110PrintList.PrintListBean"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.CommonService"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.tag.SelectTag.*"%>
<%
	PrintListBean bean = (PrintListBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
	String strErrMsg = StringUtil.nvl(bean.getStrErrMsg());
%>
<html>
	<head>
		<TITLE>�o�׎w���f�[�^�o��</TITLE>
		<%@ include file="/taglibs.jsp" %>
		
		<SCRIPT type="text/javascript">
			//search
	 		function Search(){
				$$("_Form").action="<%=Constants.URL_BASE%>/e_asproShip/SY110PrintList"
				$$("_processType").value = "<%=Constants.PAGE_SEARCH%>"
				$$("_Form").submit();
				btnDisabled();
			}
			
			
			
			
			//�u����v�{�^������������
			function Print(id1){
			<%-- 	$$("_Form").action="<%=Constants.URL_BASE%>/e_asproNyuka/IN070NyukaYojituList";
				$$("_processType").value="<%=Constants.PAGE_DOWNLOAD%>";
				$$("_Form").submit(); --%>
				document.getElementById("print"+id1).style.color = "black";
		        document.getElementById("print"+id1).style.backgroundColor = "#c5d9f1";
			}
			//�uCSV�o�́v�{�^������������
				
	 		function Download(id2) {
	 			document.getElementById("Download"+id2).style.color = "black";
		        document.getElementById("Download"+id2).style.backgroundColor = "#f2dcdb";
		        $$("_Form").action="<%=Constants.URL_BASE%>/e_asproNyuka/IN070NyukaYojituList";
				$$("_processType").value="<%=Constants.PAGE_DOWNLOAD%>";
				$$("_Form").submit(); --%>
	 		
				<%-- if (csv_down_kbn == '6') {
					$$("_header_csv_out").value = $$("_header_csv_out_noform").value;
				}
		 		if (csv_down_kbn == '7') {
					$$("_list_csv_out").value = $$("_list_csv_out_noform").value;
				}
				if (csv_down_kbn == '71') {
					$$("_toi_csv_out").value = $$("_toi_csv_out_noform").value;
				}

	 			$$("_processType").value = "<%=Constants.PAGE_DOWNLOAD%>";
	 			$$("_csv_down_kbn").value = csv_down_kbn;
				$$("_Form").action = "<%=Constants.URL_BASE%>/e_asproShip/SY160ShipList";
				$$("_Form").submit();

				$$("_btnDownload1").disabled=true;
				$$("_btnDownload2").disabled=true;
				$$("_btnDownload71").disabled=true;
				setTimeout('enableDownLoad()', '3000'); --%>
			}
		
			function PICALL(id3){
			document.getElementById("PICALL"+id3).style.color = "black";
		        document.getElementById("PICALL"+id3).style.backgroundColor = "#d8e4bc";
			}
			function PICORD(id4){
			document.getElementById("PICORD"+id4).style.color = "black";
		        document.getElementById("PICORD"+id4).style.backgroundColor = "#ccc0da";
			}
			
	 			function NOUHIN(id5){
				
				document.getElementById("NOUHIN"+id5).style.color = "black";
		        document.getElementById("NOUHIN"+id5).style.backgroundColor = "#fcd5b4";

			}
			
			
		</SCRIPT>
	</head>
	<BODY>
		<IMG SRC="<%=Constants.DOC_ROOT%>/img/head_kefir_master.gif" ALT="" ALIGN="absmiddle" BORDER="0">
		
					
							
		
		<div class=pt>�o�׎w���f�[�^�o��</div><br>
			<b style="color:red"><%=strErrMsg%></b>
			<br>
			<form action="<%=Constants.URL_BASE%>/e_asproShip/SY110PrintList"  id="_Form" method="post">
				<input type="hidden" name="userNo" id="_user_No" value=""/>
				<input type="hidden" name="strFromPageId" value="<%=bean.getStrFromPageId() %>">
				<input type="hidden" name="searchKbn">
				<input type="hidden" name="processType" id="_processType">
				<input type="hidden" name="coNo" id="_co_No" value="<%=bean.getCoNo() %>"/>
				<TABLE class="nb">
					<TR>
						<TD>
							<TABLE class="sf" style="width:600">
								<col width="100">
								<col width="230">
								<col width="100">
								<col width="170">
								<TR>
									<TH class="MUST">�o�׎w���m���</TH>
									<TD>
										<app:calender
														startYmdName="syukkaOkYmdFrom"
														startYmdId="_syukka_ok_ymd_from"
														startYmdvalue="<%=bean.getSyukkaOkYmdFrom()%>"
														msg="�o�ח\���"
														/>
														~
														<app:calender
														startYmdName="syukkaOkYmdTo"
														startYmdId="_syukka_pk_ymd_to"
														startYmdvalue="<%=bean.getSyukkaOkYmdTo()%>"
														msg="�o�ח\���"
														/>
														
							  		</TD>
							  		<TH>�q�ɖ�</TH>
									<TD colspan="3">
										<%if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {%>
											<%=CommonService.getSoukoNm(StringUtil.nvl(bean.getLoginUser().getCoNo()), bean.getSouKoCd())%>
										<%} else { %>
											<% List<Option> resultWhNm = CommonService.getSoukoOptionList(StringUtil.nvl(bean.getLoginUser().getCoNo()), false, bean.getSisya_cd());%>
											<app:select
												name="souKoCd"
												id="whCd"
												options="<%=resultWhNm%>"
												addBlank="true"
												selectedValues="<%=bean.getSouKoCd() %>"/>
										<%} %>
							  		</TD>
								</TR>
								<TR>
									<TH>�o�͏�</TH>
									<TD colspan="5">
										<select id="_downFlg" name="downFlg">
											<%if (bean.getDownFlgList() != null){%>
											<%	for (Map<String, String> itemDataDownFlg : bean.getDownFlgList()){%>
											<option value="<%=StringUtil.nvl(itemDataDownFlg.get("KEY"))%>" <%=(StringUtil.nvl(itemDataDownFlg.get("KEY")).equals(StringUtil.nvl(bean.getDownFlg())))?"selected":""%> ><%=StringUtil.nvl(itemDataDownFlg.get("VALUE"))%></option>
											<%	}%>
											<%}%>
										</select>

							  		</TD>
								</TR>
						</TABLE>
					</TD>
					<TD valign="bottom">
						<TABLE>
							<TR>
								<TD>
									<%-- ����--%>
									<input <app:PowerCheck fromId="<%=bean.getStrFromPageId() %>" baseId="SY110" value="<%=Constants.POWER_SEARCH %>"/> onclick="Search();" type="button" value="����"/>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
		</form>
		<hr align="left" width="740">
		<%if(true){%>
			<%List list = bean.getList(); %>
			<%if(list.size() > 0){%>
				<app:PageFeed />
				<TABLE CLASS="lt" width="760">
 					<COL width="40">
 					<COL width="100">
 					<COL width="80">
 					<COL width="120">
 					<COL width="90">
 					<COL width="160">
 					<COL width="140">
 					<COL width="120">
 					<COL width="120">
					<TR CLASS="head">
   						<TH rowspan="2">NO</TH>
 						<TH rowspan="2">�o�׎w���m���</TH>
 						<TH rowspan="2">�o�׎w����</TH>
 						<TH rowspan="2">�x��</TH>
 						<TH rowspan="2">�q�ɖ�</TH>
 						<TH colspan="4">CSV�o��/���</TH>
					</TR>
					<TR CLASS="head">
						
 						<TH>�o�׎w���f�[�^�o��</TH>
 						<TH>���ʃs�b�L���O���X�g</TH>
 						<TH>��Ǝw����</TH>
 						<TH>�[�i��</TH>
					</tr>
						<%for(int i=0;i<list.size();i++){ %>
							<%HashMap item = (HashMap) list.get(i); %>
							<TR>
								<!--NO-->
								<TD align="center">
									<nobr><%=StringUtil.nvl(i + 1, "&nbsp;")%></nobr>
								 </TD>
								
								 <!--�o�׎w���m���-->
								<TD align="left" title="<%=StringUtil.nvl(item.get("SYUKKA_OK_YMD"))%>">
									<nobr><%=StringUtil.nvl(item.get("SYUKKA_OK_YMD"), "&nbsp;")%></nobr>
								</TD>
								
								<!--�o�׎w����-->
								<TD align="right" title="<%=StringUtil.nvl(item.get("SYUKKA_OK_CNT"))%>">
									<a href="<%="SY160ShipList?processKbn=2&syukka_Id="
												+ StringUtil.nvl(item.get("SYUKKA_ID"))%>">
												<%=StringUtil.nvl(item.get("SYUKKA_OK_CNT"))%></a>
								</TD>
								
								<!-- �x�� -->
								<TD align="center" title="<%=StringUtil.nvl(item.get("SISYA_NAME"))%>">
									<nobr><%=StringUtil.nvl(item.get("SISYA_NAME"), "&nbsp;")%></nobr>
								</TD>
								
								<!--�q�ɖ�-->
								<TD align="center" title="<%=StringUtil.nvl(item.get("WH_NM1"))%>">
									<nobr><%=StringUtil.nvl(item.get("WH_NM1"), "&nbsp;")%></nobr>
								</TD>
								
								<!--�o�׎w��CSV�o��-->
								
								<%if(bean.getDownFlg()=="1") { %>
									<TD align="left" title="<%=StringUtil.nvl(item.get("PATTERN_NO"))%>">
										
										<input id="print<%=StringUtil.nvl(i + 1, "&nbsp;")%>" type="button" style="width:80px;" value="�����" onclick="Print(<%=StringUtil.nvl(i + 1, "&nbsp;")%>);"/>&nbsp;&nbsp;�o�͉�:<nobr><%=StringUtil.nvl(item.get("PRINT_CNT"), "&nbsp;")%></nobr>
									</TD>
								<%} else if( bean.getDownFlg()=="2" ){ %>
									<TD align="left" title="<%=StringUtil.nvl(item.get("CSV_DOWN_KBN"))%>">
										<input id="Download<%=StringUtil.nvl(i + 1, "&nbsp;")%>" type="button" style="width:80px;" value="CSV�o��" onclick="Download(<%=StringUtil.nvl(i + 1, "&nbsp;")%>);"/>&nbsp;&nbsp;�o�͉�:<nobr><%=StringUtil.nvl(item.get("DOWN_CNT"), "&nbsp;")%></nobr>
									</TD>
								<%} else {%>
									<%-- <TD align="left" title="<%=StringUtil.nvl(item.get("CSV_DOWN_KBN"))%>">
										<input type="button" style="width:80px;" disabled value="CSV�o��"/>
									</TD> --%>
									
									<TD align="left" title="<%=StringUtil.nvl(item.get("CSV_DOWN_KBN"))%>">
										<input id="Download<%=StringUtil.nvl(i + 1, "&nbsp;")%>" type="button" style="width:80px;" value="CSV�o��" onclick="Download(<%=StringUtil.nvl(i + 1, "&nbsp;")%>);"/>&nbsp;&nbsp;�o�͉�:<nobr><%=StringUtil.nvl(item.get("DOWN_CNT"), "&nbsp;")%></nobr>
									</TD>
									
								<%} %>
								<!--���ʃs�b�L���O���X�g-->
								<%if("1".equals(StringUtil.nvl(item.get("SHIP_SIJI_KBN")))) { %>
									<TD align="center" title="���ʃs�b�L���O���X�g">
										<input  type="button"  disabled value="���ʃs�b�L���O���X�g"/>
									</TD>
								<%} else { %>
									<TD align="center" title="���ʃs�b�L���O���X�g">
										<input id="PICALL<%=StringUtil.nvl(i + 1, "&nbsp;")%>" type="button"  onclick="PICALL(<%=StringUtil.nvl(i + 1, "&nbsp;")%>);" value="���ʃs�b�L���O���X�g"/>
									</TD>
								<%} %>
								<!--��Ǝw����-->
								<%if("1".equals(StringUtil.nvl(item.get("SHIP_SIJI_KBN")))) { %>
									<TD align="center" title="��Ǝw����">
										<input type="button" disabled value="��Ǝw����"/>
									</TD>
								<%} else { %>
									<TD align="center" title="��Ǝw����">
										<input id="PICORD<%=StringUtil.nvl(i + 1, "&nbsp;")%>" type="button" onclick="PICORD(<%=StringUtil.nvl(i + 1, "&nbsp;")%>);"  value="��Ǝw����"/>
									</TD>
								<%} %>
								<!--�[�i��-->
								<%if("1".equals(StringUtil.nvl(item.get("SHIP_SIJI_KBN")))) { %>
									<TD align="center" title="�[�i��">
										<input type="button" disabled value="�[�i��"/>
									</TD>
								<%} else { %>
									<TD align="center" title="�[�i��">
										<input id="NOUHIN<%=StringUtil.nvl(i + 1, "&nbsp;")%>" type="button"  onclick="NOUHIN(<%=StringUtil.nvl(i + 1, "&nbsp;")%>);" value="�[�i��"/>
									</TD>
								<%} %>
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
		<app:pageinit/>
	</BODY>
</html>