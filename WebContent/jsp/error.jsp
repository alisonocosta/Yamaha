<%@taglib uri="struts-bean"  prefix="bean"%>
<%@taglib uri="struts-html"  prefix="html"%>
<%@taglib uri="struts-logic" prefix="logic"%>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......error.jsp
 *   .: Criação.....15 de fevereiro de 2007, 13:35
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Tela de erros do sistema.
 */
--%>

<html:html>
	<head>
		<title>Erro</title>
		<html:base/>
		<meta name="Author" content="Resource Tecnologia">
		<meta name="Copyright" content="2007 Resource Tecnologia">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" type="text/css" href="css/yamaha.css"></link>
		<script language="JavaScript">
		
			function showStackTrace() {
			
				var div = window.document.getElementById( "stackTraceDiv" );
				if ( div.style.display == "" )
					div.style.display = "none";
					
				else
					div.style.display = ""; 
			
			}
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5">

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
								<font style="font-family: Verdana; font-size: 10px;	color: #000000;">
									<logic:present name="error.message" scope="request">
										<b>Mensagem:</b> <br>
										<bean:write name="error.message" scope="request"/>
									</logic:present>
									<logic:present parameter="error.message" scope="request">
										<b>Mensagem:</b> <br>
										<bean:parameter id="message" name="error.message"/>
										<bean:write name="message"/>
									</logic:present>
									<br>
									
									Clique <a href="javaScript:showStackTrace();"><b><u>aqui</u></b></a> para ver o erro completo.
								</font>
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
		</table>

		<p/>

		<%-- Início: StackTrace --%>		
		<div id="stackTraceDiv" style="display:none;">
			<table border="0" cellpadding="1" cellspacing="0" style="border: solid 1px #cc0000;" width="95%">
				<tr>
					<td align="left" valign="top" width="100%">
						
						<table border="0" cellpadding="1" cellspacing="0" background="../images/alert_bg_red.gif" style="background-position: right; background-repeat: repeat-y;" width="100%">
							<tr>
								<td>
									<font style="font-family: Verdana; font-size: 11px;	font-weight: bold; color: #cc0000;">
										<bean:message key="errors.stacktrace"/>
									</font>
								</td>
							</tr>
							<tr>
								<td>
									<font style="font-family: Verdana; font-size: 10px;	color: #000000;">
										<logic:present name="error.stackTrace" scope="request">
											<pre><bean:write name="error.stackTrace" scope="request"/></pre>
										</logic:present>
										<logic:present parameter="error.stackTrace" scope="request">
											<bean:parameter id="stackTrace" name="error.stackTrace"/>
											<pre><bean:write name="stackTrace"/></pre>
										</logic:present>
									</font>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<%-- Fim: StackTrace --%>
					
	</body>

</html:html>