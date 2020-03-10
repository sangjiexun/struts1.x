<%@page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.CommonService"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.tag.SelectTag.Option"%>
<%@page import="jp.co.tcc.ecs.e_asproShip.SY050ShijiFixEntry.ShijiFixEntryBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@include file="/taglibs.jsp"%>

<%
 	ShijiFixEntryBean bean = (ShijiFixEntryBean)request.getAttribute("bean");
 	EscapeBean.escapeBeanAllProperty(bean, new String[]{"strErrMsg"});
 	String strErrMsg = StringUtil.nvl(bean.getStrErrMsg());
 	String strExclMsg = StringUtil.nvl(bean.getStrExclMsg());
 %>

<html>
	<head>
		<title>
			�o�׎w���m��
		</title>
	    <script type="text/javascript">
			var vConfirmMsg = "<%=Properties.getMsg("HA.MSG.004")%>";

			// ����
		    function Search() {
				$$("_processType").value = "<%=Constants.PAGE_SEARCH%>";
				$$("_Form").action = "<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry"
				$$("_Form").submit();
				btnDisabled();
		    }

		    function kobetu() {
		    	if (!checkBox()) {
	           		return;
	           	}
	           	if(confirm(vConfirmMsg)){
		 	    	$$("_processType2").value = "<%=ShijiFixEntryBean.PAGE_KOBETU%>";
					$$("_Form2").action = "<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry"
					$$("_Form2").submit();
					btnDisabled();
				}
		    }

		    function ikkatu() {
		    	if(confirm(vConfirmMsg)){
		 	    	$$("_processType").value = "<%=ShijiFixEntryBean.PAGE_IKKATU%>";
					$$("_Form").action = "<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry"
					$$("_Form").submit();
					btnDisabled();
				}
		    }

		    //�I���`�F�b�N
		    function checkBox(){
		    	var checkCount = 0;
		    	$("input[name=seq_no]").each(
		    		function(i,checkbox){
	    					if(checkbox.checked == true){
	    						checkCount++;
	    					}
	    				}
		    	);
		    	if(checkCount == 0){
		    		var msg ="<%=Properties.getMsg("HA.ERROR.022",new String[] {"���׃f�[�^" },true)%>".replace("<br>","");
		    		alert(msg);
		    		return false;
		    	}else{
		    		return true;
		    	}
		    }

			//CSV�o��
			function csvDownLoad(){
				$("#js_csvDownKbn").val(<%=bean.CSV_DOWN_KBN%>);
				$$("_processType").value = "<%=Constants.PAGE_DOWNLOAD%>";
				$$("_Form").action = "<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry"
				$$("_Form").submit();
				$$("js_btnHeadDownload").disabled=true;
				setTimeout('enableDownLoad()', '3000');
			}
			function enableDownLoad(){
				$$("js_btnHeadDownload").disabled = false;
			}

			//�x�ЕύX���x�X���擾
			function getSitenList(_val){
				$('#siten_cd > option').remove();
				if( _val ==""){
					return;
				}
				var url = "<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry";
				var parms = "processType="+<%=Constants.PAGE_AJAX%>;
				parms = parms+"&sisya_cd="+_val;
				<app:AjaxSendRequest2Action params="parms" url="url" async="true"/>
			}
	    </script>
	</head>
	<body>
		<img src="<%=Constants.DOC_ROOT%>/img/head_kefir_syukka.gif" alt="" align="absmiddle" border="0">
    	<div class=pt>�o�׎w���m��</div>
		<br>
		<b style="color:red"><%=strErrMsg%><%if(!StringUtil.isNull(strExclMsg)){ %><%=Properties.getMsg(strExclMsg)%><% }%></b>
		<br>
    	<form action="<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry" id="_Form">
        	<input type="hidden" name="strForwordFlg" value="2">
        	<input type="hidden" name="searchKbn" value="2">
        	<input type="hidden" name="processType" id="_processType">
        	<input type="hidden" name="intRecordCountExcl" id="_intRecordCountExcl" value="<%=bean.getIntRecordCountExcl()%>">
        	<input type="hidden" name="csvDownKbn" id="js_csvDownKbn" value="<%=bean.getCsvDownKbn()%>">
        	<app:div hiddenFlagWidth="750">
	        	<table class="nb">
	        		<tr>
	        			<td>
	        				<table class="sf">
								<col style="width:110px;"/>
								<col style="width:260px;"/>
								<col style="width:110px;"/>
								<col style="width:260px;"/>
								<tr>
									<th>�o�ח\���</th>
									<td>
										<app:calender
											startYmdId="syukka_yotei_ymd_s"
											startYmdName="syukka_yotei_ymd_s"
											startYmdvalue="<%=bean.getSyukka_yotei_ymd_s()%>"
											msg="�o�ח\���(�J�n��)"
										/>
										�`
										<app:calender
											startYmdId="syukka_yotei_ymd_e"
											startYmdName="syukka_yotei_ymd_e"
											startYmdvalue="<%=bean.getSyukka_yotei_ymd_e()%>"
											msg="�o�ח\���(�I����)"
										/>
									</td>
									<th>�����ԍ�</th>
									<td>
										<input
											type="text"
				                        	value="<%=bean.getOrder_cd()%>"
				                        	onkeypress="_chkInp();"
				                        	class="code"
				                        	maxlength="10"
				                        	name="order_cd"
				                        	size="16">
										<app:searchflg searchFlg="1"/>
									</td>
								</tr>

								<tr>
									<th>�o�הԍ�</th>
									<td>
										<input
											type="text"
				                        	value="<%=bean.getSksj_cd()%>"
				                        	class="code"
				                        	maxlength="30"
				                        	name="sksj_cd"
				                        	onkeypress="_chkInp();"
				                        	size="20">
										<app:searchflg searchFlg="1"/>
									</td>
									<th>�A�b�v���[�hNo</th>
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
								</tr>
								<tr>
									<th>�x��</th>
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
									<th>�x�X</th>
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
									<th>��t�XCD</th>
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
									<th>��t�X��</th>
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
									<th>���n�XCD</th>
									<td>
										<input
											type="text"
											value="<%=bean.getTdk_cd()%>"
											class="code"
											maxlength="7"��
											name="tdk_cd"
											onkeypress="_chkInp();"
											size="12">
										<app:searchflg searchFlg="1"/>
									</td>
									<th>�q�ɖ�</th>
									<td>
										<%if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {%>
											<%=CommonService.getSoukoNm(StringUtil.nvl(bean.getLoginUser().getCoNo()), bean.getWh_cd())%>
										<%} else { %>
											<% List<Option> resultWhNm = CommonService.getSoukoOptionList(StringUtil.nvl(bean.getLoginUser().getCoNo()), true);%>
											<app:select
												name="wh_cd"
												id="wh_cd"
												options="<%=resultWhNm%>"
												selectedValues="<%=bean.getWh_cd() %>"/>
										<%} %>
									</td>
								</tr>
								<tr>
									<th>������</th>
									<td>
										<app:calender
				                           	startYmdId="order_ymd_s"
				                            startYmdName="order_ymd_s"
				                            startYmdvalue="<%=bean.getOrder_ymd_s()%>"
				                            msg="������(�J�n��)"
				                        />
										�`
										<app:calender
				                           	startYmdId="order_ymd_e"
				                            startYmdName="order_ymd_e"
				                            startYmdvalue="<%=bean.getOrder_ymd_e()%>"
				                            msg="������(�I����)"
				                        />
									</td>
									<th>�z�B�w���</th>
									<td>
										<app:calender
				                           	startYmdId="tdk_ymd_s"
				                            startYmdName="tdk_ymd_s"
				                            startYmdvalue="<%=bean.getTdk_ymd_s()%>"
				                            msg="�z�B�w���(�J�n��)"
				                        />
										�`
										<app:calender
				                           	startYmdId="tdk_ymd_e"
				                            startYmdName="tdk_ymd_e"
				                            startYmdvalue="<%=bean.getTdk_ymd_e()%>"
				                            msg="�z�B�w���(�I����)"
				                        />
									</td>
								</tr>
								<tr>
									<th>���i�R�[�h</th>
									<td>
										<input
											type="text"
					                       	value="<%=bean.getItem_cd1_s()%>"
					                       	class="code"
					                       	maxlength="20"
					                       	name="item_cd1_s"
					                       	onkeypress="_chkInp();"
					                       	size="12">
				                   		 �`
										<input
											type="text"
					                       	value="<%=bean.getItem_cd1_e()%>"
					                       	class="code"
					                       	maxlength="20"
					                       	name="item_cd1_e"
					                       	onkeypress="_chkInp();"
					                       	size="12">
									</td>
									<th>���i��</th>
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
								</tr>
								<tr>
									<th>�o�׋敪</th>
									<td>
										<app:KubunList
											id="shipKbn"
											name="shipKbn"
											fieldNm="SHIP_KBN"
											selectedValue="<%=bean.getShipKbn() %>"
											addBlank="true"/>
									</td>
									<th>��/��ďo��</th>
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
										<input type="button" onClick="Search(2);" value="����"/>
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
                <table class="nb">
					<tr>
						<td width="150" align="left">
							&nbsp;&nbsp;&nbsp;��<INPUT type="button" <app:PowerCheck baseId="SY050" value="<%=Constants.POWER_FIX%>"/> onclick="kobetu()" VALUE="�I���m��" BORDER="0">
						</td>
						<td width="150" align="center" nowrap bgcolor="#ffff66">
						<input type="button" id="js_btnHeadDownload" <app:PowerCheck baseId="SY050" value="<%=Constants.POWER_CSVDOWN %>"/> value="CSV�o��" onClick="csvDownLoad();"/>
						</td>
						<td width="750">
						</td>
						<td width="150" align="center" bgcolor="#ffcc66">
							<INPUT type="button" <app:PowerCheck baseId="SY050" value="<%=Constants.POWER_FIX%>"/> onclick="ikkatu()" VALUE="�ꊇ�m��" BORDER="0">
						</td>
					</tr>
				</table>
				<form action="<%=Constants.URL_BASE%>/e_asproShip/SY050ShijiFixEntry" id="_Form2" method="post">
				<input type="hidden" name="processType" id="_processType2">
				<%
					ManageUtils manageUtils = bean.getManageUtils(request);
					HashMap mapParams = manageUtils.getPageFeedActionURL(ManageUtils.PAGEFEED_DEFAULT_URL).getMapParams();
					for (Iterator iter = mapParams.keySet().iterator(); iter.hasNext();) {
						String key = StringUtil.nvl(iter.next());
						String val =  StringUtil.nvl(mapParams.get(key));
				%>
					<input type="hidden" name="<%=key%>" id="<%=key.toString()%>" value="<%=val %>" />
				<%}%>

				<table class="lt" width="1260">
					<col width="30">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="100">
					<col width="80">
					<col width="70">
					<col width="200">
					<col width="70">
					<col width="200">
					<col width="80">
					<col width="100">
					<tr class="head">
						<th><app:checkAll targetName="seq_no"></app:checkAll></th>
						<th>�o�ח\���</th>
						<th>�o�הԍ�</th>
						<th>������</th>
						<th>�����ԍ�</th>
						<th>�A�b�v���[�hNo</th>
						<th>�x��</th>
						<th>��t�XCD</th>
						<th>��t�X��</th>
						<th>���n�XCD</th>
						<th>���n�X��</th>
						<th>�z�B�w���</th>
						<th>�q�ɖ�</th>
					</tr>
				<%
					for (int i = 0; i < list.size(); i++) {
						HashMap item = (HashMap) list.get(i);
				%>
					<tr>
						<td align="center">
							<Input type="checkbox" name="seq_no" value ="<%=item.get("SEQ_NO")+","+item.get("F_EA021_EXCL_CNT")+","+item.get("F_EA025_EXCL_CNT")%>"/></td>
						<!-- �o�ח\��� -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("SYUKKA_YOTEI_YMD"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("SYUKKA_YOTEI_YMD"), "&nbsp;")%></nobr></TD>
						<!-- �o�הԍ� -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("SKSJ_CD"), "&nbsp;")%>">
							<nobr><a href="<%=Constants.URL_BASE%>/e_asproShip/SY010ShijiEntry?seq_no=<%=StringUtil.nvl(item.get("SEQ_NO"), "0")%>">
									<%=StringUtil.nvl(item.get("SKSJ_CD"), "")%></a></nobr></TD>
						<!-- ������ -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("ORDER_YMD"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("ORDER_YMD"), "&nbsp;")%></nobr></TD>
						<!-- �����ԍ� -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("ORDER_CD"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("ORDER_CD"), "&nbsp;")%></nobr>
						</TD>
						<!-- �A�b�v���[�hNo -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("UPLOAD_NO"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("UPLOAD_NO"), "&nbsp;")%></nobr></TD>
						<!-- �x�� -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("SISYA_NAME"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("SISYA_NAME"), "&nbsp;")%></nobr></TD>
						<!-- ��t�XCD -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("KOKYAKU_CD"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("KOKYAKU_CD"), "&nbsp;")%></nobr></TD>
						<!-- ��t�X�� -->
						<TD align="left" title="<%=StringUtil.nvl(item.get("KOKYAKU_NM"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("KOKYAKU_NM"), "&nbsp;")%></nobr></TD>
						<!-- ���n�XCD -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("TDK_CD"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("TDK_CD"), "&nbsp;")%></nobr></TD>
						<!-- ���n�X�� -->
						<TD align="left" title="<%=StringUtil.nvl(item.get("TDK_NM"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("TDK_NM"), "&nbsp;")%></nobr></TD>
						<!-- �z�B�w��� -->
						<TD align="center" title="<%=StringUtil.nvl(item.get("TDK_YMD"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("TDK_YMD"), "&nbsp;")%></nobr></TD>
						<!-- �q�ɖ� -->
						<TD align="left" title="<%=StringUtil.nvl(item.get("WH_NM1"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item.get("WH_NM1"), "&nbsp;")%></nobr></TD>
					</tr>
				<%  } %>
	 			</table>
		 		</form>
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