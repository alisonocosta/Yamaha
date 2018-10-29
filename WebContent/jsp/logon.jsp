<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"     prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	    %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"      %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......logon.jsp
 *   .: Criação.....18 de abril de 2008, 09:18
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela de logon do sistema.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="logonForm"/>			
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/menu/menu.css"></link>
		<script language="JavaScript"  src="../scripts/General/form.js"></script>
		<script type="text/javascript" src="../scripts/Menu/menu.js"> </script>
		<script type="text/javascript" src="../scripts/Pages/logon.js"> </script>
		<script type="text/javascript" src="../scripts/Ajax/Clock/ajaxClock.js"></script>
		
	</head>
	<body topmargin="0" leftmargin="0" onload="getClock();" >
		<%-- Aletrtas enviados pela action --%>
		<jsp:include flush="true" page="include_alerts.jsp"/>
		
		<html:form action="/Home" method="post" focus="username">
		<!-- Início: Tabela de alinhamento -->
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		
			<!-- Início: Header -->
			<tr>
				<td align="left" valign="middle">
				
					<!-- Início: Logo -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="160">
								<img src="../images/home_logo_red.gif" border="0" alt="Yamaha"/>								
							</td>
							<td align="center" valign="middle"><img src="../images/title_home.gif" border="0" alt="Sistema de Garantia"/></td>
						</tr>
					</table>
					<!-- Fim: Logo -->
				
				</td>
				<td align="right" valign="middle">
				
					<!-- Início: Relógio -->
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><img src="../images/home_clock_01.gif" border="0"/></td>
							<td background="../images/home_clock_02.gif" align="right" valign="middle" class="clock">
								<div id="clockDiv"></div>
							</td>
							<td><img src="../images/home_clock_03.gif" border="0"/></td>
						</tr>
					</table>
					<!-- Fim: Relógio -->
				
				</td>
			</tr>
			<tr>
				<td align="left" valign="top" colspan="2" width="100%">
				
					<!-- Início: Barra de divisão -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="144"><img src="../images/header_separator_01_01.gif" border="0"/></td>
							<td background="../images/header_separator_01_02.gif"></td>
							<td width="6"><img src="../images/header_separator_01_03.gif" border="0"/></td>
						</tr>
						<tr>
							<td><img src="../images/header_separator_02_01.gif" border="0"/></td>
							<td background="../images/header_separator_02_02.gif" align="right" valign="middle" class="bold">
								&nbsp;
							</td>
							<td><img src="../images/header_separator_02_03.gif" border="0"/></td>
						</tr>
						<tr>
							<td><img src="../images/header_separator_03_01.gif" border="0"/></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<!-- Fim: Barra de divisão -->
				
				</td>
			</tr>
			<!-- Fim: Header -->
			</table>
			
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<!-- Início: Body -->
			<tr>
				<td align="center" valign="top">				
					<table border="0" cellpadding="0" cellspacing="0" width="500">
						<tr>
							<td colspan="4" align="center">&nbsp;</td>					
						</tr>
						<tr>
							<td colspan="4" align="center"><img src="../images/login_logo_dsc_h.jpg" border="0"/></td>					
						</tr>
						<tr>
							<td colspan="4" align="center" class="text"><c:out value="${message}"/></td>					
						</tr>
						<tr>
							<td width="100" align="center">&nbsp;</td>	
							<td width="150" class="td_dark" align="right"><bean:message key="logon.form.username"/></td>
							<td width="150" class="text" align="left"><html:text property="username" styleClass="text_field_maior" maxlength="15"/></td>
							<td width="100" align="center">&nbsp;</td>	
						</tr>
					</table>
					<div id="password" style="display:none;">
						<table border="0" cellpadding="0" cellspacing="0" width="500">						
							<tr>
								<td width="100" align="center">&nbsp;</td>	
								<td width="150" class="td_dark" align="right"><bean:message key="logon.form.password"/></td>
								<td width="150" class="text" align="left"><html:password property="password" styleClass="text_field_maior" maxlength="14"/></td>
								<td width="100" align="center">&nbsp;</td>	
							</tr>
						</table>
					</div>
					<div id="newPassword" style="display:none;">
						<table border="0" cellpadding="0" cellspacing="0" width="500">
							<tr>
								<td width="100" align="center">&nbsp;</td>	
								<td width="150" class="td_dark" align="right"><bean:message key="logon.form.newPassword"/></td>
								<td width="150" class="text" align="left"><html:password property="newPassword" styleClass="text_field_maior" maxlength="14"/></td>
								<td width="100" align="center">&nbsp;</td>	
							</tr>
							<tr>
								<td align="center">&nbsp;</td>	
								<td class="td_dark" align="right"><bean:message key="logon.form.repPassword"/></td>
								<td class="text" align="left"><input type="password" name="confPassword" class="text_field_maior" maxlength="14"/></td>
								<td align="center">&nbsp;</td>	
							</tr>
						</table>
					</div>
					<table border="0" cellpadding="0" cellspacing="0" width="500">
						<tr>
							<td colspan="4" align="center">&nbsp;</td>					
						</tr>
						<tr>
							<td width="100" align="center">&nbsp;</td>	
							<td width="150" align="center">
								<c:if test="${isNewPass == false}">
									<html:button property="entrar" styleClass="button_medium" onclick="submitFormTsk(logonForm,'logon',validateLogonForm(logonForm))"><bean:message key="form.btn.enter"/></html:button>	
								</c:if>
								<c:if test="${isNewPass == true}">
									<html:button property="entrar" styleClass="button_medium" onclick="validatePass();"><bean:message key="form.btn.enter"/></html:button>	
								</c:if>
							</td>
							<td width="150" align="center">
								<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
							</td>	
							<td  width="100" align="center">&nbsp;</td>					
						</tr>
					</table>				
				</td>
			</tr>
			<!-- Fim: Body -->
			
		</table>
		<!-- Fim: Tabela de alinhamento -->
		</html:form>
		<script type="text/javascript">					
	
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {
					
					window.document.logonForm.entrar.click();
					
				}
			}
			<c:if test="${isNewPass == false}">
				showFields("password"); 
			</c:if>
			<c:if test="${isNewPass == true}">
				showFields("newPassword");
			</c:if>
			
			function showFields(id) {
				
				var layer = window.document.getElementById(id); 
				layer.style.display = "";   				
			
			}
			
			function validatePass() {
				var newPass  = window.document.logonForm.newPassword;
				var confPass = window.document.logonForm.confPassword;
				
				if ( newPass.value == "" ) {
				
					window.alert(" O campo Nova Senha é Obrigatório!");
					newPass.focus();
					return false;
					
				} else if ( confPass.value == "" ) {
				
					window.alert(" O campo Confirmar Senha é Obrigatório!");
					confPass.focus();
					return false;
				
				} else if ( newPass.value != confPass.value) {
				
					window.alert(" O valor do campo Confirmar Senha deve ser igual ao campo Nova Senha!");
					confPass.focus();
					return false;
				
				}
				
				submitFormTsk(logonForm,"alterPassword",true);				
			}
		</script>
	</body>
</html:html>