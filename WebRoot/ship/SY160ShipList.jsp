<%@page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproShip.SY160ShipList.SY160ShipListBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.tag.SelectTag.Option"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.CommonService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%
	SY160ShipListBean bean = (SY160ShipListBean)request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean, new String[]{"strErrMsg"});
	String strErrMsg = StringUtil.nvl(bean.getStrErrMsg());

	String header_csv_out_flg = "";
	String list_csv_out_flg = "";
	String toi_csv_out_flg = "";

	if ("".equals(bean.getHeaderCsvOutFlg())) {
		header_csv_out_flg = "disabled='disabled'";
	}
	if ("".equals(bean.getListCsvOutFlg())) {
		list_csv_out_flg = "disabled='disabled'";
	}
	if ("".equals(bean.getToi_csv_out_flg())) {
		toi_csv_out_flg = "disabled='disabled'";
	}

%>

<html>
	<head>
		<title>
			出荷一覧
		</title>
		<%@ include file="/taglibs.jsp" %>
		<app:AjaxInitTag/>
		<script type="text/javascript">
			// 検索
	 		function Search() {
	 			$$("_processType").value = "<%=Constants.PAGE_SEARCH%>";
				$$("_Form").action = "<%=Constants.URL_BASE%>/e_asproShip/SY160ShipList"
				$$("_Form").submit();

				btnDisabled();
			}

			// CSV出力
	 		function DownLoad(csv_down_kbn) {
				if (csv_down_kbn == '6') {
					$$("_header_csv_out").value = $$("_header_csv_out_noform").value;
				}
		 		if (csv_down_kbn == '7') {
					$$("_list_csv_out").value = $$("_list_csv_out_noform").value;
				}
				if (csv_down_kbn == '71') {
					$$("_toi_csv_out").value = $$("_toi_csv_out_noform").value;
				}
				if (csv_down_kbn == '72') {
					$$("_item_csv_out").value = 1;
				}

	 			$$("_processType").value = "<%=Constants.PAGE_DOWNLOAD%>";
	 			$$("_csv_down_kbn").value = csv_down_kbn;
				$$("_Form").action = "<%=Constants.URL_BASE%>/e_asproShip/SY160ShipList";
				$$("_Form").submit();

				$$("_btnDownload1").disabled=true;
				$$("_btnDownload2").disabled=true;
				$$("_btnDownload71").disabled=true;
				$$("_btnDownload72").disabled=true;
				setTimeout('enableDownLoad()', '3000');
			}

			function enableDownLoad(){
				if ("<%=header_csv_out_flg%>" == "") {
					$$("_btnDownload1").disabled = false;
				}
				if ("<%=list_csv_out_flg%>" == "") {
					$$("_btnDownload2").disabled = false;
				}
				if ("<%=toi_csv_out_flg%>" == "") {
					$$("_btnDownload71").disabled = false;
				}
				$$("_btnDownload72").disabled = false;
			}

			//支社変更時支店情報取得
			function getSitenList(_val){
				$('#siten_cd > option').remove();
				if( _val ==""){
					return;
				}
				var url = "<%=Constants.URL_BASE%>/e_asproShip/SY160ShipList";
				var parms = "processType="+<%=Constants.PAGE_AJAX%>;
				parms = parms+"&sisya_cd="+_val;
				<app:AjaxSendRequest2Action params="parms" url="url" async="true"/>
			}
		</script>
	</head>
	<body>
		<img src="<%=Constants.DOC_ROOT%>/img/head_kefir_syukka.gif" alt="" align="absmiddle" border="0">
		<div class=pt>出荷一覧</div>
		<br>
		<b style="color:red"><%=strErrMsg%></b>
		<br>
		<form action="<%=Constants.URL_BASE%>/SY160ShipList" id="_Form" method="post">
			<input type="hidden" name="strForwordFlg" value="2">
			<input type="hidden" name="processType" id="_processType" value="2">
			<input type="hidden" name="csv_down_kbn" id="_csv_down_kbn">
			<input type="hidden" name="header_csv_out" id="_header_csv_out">
			<input type="hidden" name="list_csv_out" id="_list_csv_out">
			<input type="hidden" name="toi_csv_out" id="_toi_csv_out">
			<input type="hidden" name="item_csv_out" id="_item_csv_out">
			<app:div hiddenFlagWidth="750">
				<table class="nb">
					<tr>
						<td>
							<table class="sf">
								<tr style="height:50px;">
									<th>出荷予定日</th>
									<td>
										<app:calender
											startYmdId="syukka_yotei_ymd_s"
											startYmdName="syukka_yotei_ymd_s"
											startYmdvalue="<%=bean.getSyukka_yotei_ymd_s()%>"
											msg="出荷予定日(開始日)"
										/>
										～
										<app:calender
											startYmdId="syukka_yotei_ymd_e"
											startYmdName="syukka_yotei_ymd_e"
											startYmdvalue="<%=bean.getSyukka_yotei_ymd_e()%>"
											msg="出荷予定日(終了日)"
										/>
										<br/>
										<input type="checkbox" name="past_flg" value="1"
											<%=("1".equals(StringUtil.nvl(bean.getPast_flg()))?"checked":"") %>
										 />過去データも検索する
									</td>
									<th>注文日</th>
									<td>
										<app:calender
										   	startYmdId="order_ymd_s"
											startYmdName="order_ymd_s"
											startYmdvalue="<%=bean.getOrder_ymd_s()%>"
											msg="注文日(開始日)"
										/>
										～
										<app:calender
										   	startYmdId="order_ymd_e"
											startYmdName="order_ymd_e"
											startYmdvalue="<%=bean.getOrder_ymd_e()%>"
											msg="注文日(終了日)"
										/>
									</td>
								</tr>
								<tr>
									<th>出荷番号</th>
									<td>
										<input
											type="text"
											value="<%=bean.getSksj_cd()%>"
											class="code"
											maxlength="11"
											name="sksj_cd"
											onkeypress="_chkInp();"
											size="16">
										<app:searchflg searchFlg="1"/>
									</td>
									<th>注文番号</th>
									<td>
										<input
											type="text"
											value="<%=bean.getOrder_cd()%>"
											class="code"
											maxlength="10"
											name="order_cd"
											onkeypress="_chkInp();"
											size="16">
										<app:searchflg searchFlg="1"/>
									</td>
								</tr>
								<tr>
									<th>アップロードNo</th>
									<td>
										<input
											type="text"
											value="<%=bean.getUpload_no()%>"
											class="code"
											maxlength="15"
											name="upload_no"
											onkeypress="_chkInp();"
											size="16">
										<app:searchflg searchFlg="1"/>
									</td>
									<th>倉庫名</th>
									<td>
										<%if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {%>
											<%=CommonService.getSoukoNm(StringUtil.nvl(bean.getLoginUser().getCoNo()), bean.getWh_cd())%>
										<%} else { %>
											<% List<Option> resultWhNm = (List<Option>)request.getAttribute("resultWhNm");%>
											<app:select
												name="wh_cd"
												id="wh_cd"
												options="<%=resultWhNm%>"
												selectedValues="<%=bean.getWh_cd() %>"/>
										<%} %>
									</td>
								</tr>
								<tr>
									<th>支社</th>
									<td>
										<%if (Constants.USER_KBN_SISYA.equals(bean.getLoginUser().getUserAuth())||Constants.USER_KBN_SITEN.equals(bean.getLoginUser().getUserAuth())) {%>
											<%=CommonService.getSisyaNm(StringUtil.nvl(bean.getLoginUser().getCoNo()), bean.getLoginUser().getSisyaCd())%>
										<%} else { %>
											<%List resultSisyaCd = CommonService.getSisyaList(StringUtil.nvl(bean.getLoginUser().getCoNo()), true);%>
											<app:select2 name="sisya_cd"
												id="sisya_cd"
												list = "<%=resultSisyaCd %>"
												value="<%=bean.getSisya_cd() %>"
												listKey ="KEY"
												listValue = "VALUE"
												onchange="getSitenList(this.value)"
											/>
										<%} %>
									</td>
									<th>支店</th>
									<td>
										<app:select2 name="siten_cd"
											id="siten_cd"
											list = "<%=bean.getSiten_list() %>"
											value="<%=bean.getSiten_cd() %>"
											listKey ="KEY"
											listValue = "VALUE"
										/>
									</td>
								</tr>
								<tr>
									<th>受付店CD</th>
									<td>
										<input
											type="text"
											value="<%=bean.getKokyaku_cd()%>"
											class="code"
											maxlength="7"
											name="kokyaku_cd"
											onkeypress="_chkInp();"
											size="12">
										<app:searchflg searchFlg="1"/>
									</td>
									<th>受付店名</th>
									<td>
										<input
											type="text"
											value="<%=bean.getKokyaku_nm()%>"
											class="multi"
											maxlength="30"
											name="kokyaku_nm"
											size="20">
										<app:searchflg searchFlg="2"/>
									</td>
								</tr>
								<tr>
									<th>引渡店CD</th>
									<td>
										<input
											type="text"
											value="<%=bean.getTdk_cd()%>"
											class="code"
											maxlength="7"
											name="tdk_cd"
											size="12">
										<app:searchflg searchFlg="1"/>
									</td>
									<th>引渡店名</th>
									<td>
										<input
											type="text"
											value="<%=bean.getTdk_nm()%>"
											class="multi"
											maxlength="30"
											name="tdk_nm"
											size="20">
										<app:searchflg searchFlg="2"/>
									</td>
								</tr>
								<tr>
									<th>引渡店住所</th>
									<td>
										<input
											type="text"
											value="<%=bean.getTdk_addr()%>"
											class="multi"
											maxlength="60"
											name="tdk_addr"
											size="30">
										<app:searchflg searchFlg="2"/>
									</td>
									<th>配達指定日</th>
									<td>
										<app:calender
										   	startYmdId="tdk_ymd_s"
											startYmdName="tdk_ymd_s"
											startYmdvalue="<%=bean.getTdk_ymd_s()%>"
											msg="配達指定日(開始日)"
										/>
										～
										<app:calender
										   	startYmdId="tdk_ymd_e"
											startYmdName="tdk_ymd_e"
											startYmdvalue="<%=bean.getTdk_ymd_e()%>"
											msg="配達指定日(終了日)"
										/>
									</td>
								</tr>
								<tr>
									<th>問合せNo</th>
									<td>
										<input
											type="text"
										   	value="<%=bean.getOkurino_s()%>"
										   	class="numcode"
										   	maxlength="20"
										   	name="okurino_s"
										   	onkeypress="_chkInp();"
										   	size="12">
								   		 ～
										<input
											type="text"
										   	value="<%=bean.getOkurino_e()%>"
										   	class="numcode"
										   	maxlength="20"
										   	name="okurino_e"
										   	onkeypress="_chkInp();"
										   	size="12">
									</td>
									<th>処理状況</th>
									<td>
										<%List resultSyoriJyoukyou = (List)request.getAttribute("resultSyoriJyoukyou");%>
										<app:select2 name="syori_jyoukyou"
											id="syori_jyoukyou"
											list = "<%=resultSyoriJyoukyou %>"
											value="<%=bean.getSyori_jyoukyou() %>"
											listKey ="KEY"
											listValue = "VALUE"
										/>
									</td>
								</tr>
								<tr>
									<th>指示確定</th>
									<td>
										<app:calender
										   	startYmdId="syukka_ok_ymd_s"
											startYmdName="syukka_ok_ymd_s"
											startYmdvalue="<%=bean.getSyukka_ok_ymd_s()%>"
											msg="指示確定(開始日)"
										/>
										～
										<app:calender
										   	startYmdId="syukka_ok_ymd_e"
											startYmdName="syukka_ok_ymd_e"
											startYmdvalue="<%=bean.getSyukka_ok_ymd_e()%>"
											msg="指示確定(終了日)"
										/>
									</td>
									<th>実績確定</th>
									<td>
										<app:calender
										   	startYmdId="toiout_ymd_s"
											startYmdName="toiout_ymd_s"
											startYmdvalue="<%=bean.getToiout_ymd_s()%>"
											msg="実績確定(開始日)"
										/>
										～
										<app:calender
										   	startYmdId="toiout_ymd_e"
											startYmdName="toiout_ymd_e"
											startYmdvalue="<%=bean.getToiout_ymd_e()%>"
											msg="実績確定(終了日)"
										/>
									</td>
								</tr>
								<tr>
									<th>商品名</th>
									<td>
										<input
											type="text"
										   	value="<%=bean.getItem_nm1()%>"
										   	class="multi"
										   	maxlength="100"
										   	name="item_nm1"
										   	size="20">
										<app:searchflg searchFlg="2"/>
									</td>
									<th>商品コード</th>
									<td>
										<input
											type="text"
										   	value="<%=bean.getItem_cd1_s()%>"
										   	class="code"
										   	maxlength="20"
										   	name="item_cd1_s"
										   	onkeypress="_chkInp();"
										   	size="12">
								   		 ～
										<input
											type="text"
										   	value="<%=bean.getItem_cd1_e()%>"
										   	class="code"
										   	maxlength="20"
										   	name="item_cd1_e"
										   	onkeypress="_chkInp();"
										   	size="12">
									</td>
								</tr>
								<tr>
									<th>出荷区分</th>
									<td>
										<app:KubunList
											id="shipKbn"
											name="shipKbn"
											fieldNm="SHIP_KBN"
											selectedValue="<%=bean.getShipKbn() %>"
											addBlank="true"/>
									</td>
									<th>個別/一斉出荷</th>
									<td>
										<app:KubunList
											id="orderKbn"
											name="orderKbn"
											fieldNm="ORDER_KBN"
											selectedValue="<%=bean.getOrderKbn() %>"
											addBlank="true"/>
									</td>
								</tr>
							</table>
						</td>
						<td valign="bottom">
							<table>
								<tr>
									<td>
										<input type="button" onClick="Search();" value="検索"
										<app:PowerCheck fromId="" baseId="SY160" value="<%=Constants.POWER_SEARCH%>"/>
										/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</app:div>
			</form>
		<%if("2".equals(bean.getSearchKbn())){%>
			<%List list = bean.getList();%>
			<%if(list.size() > 0){%>
				<app:PageFeed/>
				<table>
					<tr>
						<td align="center" NOWRAP width="300" bgcolor="#ffff66">
							<% List resultHeaderCSVList = (List)request.getAttribute("resultHeaderCSVList");%>
							<app:select2 name="header_csv_out_noform"
								id="_header_csv_out_noform"
								list = "<%=resultHeaderCSVList %>"
								value="<%=bean.getHeader_csv_out() %>"
								listKey ="KEY"
								listValue = "VALUE"
								style="<%=header_csv_out_flg %>"
							/>
							<input type="button" <%=header_csv_out_flg %> onClick="DownLoad('6');" value="ヘッダCSV出力"
								<app:PowerCheck fromId="" baseId="SY160" value="<%=Constants.POWER_CSVDOWN%>"/>
							id="_btnDownload1"/>

						</td>
						<td align="center" NOWRAP width="300" bgcolor="#CCFF66">
							<% List resultMeisaiCSVList = (List)request.getAttribute("resultMeisaiCSVList");%>
							<app:select2 name="list_csv_out_noform"
								id="_list_csv_out_noform"
								list = "<%=resultMeisaiCSVList %>"
								value="<%=bean.getList_csv_out() %>"
								listKey ="KEY"
								listValue = "VALUE"
								style="<%=list_csv_out_flg %>"
							/>
							<input type="button" <%=list_csv_out_flg%> onClick="DownLoad('7');" value="明細CSV出力"
								<app:PowerCheck fromId="" baseId="SY160" value="<%=Constants.POWER_CSVDOWN%>"/>
							id="_btnDownload2"/>
						</td>
						<td align="center" NOWRAP width="300" bgcolor="#ffff66">
							<% List resultToiCSVList = (List)request.getAttribute("resultToiCSVList");%>
							<app:select2 name="list_toi_out_noform"
								id="_toi_csv_out_noform"
								list = "<%=resultToiCSVList %>"
								value="<%=bean.getToi_csv_out() %>"
								listKey ="KEY"
								listValue = "VALUE"
								style="<%=toi_csv_out_flg %>"
							/>
							<input type="button" <%=toi_csv_out_flg%> onClick="DownLoad('71');" value="問合せNoCSV出力"
								<app:PowerCheck fromId="" baseId="SY160" value="<%=Constants.POWER_CSVDOWN%>"/>
							id="_btnDownload71"/>
							<div align="center" style="font-size:10px;color:red;">※問合せNo発番済が出力対象となります</div>
						</td>
						<td align="center" NOWRAP width="200" bgcolor="#00bfff">
							<input type="button" onClick="DownLoad('72');" value="商品別出荷数CSV出力"
								<app:PowerCheck fromId="" baseId="SY160" value="<%=Constants.POWER_CSVDOWN%>"/>
							id="_btnDownload72"/>
						</td>
					</tr>
				</table>
				<table class="lt" width="1420" >
 					<col width="30">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="100">
					<col width="80">
					<col width="80">
					<col width="100">
					<col width="80">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="60">
					<col width="100">
					<col width="80">
					<col width="80">
					<tr class="head">
						<th>No</th>
						<th>出荷予定日</th>
						<th>出荷番号</th>
						<th>注文日</th>
						<th>注文番号</th>
						<th>アップロードNo</th>
						<th>支社</th>
						<th>受付店CD</th>
						<th>受付店名</th>
						<th>引渡店CD</th>
						<th>引渡店名</th>
						<th>倉庫名</th>
						<th>問合せNo</th>
						<th>請求金額</th>
						<th>処理状況</th>
						<th>指示確定</th>
						<th>実績確定</th>
					</tr>
				<%
					for(int i = 0; i < list.size(); i++) {
						HashMap item = (HashMap) list.get(i);
				%>
							<tr>
								<td align="center">
									<%=StringUtil.nvl(item.get("ROW_NO"))%></td>
								<%String item10 = StringUtil.nvl(item.get("SYUKKA_YOTEI_YMD"), "&nbsp;");%>
								<td align="center" title="<%=item10%>"><nobr><%=item10%></nobr></td>
								<%String item3 = StringUtil.nvl(item.get("SKSJ_CD"), "");%>
								<td align="center" title="<%=item3%>">
									<a href="<%=Constants.URL_BASE%>/e_asproShip/SY010ShijiEntry?seq_no=<%=StringUtil.nvl(item.get("SEQ_NO"), "0")%>"><nobr><%=item3%></nobr></a></td>
								<%String item1 = StringUtil.nvl(item.get("ORDER_YMD"), "0");%>
								<td align="center" title="<%=item1%>"><nobr><%=item1%></nobr></td>
								<%String item2 = StringUtil.nvl(item.get("ORDER_CD"), "0");%>
								<td align="center" title="<%=item2%>"><nobr><%=item2%></nobr></td>
								<%String item4 = StringUtil.nvl(item.get("UPLOAD_NO"), "");%>
								<td align="center" title="<%=item4%>"><nobr><%=item4%></nobr></td>
								<%String item5 = StringUtil.nvl(item.get("SISYA_NAME"), "");%>
								<td align="center" title="<%=item5%>"><nobr><%=item5%></nobr></td>
								<%String item6 = StringUtil.nvl(item.get("KOKYAKU_CD"), "");%>
								<td align="center" title="<%=item6%>"><nobr><%=item6%></nobr></td>
								<%String item7 = StringUtil.nvl(item.get("KOKYAKU_NM"), "");%>
								<td align="left" title="<%=item7%>"><nobr><%=item7%></nobr></td>
								<%String item18 = StringUtil.nvl(item.get("TDK_CD"), "");%>
								<td align="center" title="<%=item18%>"><nobr><%=item18%></nobr></td>
								<%String item19 = StringUtil.nvl(item.get("TDK_NM"), "");%>
								<td align="left" title="<%=item19%>"><nobr><%=item19%></nobr></td>
								<%String item9 = StringUtil.nvl(item.get("WH_NM"), "");%>
								<td align="left" title="<%=item9%>"><nobr><%=item9%></nobr></td>
								<%
									String okurinoMoji = "";
									if(Integer.valueOf(StringUtil.nvl(item.get("OKURINO_CNT"),"0")) > 1){
										//他の送り状Noがある場合、右側に"他"を付加する
										okurinoMoji = "他";
									}
									String item11 = StringUtil.nvl(item.get("OKURINO"), "").replaceAll(",","<br>");
								%>
								<td align="left" title="<%=StringUtil.nvl(item.get("OKURINO"), "")%>"><nobr><%=item11%><%=okurinoMoji%></nobr></td>
								<%String item12 = StringUtil.nvl(item.get("SEIKYUU_ZEI_K_KIN"), "0");%>
								<td align="right" title="<app:NumberFormat numberValue="<%=item12%>"/>"><nobr><app:NumberFormat numberValue="<%=item12%>"/></nobr></td>
								<%String item13 = StringUtil.nvl(item.get("SYUKA_STATUS"), "");%>
								<td align="left" title="<%=item13%>"><nobr><%=item13%></nobr></td>
								<%String item14 = StringUtil.nvl(item.get("SYUKKA_OK_YMD"), "");%>
								<td align="center" title="<%=item14%>"><nobr><%=item14%></nobr></td>
								<%String item17 = StringUtil.nvl(item.get("TOIOUT_YMD"), "");%>
								<td align="center" title="<%=item17%>"><nobr><%=item17%></nobr></td>
							</tr>
						<%}%>
	 				</table>
			<%}%>
			<%if(list.size() == 0) {%>
				<font size="2">
					<%=Properties.getMsg("HA.MSG.001")%>
				</font>
			<%}%>
		<%}%>
		<app:pageinit/>
	</body>
</html>