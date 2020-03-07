<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for UserLoginForm form</title>
	</head>
	<body>
		<html:form action="/userLogin">
			<%--@declare id="userName"--%><label for="userName">userName:</label><html:text property="userName" maxlength="6"/><html:errors property="userName"/><br/>
			<%--@declare id="password"--%><label for="password">password:</label><html:password property="password" maxlength="6"/><html:errors property="password"/><br/>


			<span>默认用户名/密码：struts/struts</span>
			<html:reset/>&nbsp;
			<html:submit/>&nbsp;<html:cancel/>

		</html:form><html:errors></html:errors>
	</body>
</html>

