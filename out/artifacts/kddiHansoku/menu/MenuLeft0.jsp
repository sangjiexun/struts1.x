<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="java.util.List"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.HA070Menu.Menu"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproUser.User.User"%>
<%@page import="jp.co.tcc.ecs.e_asproLogin.HA070Menu.MenuHelper"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.CommonService"%>
<%
try{
	User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
	List<Menu> menuList = (List<Menu>) request.getSession().getAttribute("_menu");
	if (menuList == null) {
		menuList = MenuHelper.getMenu(user,request.getSession());
	}

%>
<html>
	<HEAD>
	    <TITLE>ÉÅÉjÉÖÅ[</TITLE>
	    <SCRIPT TYPE="text/javascript" SRC="<%=Constants.DOC_ROOT%>/js/LeftMenu/accordian-src.js" charset="Shift_JIS"></SCRIPT>
		<script type="text/javascript">
    		function goto(_url){
    			if (_url != ""){
    				_url = "<%=Constants.URL_BASE%>/"+_url;
    			}
    			if(_url.indexOf("?")>0){
					parent.frames["main"].location = _url+"&_fromMenu=1";
				}else{
					parent.frames["main"].location = _url+"?_fromMenu=1";
				}
			}
		</script>
		<STYLE TYPE="text/css">
		<!--

		* {
			margin:0;
			padding:0;
			list-style:none;
		}
		body {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 14px;
			margin:5px;
			color: #666666;
		}

		#basic-accordian{
			border:0px solid #333333;
			padding:0px;
			width:150px;
		}

		.accordion_headings{
			padding:3px;
			background-color:#CCCCCC;
			background-image: url("<%=Constants.DOC_ROOT%>/img/menubg1.gif"); 
			color:#333333;
			border:1px solid #666666;
			cursor:pointer;
		}

		.accordion_headings:hover{
			color:#333333;
			background-color:#FFFFFF;
			background-image: url("<%=Constants.DOC_ROOT%>/img/menubg3.gif"); 
		}

		.accordion_child{
			padding:0px;
			background:#FFFFFF;
			width:150px;
		}

		.header_highlight{
			color:#000000;
			background-color:#FFCC99;
			background-image: url("<%=Constants.DOC_ROOT%>/img/menubg2.gif"); 
		}
		-->
		</STYLE>
		<STYLE TYPE="text/css">
		<!--
		a:link { text-decoration:none;color:#0589F6; }
		a:visited { text-decoration:none;color:#0589F6; }
		a:hover { text-decoration:none;color:#FF3300; }
		-->
		</STYLE>
	</HEAD>
	<BODY BGCOLOR="#ffffff" LINK="#000066" VLINK="#000066" ONLOAD="new Accordian('basic-accordian',10,'header_highlight');" BACKGROUND="<%=Constants.DOC_ROOT%>/img/sub_menu_bg.gif" LINK="#000066" VLINK="#000066" LEFTMARGIN="0" style="background-repeat:repeat;">
		<DIV ID="basic-accordian">
<%
            String lastTopMenuNo = "";
            String listTitleMenuName = "";
            int subMenuAddCnt = 0;
            int topMenuAddCnt = 0;
            
            String topHeaderId = "";
            String ContentId = "";
            String firstWordForSubMenu = "";
            
            for(int i=0;i<menuList.size();i++){
                topMenuAddCnt += 1;
                subMenuAddCnt += 1;
                Menu menu = menuList.get(i);
                //create top
                if(!lastTopMenuNo.equals(menu.getTopMenuNo())){
                    lastTopMenuNo = menu.getTopMenuNo();
                    topHeaderId = "test" + lastTopMenuNo + "-header";
                    ContentId = "test" + lastTopMenuNo + "-content";
            %>
            <DIV ID="<%=topHeaderId %>" CLASS="accordion_headings">
                <TABLE  BORDER="0" CELLSPACING="0" CELLPADDING="2">
                <TR>
                    <TD>
                    <IMG SRC="<%=Constants.DOC_ROOT%>/img/<%=menu.getIconImageUrl()%>" HEIGHT="20" WIDTH="23" ALIGN="absmiddle">
                    <B><%=menu.getTopMenuName() %></B>
                    </TD>
                </TR>
                </TABLE>
            </DIV>
            <DIV ID="<%=ContentId %>">
                <DIV CLASS="accordion_child">
                    <TABLE BORDER="0" CELLSPACING="3" CELLPADDING="3">
                <%  }
                
                    //create title
                    if(!listTitleMenuName.equals(menu.getTitleMenuName())){
                        listTitleMenuName = menu.getTitleMenuName();
                %>
                        <TR>
                            <TD NOWRAP colspan="2"><FONT SIZE="2"><B><%=menu.getTitleMenuName()%></B></FONT></TD>
                        </TR>
                <%  }
                    
                    //check is last line
                    /**
                    if(StringUtil.toString(subMenuAddCnt).equals(menu.getSubMenuCnt())){
                        subMenuAddCnt = 0;
                        firstWordForSubMenu = "ÑØ";
                    }
                    else{
                        firstWordForSubMenu = "Ñ∞";
                    }
                    **/
                %>
                        <TR ONMOUSEOVER="this.style.backgroundColor='#ffff99'" ONMOUSEOUT="this.style.backgroundColor='#FFFFFF'">
                            <!-- <TD ALIGN="right" VALIGN="top" WIDTH="23"><FONT color="" SIZE="2"><%=firstWordForSubMenu %></FONT></TD> -->
							<TD NOWRAP WIDTH="22"><IMG SRC="<%=Constants.DOC_ROOT%>/img/arrow_b.gif" ALT="" HEIGHT="13" WIDTH="22" BORDER="0"></TD>
                            <TD NOWRAP><A HREF="#" onclick="goto('<%=menu.getSubNenuActionUrl()%>')"><FONT SIZE="2"><%=menu.getSubMenuName() %></FONT></A></TD>
                        </TR>
                <%  
                    //close top
                    if(StringUtil.toString(topMenuAddCnt).equals(menu.getTopMenuCnt())){
                        topMenuAddCnt = 0;
                %>
                    </TABLE>
                </DIV>
            </DIV>
            <%  
                }
            }
            %>
		</DIV>
	</BODY>

</HTML>
<%
} catch (Exception e) {
	e.printStackTrace();
} %>