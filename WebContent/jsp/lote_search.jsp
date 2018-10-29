<%@taglib uri="struts-bean"  prefix="bean"  %>
<%@taglib uri="struts-html"  prefix="html"  %>
<%@taglib uri="struts-logic" prefix="logic" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......lote_search.jsp
 *   .: Criação.....14 de abril de 2007, 09:53
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Tela: Pesquisa de clientes.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>		
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/dragdrop.js"></script>	
		<script language="JavaScript">
		
			function launchEditWindow() {

				var numLote = document.forms[0].numeroLote.value;
				
				if ( numLote == "0" || numLote == null ) {
				
					window.alert("<bean:message key="customer.error.invalidCriteria"/>");
				
				} else {

					var editUrl  = "javaScript:openWindow(\'win_sg_lote_edit\', \'Editar Lote\', \'Lote.do?task=edit&numeroLote=" + numLote + "\');";
					window.parent.location.href = editUrl;
				
				}
			
			}
		
		</script>
	</head>
	
	<body>
	
		<form name="loteSearch" method="get">
	
			<!-- Início: Div de critério: Pesquisar por número -->
			<div id="criteriaDiv">
				<table border="0" cellspacing="1" cellpadding="1" width="500">
					<tr>
						<td width="18%" class="td_dark"><bean:message key="lote.numero"/>:</td>
						<td>
							<input type="text" 
							       name="numeroLote" 
							       class="text_field_maior" 
							       maxlength="20"/>
						</td>
					</tr>
				</table>
			</div>
			<!-- Fim: Div de critério: Pesquisar por número -->
	
			<center>
			<!-- Início: Table de botões. -->
			<table border="0" cellspacing="1" cellpadding="1">
				<tr height="35">
					<td align="center" valign="bottom">
						<html:button property="pesquisar" styleClass="button_medium" onclick="javaScript:launchEditWindow();"><bean:message key="form.btn.search"/></html:button>&nbsp;
						<html:button property="cancelar" styleClass="button_medium" onclick="javascript:window.top.Windows.close(window.top.Windows.focusedWindow.getId());"><bean:message key="form.btn.cancel"/></html:button>	
					</td>
				</tr>
			</table>
			<!-- Fim: Table de botões. -->
			</center>
		
		</form>

	</body>
</html:html>