<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader   ("Expires",0);
%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
<%-- root --%>
<%@ page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%-- JQuery --%>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/jquery-1.2.6.pack.js" charset="Shift_JIS"></script>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/jquery.cookie.js" charset="Shift_JIS"></script>
<%-- Calendar --%>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/calendar/calendar.js" charset="Shift_JIS"></script>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/calendar/calendar_init_jp.js" charset="Shift_JIS"></script>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/calendar/calendar-setup.js" charset="Shift_JIS"></script>
<%-- ECS Solution Javascript --%>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/Tool_Func.js" charset="Shift_JIS"></script>
<script type="text/javascript" src="<%=Constants.DOC_ROOT %>/js/ecs.js" charset="Shift_JIS"></script>
<%-- CSS --%>
<link href="<%=Constants.DOC_ROOT %>/css/default.css" REL="stylesheet" TYPE="text/css" MEDIA="all">
<%-- Calendar CSS --%>
<link rel="stylesheet" href="<%=Constants.URL_BASE %>/js/calendar/calendar.css">
<script type="text/javascript" charset="Shift_JIS">
	function _gotoLogin(){
		try{
			var allcookies = document.cookie;
			var pos = allcookies.indexOf("compString=");
			var v_compValue= "0000000000";
			if (pos!=-1){
				var start = pos + 11;
				var end = allcookies.indexOf(";",start);
				if (end == -1){
					end = allcookies.length;
				}
				v_compValue = allcookies.substring(start, end);
			}
			if(this.opener != null){
		    	try{
		    		window.parent.opener.top.location = "<%=Constants.URL_BASE%>/login?compString="+v_compValue;
		    		this.window.close();
		    	}catch(e){}
		    	
		    }else{
		    	top.location = "<%=Constants.URL_BASE%>/login?compString="+v_compValue;
		    }
		}catch(e){

		}
	}
</script>
