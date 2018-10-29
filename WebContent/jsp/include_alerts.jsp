<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"  %> <%-- HTML-EL 1.0  --%>
<%@taglib uri="http://struts.apache.org/tags-bean-el"  prefix="bean"  %> <%-- BEAN-EL 1.0  --%>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic" %> <%-- LOGIC-EL 1.0 --%>
<%@taglib uri="http://java.sun.com/jstl/core"          prefix="c"     %> <%-- JSTL CORE    --%>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......include_alerts.jsp
 *   .: Criação.....04 de maio de 2007, 12:15
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Include para alertas.
 */
--%>

<center>
<logic:present name="problemMessages">
	<table border="0" cellpadding="1" cellspacing="0" style="border: solid 1px #cc0000;" width="95%">
		<tr>
			<td align="left" valign="top" width="100%">				
				<table border="0" cellpadding="1" cellspacing="0" background="../images/alert_bg_red.gif" style="background-position: right; background-repeat: repeat-y;" width="100%">
					<tr>
						<td><font style="font-family: Verdana; font-size: 11px;	font-weight: bold; color: #cc0000;"><bean:message key="infra.alert.error"/></font></td>
					</tr>
					<html:messages name="problemMessages" id="msg" locale="pt-BR">
						<tr>
							<td><font style="font-family: Verdana; font-size: 10px;	font-weight: bold; color: #000000;"><c:out value="${msg}"/></font></td>
						</tr>
					</html:messages>
				</table>				
			</td>
		</tr>
	</table>
</logic:present>
<logic:present name="warningMessages">
	<table border="0" cellpadding="1" cellspacing="0" style="border: solid 1px #ff9a00;" width="95%">
		<tr>
			<td align="left" valign="top" width="100%">
				
				<table border="0" cellpadding="1" cellspacing="0" background="../images/alert_bg_yellow.gif" style="background-position: right; background-repeat: repeat-y;" width="100%">
					<tr>
						<td><font style="font-family: Verdana; font-size: 11px;	font-weight: bold; color: #ff9a00;"><bean:message key="infra.alert.warning"/></font></td>
					</tr>
					<html:messages name="warningMessages" id="msg">
						<tr>
							<td><font style="font-family: Verdana; font-size: 10px;	font-weight: bold; color: #000000;"><c:out value="${msg}"/></font></td>
						</tr>
					</html:messages>
				</table>				
			</td>
		</tr>
	</table>
</logic:present>
<logic:present name="successMessages">
	<table border="0" cellpadding="1" cellspacing="0" style="border: solid 1px #3baf24;" width="95%">
		<tr>
			<td align="left" valign="top" width="100%">
				
				<table border="0" cellpadding="1" cellspacing="0" background="../images/alert_bg_green.gif" style="background-position: right; background-repeat: repeat-y;" width="100%">
					<tr>
						<td><font style="font-family: Verdana; font-size: 11px;	font-weight: bold; color: #3baf24"><bean:message key="infra.alert.success"/></font></td>
					</tr>
					<html:messages name="successMessages" id="msg">
						<tr>
							<td><font style="font-family: Verdana; font-size: 10px;	font-weight: bold; color: #000000;"><c:out value="${msg}"/></font></td>
						</tr>
					</html:messages>
				</table>				
			</td>
		</tr>
	</table>
</logic:present>
</center>