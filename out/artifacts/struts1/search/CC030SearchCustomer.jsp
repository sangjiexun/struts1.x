<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.CC030SearchCustomer.SearchCustomerBean"%>
<%@ include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>

<%
    String actionURL = "e_asproLogin/CC030SearchCustomer";
    SearchCustomerBean bean = (SearchCustomerBean) request.getAttribute("bean");
    EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
    List<Map<String,String>> list = bean.getList();
%>
<html>
    <head>
        <TITLE>
        	拠点選択
        </TITLE>
	    <SCRIPT type="text/javascript">
	    	//search
	 	    function Search(){
				$$("CC030Form").action="<%=Constants.URL_BASE%>/<%=actionURL%>";
				$$("CC030Form").processType.value = "1";
				$$("CC030Form").submit();
				btnDisabled();
		    }

		    function SetValue(koNo,koCd,koNm1,koKana1,koZip,koKen,koAdr1,koAdr2,koAdr3,koTel1,koFax,kokyaku_mail1,kokyaku_mail2,kokyaku_mail3,sitenCd,index) {
			    try{
					var strRun;
		         	var str_calls_name= $$("js_calls").value;
		         	strRun = "window.parent.opener." +  str_calls_name + "('"+ koNo +"','"+ koCd +"','"+ koNm1  +"','"
								+ koKana1  +"','"+ koZip +"','"+ koKen +"','"+ koAdr1 +"','"+ koAdr2 +"','"+ koAdr3
								+"','"+ koTel1  +"','"+ koFax+"','"+ kokyaku_mail1 +"','"+ kokyaku_mail2 +"','"+ kokyaku_mail3 +"','"+ sitenCd +"','"+ index +"');";
					if (window.parent.opener) {
			     		eval(strRun);
			     		window.close();
			     	}
				}catch(e){window.close();}
			}

		    function SetValue2(koNo,koCd,koNm1,koKana1,koZip,koKen,koAdr1,koAdr2,koAdr3,koTel1,koFax,kokyaku_mail1,kokyaku_mail2,kokyaku_mail3,sitenCd) {
			    try{
					var strRun;
		         	var str_calls_name= $$("js_calls").value;
		         	strRun = "window.parent.opener." +  str_calls_name + "('"+ koNo +"','"+ koCd +"','"+ koNm1
								+"','"+ koKana1+"','"+ koZip +"','"+ koKen +"','" + koAdr1 +"','"+ koAdr2+"','"+ koAdr3
								+"','"+ koTel1  +"','"+ koFax +"','"+ kokyaku_mail1 +"','"+ kokyaku_mail2 +"','"+ kokyaku_mail3+"','"+ sitenCd +"');";
					if (window.parent.opener) {
			     		eval(strRun);
			     		window.close();
			     	}
				}catch(e){window.close();}
			}

		    function Close(){
				window.close();
		    }
		</SCRIPT>
	</head>
<BODY>
<center>
	<TABLE>
    	<TR>
        	<TD ALIGN="center" BGCOLOR="white">
				<form action="<%=actionURL%>" method="post" id="CC030Form">
	            <input type="hidden" name="processType" value="<%=bean.getProcessType() %>"/>
	            <input type="hidden" name="callsSrciptFuncName" id="js_calls" value="<%=bean.getCallsSrciptFuncName() %>"/>
				<input type="hidden" name="index" id="_index" value="<%=bean.getIndex() %>"/>
	            	<TABLE BORDER="0" CELLSPACING="2" CELLPADDING="2" width="450">
	                	<TR>
	                     	<TD>
		                       	<TABLE CLASS="search_sf" style="width:600">
		                       		<TR>
		                        		<TH WIDTH="120">支社コード</TH>
					                    <TD WIDTH="180">
					                    	<%if(!"".equals(bean.getSisyaCd())){ %>
												 <%=bean.getSisyaNm()%>
												 <input type="hidden" name="sisyaCd" id="_sisyaCd" value="<%=bean.getSisyaCd() %>"/>
												 <input type="hidden" name="sisyaCdSearch" id="sisyaCdSearch" value="<%=bean.getSisyaCd() %>"/>
											<%}else{ %>
												<select NAME="sisyaCdSearch" size="1">
													<option></option>
													<%
														if (bean.getSisyaList() != null && bean.getSisyaList().size() > 0) {
															for (int i = 0; i < bean.getSisyaList().size(); i++) {
																HashMap item = (HashMap) bean.getSisyaList().get(i);
																String key = StringUtil.nvl(item.get("KEY"));
																String text = StringUtil.nvl(item.get("VALUE"));
																String selected = (key.equals(StringUtil.nvl(bean.getSisyaCdSearch()))?"selected":"");
													%>
														<option value="<%=key %>" <%=selected%>><%=text %></option>
													<%
															}
														}
													%>
												</select>
											<%} %>
					                    </TD>
		                       			<TH WIDTH="120">拠点CD</TH>
					                    <TD WIDTH="180">
											<input type="text"
					        	           			value="<%=bean.getKokyakuCdSearch()%>"
					                       			name="kokyakuCdSearch"
					                       			size="15"
													maxlength="10"
													Class="code"
													onkeypress="_chkInp()">
											<app:searchflg searchFlg="1"/>
					               		</TD>
					                </TR>
		                            <TR>
		                                <TH WIDTH="120">拠点名</TH>
					                    <TD WIDTH="180">
											<input type="text"
					                                value="<%=bean.getKokyakuNmSearch()%>"
					                                name="kokyakuNmSearch"
					                                size="15"
													maxlength="10"
													Class="multi">
											<app:searchflg searchFlg="2"/>
					                    </TD>
		                                <TH WIDTH="120">拠点住所</TH>
					                    <TD WIDTH="180">
											<input type="text"
					                                value="<%=bean.getKokyakuAddrSearch()%>"
					                                name="kokyakuAddrSearch"
					                                size="15"
													maxlength="10"
													Class="multi">
											<app:searchflg searchFlg="2"/>
					                    </TD>
					                </TR>
		                            <TR>
		                                <TH WIDTH="120">電話番号</TH>
					                    <TD WIDTH="180">
											<input type="text"
					                                value="<%=bean.getKokyakuTelSearch()%>"
					                                name="kokyakuTelSearch"
					                                size="15"
													maxlength="10"
													Class="tel"
													onkeypress="_chkInp()">
											<app:searchflg searchFlg="1"/>
					                    </TD>
		                                <TH WIDTH="120">FAX</TH>
					                    <TD WIDTH="180">
											<input type="text"
					                                value="<%=bean.getKokyakuFaxSearch()%>"
					                                name="kokyakuFaxSearch"
					                                size="15"
													maxlength="10"
													Class="tel"
													onkeypress="_chkInp()">
											<app:searchflg searchFlg="1"/>
					                    </TD>
					                </TR>
					            </TABLE>
					        </TD>
							<TD ROWSPAN="3" NOWRAP ALIGN="left" VALIGN="bottom">
								<input type="button" value="検索" onclick="Search();"/>
							</TD>
						</TR>
					</TABLE>
				</form>
			<hr align="left" width="600">
				<%if("2".equals(bean.getSearchKbn())){ %>
        			<%if(list.size() > 0){ %>
	        			<app:PageFeed tableWidth="200" mapKey="CC030"/>
		                <TABLE CLASS="lt" width=600 style="border-width: 2px 2px 0px 2px;">
	                    	<COL width="100">
	                    	<COL width="100">
	                        <COL width="120">
	                        <COL width="180">
	                        <COL width="100">
	                        <COL width="100">
	                        <TR CLASS="head">
						   		<TH>支社名</TH>
						   		<TH>拠点CD</TH>
						   		<TH>拠点名</TH>
						   		<TH>拠点住所</TH>
						   		<TH>電話番号</TH>
						   		<TH>FAX</TH>
							</TR>
						</TABLE>
						<div id="_pageDivId" style="width:720" class="listdiv">
		                <TABLE CLASS="lt" width="700" style="border-width:0px 2px 2px 2px;">
		                	<COL width="100">
		                	<COL width="100">
		                    <COL width="120">
		                    <COL width="180">
		                    <COL width="100">
		                    <COL width="100">
							<%for(int i=0;i<list.size();i++){ %>
		                		<%HashMap<String,String> map = (HashMap<String,String>)list.get(i); %>
		                        <TR>
		                             <!--支社名-->
		                             <TD align="left" title="<%=StringUtil.nvl(map.get("SISYA_NAME"))%>">
		                             	<nobr><%=StringUtil.nvl(map.get("SISYA_NAME"))%></nobr>
		                             </TD>
		                        	<!--拠点CD-->
		                            <TD align="center" title="<%=StringUtil.nvl(map.get("KOKYAKU_NO"))%>">
		                            <%if ("".equals(bean.getIndex())||"undefined".equals(bean.getIndex())){ %>
			                        	<a href='javascript:SetValue2("<%=StringUtil.nvl(map.get("KOKYAKU_NO"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_CD"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_NM1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_KANA1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ZIP"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_KEN"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ADDR1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ADDR2"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ADDR3"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_TEL1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_FAX"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_MAIL1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_MAIL2"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_MAIL3"))%>",
			                                    					"<%=StringUtil.nvl(map.get("SITEN_CD"))%>")'>
		                            <%}else{ %>
			                        	<a href='javascript:SetValue("<%=StringUtil.nvl(map.get("KOKYAKU_NO"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_CD"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_NM1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_KANA1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ZIP"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_KEN"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ADDR1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ADDR2"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_ADDR3"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_TEL1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_FAX"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_MAIL1"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_MAIL2"))%>",
			                                    					"<%=StringUtil.nvl(map.get("KOKYAKU_MAIL3"))%>",
			                                    					"<%=StringUtil.nvl(map.get("SITEN_CD"))%>")'>
			                                    					"<%=bean.getIndex()%>")'>
		                             <%}%>
		                             	<nobr><%=StringUtil.nvl(map.get("KOKYAKU_CD"))%></nobr>
			                         	</a>
		                             </TD>
		                             <!--拠点名-->
		                             <TD align="left" title="<%=StringUtil.nvl(map.get("KOKYAKU_NAME"))%>">
		                             	<nobr><%=StringUtil.nvl(map.get("KOKYAKU_NAME"))%></nobr>
		                             </TD>
		                             <!--拠点住所-->
		                             <TD align="left" title="<%=StringUtil.nvl(map.get("KOKYAKU_ADDR"))%>">
		                             	<nobr><%=StringUtil.nvl(map.get("KOKYAKU_ADDR"))%></nobr>
		                             </TD>
		                             <!--電話番号-->
		                             <TD align="left" title="<%=StringUtil.nvl(map.get("KOKYAKU_TEL1"))%>">
		                             	<nobr><%=StringUtil.nvl(map.get("KOKYAKU_TEL1"))%></nobr>
		                             </TD>
		                             <!--FAX-->
		                             <TD align="left" title="<%=StringUtil.nvl(map.get("KOKYAKU_FAX"))%>">
		                             	<nobr><%=StringUtil.nvl(map.get("KOKYAKU_FAX"))%></nobr>
		                             </TD>
		                         </TR>
	     					<%}%>
		                </TABLE>
	                    </div>
					<%} %>
				<%} %>
				<%if(bean.getList().size() == 0 && ("2".equals(bean.getSearchKbn()))){ %>
		        	<font size="2">
		            	<%=Properties.getMsg("HA.MSG.001")%>
		            </font>
				<%} %>
	    		<app:pageinit/>
	        </TD>
        </TR>
    </TABLE>
	<TABLE width="600">
		<COL width="600">
        <TR>
        	<TD  align="center">
            	<input type="button" value="閉じる" onclick="Close()">
            </TD>
        </TR>
    </TABLE>
</center>
</BODY>
</html>