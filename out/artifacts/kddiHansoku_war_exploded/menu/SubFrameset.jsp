<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<%@ include file="/taglibs.jsp" %>
	</head>
	<frameset cols="160,12,*" frameborder="no" framespacing="0" border="0" id="mainframeset">
		<frame src="<%=Constants.URL_BASE%>/e_asproLogin/menu/MenuLeft0.jsp" name="menu" id="menu" scrolling="auto" noresize scrolling="yes" />
		<frame src="<%=Constants.URL_BASE%>/e_asproLogin/menu/MenuSplitterH.jsp" name="splitter_h" noresize scrolling="no" />
		<frame src="<%=Constants.URL_BASE%>/e_asproLogin/menu/top.jsp" name="main" noresize scrolling="auto" />
	</frameset>
</html>
