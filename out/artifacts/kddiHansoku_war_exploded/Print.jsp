<%@ include file="/taglibs.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.CommonServlet"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%try{ %>
<html>
  <head>
    <title>プレビュー</title>
    <%ArrayList<HashMap<String,String>> print=(ArrayList<HashMap<String,String>>)request.getSession().getAttribute("print");
    String recallUrl = (String)request.getSession().getAttribute("recallUrl");
    HashMap<String,String> recallMap = (HashMap<String,String>)request.getSession().getAttribute("recallMap");
    String printFlg = (String)request.getSession().getAttribute("printFlg");
    %>
	<app:AjaxInitTag/>
	<script type="text/javascript">
	<!-- hide this script tags contents from old browsers
		var error=false;

		function printFinish(){
			try{
				window.parent.opener.Search();
				window.opener = "";
                window.open("","_self");
                window.close();
			}catch(e){}
		}

		function WfrOpen(){
			try{
				window.parent.opener.Search();
			}catch(e){}

			<%
				String path = request.getContextPath();
				String basePath = CommonServlet.getFullSendRedirectURL("",request);
			%>

			Wfrfc1.WindowStyle = "9";
	    	Wfrfc1.Zoom = "-1";
	    	Wfrfc1.DownloadMode = 19;
	    	<%for (int i=0;i<print.size();i++){%>
	    		var obj = document.getElementById("Wfrfc"+(<%=i%>+1));

				var tempFileName = "<%=print.get(i).get("tempFileName")%>";
				var tempName = "<%=print.get(i).get("tempName")%>";
				var fileName = "<%=print.get(i).get("fileName")%>";

				var tempFileName2 = "<%=print.get(i).get("tempFileName2")%>";
				var fileName2 = "<%=print.get(i).get("fileName2")%>";
		    	try{
					obj.PrinterName = "<%=print.get(i).get("name")%>";
		    	}catch(e){
					error=true;
				}
				obj.SetDataText(tempFileName,"<%=basePath%>Print.act?fileName="+fileName,"","",0);
				if (fileName2!=""&&fileName2!="null"){
					obj.SetDataText(tempFileName2,"<%=basePath%>Print?fileName="+fileName2,"","",0);
				}
				obj.Open("<%=basePath%>reports/"+tempName);

				<%if("1".equals(printFlg)){%>
					WfrPrint(<%=i+1%>);
				<%}%>

				<%if(i==print.size()-1&&"1".equals(printFlg)){%>
					window.opener = "";
                    window.open("","_self");
                    window.close();

				<%}%>
			<%}%>

		}

		window.confirm= function(){
			return true;
		}

		function WfrPrint(_index){
			//modify by dhk 2010/06/21 begin
			if(error){
				//alert("印刷機の初期設定は正確ではないので、手動で設定して下さい。");
				//WfrPrintConfig(_index);
				var obj = document.getElementById("Wfrfc"+_index);
				obj.PrinterName = "";
				obj.PrintOut("",0,0);
			}else{
				var obj = document.getElementById("Wfrfc"+_index);
				obj.PrintOut("",0,0);
			}
			//modify by dhk 2010/06/21 end
		}

		function WfrPrintConfig(_index){
			var obj = document.getElementById("Wfrfc"+_index);
			obj.PrintOutFromDialog();
		}

		function WfrPrint_all(){
			<%for (int i=0;i<print.size();i++){%>
				var obj = document.getElementById("Wfrfc"+(<%=i%>+1));
				try{
			    	obj.PrintTray  = "<%=print.get(i).get("tray")%>";
					obj.PrinterName = "<%=print.get(i).get("name")%>";
				}catch(e){
					error=true;
				}

				WfrPrint(<%=i+1%>);
			<%}%>

			<%if("0".equals(printFlg)){%>
				var url = "<%=basePath+recallUrl%>";
				var parms = "a=1";
				<%for(Map.Entry<String, String> entry:recallMap.entrySet()){%>
				parms+="&<%=entry.getKey()+"="+entry.getValue()+"\""%>;
				<%}%>
				<app:AjaxSendRequest2Action params="parms" url="url" debug="0"/>
			<%}%>
		}

		function WfrSetZoom(){
			Wfrfc1.Zoom = document.InfoForm.zoom.value;
		}
		//-- done hiding from old browsers -->
	</script>
</head>
<BODY BACKGROUND="#ffffff"  onLoad="WfrOpen();">
<FONT CLASS="ST">プレビュー</FONT>
<BR>
<form name="InfoForm" style="margin:0px;">
<table border=0>
<tr>
<td width="100" align="left">表示倍率設定:</td>
<td align="left" width="120">
<SELECT name="zoom" onchange="WfrSetZoom();">
<OPTION value="200">200%</OPTION>
<OPTION value="100">100%</OPTION>
<OPTION value="75">75%</OPTION>
<OPTION value="50">50%</OPTION>
<OPTION value="-1" SELECTED>ページ幅を基準</OPTION>
<OPTION value="-2">ページ全体を表示</OPTION>
</SELECT>
</td>
<td width="300" align="center">
<INPUT type="button" value="印刷" onClick="WfrPrint_all()">
</td>
</tr>
</table>
<hr size=1 NOSHADE style="margin:0px;">
</form>

<%for (int i=0;i<print.size();i++){%>
	<div align=center>
	<OBJECT ID="Wfrfc<%=i+1%>" CLASSID="CLSID:74F04531-5AF1-11D4-A7EA-0090FE02F720"
	 CODEBASE="<%=Constants.URL_BASE%>/e_asproLogin/reports/wfrprv10.cab#version=1,5,0,751" height="85%" width="99%">
	<SPAN STYLE="color:red">ActiveX control failed to load!
	 -- Please check browser security settings.</SPAN>
	</OBJECT>
	</div>
<%}%>

</BODY>
</html>
<%}catch(Exception e){
	e.printStackTrace();
} %>