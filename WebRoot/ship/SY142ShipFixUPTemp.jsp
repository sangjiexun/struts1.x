<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@ include file="/taglibs.jsp" %>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.*"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp.ShipFixUPTempBean"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>

<%
	try{
		ShipFixUPTempBean bean = (ShipFixUPTempBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean,new String[]{"strErrMsg","list"});
	String strErrMsg = StringUtil.nvl(bean.getStrErrMsg());
%>
<html>
    <head>
        <TITLE>
        	�o�׎���CSV�o�^
        </TITLE>
        <style>
			.listdiv{
			    overflow:auto;
			    height: expression( this.scrollHeight > 400 ? "400px" : "auto" ); /* sets max-height for IE */
			    max-height: 400px; /* sets max-height value for all standards-compliant browsers */
			}
        </style>
	    <SCRIPT type="text/javascript">
	 	    function Submit(){
 	    		if(confirm("<%=Properties.getMsg("HA.MSG.003", false)%>")){
 	    			$("#_processType").val("<%=Constants.PAGE_INSERT%>");
 	    			$("#_Form").prop("action","<%=Constants.URL_BASE%>/e_asproShip/SY142ShipFixUPTemp");
 	    			$("#_Form").submit();
					btnDisabled();
 	    		}
		    }

			function Search(val){
				$("#_processType").val("<%=Constants.PAGE_SEARCH%>");
				$("#_searchStatus").val(val);
				$("#_Form").prop("action","<%=Constants.URL_BASE%>/e_asproShip/SY142ShipFixUPTemp");
				$("#_Form").submit();
				btnDisabled();
			}

			function Cannel() {
				$("#_processType").val("");
				$("#_cancelKbn").val("1");
				$("#_Form").prop("action","<%=Constants.URL_BASE%>/e_asproShip/SY141ShipFixUP");
				$("#_Form").submit();
				btnDisabled();
			}

			function Download() {
				$("#_processType").val("<%=Constants.PAGE_DOWNLOAD%>");
				$("#_Form").prop("action","<%=Constants.URL_BASE%>/e_asproShip/SY142ShipFixUPTemp");
				$("#_Form").submit();
				btnDisabled();
				setTimeout('btnAbled()', '10000');
			}
		</SCRIPT>
	</head>
    <BODY>
    	<IMG SRC="<%=Constants.DOC_ROOT%>/img/head_kefir_syukka.gif" ALT="" ALIGN="absmiddle" BORDER="0">
		<div class=pt>�o�׎���CSV�o�^</div>
    	<BR>
    	<BR>
		<div>�ȉ��̃f�[�^����荞�݂܂��B<BR>�X������΁A[�o�^����]�{�^�����N���b�N���Ă��������B(�G���[�̍s������ꍇ�͓o�^�ł��܂���)</div><BR>
		<b style="color:red"><%=strErrMsg%></b>
		<div class=pt2>�G���[���e�����m�F�̏�A�C�����ēx��荞�݂��Ă��������B</div>
			<table style="border:0px;">
			<tr>
			<td><font size="3">
				<label><input type="radio" name="searchStatus" value="0" onclick="Search(0);" <%=("0".equals(bean.getSearchStatus())?"checked":"") %>>�S��</label>
				<label><input type="radio" name="searchStatus" value="-1" onclick="Search(-1);" <%=("-1".equals(bean.getSearchStatus())?"checked":"") %>>�G���[����</label>
				<%if(bean.getErrorCnt() > 0){%>
				<label><input onclick="Download()" type="button" value="�G���[���eCSV�o��" TABINDEX="0" BORDER="0"></label>
				<%} %>
				</font>
			</td>
			</tr>
			</table>

		<form action="" id="_Form">
			<input type="hidden" name="strForwordFlg" value="2">
			<input type="hidden" name="searchKbn" value="2">
			<input type="hidden" name="processType" id="_processType" value="<%=Constants.PAGE_INSERT%>">
			<input type="hidden" name="cancelKbn" id="_cancelKbn" value="">

			<input type="hidden" name="csvupKbn" id="_csvUpKbn" value="<%= bean.getCsvupKbn() %>">
			<input type="hidden" name="searchStatus" id="_searchStatus" value="<%= bean.getSearchStatus() %>">
			<input type="hidden" name="whCd" id="whCd" value="<%= bean.getWhCd() %>">
			<input type="hidden" name="uploadNo" value="<%= bean.getUploadNo() %>">
			<input type="hidden" name="patternNo" value="<%= bean.getPatternNo() %>">
			<input type="hidden" name="fileNm" value="<%= bean.getFileNm() %>">
			<input type="hidden" name="uuid" value="<%= bean.getUuid() %>">
		</form>

		<%if("2".equals(bean.getSearchKbn())){%>
			<%List list = bean.getList(); %>
			<%List h_list = bean.getHeader_list(); %>
			<%if(list.size() > 0){%>
				<app:PageFeed/>
 				<TABLE CLASS="lt">
					<tr class="head">
						<th width="30" nowrap>No</th>
						<%String t_width="";
						for (int i = 0; i< h_list.size(); i++){
							HashMap<String, String> item_h = (HashMap<String, String>)h_list.get(i);%>
							<th width="100" nowrap><%=StringUtil.nvl(item_h.get("ITEM")) %></th>
						<%}%>
						<th width="350" nowrap>�G���[���R</th>
					</tr>
					<%
					for(int i = 0; i < list.size(); i++){
						HashMap<String, String> item1 = (HashMap<String, String>)list.get(i);
						//0�FOK�@1�F�x���@-1�F�G���[
						String errFlg = StringUtil.nvl(item1.get("STATUS"), "0");
						String bgcolor = "";
						//1�F�G���[�̍s�͔w�i�F��ԐF�ɂ���
						if (StringUtil.stringEquals(errFlg,"-1")) {
							bgcolor = "#ffcccc";
						//2:�x���̍s�͔w�i�F�����F�ɂ���
						} else if(StringUtil.stringEquals(errFlg,"1")) {
							bgcolor = "#ffffcc";
						} else{
							bgcolor = "";
						} %>
					<TR style="background-color:<%=bgcolor%>">
						<%-- No --%>
						<td align="center">
							<%=StringUtil.nvl(item1.get(Constants.PAGE_LINE_NO)) %>
						</td>
						<%for (int j = 0; j< h_list.size(); j++){%>
							<td width="125" nowrap title="<%=StringUtil.nvl(item1.get("STR"+String.valueOf(j+1))) %>">
								<nobr><%=StringUtil.nvl(item1.get("STR"+String.valueOf(j+1))) %></nobr>
							</td>
						<%
						}
						%>
						<%-- �G���[���R --%>
						<td title="<%=StringUtil.nvl(item1.get("ERR_MSG"), "&nbsp;")%>">
							<nobr><%=StringUtil.nvl(item1.get("ERR_MSG"), "&nbsp;")%></nobr>
						</td>
               		</TR>
					<%}%>
					</TABLE>
				<BR>
				<TABLE class="nb" style="WIDTH:650">
					<TR BGCOLOR="#ffcccc">
					<%if (bean.getErrorCnt() == 0) {
						//1���ł��G���[������ꍇ�́@�u�o�^����v�{�^���͕\�����Ȃ�%>
						<TD ALIGN="center" width="50%">
							<input type="button" onclick="Submit()" value="�o�^����" <app:PowerCheck fromId="<%=bean.getStrFromPageId() %>" baseId="SY141" value="<%=Constants.POWER_INSERT%>"/>/>
						</TD>
					<%} %>
						<TD ALIGN="center">
							<input type="button" onclick="Cannel()" value="�L�����Z������"/>
						</TD>
					</TR>
				</TABLE>
			<%} %>
			<%if(list.size() == 0){%>
				<font size="2">
					<%=Properties.getMsg("HA.MSG.001")%>
				</font>
			<%} %>
		<%} %>
		<app:pageinit tableInit="0"/>
    </BODY>
</html>
<%} catch (Exception e){
	PrintWriter ps = response.getWriter();
	ps.write("<!--");
	e.printStackTrace(ps);
	ps.write("-->");
} %>