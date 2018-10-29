<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"  %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......denied.jsp
 *   .: Criação.....14 de junho de 2007, 15:44
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Tela de acesso negado.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
	</head>
	<body>

		<table border="0" cellpadding="1" cellspacing="0" style="border: solid 1px #cc0000;" width="95%">
			<tr>
				<td align="left" valign="top" width="100%">
					
					<table border="0" cellpadding="1" cellspacing="0" background="../images/alert_bg_red.gif" style="background-position: right; background-repeat: repeat-y;" width="100%">
						<tr>
							<td>
								<font style="font-family: Verdana; font-size: 11px;	font-weight: bold; color: #cc0000;">
									<bean:message key="infra.alert.error"/>
								</font>
							</td>
						</tr>
						<tr>
							<td>
								<font style="font-family: Verdana; font-size: 10px;	font-weight: bold; color: #000000;">
									<bean:message key="errors.denied"/>
								</font>
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
		</table>
		
	</body>
	
</html:html>
