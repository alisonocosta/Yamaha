<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	 %>

<html:html>
	<head>
		<%--<meta http-equiv="refresh" content="1;URL=<c:out value='${ sessionScope.YM_SESSION_LOGO_URL.valorParametro }${ sessionScope.YM_SESSION_USER_INNER.usuarioCode }'/>">--%>
		<meta http-equiv="refresh" content="1;URL=<c:out value='${ innerURL }${ sessionId }'/>">
		
	</head>
</html:html>