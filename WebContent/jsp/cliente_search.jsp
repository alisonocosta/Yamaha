<%@taglib uri="struts-bean"  prefix="bean"  %>
<%@taglib uri="struts-html"  prefix="html"  %>
<%@taglib uri="struts-logic" prefix="logic" %>
<%@taglib uri="/tld/ym"  	 prefix="ym"    %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......clientes_search.jsp
 *   .: Criação.....03 de abril de 2007, 15:00
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
			
			function incluir() {
				
				window.top.location.href ="javascript:newTitle('Incluir Cliente');";
				window.location.href = "Cliente.do?task=add";
			
			}
			
			function initializeClienteSearch() {
			
				document.forms[0].opened.value    = "";
				document.forms[0].idCliente.value = "undefined";
				
				var criteriaField = document.forms[0].criteria;
				criteriaField.selectedIndex = 0;
			
			}
			
			function limpar() {
				
				document.forms[0].reset();
				
				initializeClienteSearch();
				
				if ( window.document.getElementById( "nameCriteriaDiv" ).style.display != "none" ) {
				
					window.document.getElementById( "nameCriteriaDiv" ).style.display = "none";
				
				}
				
				if ( window.document.getElementById( "cpfCnpjCriteriaDiv" ).style.display != "none" ) {
				
					window.document.getElementById( "cpfCnpjCriteriaDiv" ).style.display = "none";
				
				}	
				
				if ( window.document.getElementById( "chassiCriteriaDiv" ).style.display != "none" ) {
				
					window.document.getElementById( "chassiCriteriaDiv" ).style.display = "none";
				
				}				
			
			}
		
			function closeCriteriaDiv(divName) {
			
				window.document.getElementById( divName ).style.display = "none"; 
				document.forms[0].opened.value = "";
			
			}
		
			function openCriteriaDiv() {
			
				if ( document.forms[0].opened.value != "" ) 
					closeCriteriaDiv( document.forms[0].opened.value );
			
				var criteriaField = document.forms[0].criteria;
				var criteriaValue = criteriaField.options[criteriaField.selectedIndex].value;
									
				window.document.getElementById( criteriaValue ).style.display = ""; 
				document.forms[0].opened.value = criteriaValue;
				document.forms[0].idCliente.value = "undefined";
			
			}
		
			function storeCnpj() {
				
				var cpfCnpjSource          = document.forms[0].cpfCnpjClienteForId;
				var escapedCpfCnpjTarget   = document.forms[0].escapedCpfCnpj;			
				escapedCpfCnpjTarget.value = escape(cpfCnpjSource.value);
				
			}		
		
			function storeNome() {
				
				var nomeSource          = document.forms[0].nomeClienteForId;
				var escapedNomeTarget   = document.forms[0].escapedNome;
				escapedNomeTarget.value = escape(nomeSource.value);
				
			}
			
			function storeChassi() {
				
				var chassiSource          = document.forms[0].chassi;
				var escapedChassiTarget   = document.forms[0].escapedChassi;
				escapedChassiTarget.value = escape(chassiSource.value);
				
			}
			
			function launchEditWindow() {

				var criteria = document.forms[0].opened.value;
				var entityId = document.forms[0].idCliente.value;
				var chassi   = document.forms[0].chassi.value;
				
				if ( criteria == "0" || criteria == null || criteria == "" ) {
					
					// Critério de busca é vazio. Vamos informar que o critério de 
					// pesquisa é obrigatório.
					window.alert("<bean:message key="customer.error.invalidCriteria"/>");
					document.forms[0].idCliente.value = "undefined";
				
				} else {
				
					if ( entityId != "undefined" ) {

						// Um critério foi solicitado e temos um ID de cliente. 
						// Podemos abrir a tela de busca, utilizando o modo de edição,
						// pesquisando pelo entityID.
						//var editUrl  = "javaScript:openWindow(\'win_sg_cliente_edit\', \'Editar Cliente\', \'Cliente.do?task=edit&entityId=" + entityId + "\');";
						//window.parent.location.href = editUrl;
						
						window.top.location.href ="javascript:newTitle('Editar Cliente');";
						window.location.href = "Cliente.do?task=edit&entityId=" + entityId;

					} else if ( window.document.getElementById( "cpfCnpjCriteriaDiv" ).style.display != "none" ) {
					
						if ( window.document.getElementById( "cpfCnpjClienteForId" ).value != "" ) {
							
							var cnpj = window.document.getElementById( "cpfCnpjClienteForId" ).value;
														
							//var editUrl  = "javaScript:openWindow(\'win_sg_cliente_edit\', \'Editar Cliente\', \'Cliente.do?task=editByCnpj&cnpj=" + cnpj + "\');";
							//window.parent.location.href = editUrl;
							
							window.top.location.href ="javascript:newTitle('Editar Cliente');";
							window.location.href = "Cliente.do?task=editByCnpj&cnpj=" + cnpj;
						
						} else {
						
							window.alert("Valor inválido para pesquisa!");	
						
						}
						
					} else if ( chassi != "" ) {
					
						// Um critério foi solicitado e temos um ID de cliente. 
						// Podemos abrir a tela de busca, utilizando o modo de edição,
						// pesquisando pelo entityID.
						//var editUrl  = "javaScript:openWindow(\'win_sg_cliente_edit\', \'Editar Cliente\', \'Cliente.do?task=editByChassi&chassi=" + chassi + "\');";
						//window.parent.location.href = editUrl;
						
						window.top.location.href ="javascript:newTitle('Editar Cliente');";
						window.location.href = "Cliente.do?task=editByChassi&chassi=" + chassi;
					
					} else {
					
						window.alert("Valor inválido para pesquisa!");	
						
					}
				
				}
			
			}
			
			function checkEnableButton() {
			
				var button   = document.forms[0].pesquisar;
				var idCliente = document.forms[0].idCliente.value;
				
				if ( idCliente != "undefined" ) {
				
					button.disabled = false;
				
				} else {
				
					button.disabled = true;
				
				}
			
			}
		
		</script>
	</head>
	
	<body onload="javaScript:initializeClienteSearch();">
	
		<form name="clientesSearch" method="get">
	
			<input type="hidden" name="opened" value=""/>
			<input type="hidden" name="idCliente" value="undefined"/>
	
			<!-- Início: Tabela para seleção do critério. -->
			<table border="0" cellpadding="1" cellspacing="1" width="500">
				<tr>
					<td width="25%" class="td_dark"><bean:message key="customer.criteria"/>:</td>
					<td>
						<select name="criteria" class="listSelect" onchange="javaScript:openCriteriaDiv();">
							<option></option>
							<option value="nameCriteriaDiv"><bean:message key="customer.criteria.name"/></option>
							<option value="cpfCnpjCriteriaDiv"><bean:message key="customer.criteria.cpf"/></option>
							<option value="chassiCriteriaDiv"><bean:message key="customer.criteria.chassi"/></option>
						</select>
					</td>
				</tr>
			</table>
			<!-- Fim: Tabela para seleção do critério. -->
		
			<!-- Início: Div de critério: Pesquisar por nome -->
			<div id="nameCriteriaDiv" style="display:none;">
				<table border="0" cellspacing="1" cellpadding="1" width="500">
					<tr>
						<td width="25%" class="td_dark"><bean:message key="customer.name"/>:</td>
						<td>
							<input type="hidden" name="escapedNome" value="null"/>
							<input type="text" 
							       name="nomeClienteForId" 
							       class="text_field_maior" 
							       maxlength="100" 
							       onKeyUp="javaScript:storeNome();"/>
							<span id="indicator_nome" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>
							<ajax:autocomplete baseUrl="${pageContext.request.contextPath}/DeterminaCliente.do"
                                               source="nomeClienteForId"
                                               target="idCliente"
                                               minimumCharacters="5"
                                               parser="new ResponseXmlParser()"
                                               className="autocomplete"
                                               parameters="nomeClienteForId={nomeClienteForId}"
                                               indicator="indicator_nome"/>
							<br>
							<font class="text_tip">
								<bean:message key="customer.criteria.tip.name"/>
							</font>
						</td>
					</tr>
				</table>
			</div>
			<!-- Fim: Div de critério: Pesquisar por nome -->
	
			<!-- Início: Div de critério: Pesquisar por CPF/CNPJ -->
			<div id="cpfCnpjCriteriaDiv" style="display:none;">
				<table border="0" cellspacing="1" cellpadding="1" width="500">
					<tr>
						<td width="25%" class="td_dark"><bean:message key="customer.cnpj"/>:</td>
						<td>
							<input type="text" 
							       name="cpfCnpjClienteForId" 
							       class="text_field_maior" 
							       maxlength="14" 
							       onKeyPress="return blockChar(event);" />
							<br>
							<font class="text_tip">							
								<bean:message key="customer.criteria.tip.cpf"/>
							</font>
						</td>
						<%--
						<td>
							<input type="hidden" name="escapedCpfCnpj" value="null"/>
							<input type="text" 
							       name="cpfCnpjClienteForId" 
							       class="text_field_maior" 
							       maxlength="14" 
							       onKeyPress="return blockChar(event);" 
							       onKeyUp="javaScript:storeCnpj();"/>
							<span id="indicator_cnpj" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>							       
							<ajax:autocomplete baseUrl="${pageContext.request.contextPath}/DeterminaCliente.do"
                                               source="cpfCnpjClienteForId"
                                               target="idCliente"
                                               minimumCharacters="7"
                                               className="autocomplete"
                                               parameters="cpfCnpjClienteForId={escapedCpfCnpj}"
                                               indicator="indicator_cnpj"/>
							<br>
							<font class="text_tip">							
								<bean:message key="customer.criteria.tip.cpf"/>
							</font>
						</td>
						--%>
					</tr>
				</table>
			</div>
			<!-- Fim: Div de critério: Pesquisar por CPF/CNPJ -->
			
			<!-- Início: Div de critério: Pesquisar por CHASSI -->
			<div id="chassiCriteriaDiv" style="display:none;">
				<table border="0" cellspacing="1" cellpadding="1" width="500">
					<tr>
						<td width="25%" class="td_dark"><bean:message key="customer.chassi"/>:</td>
						<td>
							<input type="hidden" name="escapedChassi" value="null"/>
							<input type="text" 
							       name="chassi" 
							       class="text_field_maior" 
							       maxlength="14"  
							       onKeyUp="javaScript:storeChassi();"/>
							<span id="indicator_chassi" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>							       
							<ajax:autocomplete 
											   baseUrl="${pageContext.request.contextPath}/DeterminaChassi.do"
                                               source="chassi"
                                               target="chassi"
                                               minimumCharacters="8"
                                               className="autocomplete"
                                               parameters="chassi={escapedChassi}"
                                               indicator="indicator_chassi"/>
							<br>
							<font class="text_tip">							
								<bean:message key="customer.criteria.tip.chassi"/>
							</font>
						</td>
					</tr>
				</table>
			</div>
			<!-- Fim: Div de critério: Pesquisar por Chassi -->

			<center>
			<!-- Início: Table de botões. -->
			<table border="0" cellspacing="1" cellpadding="1">
				<tr height="35">
					<td align="center" valign="bottom">						
						<html:button property="pesquisar" styleClass="button_medium" onclick="javaScript:launchEditWindow();"><bean:message key="form.btn.search"/></html:button> &nbsp;
						<%--<html:button property="cadastrar" styleClass="button_medium" onclick="incluir()"><bean:message key="form.btn.include"/></html:button> &nbsp;--%>
						<html:button property="clear" styleClass="button_medium" onclick="javaScript:limpar();"><bean:message key="form.btn.clear"/></html:button>		
					</td>
				</tr>
			</table>
			<!-- Fim: Table de botões. -->
			</center>
		
		</form>
		<ym:alertMessage/>
	</body>
</html:html>