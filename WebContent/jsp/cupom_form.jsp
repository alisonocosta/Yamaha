<%@taglib uri="http://struts.apache.org/tags-bean"  	prefix="bean"  %>
<%@taglib uri="http://struts.apache.org/tags-html-el"   prefix="html"  %>
<%@taglib uri="http://struts.apache.org/tags-logic" 	prefix="logic" %>
<%@taglib uri="/tld/ym"  	 							prefix="ym"    %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 			prefix="ajax"  %>
<%@ page import="java.util.List"%>

<ym:checkLogon roleName="sg_cd_rev" />

<jsp:useBean id="cupomForm" 
             type="org.apache.struts.validator.DynaValidatorActionForm" 
			 scope="request"/>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......cupom_form.jsp
 *   .: Criação.....10 de maio de 2007, 17:24
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de cupom.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="cupomForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>				
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/dragdrop.js"></script>		
		<script language="JavaScript">
			
			/** Após incluir um cupom com sucesso, 
			 *  O usuário é direcionado para a Relação de Cupons do lote
			 */
			function redirectList( loteId ) {
			
				if ( window.confirm("Deseja retornar a lista de Revisões?" ) ) {
					window.top.location.href ="javascript:newTitle('Relação de Revisões');";
					window.location.href = "Cupom.do?task=list&loteId=" + loteId;
				}
				
			}
			
			function fieldFocus( field ) {
			
				window.document.getElementById(field).focus();
			
			}
			
			function voltar() {
				window.top.location.href ="javascript:newTitle('Relação de Revisões');";
				window.location.href = "Cupom.do?task=list&loteId=" + window.document.cupomForm.loteId.value;
			}
			
			function setTitle() {
			
				window.top.location.href ="javascript:newTitle('Incluir Revisão');";
			
			}
			
			function setAlterTitle() {
			
				window.top.location.href ="javascript:newTitle('Alterar Revisão');";
			
			}			
			
			function save(){
			
				visibleFields();			
				var linhaId	 = window.document.getElementById('linhaId').value;
				var indexRev = window.document.cupomForm.numeroRevisao.selectedIndex;
   				var revNum   = window.document.cupomForm.numeroRevisao.options[indexRev];
   				
   				if ( revNum.value == "null" || revNum.value == "" ) {
   				
   					window.alert("Selecione uma revisão!");
   					window.document.cupomForm.numeroRevisao.focus();
   					window.document.cupomForm.gravar.disabled = false;
   					return false;
   				
   				}
   				
   				if(revNum.value == "1" && linhaId == "2") {
   					
   					var hasChecked = false;
   					for(i=0;i < window.document.cupomForm.tipoUsoId.length;i++) {
   						if(window.document.cupomForm.tipoUsoId[i].checked == true)
   							hasChecked = true;   					
   					}
   					
   					if(hasChecked == false){
	   					window.alert("Selecione o Tipo de Uso!");
	   					window.document.cupomForm.gravar.disabled = false;
	   					return false;
	   				}    	
	   							
   				}
   				
   				var isDates    = checkFieldsDate();
   				var isValidade = validateCupomForm(cupomForm);
   				
   				if ( isDates && isValidade ) 
   					window.document.cupomForm.gravar.disabled = true;
   				else
   					window.document.cupomForm.gravar.disabled = false;
   					
   				submitFormTsk(cupomForm,'save',( isDates && isValidade ));				
				// submitFormTsk(cupomForm,'save',( checkFieldsDate() && validateCupomForm(cupomForm) ));
			}
			
			function cancel() {
				
				var linhaId	     = window.document.getElementById('linhaId').value;
				if(linhaId == 2 )
					window.document.cupomForm.cupomCode.focus();	
					
				window.document.cupomForm.validado.value = "false";
				window.document.cupomForm.gravar.disabled = false;
				<%--submitFormTsk(cupomForm,'add',true);--%>
				
			} 
		
			function adicionarCliente() { 				
				window.location.href = "javascript:openWindow('win_sg_clientes_form', 'Incluir Clientes', 'Cliente.do?task=add');";
			} 
		
			function storeChassi() {
				
				var chassiFieldSource   = window.document.cupomForm.chassi;
				var chassiFieldTarget   = window.document.cupomForm.escapedChassi;			
				chassiFieldTarget.value = escape(chassiFieldSource.value);
				
			}
			
			function storeCnpj() {
				
				var cpfCnpjClienteFieldSource   = window.document.cupomForm.cpfCnpjCliente;
				var escapedCpfCnpjFieldTarget   = window.document.cupomForm.escapedCpfCnpj;			
				escapedCpfCnpjFieldTarget.value = escape(cpfCnpjClienteFieldSource.value);
				
			}
			
			function initProgress() {
				Element.hide('msg');
				Element.show('indicator');	
			}
			
			function finalProgress() {
			
				Element.hide('indicator');				
				if (window.document.cupomForm.numeroRevisao.options.length == 0 ) {
					window.document.getElementById("msg").innerHTML = "Revisões não encontradas para o modelo!";
					Element.show('msg');
				}							
			}
						
			function reportError() {
			
				if (window.document.cupomForm.numeroRevisao.options.length == 0) {
					window.document.msg.innerHTML = "Erro - Valores não Encontrados!";
				}
				Element.show('msg');
				setTimeout("Element.hide('msg')", 2500);
			}
			
			function finalClient() {
			
				if ( window.document.cupomForm.targetCliente.value != "" ) {
			
					var tgCliente = window.document.cupomForm.targetCliente.value;
					var vCliente  = tgCliente.substring( (tgCliente.indexOf("@") + 1), tgCliente.length );					
					var clienteID = tgCliente.substring( 0, tgCliente.indexOf("@") );
					
					window.document.cupomForm.clienteNome.value = vCliente;
					window.document.cupomForm.clienteId.value   = clienteID;
				
					Element.hide('indicator_cnpj');	
					<%-- Alteração dia 18/06 - flar_edson --%>
					if (clienteID == "null" || window.document.cupomForm.clienteNome.value == "" || window.document.cupomForm.clienteNome.value == "O CPF/CNPJ não foi encontrado!" ) {
					
						var clienteHtml = "CPF/CNPJ não encontrado! &nbsp; <a href=\"javascript:submitFormTsk(cupomForm," 
										+ "'includeClient' ,true)\" class='text'>Incluir &nbsp;<img src='../images/icon_cliente.gif' alt='Incluir Cliente' border='0'/></a>";
										
						window.document.getElementById("msg_cnpj").innerHTML = clienteHtml;
						Element.show('msg_cnpj');
						setTimeout("window.document.cupomForm.clienteNome.value = ''", 2500);
						
					} else if (clienteID == "null-e" || clienteID == "null-dt" || clienteID == "null-s"){
						/*
							Alteracao: Demanda 00003
							Data: 29/06/2012
							Resource IT
						*/
						var clienteHtml = "Existem informa\u00e7\u00f5es n\u00e3o preenchidas  ! &nbsp; <a href=\"javascript:submitFormTsk(cupomForm," 
							+ "'editByCnpjClient' ,true)\" class='text'>Alterar &nbsp;<img src='../images/icon_cliente.gif' alt='Alterar Cliente' border='0'/></a>";
							
						window.document.getElementById("msg_cnpj").innerHTML = clienteHtml;
						Element.show('msg_cnpj');
						
					} else { 	
						Element.hide('msg_cnpj');	
					}
					
				} 
								
			}
			
			/** Apresenta os campos do div revisão */
			function visibleFields() {
			
				var revisao      = window.document.getElementById("numeroRevisao");	
				var linhaId	     = window.document.getElementById('linhaId').value;
				var indice 	     = revisao.selectedIndex;
				var revisaoValue = revisao.options[indice].value;
				
				window.document.forms[0].valorRevisaoSelecionado.value = revisaoValue;
				
				/** O valor deverá ser comparado com 0 (zero), provisóriamente estamos */
				/** comparando com 1 */
				if ( revisaoValue == "1" ){
				
	   				showFields('revisao_zero');	   				
	   				
	   				window.document.getElementById("cpfCnpjCliente").disabled = false;
	   				window.document.getElementById("dataVenda").disabled      = false; 
	   				window.document.getElementById("dataEntrega").disabled    = false; 
	   				
	   				if ( linhaId == 2 )  {
	   				
	   					showFields('nautica_date');
	   					showFields('nautica_geral');
	   					showFields('nautica_zero');
	   					hiddenFields('motocicleta');
	   					hiddenFields('revisao_date');
	   					
	   					window.document.getElementById("horasUso").disabled = false; 
	   					window.document.getElementById("horasUso").value    = "0";
	   					window.document.getElementById("horasUso").readOnly = true;
	   					
	   					window.document.getElementById("tipoUsoId").disabled     = false; 
	   					window.document.getElementById("modeloBarco").disabled   = false; 
	   					window.document.getElementById("marcaBarco").disabled    = false; 
	   					window.document.getElementById("quilometragem").disabled = true; 
	   					
	   				} else {
	   					
	   					showFields('revisao_date');
	   					showFields('motocicleta');
	   					hiddenFields('nautica_geral');
	   					hiddenFields('nautica_zero');
	   					hiddenFields('nautica_date');
	   					
	   					window.document.getElementById("horasUso").disabled      = true; 
	   					window.document.getElementById("tipoUsoId").disabled     = true; 
	   					window.document.getElementById("modeloBarco").disabled   = true; 
	   					window.document.getElementById("marcaBarco").disabled    = true; 
	   					window.document.getElementById("quilometragem").disabled = false;
	   					window.document.getElementById("quilometragem").value    = "0";
	   					window.document.getElementById("quilometragem").readOnly = true;
	   					
	   				}
	   				
	   			} else {	
	   				
	   				if ( linhaId == 2 ) {
	   				
	   					showFields('nautica_geral');
	   					hiddenFields('motocicleta');
	   					
	   					window.document.getElementById("quilometragem").disabled = true; 
	   						   					
	   					window.document.getElementById("horasUso").disabled      = false; 
	   					window.document.getElementById("horasUso").readOnly      = false;
	   					
	   				} else {
	   				
	   					showFields('motocicleta');
	   					hiddenFields('nautica_geral');
	   					
	   					window.document.getElementById("quilometragem").disabled = false; 
	   					window.document.getElementById("quilometragem").readOnly = false;
	   					window.document.getElementById("horasUso").disabled      = true;	   					 
	   				
	   				}
	   				
	   				showFields('revisao_date');
	   				hiddenFields('revisao_zero');
	   				hiddenFields('nautica_zero');
	   				hiddenFields('nautica_date');
	   				
	   				window.document.getElementById("cpfCnpjCliente").disabled = true; 
	   				window.document.getElementById("dataVenda").disabled      = true; 
	   				window.document.getElementById("dataEntrega").disabled    = true; 
	   				window.document.getElementById("tipoUsoId").disabled      = true; 
	   				window.document.getElementById("modeloBarco").disabled    = true;
	   				window.document.getElementById("marcaBarco").disabled     = true; 
	   				
	   			}
   				
			}
			
			function showFields(id) {
				
				var layer 			= window.document.getElementById(id); 
   				layer.style.display = "";   				
			
			}
						
			/** Oculta os campos do div revisão */
			function hiddenFields(id) {			
			
				var layer 			= window.document.getElementById(id); 
   				layer.style.display = "none"; 
   				
			}
			
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
					
				}
	 		}
	 		
	 		function checkFieldsDate() {
	 			
	 			var dataVenda   = window.document.getElementById("dataVenda");
	 			var dataRevisao = window.document.getElementById("dataRevisao");
	 			var dataEntrega = window.document.getElementById("dataEntrega");
	 			var linhaId	    = window.document.getElementById('linhaId').value;
	 				 			
	 			var revisao;
	 			var entrega;
	 			var venda;	 			
	 			var checkDataEntrega;
	 			
	 			var numeroRevisao = window.document.forms[0].valorRevisaoSelecionado.value;
	 			
	 			if ( numeroRevisao == 1 && linhaId == 2 ) {
	 					 					
	 				if ( dataVenda.value == "" ) {
	 				
	 					window.alert("Data da venda inválida!");
					 	dataVenda.focus();
					 	window.document.cupomForm.gravar.disabled = false;
					 	return false;
	 				
	 				} else if ( dataEntrega == "" ) {
	 				
	 					window.alert("Data da entrega inválida!");
					 	dataEntrega.focus();
					 	window.document.cupomForm.gravar.disabled = false;
					 	return false;					 	
	 				} 
	 					 				
	 				entrega = checkDate("dataEntrega");
	 				venda   = checkDate("dataVenda");
	 				if ( venda && entrega ) { 
	 					
	 					checkDataEntrega = comparaData( dataEntrega.value, dataVenda.value);
	 					if ( checkDataEntrega > -1 ) {			
	 						return true;
	 					} else {
				 	
					 		window.alert("Data de entrega inválida!");
					 		dataEntrega.value = "";
					 		dataEntrega.focus();
					 		window.document.cupomForm.gravar.disabled = false;
					 		return false;
					 		
						}
					}
					
	 			} else { 
	 			
	 				if ( dataRevisao.value == "" ) {
	 				
	 					window.alert("Data da revisão inválida!");
					 	dataRevisao.focus();
					 	window.document.cupomForm.gravar.disabled = false;
					 	return false;
	 				
	 				}  else  {
	 			
		 				revisao = checkDate("dataRevisao");
		 				
		 				if ( revisao ) 	
		 			 		return true;
		 			}
	 			 		 				
	 			} 
	 			window.document.cupomForm.gravar.disabled = false;
	 			return false;
	 		
	 		}
	 		
	 		function checkDate(id) {
	 		
	 			var data1 = window.document.getElementById("sysDate").value;
	 			var data2 = window.document.getElementById(id).value;
	 			
	 			if ( data2 != "" && data1 != "" ) {
		 			if ( comparaData( data1, data2 ) < 0 ) {
		 				window.alert("Data inválida!");
		 				window.document.getElementById(id).value = "";
		 				window.document.getElementById(id).focus();
		 				window.document.cupomForm.gravar.disabled = false;
		 				return false;
		 			}
		 		}	 		
		 		
		 		return true;
	 		}
			
			function checkCpfCnpj(){
				
				if ( document.cupomForm.cpfCnpjCliente.value != "" && document.cupomForm.clienteId.value == "null") {
					if ( document.cupomForm.valorRevisaoSelecionado.value == 1 ) {
					
						if ( !validateCpfCnpj(document.cupomForm) ) {
						
							document.cupomForm.cpfCnpjCliente.focus();	
						
						}
					
					}
				}
			
			}
			
			/** Limpar os valores dos campos do formulário */
			function resetValues() {
			
			    	$('chassi').value ="";
			    	new Effect.Highlight('chassi');
			    	
			    	$('escapedChassi').value = "";
			    	new Effect.Highlight('escapedChassi');
			    	
			    	$('cupomCode').value = "";
			    	new Effect.Highlight('cupomCode');
			    	
			    	$('dataRevisao').value = "";
			    	new Effect.Highlight('dataRevisao');
			    	
			    	$('dataEntrega').value = "";
			    	new Effect.Highlight('dataEntrega');
			    	
			    	$('cpfCnpjCliente').value = "";
			    	new Effect.Highlight('cpfCnpjCliente');
			    	
			    	$('clienteNome').value = "";
			    	new Effect.Highlight('clienteNome');
			    	
			    	$('escapedCpfCnpj').value = "";
			    	new Effect.Highlight('escapedCpfCnpj');
			    	
			    	$('quilometragem').value = "";
			    	new Effect.Highlight('quilometragem');
			    	
			    	$('horasUso').value = "";
			    	new Effect.Highlight('horasUso');
			    	
			    	$('modeloBarco').value = "";
			    	new Effect.Highlight('modeloBarco');
			    	
			    	$('marcaBarco').value = "";
			    	new Effect.Highlight('marcaBarco');
			    	
			    	window.document.cupomForm.reset();
			    	
			    	window.document.cupomForm.gravar.disabled = false;
			    	
			    	$('chassi').focus();
			
			}
			
			function posGetChassi(){
				
				var tgChassi = window.document.cupomForm.targetChassi.value;
				var linhaId	 = window.document.getElementById('linhaId').value;
				
				if ( linhaId == "1" ) {
				
					var vChassi = tgChassi.substring( (tgChassi.indexOf("@") + 1), tgChassi.length );
					
					var vNac    = tgChassi.substring( 0, tgChassi.indexOf("@") );
					
					window.document.cupomForm.chassi.value = vChassi;
					
					window.document.cupomForm.chassiNacional.value = vNac;
					
				} else {
					
					window.document.cupomForm.chassi.value = tgChassi;
					
					window.document.cupomForm.chassiNacional.value = "N";
				
				}
			
			}
			
			function posGetTipoMoto(){
			
				var tgHasKM = window.document.cupomForm.hasKM.value;
				var tgReq = window.document.getElementById("motocicletaReq");
				
				if ( tgHasKM == "true" ) {
				
					tgReq.innerHTML = "<img src='../images/icon_required.gif' alt='Campo de preenchimento obrigatório!'>"; 
					
				} 
				
			}
			
			function defDateEntrega(){
			
				var vNac = window.document.cupomForm.chassiNacional.value;
				var isDisabled = window.document.cupomForm.dataEntrega != null;
				
				//if ( vNac == "S" && isDisabled ) {
				if ( isDisabled == false ) {
					
					window.document.cupomForm.dataEntrega.value = window.document.cupomForm.dataRevisao.value;				
				
				}
			
			}
			
			function formataCpfCnpj(pIsCnpj) {
			
				var vCnpj = window.document.cupomForm.cpfCnpjCliente;
				var vFCnpj = formatCpfCnpj(vCnpj.value, true, pIsCnpj);		
				//window.alert(vFCnpj);
				vCnpj.value = vFCnpj;			
			
			}
			
		</script>
	</head>
	<body leftmargin="0" topmargin="3">		
		<html:form action="/Cupom" method="post">
		
			<html:hidden property="entityId"/>
			<html:hidden property="criadoPor"/>
			<html:hidden property="loteId"/>
			<html:hidden property="atualizadoPor"/>
			<html:hidden property="dataAtualizacao"/>			
			<html:hidden property="linhaId"/>
			<html:hidden property="validado"/>
			<html:hidden property="alterState"/>
			<html:hidden property="hasKM"/>
			<html:hidden property="sysDate"/>
			<input type="hidden" name="valorRevisaoSelecionado"/>
			<input type="hidden" name="chassiNacional"/>
			<logic:equal name="cupomForm" property="linhaId" value="1">
				<html:hidden property="cupomCode"/>
			</logic:equal>
			<%-- Aletrtas enviados pela action --%>
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>
			<%-- Título apresentando a Linha do produto - Náutica ou Motocicleta  --%>
			<fieldset>
			<legend  class="labelList"><b>Linha</b> <bean:write name="cupomForm" property="linhaDescricao"/> </legend>			
			
			<%-- Campos que devem ser apresentados para todas as linhas de produtos --%>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="cupom.lote"/>:
				</td>
				<td width="75%" class="text" style="border: 1px solid #989898;">	
					<bean:write name="cupomForm" property="loteId"/>
				</td>
			</tr>
			
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.chassi"/>:
				</td>
				<td width="75%" class="text">
					<logic:equal name="cupomForm" property="alterState" value="false">
						<html:text property="chassi" styleClass="text_field_menor" maxlength="13" onkeyup="javaScript:storeChassi()"></html:text>
					</logic:equal>
					<logic:equal name="cupomForm" property="alterState" value="true">
						<html:text property="chassi" styleClass="text_field_menor" maxlength="13" readonly="true"></html:text>
					</logic:equal>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/> 
					<span id="indicator" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>
					<input type="hidden" name="escapedChassi" value="null"/>
					<input type="hidden" name="targetChassi" value="null"/>
					<logic:equal name="cupomForm" property="alterState" value="false">
						<script type="text/javascript">
							new AjaxJspTag.Autocomplete(
								"<%= request.getContextPath() %>/DeterminaChassiRev.do", {
									minimumCharacters: "8",
									parameters: "chassi={escapedChassi},loteId={loteId},linhaId={linhaId}", 
									target: "targetChassi",
									className: "autocomplete",
									source: "chassi",
									indicator: "indicator",
									postFunction: posGetChassi
								});
						</script>
					</logic:equal>
				</td>
			</tr>
			<%--
			<logic:notEqual name="cupomForm" property="linhaId" value="1">
				<tr height="25"> 
					<td width="25%" class="td_dark" >	
						<bean:message key="cupom.numberCupom"/>:
					</td>
					<td width="75%" class="text">						
						<html:text property="cupomCode" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>
					</td>
				</tr>
			</logic:notEqual>
			 --%>
			<logic:notEqual name="cupomForm" property="validate" value="true">
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.revision"/>:
				</td>
				<td width="75%" class="text">
					<div id="msg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 11px Arial;color:#900"></div>										
					<html:select property="numeroRevisao" styleClass="listSelect" onchange="javascript:visibleFields()">
						<html:optionsCollection property="listRevisao" label="descricao" value="numeroRevisao"/>
					</html:select>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/> 
					<span class="text"><bean:message key="cupom.msg.revision"/></span>		
					<logic:equal name="cupomForm" property="alterState" value="false">
						<ajax:select
							  baseUrl="${pageContext.request.contextPath}/LoadRevisao.do"
							  source="chassi"
							  target="numeroRevisao"	
							  eventType="blur"						  
							  parameters="chassi={chassi}"
							  preFunction="initProgress"
							  postFunction="finalProgress"
							  errorFunction="reportError"/>	
						<%--
						<ajax:htmlContent 
								baseUrl="${pageContext.request.contextPath}/LoadCampaignNew.do" 
								target="hasCampaign" 								
								parameters="chassi={chassi}"
								source="chassi"
								eventType="blur"/>
						--%>
						<%-- 
						/** Esta TAG busca valores no banco para o campo hasKM
						 *  Ela é disparada na saída do foco do campo numeroRevisao que chama a função getTipoMoto().
						 --%>
						<ajax:updateField
									  baseUrl="${pageContext.request.contextPath}/GetTipoMoto.do"
									  source="chassi" 
									  eventType="blur"
									  action="numeroRevisao"
									  target="hasKM" 
									  parser="new ResponseXmlParser()" 	 				  
									  parameters="chassi={chassi}"
									  postFunction="posGetTipoMoto"/>  
					   
					</logic:equal>
				</td>
			</tr>	
			</logic:notEqual>
			<logic:equal name="cupomForm" property="validate" value="true">
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.revision"/>:
				</td>
				<td width="75%" class="text">	
					<%
						String parametro   = cupomForm.getString("numeroRevisao");
						List   listResults = (List)cupomForm.get("listResults");						
					%>				
					<script type="text/javascript">					
						window.document.cupomForm.valorRevisaoSelecionado.value = <%= parametro %>;
					</script>
					<html:select property="numeroRevisao"  styleClass="listSelect" onchange="javascript:visibleFields()">
						<ym:selectRevisao  selectedId="<%= parametro %>" listRevisao="<%= listResults %>"/>
					</html:select>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/> 
					
				</td>
			</tr>	
			</logic:equal>
			</table>
			<div id="revisao_date" style="display:none;">
			<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="cupom.revision"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dataRevisao"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 maxLength      ="10"
							 fieldYears		="4"
							 onBlurMethod   ="checkDate('dataRevisao');defDateEntrega();"
							 onKeyUp		="mascara_data('dataRevisao',event)"
							 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
							 <bean:write name="cupomForm" property="dataRevisao"/>
					</ym:inputDate>&nbsp;			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/> 
					<bean:message key="cupom.sg.dt.default"/>
				</td>
			</tr>
			</table>
			</div>	
			<div id="nautica_date" style="display:none;">
			<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center">			
			<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="cupom.sellDate"/>:
					</td>
					<td width="75%" class="text">	
						<ym:inputDate icon="../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="dataVenda"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 maxLength      ="10"
								 fieldYears		="4"
								 onBlurMethod   ="checkDate('dataVenda');"
								 onKeyUp		="mascara_data('dataVenda',event)"
								 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="cupomForm" property="dataVenda"/>
						</ym:inputDate>&nbsp;			   
						<html:img  srcKey="form.img.required" altKey="form.msg.required"/> 
						<bean:message key="cupom.sg.dt.default"/>
					</td>
				</tr>
				<tr height="25"> 
					<td width="25%" class="td_dark" >	
						<bean:message key="cupom.deliveryDate"/>:
					</td>
					<td width="75%" class="text">
						<ym:inputDate icon="../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dataEntrega"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 maxLength      ="10"
							 fieldYears		="4"
							 onBlurMethod   ="checkDate('dataEntrega');"
							 onKeyUp		="mascara_data('dataEntrega',event)"
							 tabIndex       ="1000"> <%-- Valor aleatório para ordem de tablulação --%>
							 <bean:write name="cupomForm" property="dataEntrega"/>
						</ym:inputDate>&nbsp;			   
						<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
						<bean:message key="cupom.sg.dt.default"/>
					</td>
				</tr>
			</table>
			</div>					
			<%--  
			 *  Campos que devem ser apresentados apenas quando a revisão escolhida for Revisão Zero
			 *  Para todas as linhas de Produtos			
			 --%>
			<div id="revisao_zero" style="display:none;">
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">				
				<tr height="25"> 
					<td width="25%" class="td_dark" >	
						<bean:message key="cupom.customerCnpj"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="cpfCnpjCliente" 
								   styleClass="text_field_menor" 
								   maxlength="16"
								   onkeyup="javaScript:storeCnpj()"
								   onkeypress="return blockChar(event);"
								   onblur="checkCpfCnpj()">
					    </html:text> &nbsp; - &nbsp;
					    <html:text property="clienteNome" tabindex="200" styleClass="text_field_menor" maxlength="100" readonly="true"/>
							   
						<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
						<span id="indicator_cnpj" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>
						<div id="msg_cnpj" class="alertDiv" style="display:none;"></div>										
						<input type="hidden" name="escapedCpfCnpj" value="null"/>
						<input type="hidden" name="targetCliente"  value="null"/>
						<html:hidden property="clienteId"/>
						<ajax:autocomplete
							  baseUrl="${pageContext.request.contextPath}/DeterminaCliente.do"
							  source="cpfCnpjCliente"
							  target="targetCliente"	
							  minimumCharacters="8" 	
							  className="autocomplete"				  
							  parameters="cpfCnpjCliente={escapedCpfCnpj}"
							  indicator="indicator_cnpj"
							  postFunction="finalClient"/>	
					</td>
				</tr>
				</table>				
			</div>	
			
			<%-- 
			  *  Campo que deve ser apresentado apenas quando a linha do produto for Motocicleta 
			  *  Para qualquer tipo de Revisão
			 --%>			
			<div id="motocicleta" style="display:none;">
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">		
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.kilometrage"/>:
				</td>
				<td width="75%" class="text" nowrap="nowrap">
					<html:text property="quilometragem" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>			   
					<span id="motocicletaReq"></span>
				</td>
			</tr>
			</table>
			</div>
			
			<%-- 
			  *  Campo que deve ser apresentado apenas quando a linha do produto for Náutica 
			  *  Para qualquer tipo de Revisão
			 --%>
			<div id="nautica_geral" style="display:none;">
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<%-- 
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.useDays"/>: 
				</td>
				<td width="75%" class="text">
					<html:text property="diasUso" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			--%>
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.useHours"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="horasUso" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			</table>	
			</div>
			
			<%-- 
			  *  Campo que deve ser apresentado apenas quando a linha do produto for Náutica 
			  *  Apenas para Revisão Zero
			 --%>			
			<div id="nautica_zero" style="display:none;">
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.useType"/>:
				</td>
				<td width="75%" class="text">
					<table width="45%" border="0" cellpadding="1" cellspacing="1" align="left" style="border: 1px solid #989898;">
					<logic:iterate name="cupomForm" property="listUseType" id="tipoUsoBarco" >
						<tr>
							<td class="text" valign="middle">							
									<html:radio property="tipoUsoId" value="${tipoUsoBarco.entityId}"></html:radio> 
									<bean:write name="tipoUsoBarco" property="descricao"/>							
							</td>						
						</tr>
					</logic:iterate>
					</table>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.model"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="modeloBarco" styleClass="text_field_menor" maxlength="10"></html:text>	
				</td>
			</tr>				
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.mark"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="marcaBarco" styleClass="text_field_menor" maxlength="10"></html:text>
				</td>
			</tr>
			</table>
			</div>
			</fieldset>
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">						
			<tr height="25">
				<td colspan="2" align="center">
					<%--<html:button property="gravar" styleClass="button_medium" onclick="submitFormTsk(cupomForm,'save',( checkFieldsDate() && validateCupomForm(cupomForm) ))">--%>
					<html:button property="gravar" styleClass="button_medium" onclick="javascript:save();">
						<logic:equal name="cupomForm" property="alterState" value="false"><bean:message key="form.btn.save"/></logic:equal>
						<logic:equal name="cupomForm" property="alterState" value="true"><bean:message key="form.btn.alter"/></logic:equal>
					</html:button>
					<logic:equal name="cupomForm" property="alterState" value="false">	
						&nbsp;
						<html:button property="limpar" styleClass="button_medium" onclick="javascript:resetValues();"><bean:message key="form.btn.clear"/></html:button>	
					</logic:equal>
					&nbsp;
					<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>		
				</td>
			</tr>
			<tr> 
				<td height="5" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><html:image srcKey="form.img.required" altKey="form.msg.required"/>&nbsp;-&nbsp;<bean:message key="form.msg.required"/></td>
			</tr>
			</table>
			<div id="hasCampaign"></div>
			<script language="javascript">			
				if ( window.document.getElementById("numeroRevisao").length > 0 )				
					visibleFields();
				
			</script>
		</html:form>
		<ym:javaScriptExecuter/>
		<ym:alertMessage/>
		<ym:confirmMessage/>		
	</body>
</html:html>