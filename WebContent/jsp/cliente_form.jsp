<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@ taglib uri="/tld/ym"  	 						  prefix="ym"      %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"  %>

<jsp:useBean id="clienteForm" 
             type="org.apache.struts.validator.DynaValidatorActionForm" 
			 scope="request"/>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......clientes_form.jsp
 *   .: Criação.....16 de abril de 2007, 08:37
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de clientes.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="clienteForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/cep.js"></script>		
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		
		<script type="text/javascript">
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
		
			
			/*
				Demanda 00003
				Resource IT
			*/
			setTimeout("formataFJ();", 300);
			
			function initProgress() {
			
				Element.addClassName('cnpj', 'progressMeterLoading');
			  
			  	$('nome').value 		= "";		
			  	$('endereco').value 	= "";		
			  	$('cidade').value 		= "";		
			  	$('estado').value 		= "";		
			  	$('bairro').value 		= "";		
			  	$('cep_tmp').value 		= "";	
			  	$('telefoneRes').value 	= "";
			  	$('telefoneCom').value 	= "";
			  	$('telefoneCel').value 	= "";
			  	$('dddRes').value 		= "";
			  	$('dddCom').value 		= "";
			  	$('dddCel').value 		= "";
			  	$('email').value 		= "";		  
			  
			}

			function resetProgress() {
			
			  Element.removeClassName('cnpj', 'progressMeterLoading');
			
			  if ( $F('nome') != "" ) {
			  
			    /* clear error box */
			    $('errorMsg').innerHTML = "";
			    if ($('nome').value == "null" )
			    	$('nome').value ="";
			    if ($('endereco').value == "null" )
			    	$('endereco').value ="";
			    if ($('estado').value == "null" )
			    	$('estado').value ="";
			    if ($('bairro').value == "null" )
			    	$('bairro').value ="";
			    if ($('cep_tmp').value == "null" )
			    	$('cep_tmp').value ="";
			    if ($('telefoneRes').value == "null" )
			    	$('telefoneRes').value ="";
			    if ($('telefoneCom').value == "null" )
			    	$('telefoneCom').value ="";
			    if ($('telefoneCel').value == "null" )
			    	$('telefoneCel').value ="";
			    if ($('dddRes').value == "null" )
			    	$('dddRes').value ="";
			    if ($('dddCom').value == "null" )
			    	$('dddCom').value ="";
			    if ($('dddCel').value == "null" )
			    	$('dddCel').value ="";
			    if ($('email').value == "null" )
			    	$('email').value ="";
			
			    /* do cool effect */
			    new Effect.Highlight('cnpj');
			    new Effect.Highlight('nome');
			    
			    /* display success message */
			  	Element.show('successMsg');
			  	setTimeout("Element.hide('successMsg');", 2000);
			    
			  } else if ( $F('nome') == "" ) {
			    
			    	$('errorMsg').innerHTML = "Cliente não Cadastrado!";
			    	Element.show('errorMsg');
			    	setTimeout("Element.hide('errorMsg');", 2000);
			    	$('nome').focus();
			    
			  }
			  
			}
			
			function pesquisar(){
			
				window.top.location.href = "javascript:newTitle('Pesquisar Clientes');";
				window.location.href = "Cliente.do?task=search";
			
			}
			
			function setTitle() {
			
				window.top.location.href ="javascript:newTitle('Incluir Cliente');";
				window.document.clienteForm.cnpj.focus();		
			
			}
			
			function formataFJ () {
			
				var pessoaFJ   = "";
				var cpfCnpj    = window.document.getElementById("cnpj");
				
				if ( window.document.clienteForm.pessoaFJ[0].checked ) { 
				
					pessoaFJ = window.document.clienteForm.pessoaFJ[0].value;
					
				} else if ( window.document.clienteForm.pessoaFJ[1].checked ) {
				
					pessoaFJ = window.document.clienteForm.pessoaFJ[1].value;	
				}
				
				showDadosPessoaFisica(pessoaFJ);
				
				if ( cpfCnpj.value != "" ) {
				
					if ( pessoaFJ == "F" ){
					
						formataCpfCnpj('cnpj', true, false);
					
					} else {				
					
						formataCpfCnpj('cnpj', true, true);
						
					}
					
					validateCpfCnpj(window.document.clienteForm);
					
				}
			}
			
			/*
				Demanda 00003
				Resource IT
			*/
			function showDadosPessoaFisica(tipoPessoa) {
				var trDtNascimento;
				var trSexo;				
				if (tipoPessoa == "F") {
			
					trDtNascimento = window.document.getElementById("trDtNascimento");
					trDtNascimento.style.display='block';
					
					trSexo = window.document.getElementById("trSexo");
					trSexo.style.display='block';
					
				} else {
					trDtNascimento = window.document.getElementById("trDtNascimento");
					trDtNascimento.style.display='none';
					
					trSexo = window.document.getElementById("trSexo");
					trSexo.style.display='none';
			}
			}
			
			function validateDdd(){
						
				if( window.document.getElementById("telefoneRes").value != "" ) {
				
					if( window.document.getElementById("dddRes").value == "" ) {
						window.alert("Informe o DDD do telefone Residencial!");
						window.document.getElementById("dddRes").focus();
						return false;
					} 
					
				}
				
				if( window.document.getElementById("telefoneCom").value != "" ) {
					
					if( window.document.getElementById("dddCom").value == "" ) {
						window.alert("Informe o DDD do telefone Comercial!");
						window.document.getElementById("dddCom").focus();
						return false;
					} 
					
				} 
				
				if( window.document.getElementById("telefoneCel").value != "" ) {	
					
					if( window.document.getElementById("dddCel").value == "" ) {
						window.alert("Informe o DDD do telefone Celular!");
						window.document.getElementById("dddCel").focus();
						return false;
					} 
				}	
				
				return validateClienteForm(clienteForm);
			
			}
		
		</script>
		
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Cliente" method="post">
		
			<html:hidden property="entityId"/>	
			<html:hidden property="codigoCliente"/>
			<html:hidden property="criadoPor"/>
			<html:hidden property="atualizadoPor"/>
			<html:hidden property="dataAtualizacao"/>
			<html:hidden property="isFromCupom"/>	
		
			<jsp:include flush="true" page="include_alerts.jsp"/>
		
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.pessoaFJ"/>:
				</td>
				<td width="75%" class="text">	
					<html:radio property="pessoaFJ" value="F" onclick="formataFJ(); "/> 
						<bean:message key="customer.pessoaF"/> &nbsp; 
					<html:radio property="pessoaFJ" value="J" onclick="formataFJ(); "/> 
						<bean:message key="customer.pessoaJ"/> &nbsp;
					<html:img srcKey="form.img.required" altKey="form.msg.required"/>					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="customer.cnpj"/>:
				</td>
				<td width="75%" class="text">	
					<html:text property="cnpj"
							   maxlength="14"							   
							   styleClass="text_field_menor" 
							   onblur="javascript:formataFJ();"
							   onkeypress="return blockChar(event);" />
					
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/> &nbsp; 					
					<html:button property="findCustomer" styleClass="button_medium"><bean:message key="customer.find"/></html:button><br>
					<div id="successMsg" style="display:none;border:1px solid #0e0;background-color:#efe;padding:2px;margin-top:8px;width:300px;font:normal 12px Arial;color:#090">Consulta concluída!</div>
					<div id="errorMsg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 12px Arial;color:#900"></div>			
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.name"/>:
				</td>
				<td width="75%" class="text">	
					<html:text property="nome" maxlength="100" styleClass="text_field_maior"/>
					<html:img srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.ender"/>:
				</td>
				<td width="75%" class="text">	
					<html:text property="endereco" maxlength="100" styleClass="text_field_maior"/> 
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.neighborhood"/>:
				</td>
				<td width="75%" class="text">	
					<html:text property="bairro" maxlength="100" styleClass="text_field_menor"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.city"/>:
				</td>
				<td width="75%" class="text">	 
					<html:text property="cidade" maxlength="100" styleClass="text_field_menor"/>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.postalCode"/>:
				</td>
				<td width="75%" class="text">	
					<script language="JavaScript">
						function fillCep() {
							
							var currValue = document.clienteForm.cep_tmp.value;
							document.clienteForm.cep.value = currValue.replace("-", "");
						
						}
					</script>
					<html:hidden property="cep"/>
					<input type="text" name="cep_tmp" maxlength="9" class="text_field_menor" onblur="javaScript:fillCep();" onkeypress="return formatCep(this, event);return blockChar(event);"/>
					<html:img srcKey="form.img.required" altKey="form.msg.required"/>  
					<script language="JavaScript">
						
						document.clienteForm.cep_tmp.value = document.clienteForm.cep.value;
						
						if ( document.clienteForm.cep_tmp.value.length > 0 ) { 	
						
							var src = document.clienteForm.cep_tmp;
							var v = src.value.length;
							var z = 8 - v;
							
							for ( i = 0 ; i < z ; i ++ ) {
								src.value = '0' + src.value;
							}
							var valor = src.value;
							var cepI = valor.substring(0,5);
							var cepF = valor.substring(5,8);
							
							src.value = cepI + "-" + cepF;
							
						}
						
					</script>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.state"/>:
				</td>
				<td width="70%" class="text">
					<html-el:select property="estado" styleClass="listSelect">
						<logic:notEmpty name="clienteForm" property="listCliente">
							<html-el:optionsCollection property="listCliente" label="estado" value="uf"/>
						</logic:notEmpty>
					</html-el:select>
					<html:img srcKey="form.img.required" altKey="form.msg.required"/> 
				</td>
			</tr>			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.phoneRes"/>:
				</td>
				<td width="75%" class="text">	
					<bean:message key="customer.phoneDDD"/>&nbsp;
					<html:text property="dddRes" maxlength="3" size="3" styleClass="text_field_ddd" onkeypress="return blockChar(event);"/>&nbsp;
					<bean:message key="customer.phone"/>&nbsp;
					<html:text property="telefoneRes" maxlength="20" styleClass="text_field_menor"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.phoneCom"/>:
				</td>
				<td width="75%" class="text">
					<bean:message key="customer.phoneDDD"/>&nbsp;	
					<html:text property="dddCom" maxlength="3" size="3" styleClass="text_field_ddd" onkeypress="return blockChar(event);"/>&nbsp;
					<bean:message key="customer.phone"/>&nbsp;
					<html:text property="telefoneCom" maxlength="20" styleClass="text_field_menor"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.phoneCel"/>:
				</td>
				<td width="75%" class="text">
					<bean:message key="customer.phoneDDD"/>&nbsp;
					<html:text property="dddCel" maxlength="3" size="3" styleClass="text_field_ddd" onkeypress="return blockChar(event);"/>&nbsp;	
					<bean:message key="customer.phone"/>&nbsp;
					<html:text property="telefoneCel" maxlength="20" styleClass="text_field_menor"/>
				</td>
			</tr>			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.email"/>:
				</td>
				<td width="75%">	
					<html:text styleId="email" property="email" maxlength="100" styleClass="text_field_maior"/>
					<html:img srcKey="form.img.required" altKey="form.msg.required"/>
				</td>
			</tr>
			<!-- Demanda 00003 - Resource IT -->			
			<tr height="25" id="trDtNascimento"> 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.dataNascimento"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dataNascimento"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 maxLength      ="10"
							 fieldYears		="4"							 
							 onKeyUp		="mascara_data('dataNascimento',event)"
							 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
							 <bean:write name="clienteForm" property="dataNascimento"/>
					</ym:inputDate>&nbsp;			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/> 
					<bean:message key="customer.sg.dt.default"/>					
				</td>
			</tr>
			<!-- Demanda 00003 - Resource IT -->
			<tr height="25" id="trSexo" > 
				<td width="25%" class="td_dark">	
					<bean:message key="customer.sexo"/>:
				</td>
				<td width="75%" class="text">	
					<html:radio property="sexo" value="M" /> Masculino
					<html:radio property="sexo" value="F" /> Feminino &nbsp;&nbsp; <html:img  srcKey="form.img.required" altKey="form.msg.required"/>
				</td>
			</tr>			
			<tr> 
				<td height="5">&nbsp;</td>
			</tr>
			<tr height="25">
				<td colspan="2" align="center">
				
					<html:button property="gravar" styleClass="button_medium" onclick="submitFormTsk(clienteForm,'save',validateDdd())"><bean:message key="form.btn.save"/></html:button>	
					&nbsp;
					<html:button property="limpar" styleClass="button_medium" onclick="javascript:initProgress();"><bean:message key="form.btn.clear"/></html:button>												
					
					<logic:equal name="clienteForm" property="isFromCupom" value="false">
						
						&nbsp;
						<html:button property="pesq" styleClass="button_medium" onclick="javaScript:pesquisar();"><bean:message key="form.btn.search"/></html:button>
												
					</logic:equal>
					
				</td>
			</tr>
			<tr> 
				<td height="5" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><html:image srcKey="form.img.required" altKey="form.msg.required"/>&nbsp;-&nbsp;<bean:message key="form.msg.required"/></td>
			</tr>
			</table>
			<ajax:updateField
						  baseUrl="${pageContext.request.contextPath}/GetCliente.do"
						  source="cnpj"
						  target="entityId,nome,endereco,bairro,estado,cidade,cep_tmp,telefoneRes,telefoneCom,telefoneCel,email"
						  action="findCustomer"
						  parser="new ResponseXmlParser()"						  
						  parameters="cnpj={cnpj}" 
						  preFunction="initProgress"
  						  postFunction="resetProgress"/>
		</html:form>
		<ym:javaScriptExecuter/>
	</body>
</html:html>