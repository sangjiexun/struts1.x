<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko.SearchZaikoBean"%>
<%@include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>

<%
	String actionURL = "e_asproLogin/CC031SearchZaiko";
	SearchZaikoBean bean = (SearchZaikoBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
%>
<html>
    <head>
        <TITLE>
        	ç›å…è§ïiëIë
        </TITLE>
	    <SCRIPT type="text/javascript">
	    	//search
	 	    function Search(){
				$$("CC031Form").action="<%=Constants.URL_BASE%>/<%=actionURL%>";
				$$("CC031Form").processType.value = "1";
				$$("CC031Form").submit();
				btnDisabled();
		    }

		    function SetValue(itemCd,itemNm,salePrice,yotei,sisyaCd,sitenCd,irisu,index) {
			    try{
					var strRun;
		         	var str_calls_name= $$("js_calls").value;
		         	strRun = "window.parent.opener." +  str_calls_name + "('"+ itemCd +"','"+ itemNm +"','"+ salePrice +"','"+
		         			 reComma(yotei) +"','"+ sisyaCd +"','"+ sitenCd +"','"+ irisu +"','"+ index +"');";
					if (window.parent.opener) {
			     		eval(strRun);
			     		window.close();
			     	}
				}catch(e){window.close();}
			}

		    function SetValue2(itemCd,itemNm,salePrice,yotei,sisyaCd,sitenCd,irisu) {
			    try{
					var strRun;
					var str_calls_name= $$("js_calls").value;
		         	strRun = "window.parent.opener." +  str_calls_name + "('"+ itemCd +"','"+ itemNm +"','"+ salePrice +"','"+
        			         reComma(yotei) +"','"+ sisyaCd +"','"+ sitenCd +"','"+ irisu +"');";
					if (window.parent.opener) {
						eval(strRun);
						window.close();
					}
				}catch(e){window.close();}
			}

			function Close(){
				window.close();
		    }

			function getSitenList(_val){
				if( _val ==""){
					return;
				}
				var sitenCd = $("#_sitenCd").val();
				$('#sitenCdSearch > option').remove();
				var url = "<%=Constants.URL_BASE%>/<%=actionURL%>";
				var parms = "kbn=1&processType="+<%=Constants.PAGE_AJAX%>;
				parms = parms+"&sisyaCd="+_val;
				parms = parms+"&sitenCd="+sitenCd;
				<app:AjaxSendRequest2Action params="parms" url="url" async="true"/>
			}
		</SCRIPT>
	</head>
	<BODY>
		<TABLE>
			<TR>
	        	<TD ALIGN="center" BGCOLOR="white">
					<form action="<%=actionURL%>" method="post" id="CC031Form">
					<input type="hidden" name="processType" value="<%=bean.getProcessType() %>"/>
					<input type="hidden" name="callsSrciptFuncName" id="js_calls" value="<%=bean.getCallsSrciptFuncName() %>"/>
					<input type="hidden" name="index" id="_index" value="<%=bean.getIndex() %>"/>
					<input type="hidden" name="sisyaCd" id="_sisyaCd" value="<%=bean.getSisyaCd() %>"/>
					<input type="hidden" name="sitenCd" id="_sitenCd" value="<%=bean.getSitenCd() %>"/>
						<TABLE class="nb" width="600">
							<TR>
								<TD>
									<TABLE CLASS="search_sf" style="width:600">
										<tr>
											<th width="120">éxé–</th>
											<td width="180">
												<%if("".equals(bean.getSisyaCd())){%>
													<select name="sisyaCdSearch" onchange="getSitenList(this.value);">
														<% for (Map<String,String> item : bean.getSisyaList()) { %>
															<option value="<%=StringUtil.nvl(item.get("KEY")) %>" <%=StringUtil.stringEquals(item.get("KEY"),bean.getSisyaCdSearch())?"selected":""%>>
																<%=StringUtil.nvl(item.get("VALUE"))%>
															</option>
														<%} %>
													</select>
												<%}else{ %>
													<%=bean.getSisyaNm()%>
													<input type="hidden" name="sisyaNm" id="_sisyaNm" value="<%=bean.getSisyaNm() %>"/>
													<input type="hidden" name="sisyaCdSearch" id="_sisyaCdSearch" value="<%=bean.getSisyaCd()%>"/>
												<%} %>
											</td>
											<th width="120">éxìX</th>
											<td width="180">
												<%if("0".equals(bean.getSitenCd())){ %>
													<input type="hidden" name="sitenCdSearch" id="_sitenCdSearch" value="<%=bean.getSitenCd()%>"/>
												<%}else{ %>
													<select name="sitenCdSearch" id="sitenCdSearch">
														<% for (Map<String,String> item : bean.getSitenList()) { %>
															<option value="<%=StringUtil.nvl(item.get("KEY")) %>" <%=StringUtil.stringEquals(item.get("KEY"),bean.getSitenCdSearch())?"selected":""%>>
																<%=StringUtil.nvl(item.get("VALUE"))%>
															</option>
														<%} %>
													</select>
												<%} %>
											</td>
										</tr>
										<tr>
											<th width="120">ëqå…</th>
											<td width="180">
												<%if("".equals(bean.getWhCd())){%>
													<select name="whCdSearch">
														<% for (Map<String,String> item : bean.getWhList()) { %>
															<option value="<%=StringUtil.nvl(item.get("KEY")) %>" <%=StringUtil.stringEquals(item.get("KEY"),bean.getWhCdSearch())?"selected":""%>>
																<%=StringUtil.nvl(item.get("VALUE"))%>
															</option>
														<%} %>
													</select>
												<%}else{ %>
													<%=bean.getWhNm()%>
													<input type="hidden" name="whCd" id="_whCd" value="<%=bean.getWhCd() %>"/>
													<input type="hidden" name="whNm" id="_whNm" value="<%=bean.getWhNm() %>"/>
													<input type="hidden" name="whCdSearch" id="_whCdSearch" value="<%=bean.getWhCd()%>"/>
												<%} %>
											</td>
											<th width="120">ç›å…ãÊï™</th>
											<td width="180">
												<%if("".equals(bean.getZaikoKbn())){%>
													<select name="zaikoKbnSearch">
														<% for (Map<String,String> item : bean.getZaikoKbnList()) { %>
															<option value="<%=StringUtil.nvl(item.get("KEY")) %>" <%=StringUtil.stringEquals(item.get("KEY"),bean.getZaikoKbnSearch())?"selected":""%>>
																<%=StringUtil.nvl(item.get("VALUE"))%>
															</option>
														<%} %>
													</select>
												<%}else{ %>
													<%=bean.getZaikoKbnNm()%>
													<input type="hidden" name="zaikoKbn" id="_zaikoKbn" value="<%=bean.getZaikoKbn() %>"/>
													<input type="hidden" name="zaikoKbnNm" id="_zaikoKbnNm" value="<%=bean.getZaikoKbnNm() %>"/>
													<input type="hidden" name="zaikoKbnSearch" id="_zaikoKbnSearch" value="<%=bean.getZaikoKbn()%>"/>
												<%} %>
											</td>
										</tr>
										<tr>
											<th width="120">è§ïiÉRÅ[Éh</th>
											<td width="180">
												<input type="text"
														value="<%=bean.getItemCdSearch()%>"
														name="itemCdSearch"
														size="15"
														maxlength="10"
														Class="code"
														onkeypress="_chkInp()">
											</td>
											<th width="120">è§ïiñº</th>
											<td width="180">
												<input type="text"
														value="<%=bean.getItemNmSearch()%>"
														name="itemNmSearch"
														size="15"
														maxlength="10"
														class="multi">
												<app:searchflg searchFlg="2"/>
											</td>
										</tr>
								</TABLE>
								</TD>
								<TD ROWSPAN="3" NOWRAP ALIGN="left" VALIGN="bottom">
									<input type="button" value="åüçı" onclick="Search();"/>
								</TD>
							</TR>
						</TABLE>
					</form>
				<hr align="left" width="950">
					<%if("2".equals(bean.getSearchKbn())){ %>
						<%if(bean.getList().size() > 0){ %>
							<app:PageFeed tableWidth="200" mapKey="CC031"/>
							<TABLE CLASS="lt" width="910">
								<COL width="80">
								<COL width="120">
								<COL width="100">
								<COL width="100">
								<COL width="150">
								<COL width="100">
								<COL width="100">
								<COL width="80">
								<COL width="80">
								<TR CLASS="head">
									<TH>éxé–</TH>
									<TH>éxìX</TH>
									<TH>ëqå…ñº</TH>
									<TH>è§ïiÉRÅ[Éh</TH>
									<TH>è§ïiñº</TH>
									<TH>ç›å…ãÊï™</TH>
									<TH>åªç›ç›å…</TH>
									<TH>ó\íËç›å…</TH>
								</TR>
								<%for(int i=0;i<bean.getList().size();i++){ %>
									<%HashMap<String,String> map = (HashMap<String,String>)bean.getList().get(i); %>
									<TR>
										<!--éxé–-->
										<TD align="left" title="<%=StringUtil.nvl(map.get("SISYA_NAME"))%>">
											<nobr><%=StringUtil.nvl(map.get("SISYA_NAME"))%></nobr>
										</TD>
										<!--éxìX-->
										<TD align="left" title="<%=StringUtil.nvl(map.get("SITEN_NAME"))%>">
											<nobr><%=StringUtil.nvl(map.get("SITEN_NAME"))%></nobr>
										</TD>
										<!--ëqå…ñº-->
										<TD align="left" title="<%=StringUtil.nvl(map.get("WH_NM1"))%>">
											<nobr><%=StringUtil.nvl(map.get("WH_NM1"))%></nobr>
										</TD>
										<!--è§ïiÉRÅ[Éh-->
										<TD align="center" title="<%=StringUtil.nvl(map.get("ITEM_NO"))%>">
										<%if ("".equals(bean.getIndex())||"undefined".equals(bean.getIndex())){ %>
											<a href='javascript:SetValue2("<%=StringUtil.nvl(map.get("ITEM_NO"))%>",
																		"<%=StringUtil.nvl(map.get("ITEM_NM1"))%>",
																		"<%=StringUtil.nvl(map.get("SALE_TNK"))%>",
																		"<%=StringUtil.nvl(map.get("YOTE_ZAIKO"))%>",
																		"<%=StringUtil.nvl(map.get("SISYA_CD"))%>",
																		"<%=StringUtil.nvl(map.get("SITEN_CD"))%>",
																		"<%=StringUtil.nvl(map.get("IRI_SU"))%>")'>
										<%}else{ %>
											<a href='javascript:SetValue("<%=StringUtil.nvl(map.get("ITEM_NO"))%>",
																		"<%=StringUtil.nvl(map.get("ITEM_NM1"))%>",
																		"<%=StringUtil.nvl(map.get("SALE_TNK"))%>",
																		"<%=StringUtil.nvl(map.get("YOTE_ZAIKO"))%>",
																		"<%=StringUtil.nvl(map.get("SISYA_CD"))%>",
																		"<%=StringUtil.nvl(map.get("SITEN_CD"))%>",
																		"<%=StringUtil.nvl(map.get("IRI_SU"))%>",
																		"<%=bean.getIndex()%>")'>
										<%}%>
											<nobr><%=StringUtil.nvl(map.get("ITEM_NO"))%></nobr>
											</a>
										</TD>
										<!--è§ïiñº-->
										<TD align="left" title="<%=StringUtil.nvl(map.get("ITEM_NM1"))%>">
											<nobr><%=StringUtil.nvl(map.get("ITEM_NM1"))%></nobr>
										</TD>
										<!--ç›å…ãÊï™-->
										<TD align="left" title="<%=StringUtil.nvl(map.get("ZAIKO_KBN_NAME"))%>">
											<nobr><%=StringUtil.nvl(map.get("ZAIKO_KBN_NAME"))%></nobr>
										</TD>
										<!--åªç›ç›å…-->
										<TD align="right" title="<%=StringUtil.nvl(map.get("GEN_ZAIKO"))%>">
											<nobr><app:NumberFormat numberValue='<%=StringUtil.nvl(map.get("GEN_ZAIKO"))%>'/></nobr>
										</TD>
										<!--ó\íËç›å…-->
										<TD align="right" title="<%=StringUtil.nvl(map.get("YOTE_ZAIKO"))%>">
											<nobr><%=StringUtil.nvl(map.get("YOTE_ZAIKO"))%></nobr>
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
		<TABLE width="950">
			<COL width="905">
			<TR>
				<TD  align="center">
					<input type="button" value="ï¬Ç∂ÇÈ" onclick="Close()">
				</TD>
			</TR>
		</TABLE>
	</BODY>
</html>