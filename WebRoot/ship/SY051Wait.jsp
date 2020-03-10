<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@page import="java.net.URLDecoder"%>
<%@ include file="/taglibs.jsp" %>

<%
	request.setCharacterEncoding("Windows-31J");
	String submiturl = Constants.URL_BASE + "/e_asproShip/SY050ShijiFixEntry?"
					+ "&processType="+(String)request.getParameter("processType")
					+ "&searchKbn="+(String)request.getParameter("searchKbn")
					+ "&intRecordCountExcl="+(String)request.getParameter("intRecordCountExcl")
					+ "&syukka_yotei_ymd_s="+(String)request.getParameter("syukka_yotei_ymd_s")
					+ "&syukka_yotei_ymd_e="+(String)request.getParameter("syukka_yotei_ymd_e")
					+ "&order_cd="+(String)request.getParameter("order_cd")
					+ "&sksj_cd="+(String)request.getParameter("sksj_cd")
					+ "&upload_no="+(String)request.getParameter("upload_no")
					+ "&sisya_cd="+(String)request.getParameter("sisya_cd")
					+ "&siten_cd="+(String)request.getParameter("siten_cd")
					+ "&kokyaku_cd="+(String)request.getParameter("kokyaku_cd")
					+ "&kokyaku_nm="+(String)request.getParameter("kokyaku_nm")
					+ "&tdk_cd="+(String)request.getParameter("tdk_cd")
					+ "&wh_cd="+(String)request.getParameter("wh_cd")
					+ "&order_ymd_s="+(String)request.getParameter("order_ymd_s")
					+ "&order_ymd_e="+(String)request.getParameter("order_ymd_e")
					+ "&tdk_ymd_s="+(String)request.getParameter("tdk_ymd_s")
					+ "&tdk_ymd_e="+(String)request.getParameter("tdk_ymd_e")
					+ "&make_ymd_e="+(String)request.getParameter("make_ymd_e")
					+ "&make_hms_e="+(String)request.getParameter("make_hms_e")
					+ "&item_cd1_s="+(String)request.getParameter("item_cd1_s")
					+ "&item_cd1_e="+(String)request.getParameter("item_cd1_e")
					+ "&item_nm1="+(String)request.getParameter("item_nm1")
					+ "&shipKbn="+(String)request.getParameter("shipKbn")
					+ "&orderKbn="+(String)request.getParameter("orderKbn");

	String[] seq_no=(String[])request.getParameterValues("seq_no");
	if (seq_no != null && seq_no.length>0){
		for (String ship:seq_no){
			submiturl=submiturl+ "&seq_no="+ship;
		}
	}
%>
<html>
    <head>
    	<META HTTP-EQUIV="refresh" CONTENT="1;URL='<%=submiturl %>'">
        <TITLE>
        	出荷指示確定
        </TITLE>
	</head>
    <BODY>
    	<IMG SRC="<%=Constants.DOC_ROOT%>/img/head_kefir_syukka.gif" ALT="" ALIGN="absmiddle" BORDER="0">
		<div class=pt>出荷指示確定</div>
		<br>
		<br>
    	<div class=pt>処理中です・・・</div>
    	<IMG SRC="<%=Constants.DOC_ROOT %>/img/searching1.gif" BORDER="0" HEIGHT="35" WIDTH="35" ALIGN="MIDDLE">
    </BODY>
</html>