<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.HashMap"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>
<%
User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
Vector<HashMap<String,String>> listMenuCnt = (Vector<HashMap<String,String>>) request.getSession().getAttribute("countList");
String logoPicName = user.getLoginMenuImg();
%>
<HTML>
	<HEAD>
		<link rel="stylesheet" href="<%=Constants.DOC_ROOT%>/css/default.css" type="text/css">
        <META http-equiv="content-type" content="text/html;charset=shift_jis">
		<app:AjaxInitTag/>
		<TITLE></TITLE>
		<STYLE TYPE="text/css">										
		<!--															
		a:link { color: #000099; text-decoration: none }
		a:visited { color: #000099; text-decoration: none }
		a:hover { color: #FF0000; text-decoration:underline}
		p.path           { font-size: 12px; text-align: left; margin-top: 0; margin-bottom: 0 }
		p.path a   { color: #00f; text-decoration: none }
		p.path a:hover   { color: #f30 }
		DIV.c2  {
             width:120px;
			 color: #000066;
			 text-decoration:none;
             background-color:#FFFFFF;
             padding-top:2;padding-left:0;padding-right:0;padding-bottom:2;
		      border-top:#33CCFF Solid 1;
		      border-left:#33CCFF Solid 1;
		      border-bottom:#33CCFF Solid 1;
		      border-right:#33CCFF Solid 1}

		DIV.c3  {
             width:120px;
			 color: #AAAAAA;
			 text-decoration:none;
             background-color:#EEEEEE;
             padding-top:2;padding-left:0;padding-right:0;padding-bottom:2;
		     border-top:#AAAAAA Solid 1;
		     border-left:#AAAAAA Solid 1;
		     border-bottom:#AAAAAA Solid 1;
		     border-right:#AAAAAA Solid 1}
		 font.menu{
		    font-size       : 10pt;
		    font-weight     : normal;
		}	
		-->
		</STYLE>
		<script type="text/javascript">
			document.oncontextmenu=function (){window.event.returnValue=false;};
			//go top
	        function gotoTopAll(_url){
	            parent.location = _url;
	        }
	        
	        function changeWhCd(){
	        	var whCd = document.all("whCdList").value;
	        	var url = "<%=Constants.URL_BASE%>/e_asproLogin/menu";
				var parms = "strForwordFlg="+<%=Constants.PAGE_AJAX%>;
					parms = parms+"&whCd="+whCd;
				<app:AjaxSendRequest2Action params="parms" url="url" async="false"/>
	        }
	        
	        function goto(){
	        	parent.location = "<%=Constants.URL_BASE%>/e_asproLogin/menu";
			}
	        function gotoTop(_url){
	            //parent.frames["sub"].frames["main"].location = _url;
	        	parent.frames["sub"].frames["main"].location = "<%=Constants.URL_BASE%>/e_asproLogin/TopMenu";
	        }
	    	function setCnt(_cnt){
	        	var cntArr = _cnt.split(",");
	    		for (var i=0;i<_cnt.split(",").length;i++){
	    			var obj = $$("cnt"+i);
	    			if(obj.innerHTML!=cntArr[i]){
	    				obj.innerHTML = cntArr[i];
	    				if (cntArr[i]=="0"){
	    					$$("div1"+i).style.display="block";
	    					$$("div2"+i).style.display="none";
	    				}else{
	    					$$("div2"+i).style.display="block";
	    					$$("div1"+i).style.display="none";
	    				}
	    				
	    			}
	    		}
	    	}
		</script>
	</HEAD>
	<body style="margin:0">
		<TABLE STYLE="background-repeat:no-repeat;" WIDTH="100%" HEIGHT="50" BORDER="0" CELLSPACING="0" cellpadding="0" style="margin:0">
			<TR>
				<TD>
					<A onclick="gotoTopAll('<%=Constants.URL_BASE%>/e_asproLogin/menu');">
					<IMG onmouseover="this.style.cursor='hand'" SRC="<%=Constants.DOC_ROOT%>/img/<%=logoPicName %>" ALT="" HEIGHT="48" BORDER="0">
					</A>
				</TD>
				<TD BACKGROUND="<%=Constants.DOC_ROOT%>/img/head_bk.gif">
					<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="1">
						<% int cols = 6;
						int endFlg = 0;	//0:開始中 1:終了
						if(listMenuCnt.size() > 0){%>
							<%for (int i=0;i<listMenuCnt.size();i++){
								HashMap<String,String> lists = listMenuCnt.get(i);
							%>
								
								<%  String display1 = "";String display2 = "";
									if (StringUtil.stringEquals(lists.get("cnt"),"0")){
										display2="none";
										display1="block";
									} else {
										display1="none";
										display2="block";
									}%>
								<% if (user.getCoNo()==0){ %> 
									<% //if (i==0 || i==6 || i==12){ 
										if ((i+1)%cols == 1) {
											endFlg = 0;
										
									%>
									<TR>
									<%}%>
									<TD VALIGN="middle" NOWRAP WIDTH="120">
										<DIV CLASS="c3" id="div1<%=i%>" style="display:<%=display1%>">
											<FONT class="menu" SIZE="2">
												<IMG SRC="<%= Constants.DOC_ROOT%>/img/swith41.gif" ALT="" ALIGN="absmiddle" BORDER="0">
													<%=lists.get("name")%>（0）
											</FONT>
										</DIV>
										<DIV CLASS="c2" id="div2<%=i%>" style="display:<%=display2%>">
											<FONT class="menu" SIZE="2">
												<%if ("".equals(lists.get("url"))){%>
													<IMG SRC="<%= Constants.DOC_ROOT%>/img/swith40.gif" ALT="" ALIGN="absmiddle" BORDER="0">
													<%=lists.get("name")%>（<span id="cnt<%=i%>"><%=lists.get("cnt")%></span>）
												<%}else{ %>
													<A HREF="<%=Constants.URL_BASE%>/<%=lists.get("url")%>" TARGET="main">
													<IMG SRC="<%= Constants.DOC_ROOT%>/img/swith40.gif" ALT="" ALIGN="absmiddle" BORDER="0">
													<%=lists.get("name")%>（<span id="cnt<%=i%>"><%=lists.get("cnt")%></span>）
													</A>
												<%} %>
											</FONT>
										</DIV>
									</TD>
									<%// if (i==5 || i==11 || i==15){
										if ((i+1)%cols == 0) {
											endFlg = 1;%>
									</TR>
									<%}%>
								<%}%>
							<%} %>
						<%} %>
						<%if (endFlg==0){ %>
							<%for (int j = 0; j< (cols-listMenuCnt.size()%cols); j ++){ %>
								<TD></TD>
							<%} %>
						</TR>
						<%} %>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>