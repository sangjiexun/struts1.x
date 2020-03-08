<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@ include file="/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function selectItem(){
			var url= "<%=Constants.URL_BASE%>/CC020SearchAddressee.act?callsSrciptFuncName=setValue";
		    url = url + "&customerCd=1";
		    url = url + "&strToolbarX=50";
		    _searchWin = window.open(url,"CC020","width=700,height=500,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		
		function setValue(_cd){
			document.getElementById("CC020").value=_cd;
		}
		
		function cc021(){
			var url= "<%=Constants.URL_BASE%>/CC021SearchProduct.act?callsSrciptFuncName=setValue21";
		    url = url + "&index=2";
		    url = url + "&strToolbarX=110";
		    _searchWin = window.open(url,"CC021","width=700,height=500,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		
		function setValue21(_cd,_index){
			document.getElementById("CC021_CD").value=_cd;
			document.getElementById("CC021_INDEX").value=_index;
		}
		function selectItem1(){
			var url= "<%=Constants.URL_BASE%>/CC024SearchSupplier.act?callsSrciptFuncName=setValue";
		    url = url + "&supplierKbn=1";
		    _searchWin = window.open(url,"CC020","width=700,height=500,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		function selectItemos024(v_kbn){
			var url= "<%=Constants.URL_BASE%>/CC024SearchSupplier.act?callsSrciptFuncName=setValue";
		    url = url + "&supplierKbn="+v_kbn;
		    _searchWin = window.open(url,"CC024","width=700,height=500,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		
		function setValue(_cd,_nm){
			document.getElementById("CC020").value=_cd;
			alert(_cd);
			alert(_nm);
		}
		function selectItemos023(){
			var url= "<%=Constants.URL_BASE%>/CC023SearchCustomer.act?callsSrciptFuncName=setValue";
		    _searchWin = window.open(url,"CC023","width=700,height=520,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		function selectItemos022(){
			var url= "<%=Constants.URL_BASE%>/CC022SearchCorp.act?callsSrciptFuncName=setValue";
		    _searchWin = window.open(url,"CC022","width=700,height=500,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		function selectItemos041(wv_kbn){
			var url= "<%=Constants.URL_BASE%>/CC041ScanList.act?idWorkKbn="+wv_kbn+"&uuid=123&no=1234&lineNo=1234&sorId=ST";
		    //alert(url);
		    //var url= "<%=Constants.URL_BASE%>/CC041ScanList.act?idWorkKbn=1&uuid=123&no=1234&lineNo=1234&sorId=ST";
		    _searchWin = window.open(url,"CC041","width=800,height=600,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		function selectItemos043(){
			var url= "<%=Constants.URL_BASE%>/CC043ScanidImd.act?uuid=123&corpCd=123";
		    _searchWin = window.open(url,"CC043","width=400,height=300,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
		function selectItemosOD033(){
			
			var url= "<%=Constants.URL_BASE%>/OD033ProductChange.act?orderCd=1000000164&callsSrciptFuncName=setValue";
		    _searchWin = window.open(url,"OD033","width=1100,height=600,menubar=no,location=no,status=yes,resizable=yes,scrollbars=yes");
	        _searchWin.focus();
		}
</script>
  </head>
  
  <body>
    <A href="javascript:selectItem();">cc020</A>
    
    <INPUT
		ID="CC020" 
		value=""
		>
	<br>
	
	<A href="javascript:cc021();">cc021</A>
    
    <INPUT
		ID="CC021_CD" 
		value=""
		>
	<INPUT
		ID="CC021_INDEX" 
		value=""
		>
		<A href="javascript:selectItem1();">cc024</A>
		
		<br>
		<A href="<%=Constants.URL_BASE%>/CC040Scan.act?modo=1&call=OT&productCd=B20&corpCd=2010&shipNo=9999&shipLineNo=1">OT：出荷実績、</A>
		<br>
		<A href="<%=Constants.URL_BASE%>/CC040Scan.act?modo=1&call=IN1&productCd=B20&corpCd=2010&shipNo=9999998&shipLineNo=1">IN1：入荷実績（新品）</A>
		<br>
		<A href="<%=Constants.URL_BASE%>/CC040Scan.act?modo=1&call=IN2&productCd=B20&corpCd=2010&shipNo=9999999&shipLineNo=1">IN2：入荷実績（撤去）</A>
		<br>
		<A href="<%=Constants.URL_BASE%>/CC040Scan.act?modo=1&call=ST&productCd=B20&corpCd=2010&locationBefore=locationBefore&locationAfter=locationAfter&statusBefore=statusBefore&statusAfter=statusAfter">ST:ステータス変更</A>
		<A href="<%=Constants.URL_BASE%>/CC050OutZaiko.act?corpPlntCd=9999&productCd=1985-1">在庫詳細</A>
		<A href="<%=Constants.URL_BASE%>/CC050OutZaiko.act?corpPlntCd=9999&productCd=1985-2">在庫詳細(1985-2)</A>
		<A href="javascript:selectItemos024('2');">os024</A>
    <A href="javascript:selectItemos024('1');">os024-1</A>
    
    
    <A href="javascript:selectItemosOD033();">OD033</A>
    
    <INPUT
		ID="CC020" 
		value=""
		>
		<br>
		
    <A href="javascript:selectItemos023();">os023</A>
    <A href="javascript:selectItemos022();">os022</A>
    <A href="javascript:selectItemos041('0');">os041-insert</A>
    <A href="javascript:selectItemos041('1');">os041-update</A>
    <A href="javascript:selectItemos043();">os043</A>
    
    <br>
	<A href="<%=Constants.URL_BASE%>/IN011InvInSignIMD.act?updateKbn=1&finishKbn=0&productKbn=1">ins端末IN011</A>
	<br>
	<A href="<%=Constants.URL_BASE%>/IN011InvInSignIMD.act?updateKbn=1&finishKbn=0&productKbn=2">ins資材IN011</A>
	<br>
	<A href="<%=Constants.URL_BASE%>/IN011InvInSignIMD.act?updateKbn=2&finishKbn=0&inTargetNo=1000000006&productKbn=1&productKbn=1&inFlg=A">upd端末IN011</A>
	<br>
	<A href="<%=Constants.URL_BASE%>/IN011InvInSignIMD.act?updateKbn=2&finishKbn=0&inTargetNo=1000000006&productKbn=2">upd資材IN011</A>
	<A href="<%=Constants.URL_BASE%>/IN012InvInSignIDList.act?inTargetNo=9999997&inTargetLineNo=1&corpPlntCd=9999">IN012</A>
  </body>
</html>
