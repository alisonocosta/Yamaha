<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	%>
<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@taglib uri="http://struts.apache.org/tags-logic"    prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"	    %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		   prefix="ajax"    %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	   %>

<%-- <ym:checkLogon roleName="sg_cd_solg" />--%>

<%-- 
/* Yamaha.
 * Copyright (c) 2013 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......classificaGarantiaMoto_form.jsp
 *   .: Criação.....11 de junho de 2013
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de Classificação Técnica de Garantia.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/autocomplete/autocomplete.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/progressbar/carregador.css"></link>
		<script type="text/javascript" src="../../scripts/General/form.js"></script>
		<script type="text/javascript" src="../../scripts/Formatter/maskDate.js"></script>	
		<script type="text/javascript" src="../../scripts/Formatter/cpfCnpj.js"></script>		
		<script type="text/javascript" src="../../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script type="text/javascript" src="../../scripts/Ajax/Base/overlibmws.js"></script>
		<script type="text/javascript" src="../../scripts/Ajax/Base/builder.js"></script>
		<script type="text/javascript" src="../../scripts/Ajax/Base/scriptaculous.js"></script>
		<script type="text/javascript" src="../../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script type="text/javascript" src="../../scripts/progressbar/carregador.js"></script>
		<script type="text/javascript">
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
			
			//----[ Função: showHide() ]--------------------------------------------------------
			function showHide(wobj) {
				document.getElementById(wobj).style.display = (document.getElementById(wobj).style.display == 'none') ? '' : 'none';
			}

			//----[ Função: showHideInput() ]---------------------------------------------------
			function showHideInput(name) {

				document.getElementById(name +  'NewText').value = "";
				showHide(name + '_inputLine');
				showHide(name + '_addLine');

			}
			
			//----[ Função: addText() ]---------------------------------------------------------
			function addText(name) {

				window.alert(name);
				
				var targetField     = document.getElementById(name);
				var sourceField     = document.getElementById(name + 'NewText');
				var hiddenField     = document.getElementById(name + '_newItems');
				var finalDate       = getCurrentDate();
				var currentContent  = targetField.value;
				var newContent      = sourceField.value;
				
				if ( newContent == "" ) {
				
					window.alert("Por favor forneca uma informação válida.");
					
				} else {
				
					if ( hiddenField.value != "blank" )
						hiddenField.value += "_";
				
					hiddenField.value += "[" + finalDate + "] " + newContent;
				
					if ( currentContent == "" )
						var finalContent = "[" + finalDate + "] " + newContent;
					else 
						var finalContent = currentContent + "\n" + "[" + finalDate + "] " + newContent;
					
					targetField.value = finalContent;

					showHide(name + '_inputLine');
					showHide(name + '_addLine');
				
				}

			}
			
			function initProgressObservacaoYm() {
				
				Element.addClassName('observacaoYm', 'progressMeterLoading');
			}

			function resetProgressObservacaoYm() {
			
			  Element.removeClassName('observacaoYm', 'progressMeterLoading');
			  
			  if ($('observacaoYm').value == "null" )
			  	$('observacaoYm').value ="";
			
			    /* do cool effect */
			    new Effect.Highlight('observacaoYm');
			    
			    window.document.getElementById("observacaoYmTxt").value = "";			    
			  
			}
			
			
			
			function setFocus(field) {
			
				window.document.getElementById(field).focus();
			
			}
		
			function setTitle() {
			
				window.top.location.href ="javascript:newTitle('Classificação Técnica de Garantia');";
			
			}
			
			function setTitleAlter(title) {
			
				window.top.location.href ="javascript:newTitle('" + title + "');";
			
			}
			
			function devolver() {
				
				if(window.confirm("Confirma a Devolução da Solicitação de Garantia?")) {
					__showAnima();
					submitFormTsk(window.document.classificaGarantiaMotoForm,"classificar&prcCode=BCK_SOL",true);
				} else
					return false;
				
			}
			
			function enviarClassifica() {
				
				if(window.confirm("Confirma o Envio da Solicitação de Garantia?")) {
					__showAnima();
					submitFormTsk(window.document.classificaGarantiaMotoForm,"classificar&prcCode=LIB_COM",true);
				} else
					return false;
				
			}
			
			function imprimir() {
				
				if(window.confirm("Confirma a Impressão da Solicitação de Garantia?")) {					
					Element.show("imprimir");
					Element.hide("botoes");
					window.print();
					Element.hide("imprimir");
					Element.show("botoes");
				} else
					return false;
				
			}
			
			function voltar() {
				if(window.confirm("Retornar a Pesquisa de Garantias?")) {
					__showAnima();
					window.location.href = "ClassificaGarantiaMoto.do?task=showForm";
				}
			}
			
			function redirectList(result) {
			
				if(result) {
					if(confirm("Retornar a Pesquisa de Garantias?")){
						__showAnima();
						window.location.href = "ClassificaGarantiaMoto.do?task=showForm";
						
					} else {
						 window.document.getElementById("devolverBtn").disabled = "true";
						 window.document.getElementById("enviarBtn").disabled  = "true";
						 window.document.getElementById("insertObservacao").disabled = "true";
						 window.document.getElementById("insertParecer").disabled = "true";
					}
				}				
			}
			 
		</script>
	</head>
	<body leftmargin="0" topmargin="2" onLoad="__loadEsconde();">
		<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <img src="../../images/run.gif"/>
		            <br /><br />Carregando Aguarde...
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>		
		<html:form action="/ClassificaGarantiaMoto" method="post" enctype="multipart/form-data">			
			<html:hidden property="entityId" styleId="entityId"/>	
			<html:hidden property="loteId" styleId="loteId"/>
			<html:hidden property="parecerYm_newItems"/>
			<html:hidden property="observacaoYm_newItems"/>		
			<jsp:include flush="true" page="../include_alerts.jsp"/><br>
			 
			<div id="imprimir" style="display:none;">
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">
				<tr> 
					<td><img src="../../images/home_logo_red.gif"/></td>					
				</tr>
				</table>
			</div>			 
			
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.garantia"/></legend>
				
							
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">
			<tr height="25"> 
				<td width="8%"  class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.lote"/>:
				</td>
				<td width="15%" class="text">	
					<bean:write name="classificaGarantiaMotoForm" property="loteId"/>
				</td>
				<td width="9%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.sgnum"/>:
				</td>
				<td width="10%" class="text">	
					<bean:write name="classificaGarantiaMotoForm" property="entityId"/>
				</td>
				<td width="8%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.chassi"/>:
				</td>
				<td class="text" colspan="3">
					<html:text property="chassi" styleId="chassi" styleClass="text_field_maior" readonly="true"></html:text>
				</td>
			</tr>
			<tr height="25"> 
				<td width="8%"  class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.dtVenda"/>:
				</td>
				<td class="text" width="17%">	
					<html:text property="dtVenda" styleId="dtVenda" styleClass="text_field_date" readonly="true"></html:text>							
				</td>
				<td width="9%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.nuOS"/>:
				</td>
				<td width="10%" class="text">
					<html:text property="numOS" styleId="numOS" styleClass="text_field_maior" readonly="true"></html:text>
				</td>
				<td width="8%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.nudtAbert"/>:
				</td>
				<td class="text">	
					<html:text property="dtAbert" styleId="dtAbert" styleClass="text_field_date" readonly="true"></html:text>
				</td>
				<td width="8%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.dtFech"/>:
				</td>
				<td class="text">	
					<html:text property="dtFech" styleId="dtFech" styleClass="text_field_date" readonly="true"></html:text>
				</td>
			</tr>
			</table>
			<br/>	
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
			<tr height="25"> 
				<td class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.descricao.condicao"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td  class="text">	
					<html:textarea property="condicaoProblema" styleId="condicaoProblema" styleClass="text_field_maior" style="width:100%" rows="6" readonly="true"/>
				</td>
			</tr>
			<tr height="25"> 
				<td class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.descricao.causa"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td class="text">	
					<html:textarea property="causaProblema" styleId="causaProblema" styleClass="text_field_maior" style="width:100%" rows="6" readonly="true"/>
				</td>
			</tr>
			<tr height="25"> 
				<td class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.descricao.diagnostico"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td class="text">	
					<html:textarea property="diagnostico" styleId="diagnostico" styleClass="text_field_maior" style="width:100%" rows="6" readonly="true"/>
				</td>
			</tr>
			<tr height="25"> 
				<td class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.garantia.descricao.solucao"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td class="text">	
					<html:textarea property="solucao" styleId="solucao" styleClass="text_field_maior" style="width:100%" rows="6" readonly="true"/>
				</td>
			</tr>			
			</table>
			<br/>
			</fieldset>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.faturamento"/></legend>
				
			<table width="98%" border="0" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;" align="center">
			<tr height="25"> 
				<td width="15%"  class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.analise.faturamento.dtFaturamento"/>:
				</td>
				<td width="40%" class="text">	
					<bean:write name="classificaGarantiaMotoForm" property="dtFaturamento"/>
				</td>
				<td width="10%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.analise.faturamento.ntFiscal"/>:
				</td>
				<td width="10%" class="text">	
					<bean:write name="classificaGarantiaMotoForm" property="nuNtFiscal"/>
				</td>
				<td width="10%" class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.analise.faturamento.empresa"/>:
				</td>
				<td class="text" class="text">
					<bean:write name="classificaGarantiaMotoForm" property="empresa"/>
				</td>
			</tr>
			<tr height="25"> 
				<td  class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.analise.faturamento.conces"/>:
				</td>
				<td class="text">	
					<bean:write name="classificaGarantiaMotoForm" property="dsConcessionaria"/> <br/> <bean:write name="classificaGarantiaMotoForm" property="cnpjConc"/>							
				</td>
				<td class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.analise.faturamento.cidade"/>:
				</td>
				<td class="text">
					<bean:write name="classificaGarantiaMotoForm" property="dsCidade"/>
				</td>
				<td class="td_dark">	
					<bean:message key="classificaGarantiaMoto.label.analise.faturamento.uf"/>:
				</td>
				<td class="text">	
					<bean:write name="classificaGarantiaMotoForm" property="dsUf"/>
				</td>				
			</tr>
			</table>
			<br/>
			</fieldset>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.revisao"/></legend>
			<center>
			<display:table name="requestScope.classificaGarantiaMotoForm.listRevisao" 
			               requestURI="${pageContext.request.contextPath}/ClassificaGarantiaMoto.do"
			               export="false" 
			               sort="list" 
			               id="cupom"
			               class="grid"
			               style="width='98%'">
								
				<display:column titleKey="classificaGarantiaMoto.label.analise.revisao.dtRevisao" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${cupom.maskedDataRevisao}"/>	
				</display:column>	
									
				<display:column titleKey="classificaGarantiaMoto.label.analise.revisao.nuRevisao" maxLength="30" headerClass="headerAlign">						
					<c:out value="${cupom.revisao.numeroRevisao}"/>	
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.revisao.concessionaria"  headerClass="headerAlign">						
					 <c:out value="${cupom.lote.concessionaria.razaoSocial}"/> <br/> <c:out value="${cupom.lote.concessionaria.maskedCnpj}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.revisao.quilometragem" style="text-align: right;"  headerClass="headerAlign">						
					 <c:out value="${cupom.quilometragem}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.revisao.lote" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${cupom.lote.entityId}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.revisao.cupom" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${cupom.cupomCode}"/>
				</display:column>
				
				<display:column  titleKey="classificaGarantiaMoto.label.analise.revisao.statusLote" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${cupom.lote.statusLote.descricaoReduzida}"/>
				</display:column>	
				
				<display:column  titleKey="classificaGarantiaMoto.label.analise.revisao.statusRev" style="text-align: center;"  headerClass="headerAlign">
					 <c:out value="${cupom.statusGarantia.descricao}"/>
				</display:column>
				
			</display:table>
			</center>
			</fieldset>
			<br/>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.historico.garantia"/></legend>
			<center>	
			<display:table name="requestScope.classificaGarantiaMotoForm.listGarantias" 
			               requestURI="${pageContext.request.contextPath}/ClassificaGarantiaMoto.do"
			               export="false" 
			               sort="list" 
			               id="garantia"
			               class="grid"
			               style="width='98%'">
								
				<display:column titleKey="classificaGarantiaMoto.label.analise.historico.lote" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${garantia.loteId}"/>	
				</display:column>	
									
				<display:column titleKey="classificaGarantiaMoto.label.analise.historico.sg" style="text-align: center;" maxLength="30" headerClass="headerAlign">						
					<c:out value="${garantia.garantiaId}"/>	
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.historico.conc"  headerClass="headerAlign">						
					 <c:out value="${garantia.razaoSocialConcessionaria}"/> <br/> <c:out value="${garantia.maskedCnpj}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.historico.dtabertura" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${garantia.maskedDataAbertura}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.historico.dtLiberacao" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${garantia.maskedDataLiberacao}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.historico.status" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${garantia.dsStatusMov}"/>
				</display:column>				
			</display:table>
			</center>
			</fieldset>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.pecas"/></legend>
			<center>
			<display:table name="requestScope.classificaGarantiaMotoForm.listPecas" 
			               requestURI="${pageContext.request.contextPath}/ClassificaGarantiaMoto.do"
			               export="false" 
			               sort="list" 
			               id="peca"
			               class="grid"
			               style="width='98%'">
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.linha" style="text-align: center;"  headerClass="headerAlign">
					<c:out value="${peca.compositeEntityId.lineId}"/>
				</display:column>
					
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.qtde" paramProperty="quantidade" style="text-align: center;"  headerClass="headerAlign">	
					<c:out value="${peca.quantidade}"/>
				</display:column>	
								
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.peca" headerClass="headerAlign">						
					<c:out value="${peca.item.itemCode}"/>	- <c:out value="${peca.item.descricao}"/>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.pecaCausadora" style="text-align: center;" headerClass="headerAlign">	
					<c:if test="${peca.pecaCausadora != 'S'}">					
						<input type='checkbox'  id='pecaCausadora'  name='pecaCausadora' disabled="disabled" value='<c:out value="${peca.pecaCausadora}"/>'/>
					</c:if>
					<c:if test="${peca.pecaCausadora == 'S'}">					
						<input type='checkbox'  id='pecaCausadora'  name='pecaCausadora' disabled="disabled" checked="checked" value='<c:out value="${peca.pecaCausadora}"/>'/>
					</c:if>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.cobraPecaFornecedor" style="text-align: center;" headerClass="headerAlign">	
					<c:if test="${peca.cobraPecaFornecedor != 'S'}">					
						<input type='checkbox'  id='cobraPecaFornecedor'   name='cobraPecaFornecedor' value='<c:out value="${peca.pecaCausadora}"/>@<c:out value="${peca.compositeEntityId.lineId}"/>'/>
					</c:if>
					<c:if test="${peca.cobraPecaFornecedor == 'S'}">					
						<input type='checkbox'  id='cobraPecaFornecedor'   name='cobraPecaFornecedor' checked="checked" value='<c:out value="${peca.pecaCausadora}"/>@<c:out value="${peca.compositeEntityId.lineId}"/>'/>
					</c:if>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.pecaFaltante" style="text-align: center;" headerClass="headerAlign">	
					<c:if test="${peca.pecaFaltante != 'S'}">					
						<input type='checkbox'  id='pecaFaltante'   name='pecaFaltante' disabled="disabled" value='<c:out value="${peca.pecaCausadora}"/>'/>
					</c:if>
					<c:if test="${peca.pecaFaltante == 'S'}">					
						<input type='checkbox'  id='pecaFaltante'   name='pecaFaltante' disabled="disabled" checked="checked" value='<c:out value="${peca.pecaCausadora}"/>'/>
					</c:if>
				</display:column>
				
				<display:column titleKey="classificaGarantiaMoto.label.analise.pecas.enviar" style="text-align: center;" headerClass="headerAlign">	
					<c:if test="${peca.enviaPeca != 'S'}">					
						<input type='checkbox'  id='enviar'   name='enviar' disabled="disabled" value='${peca.enviaPeca}'/>
					</c:if>
					<c:if test="${peca.enviaPeca == 'S'}">					
						<input type='checkbox'  id='enviar'   name='enviar' disabled="disabled" checked="checked" value='<c:out value="${peca.enviaPeca}"/>'/>
					</c:if>
				</display:column>
							
			</display:table>
			</center>
			</fieldset>
			<br/>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.yamaha"/></legend>
				
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
			<tr>
				<td class="td_dark" align="left">	
					<bean:message key="classificaGarantiaMoto.label.analise.yamaha.parecer"/>:
				</td>
			</tr>
			<tr>
				<td>
					<html:textarea property="parecerYm" styleId="parecerYm" styleClass="text_field_maior" style="width:100%" rows="6" readonly="true"/>	
				</td>
			</tr>
			<tr id="parecerYm_inputLine" style="display:none">
				<td align="right" width="100%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td class="td_dark" width="10%" align="right"><bean:message key="form.lbl.add"/>:&nbsp;</td>
							<td>
								<input type="text" name="parecerYmNewText" id="parecerYmTxt" class="text_field_maior"/>															
							</td>							
						</tr>
					</table>
				</td>
			</tr>
			</table>
			<br/>
			</fieldset>
			<br/>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.classifica.yamaha.classifica"/></legend>
				
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">		
			<tr>
				<td class="td_dark" align="left">	
					<bean:message key="classificaGarantiaMoto.label.classifica.yamaha.tipo.problema"/>:
				</td>
				<td class="td_dark" align="left">	
					<bean:message key="classificaGarantiaMoto.label.classifica.yamaha.acao"/>:
				</td>
			</tr>
			<tr>
				<td>
					<html:select property="tipoProblema" styleId="tipoProblema" styleClass="text_field_list">
							<html:option value=""></html:option>
							<html:option value="AA1">AA1</html:option>
							<html:option value="AA2">AA2</html:option>
							<html:option value="AA3">AA3</html:option>
				    </html:select>
				</td>
				<td>
					<html:select property="classificaAcao" styleId="classificaAcao" styleClass="text_field_list">
							<html:option value=""></html:option>
							<html:option value="Solicitação de Fotos">Solicitação de Fotos</html:option>
							<html:option value="Solicitação de Maiores Informações do Reparo">Solicitação de Maiores Informações do Reparo</html:option>
							<html:option value="Liberar Sem Solicitação">Liberar Sem Solicitação</html:option>
				    </html:select>
				</td>
			</tr>			
			<tr>
				<td class="td_dark" align="left" colspan="2">	
					<bean:message key="classificaGarantiaMoto.label.classifica.yamaha.observacao"/>:
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<html:textarea property="observacaoYm" styleId="observacaoYm" styleClass="text_field_maior" style="width:100%" rows="6"  readonly="true"/>	
				</td>
			</tr>
			<tr id="observacaoYm_inputLine" style="display:none">
				<td align="right" width="100%" colspan="2">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td class="td_dark" width="10%" align="right"><bean:message key="form.lbl.add"/>:&nbsp;</td>
							<td>
								<input type="text" name="observacaoYmNewText" id="observacaoYmTxt" class="text_field_maior"/>															
							</td>
							<td width="30%" align="right">														
								<html:button property="saveObservacao" styleClass="button_medium"><bean:message key="form.btn.save"/></html:button>
								&nbsp;
								<button name="cancelObservacao"  class="button_medium" onClick="JavaScript:showHideInput('observacaoYm');"><bean:message key="form.btn.cancel"/></button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="observacaoYm_addLine" style="display:show">
				<td align="right" colspan="2">
					<button name="insertObservacao" id="insertObservacao" class="button_medium" onClick="JavaScript:showHideInput('observacaoYm');"><bean:message key="form.lbl.add"/></button>
				</td>
			</tr>
			</table>
			<br/>
			</fieldset>
			<fieldset>
				<legend  class="labelList"><bean:message key="classificaGarantiaMoto.label.analise.arquivo"/></legend>
			
			<logic:notEmpty name="classificaGarantiaMotoForm" property="listFiles">
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">
				<tr>
					<td class="td_dark"><bean:message key="classificaGarantiaMoto.label.analise.arquivo.title"/></td>
				</tr>												
				<logic:iterate name="classificaGarantiaMotoForm" property="listFiles" id="file">
					<tr>
						<td class="text">
						 		<html:link action="/ClassificaGarantiaMoto.do?task=download&fileId=${file.entityId}"
							 			onmouseover="window.status='${file.filename}';"
										onmouseout="window.status='';"
										style="text-decoration: underline;">
							 		<bean:write name="file" property="filename"/>
							 	</html:link>
						</td>							
					</tr>
				</logic:iterate>
				</table>
			</logic:notEmpty>
			
			</fieldset>
			<br/>
			<div id="botoes">
			<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
				<tr>
					<td height="10" class="text" align="center">
						<html:button property="devolverBtn" styleId="devolverBtn"  altKey="classificaGarantiaMoto.label.btn.alt.devolver" styleClass="button_medium" onclick="javascript:devolver();">
							<bean:message key="form.btn.devolver"/>
						</html:button>
							&nbsp;
						<html:button property="enviarBtn" styleId="enviarBtn" altKey="classificaGarantiaMoto.label.btn.alt.liberar" styleClass="button_medium" onclick="javascript:enviarClassifica();">
							<bean:message key="form.btn.liberar"/>
						</html:button>
							&nbsp;
						<html:button property="imprimirBtn" styleId="imprimirBtn" altKey="classificaGarantiaMoto.label.btn.alt.imprimir" styleClass="button_medium" onclick="javascript:imprimir();">
							<bean:message key="form.btn.imprimir"/>
						</html:button>
							&nbsp;
						<html:button property="backSGBtn" styleId="backSGBtn" altKey="classificaGarantiaMoto.label.btn.alt.voltar" styleClass="button_medium" onclick="javascript:voltar();">
							<bean:message key="form.btn.preview"/>
						</html:button>
					</td>
				</tr>
			</table>
			</div>
								  
			<%-- Função AJAX para incluir a Observação --%>
				<ajax:updateField
					  baseUrl="${pageContext.request.contextPath}/InsertAnaliseObservacaoYm.do"
					  source="observacaoYmTxt"
					  target="observacaoYm"
					  action="saveObservacao"
					  parser="new ResponseXmlParser()"						  
					  parameters="observacaoYmTxt={observacaoYmTxt},entityId={entityId}" 
					  preFunction="initProgressObservacaoYm"
					  postFunction="resetProgressObservacaoYm"/>
		</html:form>
		<script type="text/javascript">
			__loadEsconde();			
		</script>
		<ym:javaScriptExecuter/>
		<ym:confirmMessage />
		<ym:alertMessage />
	</body>
</html>