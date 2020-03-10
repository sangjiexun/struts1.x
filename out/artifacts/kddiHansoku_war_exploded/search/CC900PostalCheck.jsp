<%@ page language="java" contentType="text/html; charset=windows-31j" pageEncoding="windows-31j"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Constants"%>
<%@ include file="/taglibs.jsp" %>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeHTML"%>
<html>
    <head>
        <TITLE>
        	住所選択
        </TITLE>
	    <SCRIPT type="text/javascript">
		    function SetValue(_addr1, _addr2, _addr3)
		    {
			    try{
					var strRun;
		         	strRun = "window.parent.opener.setAddr('" + _addr1 +"','"+ _addr2 +"','"+ _addr3
		         	 +"','<%=request.getSession().getAttribute("index")%>','<%=request.getSession().getAttribute("kenCd")%>')";
				if (window.parent.opener) {
			     		eval(strRun);
			     		window.close();
			     	}
				}catch(e){
					confirm('スクリプトエラーが発生しました。住所反映できません。');
					window.close();
				}	
		    }
		    function Close(){
				window.close();
		    }
		</SCRIPT>
	</head>
<BODY>
<CENTER>
	<TABLE>
    	<TR>
    		<TD>
				候補の住所が複数あります。住所を選択してください。
    		</TD>
    	</TR>
    	<TR>
        	<TD ALIGN="center" BGCOLOR="white">
		   		<%Vector<Map<String, String>> list = (Vector<Map<String, String>>)request.getSession().getAttribute("list"); %>
       			<%if(list.size() > 0){ %>
	                <TABLE CLASS="lt" width=400>
                    	<COL width="300">
                        <TR CLASS="head">
					   		<TH>住所</TH>
						</TR>
						<%for(int i=0;i<list.size();i++){ %>
	                		<%HashMap<String,String> map = (HashMap<String,String>)list.get(i); %>
	                        <TR>
	                        	<!--住所-->
	                            <TD align="left" title="<%=StringUtil.nvl(map.get("ALL_ADDR"))%>">
		                        	<a href='javascript:SetValue("<%=StringUtil.nvl(map.get("ADDR1"))%>",
		                        								 "<%=StringUtil.nvl(map.get("ADDR2"))%>",
		                        								 "<%=StringUtil.nvl(map.get("ADDR3"))%>")'>
	                             	<nobr><%=StringUtil.nvl(map.get("ALL_ADDR"))%></nobr>
		                         	</a>
	                             </TD>
	                        </TR>
     					<%}%>
	                </TABLE>
                    </div>
				<%} %>
        	</TD>
        </TR>
	</TABLE>
	<TABLE width="400">
	<COL width="400">
       <TR>
       	<TD  align="center">
          	<input type="button" value="閉じる" onclick="Close()">
           </TD>
       </TR>
   </TABLE>
</center>
</BODY>
</html>