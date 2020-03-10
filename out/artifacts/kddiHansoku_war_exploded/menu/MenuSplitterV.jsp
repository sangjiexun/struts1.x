<%@ include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<%
User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
String bgcolor = user.getLoginMenuColor();
%>
<html>
<head>
	<link rel="stylesheet" href="<%=Constants.DOC_ROOT%>/css/default.css" type="text/css">
		<CSSCRIPTDICT IMPORT>
			<SCRIPT TYPE="text/javascript" SRC="<%=Constants.DOC_ROOT%>/js/CSScriptLib.js"></SCRIPT>
		</CSSCRIPTDICT>
		<CSACTIONDICT>
			<SCRIPT TYPE="text/javascript"><!--
				var preloadFlag = true;
				function preloadImages() {
					if (document.images) {
						preloadFlag = true;
					}
				}
// --></SCRIPT>
		</CSACTIONDICT>
  		<STYLE TYPE="text/css">
		<!--
		 font.menu{
			font-size		: 10pt;
			font-weight 	: normal;
		}	
		-->
		</STYLE>
	<script type="text/javascript">
		var timeID = null;
		var flag = -1;
		var MaxLeftHeight = 50;
		function to_change(){
			flag = flag * -1;
			move();
		}
		function move(){
			var framewidth;
			var speed = 15;
			var interval = 10;	
			framewidth = parent.document.getElementById("head").height;

			if(flag == 1){
				if(framewidth>35){interval = 10;speed = 10;}
				else if(framewidth>5 && framewidth <=15){interval = 30;speed = 7;}
				else if(framewidth>0 && framewidth <=5){interval = 60;speed = 5;	}}
				else{if(framewidth < 80){interval = 10;speed = 10;}
				else if(framewidth>=80 && framewidth <100){interval = 30;speed = 7;}
				else if(framewidth>=100 && framewidth <MaxLeftHeight){interval = 60;speed = 1;	}
			}

			framewidth = framewidth - speed*flag;

			if ( framewidth <= 0 || framewidth >=MaxLeftHeight ){
				clearTimeout(timeID);
				if(flag ==-1){
					document.getElementById("js_splitter").src="<%=Constants.DOC_ROOT%>/img/bwp_splitter_v_u.gif";
				} else {
					document.getElementById("js_splitter").src="<%=Constants.DOC_ROOT%>/img/bwp_splitter_v_d.gif";
				}
			} else {
				timeID = setTimeout("move()",interval);	
			}
			
			parent.document.getElementById("headframeset").rows =framewidth + ",44,*";
		}
		function gotoTop(_url){
			parent.frames["sub"].frames["main"].location = _url;
		}
		function returnLogin(){
			//_gotoLogin();
			parent.location = "<%=Constants.URL_BASE%>/e_asproLogin/menu?strForwordFlg=4";
		}

	</script>
</head>
	<body width="100%" class="splitter_v" style="margin-top: 0; margin-left: 0px; margin-right: 0;">
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" STYLE="background-repeat:no-repeat;margin:0;" BGCOLOR="<%=bgcolor %>">	
			<TR HEIGHT="30">
				<td HEIGHT="30"><BR>
				</td>
				<td ALIGN="center" HEIGHT="30">
					<table BORDER="0" CELLSPACING="2" CELLPADDING="1" STYLE="background-repeat:no-repeat;margin:0;">
						<tr>
							<td NOWRAP align="right"><b><font color="white" size="2">User:</font></b></td>
							<td NOWRAP width="350" bgColor="white"><font class="menu" size="2"><%=user.getUserName()%></font></td>
							<td NOWRAP>
								<a onmouseover="changeImages('logout','<%=Constants.DOC_ROOT%>/img/logout2.gif');return true"
								   onmouseout="changeImages('logout','<%=Constants.DOC_ROOT%>/img/logout.gif');return true"
								   target="_top"
								   style="text-decoration: none"
								   onclick="returnLogin();" href="javascript:;"/>
								<img id="logout" alt="" src="<%=Constants.DOC_ROOT%>/img/logout.gif" width="70" border="0" name="logout">
								</a>
							</td>
						</tr>
					</table>
				</td>
				<td align="right" width="30">
				</td>
			</TR>
		</TABLE>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			<tr valign="top">
				<td align="center" height="4" style="margin-top:0" bgcolor="#CCCCCC" >
					<img id="js_splitter" onClick="to_change();" src="<%=Constants.DOC_ROOT%>/img/bwp_splitter_v_u.gif" border="0">
				</td>
			</tr>
		</table>
	</body>
</html>