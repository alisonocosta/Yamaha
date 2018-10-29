<%@taglib uri="struts-bean"  prefix="bean"%>
<%@taglib uri="struts-html"  prefix="html"%>
<%@taglib uri="struts-logic" prefix="logic"%>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......sessionExpired.jsp
 *   .: Criação.....29 de setembro de 2008, 17:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela de erro de Sessão do usuário expirada.
 */
--%>

<html:html>
	<head>
		<title>Session Expired</title>
		<html:base/>
		<meta name="Author" content="Resource Tecnologia">
		<meta name="Copyright" content="2007 Resource Tecnologia">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" type="text/css" href="css/yamaha.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"> </script>
		<script language="JavaScript">
		
			function showLogonForm() {
				
				window.parent.close();
	
			}
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5">
		<%-- Aletrtas enviados pela action --%>
		<jsp:include flush="true" page="include_alerts.jsp"/>
		<br>
		<table border="0" cellpadding="1" cellspacing="0" background="../images/alert_bg_red.gif" style="background-position: right; background-repeat: repeat-y;" width="100%">						
			<tr>
				<td>
					<font style="font-family: Verdana; font-size: 10px;	color: #000000;">																		
						Clique <a href="javaScript:showLogonForm();"><b><u>aqui</u></b></a> para fazer o login novamente.
					</font>
				</td>
			</tr>
		</table>
	</body>

</html:html>