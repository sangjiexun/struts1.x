<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for UserLoginForm form</title>
	</head>
	<body>
		<html:form action="/userLogin">
			password : <html:password property="password"/><html:errors property="password"/><br/>
			userName : <html:text property="userName"/><html:errors property="userName"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

