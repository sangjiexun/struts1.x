<%@page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct.SearchProductBean"%>
<%@include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>

<%
	String actionURL = "e_asproLogin/CC021SearchProduct";
	SearchProductBean bean = (SearchProductBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg"});
    List<Map<String,String>> list = bean.getList();
%>
<html>
	<head>
		<title>
			商品選択
		</title>
		<script type="text/javascript">
			//search
			function Search(){
				$$("CC021Form").action="<%=Constants.URL_BASE%>/<%=actionURL%>";
				$$("CC021Form").processType.value = "1";
				$$("CC021Form").submit();
				btnDisabled();
			}

			function SetValue(productNo,productNm,gedaiPrice,salePrice,index) {
				try{
					var strRun;
					var str_calls_name= $$("js_calls").value;
					strRun = "window.parent.opener." + str_calls_name + "('"+ productNo +"','" + productNm +"','"+ gedaiPrice +"','"+ salePrice +"','"+ index +"');";
					if (window.parent.opener) {
						eval(strRun);
						window.close();
					}
				}catch(e){window.close();}
			}

			function SetValue2(productNo,productNm,gedaiPrice,salePrice) {
				try{
					var strRun;
					var str_calls_name= $$("js_calls").value;
					strRun = "window.parent.opener." +  str_calls_name + "('"+ productNo +"','" + productNm +"','"+ gedaiPrice +"','"+ salePrice + "');";
					if (window.parent.opener) {
						eval(strRun);
						window.close();
					}
				}catch(e){window.close();}
			}

			function Close(){
				window.close();
			}
		</script>
	</head>
	<body>
	<center>
	<table>
		<tr>
			<td align="center" bgcolor="white">
				<form action="<%=actionURL%>" method="post" id="CC021Form">
				<input type="hidden" name="processType" value="<%=bean.getProcessType() %>"/>
				<input type="hidden" name="callsSrciptFuncName" id="js_calls" value="<%=bean.getCallsSrciptFuncName() %>"/>
				<input type="hidden" name="index" id="_index" value="<%=bean.getIndex() %>"/>
				<input type="hidden" name="kyotuDispFlg" id="_kyotuDispFlg" value="<%=bean.getKyotuDispFlg() %>"/>
				<input type="hidden" name="kyotuOnlyFlg" id="_kyotuOnlyFlg" value="<%=bean.getKyotuOnlyFlg() %>"/>
				<input type="hidden" name="sendSisyaCd" value="<%=bean.getSendSisyaCd() %>"/>
					<table border="0" cellspacing="2" cellpadding="2" width="600">
						<tr>
							<td>
								<table class="search_sf" style="width:600">
									<col width="100">
									<col width="200">
									<tr>
										<th>支社</th>
										<td colspan="3">
											<%if(!"".equals(bean.getKyotuOnlyFlg()) || ("".equals(bean.getKyotuOnlyFlg())&& !"".equals(bean.getSendSisyaCd())) ){ %>
												<%=bean.getSisyaNm() %>
												<input type="hidden" name="sisyaCd" value="<%=bean.getSisyaCd() %>"/>
												<input type="hidden" name="sisyaNm" value="<%=bean.getSisyaNm() %>"/>
											<%}else{ %>
												<select name="sisyaCd" id="_sisyaCd" size="1">
													<% List<Map<String, String>> sisyaList = bean.getSisyaList(); %>
			                               			<%	for (int i = 0; i < sisyaList.size(); i++) {
															Map<String, String> item = (Map<String, String>)sisyaList.get(i);
															String key = StringUtil.nvl(item.get("KEY"));
															String text = StringUtil.nvl(item.get("VALUE"));
															if (key.equals(bean.getSisyaCd())) {
													%>
																<option value="<%=key %>" selected><%=text %></option>
													<%		} else {%>
																<option value="<%=key %>"><%=text %></option>
													<%		}
														}
													%>
												</select>
											<%} %>
										</td>
									</tr>
									<tr>
										<th>商品コード</th>
										<td>
											<input type="text"
													value="<%=bean.getProductCdSearch()%>"
													name="productCdSearch"
													size="15"
													maxlength="10"
													Class="code"
													onkeypress="_chkInp()">
											<app:searchflg searchFlg="1"/>
										</td>
										<th>商品名</th>
										<td>
											<input type="text"
													value="<%=bean.getProductNmSearch()%>"
													name="productNmSearch"
													size="15"
													maxlength="10"
													Class="multi">
											<app:searchflg searchFlg="2"/>
										</td>
									</tr>
								</table>
							</td>
							<td rowspan="3" nowrap align="left" valign="bottom">
								<input type="button" value="検索" onclick="Search();"/>
							</td>
						</tr>
					</table>
				</form>
			<hr align="left" width="600">
				<%if("2".equals(bean.getSearchKbn())){ %>
					<%if(list.size() > 0){ %>
						<app:PageFeed tableWidth="200" mapKey="CC021"/>
						<table class="lt" width=560 style="border-width: 2px 2px 0px 2px;">
							<col width="120">
							<col width="80">
							<col width="300">
							<col width="60">
							<tr class="head">
						   		<th>支社</th>
						   		<th>商品コード</th>
						   		<th>商品名</th>
						   		<th>販売単価</th>
							</tr>
						</table>
						<div id="_pageDivId" style="width:660" class="listdiv">
						<table class="lt" width="560" style="border-width:0px 2px 2px 2px;">
							<col width="120">
							<col width="80">
							<col width="300">
							<col width="60">
							<%for(int i=0;i<list.size();i++){ %>
								<%HashMap<String,String> map = (HashMap<String,String>)list.get(i); %>
								<tr>
									 <!--支社-->
									 <td align="left" title="<%=StringUtil.nvl(map.get("SISYA_NAME"))%>">
									 	<nobr><%=StringUtil.nvl(map.get("SISYA_NAME"))%></nobr>
									 </td>
									<!--商品コード-->
									<td align="left" title="<%=StringUtil.nvl(map.get("ITEM_NO"))%>">
									<%if ("".equals(bean.getIndex())||"undefined".equals(bean.getIndex())){ %>
										<a href='javascript:SetValue2("<%=StringUtil.nvl(map.get("ITEM_NO"))%>",
																	"<%=StringUtil.nvl(map.get("ITEM_NM1"))%>",
																	"<%=StringUtil.nvl(map.get("GEDAI_PRICE"))%>",
																	"<%=StringUtil.nvl(map.get("SALE_TNK"))%>")'>
									<%}else{ %>
										<a href='javascript:SetValue("<%=StringUtil.nvl(map.get("ITEM_NO"))%>",
																	"<%=StringUtil.nvl(map.get("ITEM_NM1"))%>",
																	"<%=StringUtil.nvl(map.get("GEDAI_PRICE"))%>",
																	"<%=StringUtil.nvl(map.get("SALE_TNK"))%>",
																	"<%=bean.getIndex()%>")'>
									 <%}%>
									 	<nobr><%=StringUtil.nvl(map.get("ITEM_NO"))%></nobr>
									 	</a>
									 </td>
									 <!--商品名-->
									 <td align="left" title="<%=StringUtil.nvl(map.get("ITEM_NM1"))%>">
									 	<nobr><%=StringUtil.nvl(map.get("ITEM_NM1"))%></nobr>
									 </td>
									 <!--販売単価-->
									 <td align="right" title="<%=StringUtil.nvl(map.get("SALE_TNK"))%>">
									 	<nobr><%=StringUtil.nvl(map.get("SALE_TNK"))%></nobr>
									 </td>
								 </tr>
		 					<%}%>
						</table>
						</div>
					<%} %>
				<%} %>
				<%if(bean.getList().size() == 0 && ("2".equals(bean.getSearchKbn()))){ %>
					<font size="2">
						<%=Properties.getMsg("HA.MSG.001")%>
					</font>
				<%} %>
				<app:pageinit/>
			</td>
		</tr>
	</table>
	<table width="600">
		<col width="600">
		<tr>
			<td  align="center">
				<input type="button" value="閉じる" onclick="Close()">
			</td>
		</tr>
	</table>
</center>
</body>
</html>