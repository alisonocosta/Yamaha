<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	%>
<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@taglib uri="http://struts.apache.org/tags-logic"    prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"	    %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		   prefix="ajax"    %>

<ym:checkLogon roleName="sg_cd_solg" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......garantia_moto_form.jsp
 *   .: Criação.....13 de outubro de 2012, 10:25
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de garantia para linha de Motocicleta.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="garantiaMotoForm" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/autocomplete/autocomplete.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/progressbar/carregador.css"></link>
		<script language="JavaScript" src="../../scripts/General/form.js"></script>
		<script language="JavaScript" src="../../scripts/Formatter/maskDate.js"></script>	
		<script language="JavaScript" src="../../scripts/Formatter/cpfCnpj.js"></script>		
		<script language="JavaScript" src="../../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript" src="../../scripts/progressbar/carregador.js"></script>
		<script type="text/javascript">
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
		
			
			/* Setamos uma variável que determina o estado do campos do form 
			 * conforme o status de um lote
			 */
			<core:set var="readonly" value="false"/>
			
			<core:if test="${statusLoteId == 4}">
			
				<core:set var="readonly" value="true"/>
			
			</core:if>
			
			function voltar() {
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relação de Garantias');";
				window.location.href = "GarantiaMoto.do?task=list&loteId=" + window.document.garantiaMotoForm.lote.value;
			}
			
			function setFocus(field) {
			
				window.document.getElementById(field).focus();
			
			}
		
			function setTitle() {
			
				window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia');";
			
			}
			
			function setTitleAlter(title) {
			
				window.top.location.href ="javascript:newTitle('" + title + "');";
			
			}
			
			function setTitleRecall() {
			
				window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia - Recall');";
			
			}
			
			function save() {
				__showAnima();
				submitFormTsk(window.document.garantiaMotoForm,"save",true);
			
			}
			
			function editPeca() {
			
				__showAnima();
				submitFormTsk(window.document.garantiaMotoForm,"alterLine",true);
			
			}
			
			function viewPeca() {			
			
				__showAnima();
				submitFormTsk(window.document.garantiaMotoForm,"alterLine&view=true",true);
			
			}
			
			function cancel() {
				
				window.document.garantiaMotoForm.validated.value = "false";
				
			}
			
			// *********************************************************************** //
			// *    Funções auxiliares da busca de chassi - AJAX					 * //
			// *********************************************************************** //
			function storeChassi() {
					
					var chassiFieldSource   = window.document.garantiaMotoForm.chassi;
					var chassiFieldTarget   = window.document.garantiaMotoForm.escapedChassi;			
					chassiFieldTarget.value = escape(chassiFieldSource.value);
					
			}
			
			function initProgress() {
				Element.hide('msg');
				Element.show('indicator');	
			}
			
			function finalProgress() {
			
				Element.hide('indicator');				
				if (window.document.garantiaMotoForm.autEsp.options.length == 0 ) {
					window.document.garantiaMotoForm.autEsp.disabled = true;					
				} else {
					window.document.garantiaMotoForm.autEsp.disabled = false;
				}
							
			}
						
			function reportError() {
			
				if (window.document.garantiaMotoForm.autEsp.options.length == 0) {
					window.document.getElementById("msg").innerHTML = "Erro - Valores não Encontrados!";
				}
				Element.show('msg');
				setTimeout("Element.hide('msg')", 2500); 
			}
			
			// *********************************************************************** //
			// *    Funções auxiliares da busca de Serviço - AJAX					 * //
			// *********************************************************************** //
			function storeServico(field) {
					
					var servicoFieldSource   = eval("window.document.garantiaMotoForm.servico_" + field);
					var servicoFieldTarget   = eval("window.document.garantiaMotoForm.escapedServico_" + field);			
					servicoFieldTarget.value = escape(servicoFieldSource.value);
					
			}
			
			function fimServico_1() {
			
				Element.hide('indicatorServ_1');
					
				if ( window.document.garantiaMotoForm.descServico_1.value == "O Serviço não foi encontrado!" )	
					setTimeout("window.document.garantiaMotoForm.descServico_1.value = ''", 2500);
			
			}
			
			function fimServico_2() {
			
				Element.hide('indicatorServ_2');
					
				if ( window.document.garantiaMotoForm.descServico_2.value == "O Serviço não foi encontrado!" )	
					setTimeout("window.document.garantiaMotoForm.descServico_2.value = ''", 2500);
			
			}
			
			function fimServico_3() {
			
				Element.hide('indicatorServ_3');
					
				if ( window.document.garantiaMotoForm.descServico_3.value == "O Serviço não foi encontrado!" )	
					setTimeout("window.document.garantiaMotoForm.descServico_3.value = ''", 2500);
			
			}
			
			// ****************************  FIM   ********************************* //
			
			function getGarantiaValues() {
				
				if ( window.document.getElementById("chassi").value != "" ) {
					window.document.getElementById("selldate").click();
					window.document.getElementById("reqChassi").click();	
					window.document.getElementById("imgCampaign").click();	
					window.document.getElementById("numOS").focus();
				}			
				
			}
			
			function getGarantiaValuesRetorno() {
				
				window.document.getElementById("selldate").click();	
				window.document.getElementById("reqChassi").click();
				window.document.getElementById("imgCampaign").click();		
				//window.document.getElementById("chassi").focus();			
				
			}
			
			function clearValues() {
			
				$('dtVenda').value    = "";
				$('isRecall').value   = "";
				
			}
			
			function resetValues() {
			
				var layer_recall   = window.document.getElementById("recall_div");
				
				if ( $('dtVenda').value == "null" )
			    	$('dtVenda').value ="";
			    if ( $('isRecall').value == "null" )
			    	$('isRecall').value = "false";
			    	
			    if ( $('isRecall').value == "true" ) {
			    
			    	layer_recall.style.display = ""; 
			    
			    } else {
			    
			    	layer_recall.style.display = "none"; 
			    
			    }
			    	
			    new Effect.Highlight('dtVenda');
			    new Effect.Highlight('isRecall');
			
			}
			
			function clearValuesCampaign() {
			
				$('isCampaign').value = "";
				
			}
			
			function resetValuesCampaign() {
			
				var layer_campaign = window.document.getElementById("campaign_div"); 
				var campaign       = window.document.getElementById("campaignId"); 
								
				if ( campaign.length > 0 )
					layer_campaign.style.display = "";
				else
					layer_campaign.style.display = "none";
					
			}
			
			/* ////////////////////////////////////////////*/
			/* Início das validações e gravação da revisão */
			/* ////////////////////////////////////////////*/
			function gravarMotoForm(form) {
			
    			var i;
    			var isServTer; 
    			var isRecall   = false;
    			var isCortesia = false;
    			var vData1  = form.dtAbert.value != "" ? true : false ;
    			var vData2  = form.dtFech.value != "" ? true : false ;
    			var data1   = form.dtAbert.value;
    			var data2   = form.dtFech.value;
    			
    			//Quando não existir revisão de entrega e km maior que zero, nãp permitir gravar e solicitar XML
    			if(!checkRevisaoEntrega()) {
    				
    				return false;
    			}
    			
    			if ( vData1  && vData2 ) {
    			
    				// Verificamos se a data de abertura e fechamento são menores ou igual a data atual
    				if ( checkDateToday("dtAbert") ) {
    					
    					if ( checkDateToday("dtFech") ) {
	    					
	    					// Verificamos se a data de abertura e fechamento são válidas
			    			if ( comparaData( data1, data2 ) == 1 ) {
			    				
			    				window.alert("A data de fechamento deve ser maior que a data de Abertura!");
			    				form.dtFech.value = "";
			    				form.dtFech.focus();
			    				return false;
			    							    			
			    			}
			    			
			    		} else { 
			    		
			    			return false;
			    		
			    		}
	    			
	    			} else {
	    			
	    				return false;
	    			
	    			}
	    			
	    		} else {
	    		
	    			if ( !vData1 ) {
	    			
	    				window.alert("A data de Abertura é obrigatória!");
	    				form.dtAbert.value = "";
	    				form.dtAbert.focus();
	    				return false;
	    			}
	    			<%--  24/07/2013  ist_edson
	    			else if ( !vData2 ) {
	    			
	    				window.alert("A data de fechamento é obrigatória!");
	    				form.dtFech.value = "";
	    				form.dtFech.focus();
	    				
	    			}
	    			--%>
	    			
	    		}
	    		
	    		for (i=0;i<window.document.garantiaMotoForm.hasServTer.length;i++){ 
    			
       				if (window.document.garantiaMotoForm.hasServTer[i].checked) {
       				
          				isServTer = window.document.garantiaMotoForm.hasServTer[i]; 
          				
    				} 
    				
    		    }
	    		
	    		if ( isServTer.value == "S" ) {
	    		
	    			var valueServTer = window.document.garantiaMotoForm.servTer;
	    			var justServTer  = window.document.garantiaMotoForm.justif;
	    			
	    			if ( valueServTer.value.trim() == "" || (valueServTer.value.trim()).length < 1 ) {
	    			
	    				window.alert("O campo Valor de Serviços de Terceiros é Obrigratório!");
	    				window.document.garantiaMotoForm.servTer.focus();
	    				return false;
	    			
	    			} else if ( justServTer.value.trim() == "" || (justServTer.value.trim()).length < 1 ) {
	    			
	    				window.alert("O campo Justificativa de Serviços de Terceiros é Obrigratório!");
	    				window.document.garantiaMotoForm.justif.focus();
	    				return false;
	    			
	    			}
	    		}
	    		
	    		/* Precisamos verificar é recall */
	    		if ( window.document.garantiaMotoForm.recall != undefined ) {
	    			
	    			
	    			
	    			if ( window.document.garantiaMotoForm.recall[0].checked == true ) {
	    				
	    				var indexRecall = window.document.garantiaMotoForm.recallId.selectedIndex;
	    				var recallId    = window.document.garantiaMotoForm.recallId.options[indexRecall];
	    				
	    				isRecall = true;
	    				
	    				if ( recallId.value == "null" || recallId.value == "" ) {
	    				
	    					window.alert("Selecione o número da informação técnica!");
	    					window.document.garantiaMotoForm.recallId.focus();
	    					return false;
	    				
	    				}
	    			
	    			}	    		
	    		
	    		}
	    		
	    		/* Verificamos se é cortesia */	    		
	    		if ( window.document.garantiaMotoForm.cortesia != undefined ) {	    			
	    			isCortesia = window.document.garantiaMotoForm.cortesia[0].checked; 
	    		}
	    		
	    		<core:if test="${idTipo == 1}">
	    		
	    			var phasKM = true; 
	    			if ( window.document.garantiaMotoForm.hasKM.value == "false" )
	    				phasKM = false;
	    			
		    		if ( phasKM ) {
	    			
			    		/* Validação da quilomtragem mínima*/ 	    		
			    		if ( !checkKM() )
			    			return false;
			    		
			    		/* Se for recall ou cortesia, validamos a KM máxima 
			    		 * Se for maior que o parâmetro, apresentar mensagem de confirmação
			    		*/	
			    		if ( !checkKMMax() ) 
			    			if ( !isRecall && !isCortesia) {	
			    				window.alert("Quilometragem inválida para realização de serviços de Garantia!");	    			
			    				return false;
			    			} else 
			    				if ( !window.confirm("Confirma o valor " +  window.document.getElementById('km').value + " para Quilometragem?") ) 
			    					return false;
			    	}
		    					
	    		</core:if>
	    			
    		    
    		    /* Validação das informações - Validation Struts */
    		    if ( validateGarantiaMotoForm(form) ) {
    		    
    		    	window.document.garantiaMotoForm.gravar.disabled = true;
    		    	__showAnima();
    		    	submitFormTsk(form,"save",true);
    		    	
    		    } else 
    		    	return false;
    		    	
			}
			/* /////////////////////////////////////////*/
			/* Fim das validações e gravação da revisão */
			/* /////////////////////////////////////////*/			
			
			/*///////////////////////////////////////////////////////////////////*/
			/*	Habilita o campo para digitar o número da Informação técnica 	 */
			/*///////////////////////////////////////////////////////////////////*/
			function enableNumber() {
			
				var layer_numIT	= window.document.getElementById("numIT_div"); 
			
				if ( window.document.garantiaMotoForm.recall[0].checked ) {
				
					layer_numIT.style.display = "";
					window.document.getElementById("recallId").focus();
					
				} else if ( window.document.garantiaMotoForm.recall[1].checked ) {
				
					layer_numIT.style.display = "none";
					window.document.getElementById("recallId").selectedIndex = "0";
					
				}
							
			}
			
			/*///////////////////////////////////////////////////////////////////*/
			/*	Habilita o campo para selecionar o código da campanha 			 	 */
			/*///////////////////////////////////////////////////////////////////*/
			function enableCodeCampaign() {
			
				var layer_campaignNum	= window.document.getElementById("campaignNum_div"); 
			
				if ( window.document.garantiaMotoForm.campaign[0].checked ) {
				
					layer_campaignNum.style.display = "";
					window.document.getElementById("campaignId").focus();
					
				} else if ( window.document.garantiaMotoForm.campaign[1].checked ) {
				
					layer_campaignNum.style.display = "none";
					window.document.getElementById("campaignId").selectedIndex = "0";
					
				}
							
			}
			
			/*////////////////////////////////////////////////////////////*/
			/*                 Valida o valor de serviços                 */
			/*////////////////////////////////////////////////////////////*/
			function validateVST() {
				
				var valor = window.document.getElementById("servTer").value;
			
				var valorUnformat = clearFormat(valor);
				 
				if ( valor != "") {
					if (isNaN(valorUnformat) ) {
						window.alert("Valor incorreto!");
						window.document.getElementById("servTer").focus();
					} else {
						window.document.getElementById("servTerUnFormat").value = valorUnformat;
					}
				}
			}
			
			/*/////////////////////////////////////////////////////*/
			/*  Retira a formatação de moeda                       */
			/*/////////////////////////////////////////////////////*/
			function clearFormat( valor ) {
				var val = new String();
				val = valor.toString().replace(".","");
				val = val.toString().replace(",",".");
				
				return val;
			
			}
			
			/*//////////////////////////////////////////////////////*/
			/*  Checa se data de Abertura da SG é menor que a venda */
			/*//////////////////////////////////////////////////////*/
			function checkDate(id) {
	 		
	 			var data1 = window.document.getElementById("dtVenda").value;
	 			var data2 = window.document.getElementById(id).value;
	 			
	 			//window.alert("Data 1:" + data1 + "  Data 2:" + data2);
	 			
	 			if ( data2 != "" && data1 != "" ) {
		 			if ( comparaData( data1, data2 ) > 0 ) {
		 				window.alert("A Data de Abertura não pode ser menor que a data da venda!");
		 				window.document.getElementById(id).value = "";
		 				window.document.getElementById(id).focus();
		 				return false;
		 			}
		 		}	 		
		 		
		 		return true;
	 		}
	 		
	 		/*//////////////////////////////////////////////////////*/
			/*  Checa se data de um campo é menor ou igual a data atual */
			/*//////////////////////////////////////////////////////*/
	 		function checkDateToday(id) {
	 		
	 			var data1 = window.document.getElementById("sysDate").value;
	 			var data2 = window.document.getElementById(id).value;
	 			
	 			if ( data2 != "" && data1 != "" ) {
		 			if ( comparaData( data1, data2 ) < 0 ) {
		 				window.alert("Data informada não pode ser maior que a data atual!");
		 				window.document.getElementById(id).value = "";
		 				window.document.getElementById(id).focus();
		 				return false;
		 			}
		 		}	 		
		 		
		 		return true;
	 		}
			
			/*////////////////////////////////////////////////////////////////////////////////*/
			/* Este método trata a apresentação da descrição do valor selecionado nos objetos */
			/* select do form.                                                                */
			/*////////////////////////////////////////////////////////////////////////////////*/
			function showLabel(campoId,objId) {
			
				var campo  = window.document.getElementById(campoId);
				var objeto = window.document.getElementById(objId);
				
				var	index   = campo.selectedIndex;
				var	code    = campo.options[index].text;				
				var	valor   = campo.options[index].value;
				
				
				/** Caso o valor selecionado seja vazio, devemos ocultar a mensagem */
				if ( valor == 0  || valor == "" ){
				
					Element.hide(objId);	
					
				} else {
				
					/** Verificamos de qual objeto veio a solicitação e exibimos a descrição correspondente 
					 *  Este  primeiro item trata a seleção de um item do objeto select de Sintoma.
					 */
					if ( objId == "si" ) {
				
						<logic:iterate name="garantiaMotoForm" property="listSintomas" id="sintoma">
						
							if ( code == "<core:out value='${sintoma.sintomaCode}'/>" ) {
							
								objeto.innerHTML = "<core:out value='${sintoma.descricao}'/>";
								Element.show(objId);
							
							}
						
						</logic:iterate>							
					
					/** Aqui tratamos a seleção do objeto select de Condição */		
					} else if ( objId == "co" ) {
				
						<logic:iterate name="garantiaMotoForm" property="listCondicoes" id="condicao">
						
							if ( code == "<core:out value='${condicao.condicaoCode}'/>" ) {
							
								objeto.innerHTML = "<core:out value='${condicao.descricao}'/>";
								Element.show(objId);
							
							}
						
						</logic:iterate>	
					} 								
					
				}	
			
			}
			
			/** Função para apresentar e ocultar os campos de valor de terceiros */
			function enableServTer() {
			
				var layer_servTer	= window.document.getElementById("servTer_div"); 
				
				if ( window.document.garantiaMotoForm.hasServTer[0].checked ) { 
				
					layer_servTer.style.display = ""; 
					window.document.getElementById("servTer").focus();
				
				} else if ( window.document.garantiaMotoForm.hasServTer[1].checked ) { 
					
					window.document.getElementById("servTer").value = "";
					window.document.getElementById("servTerUnFormat").value = "";
					window.document.getElementById("justif").value  = "";
					layer_servTer.style.display = "none"; 
				
				}			
			
			}
			
			/** Função que habilita ou não a digitação do número da cortesia */
			function enableCortesia() {
			
				var layer_contesia	= window.document.getElementById("cortesia_div"); 
				
				if ( window.document.garantiaMotoForm.cortesia[0].checked ) { 
					
					layer_contesia.style.display = ""; 
					window.document.getElementById("autEsp").readOnly = false;
					window.document.getElementById("autEsp").focus();
				
				} else if ( window.document.garantiaMotoForm.cortesia[1].checked ) { 
					
					window.document.getElementById("autEsp").value = "";
					window.document.getElementById("autEsp").readOnly = true;
					layer_contesia.style.display = "none"; 
				
				}
			}
			
			/** Função para confirmação da opção de Recall e restauração da página */
			function confirmRecall() {
			
				var form  = window.document.garantiaMotoForm;
				var obj   = window.document.getElementById("recallId");			
				var index = obj.selectedIndex;				
				
				if ( index != 0 && index != "" ) {
				
						if ( window.confirm("Confirma a opção de Recall?") ) {
						
							submitFormTsk(form,"addRecall",true);
						
						}
				
				}
			
			}
			
			/** Função para confirmação da opção de Campanha e restauração da página */
			function confirmCampaign() {
			
				var form  = window.document.garantiaMotoForm;
				var obj   = window.document.getElementById("campaignId");			
				var index = obj.selectedIndex;				
				
				if ( index != 0 && index != "" ) {
				
						if ( window.confirm("Confirma a opção da Campanha?") ) {
						
							submitFormTsk(form,"addCampaign",true);
						
						} else {
							window.document.getElementById("campaignNum_div").style.display = "none";
							window.document.garantiaMotoForm.campaign[1].checked = true;
							obj.selectedIndex = 0;
						}
							
				}
			
			}
			
			/** Move o foco para o próximo objeto, aplicável a caixa de texto
			 *  quando maxLength foi atingido o foco é direcionado para o próximo objeto
			 *
			 *  obj  - Objeto 
			 *  tam  - tamanho do objeto
			 */
			function checkLen(x,y) {
				if (y.length==x.maxLength) {
			
					var isThis =x.name;
					
					if ( isThis == "dtAbert" ) {
				
						window.document.getElementById("garantiaMotoForm").dtFech.focus();
					
					} else if ( isThis == "dtFech" ) {
					
						window.document.getElementById("garantiaMotoForm").preenchidoPor.focus();
						/*
						if ( window.document.getElementById("campaign_div").style.display == "" )
							window.document.getElementById("garantiaMotoForm").campaign[1].focus();				
						else if ( window.document.getElementById("garantiaMotoForm").km != undefined )
							window.document.getElementById("garantiaMotoForm").km.focus();
						else
							window.document.getElementById("garantiaMotoForm").horasUso.focus();
						*/
					} 
				}
			}
			
			/** Limpar os valores dos campos do formulário */
			function resetCoSi() {
													
					$('numOS').value ="";
			    	new Effect.Highlight('numOS');
			    	
			    	$('dtAbert').value ="";
			    	new Effect.Highlight('dtAbert');
			    	
			    	$('dtFech').value ="";
			    	new Effect.Highlight('dtFech');
					
					$('km').value ="";
			    	new Effect.Highlight('km');
			    	
			    	$('servico_1').value ="";
			    	new Effect.Highlight('servico_1');
					
					$('servico_2').value ="";
			    	new Effect.Highlight('servico_2');
					
					$('servico_3').value ="";
			    	new Effect.Highlight('servico_3');
			    				    	
			    	$('descServico_1').value ="";
			    	new Effect.Highlight('descServico_1');
					
					$('descServico_2').value ="";
			    	new Effect.Highlight('descServico_2');
					
					$('descServico_3').value ="";
			    	new Effect.Highlight('descServico_3');
			    	
			    	$('justif').value ="";
			    	new Effect.Highlight('justif');
					
					$('servTer').value ="0,00";
			    	new Effect.Highlight('servTer');
			    	
			    	var radiosCort = window.document.garantiaMotoForm.elements["cortesia"];
			    	radiosCort[1].checked = true;	
			    	
			    	var radiosServ = window.document.garantiaMotoForm.elements["hasServTer"];
			    	radiosServ[1].checked = true;	
			    	
			    	var radiosRec = window.document.garantiaMotoForm.elements["recall"];
			    	radiosRec[1].checked = true;
			    	
			    	var radiosCam = window.document.garantiaMotoForm.elements["campaign"];
			    	radiosCam[1].checked = true;
			    	
			    	window.document.getElementById("cortesia_div").style.display = "none"; 		    	
					window.document.getElementById("servTer_div").style.display  = "none";  
					window.document.getElementById("recall_div").style.display   = "none";
					window.document.getElementById("campaign_div").style.display = "none";
					
					window.document.getElementById("sintoma").options[0].selected = true;
					window.document.getElementById("condicao").options[0].selected = true;
					
			    	$('co').innerHTML ="";
			    	Element.hide('co');
			    	
			    	$('si').innerHTML ="";
			    	Element.hide('si');
			    	
			    	$('numOS').focus();
			
			}
			
			function posGetTipoMoto(){
			
				var tgHasKM = window.document.garantiaMotoForm.hasKM.value;
				var tgReq = window.document.getElementById("motocicletaReq");
				
				if ( tgHasKM == "true" ) {
				
					tgReq.innerHTML = "<img src='../../images/icon_required.gif' alt='Campo de preenchimento obrigatório!'>"; 
					
				} else {
					tgReq.innerHTML = "";
				}
				
			}
			
			function checkRevisaoEntrega() {
				
				var isRevEntrega  = window.document.getElementById("isRevEntrega").value;
				var isEdit  = window.document.garantiaMotoForm.isEdit.value;
				var km    = Number(window.document.getElementById("km").value);
				
				if ( isRevEntrega == "false" && isEdit != "true" ) {
					if(km > 0) {
						var str = "A Garantia não pode ser salva, não foi localizada a revisão de entrega para o chassi informado." + '\n';
						str = str + "Envie o arquivo XML para o email XXXXXXXXX@XXXXXXXX.com.br";
						window.alert(str);
						return false;
					}
				} 
				
				return true;
				
			}
			
			function checkKM() {
			
				var km    = Number(window.document.getElementById("km").value);
				var kmMin = Number(window.document.getElementById("kmMin").value);
				
				if ( km <  kmMin ) {
					window.alert("A quilometragem diverge do serviço anterior!");
					return false;
				} 
				
				return true;
				
			}
			
			// Realiza a validação da Quilometragem máxima permitida para uma Garantia
			function checkKMMax() {
			
				var km    = Number(window.document.getElementById("km").value);
				var kmMax = Number(window.document.getElementById("kmMax").value);
				<%--
				var kmVal = window.document.getElementById("kmValida").value;
								
				if( kmVal == "NAN" || kmVal == "") {
					
					window.alert("Não foram localizadas informações referente a próxima revisão para o modelo!");
					return false;
					
				}
				
				if ( km >  kmMax || km > Number(kmVal)) {
					return false;
				} 
				--%>
				
				if ( km >  kmMax ) {
					return false;
				} 
				return true;
				
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
		<html:form action="/GarantiaMoto" method="post">			
			<html:hidden property="entityId" styleId="entityId"/>
			<html:hidden property="isEdit" styleId="isEdit"/>
			<html:hidden property="lote" styleId="lote"/>	
			<html:hidden property="linhaId" styleId="linhaId"/>
			<html:hidden property="statusLoteId" styleId="statusLoteId" value="<core:out value='${statusLoteId}'/>"/>
			<html:hidden property="validated" styleId="validated"/>	
			<html:hidden property="isRecall" styleId="isRecall"/>
			<html:hidden property="isCampaign" styleId="isCampaign"/>
			<html:hidden property="sysDate" styleId="sysDate"/>
			<html:hidden property="alterState" styleId="alterState"/>
			<html:hidden property="hasKM" styleId="hasKM"/>
			<input type="hidden" name="kmMin" id="kmMin" value="0"/> 
			<input type="hidden" name="kmMax" id="kmMax" value="0"/> 
			<input type="hidden" name="kmValida" id="kmValida" value="0"/> 
			<input type="hidden" name="isRevEntrega" id="isRevEntrega" value="false"/> 
			<jsp:include flush="true" page="../include_alerts.jsp"/><br> 
				
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr> 
				<td height="5" colspan="2">
					<div align="right" class="text" >
						<span class="bold"><bean:message key="garantia.list.sysDate"/></span>:	
						<bean:write name="garantiaMotoForm" property="sysDate"/>
					</div>										
					<table width="100%" border="0" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;">
					<tr height="25"> 
						<td width="8%"  class="td_dark">	
							<bean:message key="garantia.form.sg.loteNum"/>:
						</td>
						<td width="15%" class="text">	
							<bean:write name="garantiaMotoForm" property="lote"/>
						</td>
						<td width="9%" class="td_dark">	
							<bean:message key="garantia.form.sg.sgNum"/>:
						</td>
						<td width="10%" class="text">	
							<bean:write name="garantiaMotoForm" property="entityId"/>&nbsp;
						</td>
						<td width="8%" class="td_dark">	
							<bean:message key="garantia.form.sg.chassi"/>:
						</td>
						<td class="text" colspan="3">
							<logic:equal name="garantiaMotoForm" property="alterState" value="false">
								<html:text property="chassi" styleId="chassi" styleClass="text_field_maior" maxlength="13" readonly="${consulta}" onkeyup="javaScript:storeChassi()" onblur="getGarantiaValues()"></html:text>
							</logic:equal>
							<logic:equal name="garantiaMotoForm" property="alterState" value="true">
								<html:text property="chassi" styleId="chassi" styleClass="text_field_maior" maxlength="13" readonly="true" onkeyup="javaScript:storeChassi()" onblur="getGarantiaValues()"></html:text>
							</logic:equal>
							<img src="../../images/icon_required.gif" alt="Campo de preenchimento obrigatório!" id="reqChassi">
							<span id="indicator" style="display:none;"><html:img srcKey="form.moto.img.indicador" altKey="form.msg.indicador"/></span>
							<input type="hidden" name="escapedChassi" id="escapedChassi" value="null" />
							<core:if test="${statusLoteId != 4}">
								<script type="text/javascript">
									new AjaxJspTag.Autocomplete(
										"<%= request.getContextPath() %>/DeterminaChassi.do", {
											minimumCharacters: "8",
											parameters: "chassi={escapedChassi},linhaId={linhaId}", 
											target: "chassi",
											className: "autocomplete",
											source: "chassi",
											indicator: "indicator"
										});
								</script> 								
								
							</core:if>
						</td>
					</tr>
					<tr height="25"> 
						<td width="8%"  class="td_dark">	
							<bean:message key="garantia.form.sg.dtVenda"/>:
						</td>
						<td class="text" width="17%">	
							<html:text property="dtVenda" styleId="dtVenda" styleClass="text_field_date" readonly="true"></html:text>
							<img src="../../images/icon_calendar.gif" border="0" alt="Clique aqui para buscar a data da venda" id="selldate">
							
						</td>
						<td width="9%" class="td_dark">	
							<bean:message key="garantia.form.sg.numOS"/>:
						</td>
						<td width="10%" class="text">
							<core:if test="${statusLoteId != 3}">
								<html:text property="numOS" styleId="numOS" styleClass="text_field_maior" maxlength="10" readonly="${consulta}"></html:text>
							</core:if>
							<core:if test="${statusLoteId == 3}">
								<html:text property="numOS" styleId="numOS" styleClass="text_field_maior" maxlength="10" disabled="true"></html:text>
							</core:if>
						</td>
						<td width="8%" class="td_dark">	
							<bean:message key="garantia.form.sg.dtAbert"/>:
						</td>
						<td class="text">	
							<ym:inputDate icon="../../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="dtAbert"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 maxLength      ="10"
								 fieldYears		="4"
								 onBlurMethod   ="checkDate('dtAbert')"
								 readOnly	    ="${consulta}"
								 onKeyUp		="mascara_data('dtAbert',event);checkLen(this,this.value);"
								 tabIndex       ="1000"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="garantiaMotoForm" property="dtAbert"/>						 
							</ym:inputDate> &nbsp;
							<img src="../../images/icon_required.gif" alt="form.msg.required"/> 
						</td>
						<td width="8%" class="td_dark">	
							<bean:message key="garantia.form.sg.dtFech"/>:
						</td>
						<td class="text">	
							<ym:inputDate icon="../../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="dtFech"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 maxLength      ="10"
								 fieldYears		="4"
								 readOnly	    ="true" 
								 disabled       ="true"
								 onKeyUp		="mascara_data('dtFech',event);checkLen(this,this.value);"
								 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="garantiaMotoForm" property="dtFech"/>
							</ym:inputDate> &nbsp;
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
			<div id="recall_div" style="display:none;">	
				<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">				
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.recall"/>:
					</td>
					<td width="75%" class="text">													
						<html:radio property="recall" value="S" onclick="javascript:enableNumber();"/>&nbsp; 
						<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
						<html:radio property="recall" value="N" onclick="javascript:enableNumber();"/>&nbsp; 
						<bean:message key="form.msg.no"/>     
					</td>
				</tr>
				</table>
				<div id="numIT_div" style="display:none;">	
					<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.numeroIT"/>:
						</td>
						<td width="75%" class="text">
									<%--<html:select property="recallId" styleId="recallId" styleClass="listSelectCode" onchange="confirmRecall();" disabled="${consulta}"> --%>
									<html:select property="recallId" styleId="recallId" styleClass="listSelectCode" disabled="${consulta}">
										<html:option value=""></html:option>
										<html:optionsCollection property="listRecall" label="numeroIT" value="entityId"/>
									</html:select> &nbsp; 
									<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
									<bean:message key="garantia.form.msg.numeroIT"/>
								
								<%-- <logic:equal name="garantiaMotoForm" property="isRecall" value="false">	--%>
									<%-- 
									/** Esta TAG busca valores no banco para os campo select numeroIT
									 *  Ela é disparada juntamente com a TAG updateFiled abaixo.
									 
									<ajax:select
										  baseUrl="${pageContext.request.contextPath}/LoadRecall.do"
										  source="selldate"
										  target="recallId"	
										  eventType="click"						  
										  parameters="chassi={chassi}"/>
								 </logic:equal>--%> 					
						</td>
					</tr>
					</table>
				</div>
			</div>
			<div id="campaign_div">	
				<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">				
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.campaign"/>:
					</td>
					<td width="75%" class="text">													
						<html:radio property="campaign" styleId="campaign" value="S" onclick="javascript:enableCodeCampaign();"/>&nbsp; 
						<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
						<html:radio property="campaign" styleId="campaign" value="N" onclick="javascript:enableCodeCampaign();"/>&nbsp; 
						<bean:message key="form.msg.no"/>     
					</td>
				</tr>
				</table>
				<div id="campaignNum_div" style="display:none;">	
					<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.campaignNum"/>:
						</td>
						<td width="75%" class="text">
									<html:select property="campaignId" styleId="campaignId" styleClass="listSelect" onchange="confirmCampaign();" disabled="${consulta}">
										<html:option value=""></html:option>
										<html:optionsCollection property="listCampaign" label="campaignNum" value="entityId"/>
									</html:select> &nbsp; 
									<img src="../../images/icon_required.gif" alt="form.msg.required" id="imgCampaign"/>  
									<bean:message key="garantia.form.msg.campaignNum"/>
								
								<%--<logic:equal name="garantiaMotoForm" property="isCampaign" value="false">	 --%>
									<%-- 
									/** Esta TAG busca valores no banco para os campo select código da campanha
									 *  Ela é disparada juntamente com a TAG updateFiled abaixo.
								
									<ajax:select
										  baseUrl="${pageContext.request.contextPath}/LoadCampaign.do"
										  source="imgCampaign"
										  target="campaignId"	
										  eventType="click"						  
										  parameters="chassi={chassi}"
										  preFunction="clearValuesCampaign"
  						          		  postFunction="resetValuesCampaign"/>
								</logic:equal>  --%>					
						</td>
					</tr>
					</table>
				</div>
			</div>
			<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">	
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.preenchidoPor"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="preenchidoPor" styleId="preenchidoPor" styleClass="text_field_menor" maxlength="60" readonly="${consulta}" onkeypress="return apenasLetras(event);"/>
					<img src="../../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
				</td>
			</tr>	
			<core:if test="${idTipo == 1}">			
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.km"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="km" styleClass="text_field_menor" maxlength="10" readonly="${consulta}" onblur="checkKM();"/>
						<span id="motocicletaReq"></span>  
						<%-- 
						/** Esta TAG busca valores no banco para o campo hasKM
						 *  Ela é disparada na saída do foco do campo numeroRevisao que chama a função getTipoMoto().
						 --%>
						<ajax:updateField
									  baseUrl="${pageContext.request.contextPath}/GetTipoMoto.do"
									  source="chassi"
									  action="reqChassi"
									  target="hasKM" 
									  parser="new ResponseXmlParser()" 	 				  
									  parameters="chassi={chassi}"
									  postFunction="posGetTipoMoto"/>  
					</td>
				</tr>
			</core:if>				
			<core:if test="${idTipo == 2 or idTipo == 3}">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.hrsUso"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="horasUso" styleId="horasUso" styleClass="text_field_date" maxlength="10" readonly="${consulta}"/>
						<img src="../../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
						
						<bean:message key="garantia.form.sg.im"/>:
						<logic:equal name="garantiaMotoForm" property="hasInforMercado" value="true">
							<html:text property="infoMercadoId" styleId="infoMercadoId" styleClass="text_field_date" maxlength="10" readonly="true"/>
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="hasInforMercado" value="false">
							<html:text property="infoMercadoId" styleId="infoMercadoId" styleClass="text_field_date" maxlength="10" onkeypress="return blockChar(event);"/>
						</logic:equal>
					</td>
				</tr>
			</core:if>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.cortesia"/>:
				</td>
				<td width="75%" class="text">	
					<%--<core:if test="${statusLoteId == 4}"> 												
						<input type="radio" name="cortesia" value="S" disabled="disabled"/>&nbsp; 
						<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
						<input type="radio" name="cortesia" value="N" disabled="disabled"/>&nbsp; 
						<bean:message key="form.msg.no"/>&nbsp;&nbsp;  
					</core:if>
					<core:if test="${statusLoteId != 4}"> --%> 												
						<%--<input type="radio" name="cortesia" value="S" onclick="enableCortesia();"/>&nbsp; --%>
						<html:radio property="cortesia" styleId="cortesia" value="S" onclick="enableCortesia();"></html:radio>&nbsp;
						<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
						<%--<input type="radio" name="cortesia" value="N" checked="checked" onclick="enableCortesia();"/>&nbsp; --%>
						<html:radio property="cortesia" styleId="cortesia" value="N" onclick="enableCortesia();"></html:radio>&nbsp;
						<bean:message key="form.msg.no"/>&nbsp;&nbsp;
						<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
					<%--</core:if>--%>
				</td>
			</tr>
			</table>
			<div id="cortesia_div" style="display:none;">	
				<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">	
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.numCortesia"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="autEsp" styleId="autEsp" styleClass="text_field_menor"  maxlength="10" readonly="true"/>
					</td>
				</tr>
				</table>
			</div>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.sintoma"/>:
				</td>
				<td width="75%" class="text">
				
					<logic:equal name="garantiaMotoForm" property="isRecall" value="false">
						<core:if test="${statusLoteId != 1 and statusLoteId != 4}">
							<html:hidden property="sintomaId" styleId="sintomaId"/>												
							<html:select property="sintoma" styleId="sintoma" disabled="true" styleClass="listSelectCode" onchange="showLabel('sintoma','si');">
								<html:option value=""></html:option>
								<html:optionsCollection property="listSintomas" value="entityId" label="sintomaCode"/>
							</html:select> &nbsp; 
						</core:if>
						<core:if test="${statusLoteId == 1 or statusLoteId == 4}">											
							<html:select property="sintoma" styleId="sintoma" disabled="${consulta}" styleClass="listSelectCode" onchange="showLabel('sintoma','si');">
								<html:option value=""></html:option>
								<html:optionsCollection property="listSintomas" value="entityId" label="sintomaCode"/>
							</html:select> &nbsp; 
						</core:if>
						<img src="../../images/icon_required.gif" alt="form.msg.required"/> &nbsp; 
						<span id="si"  style="display:none;" class="text"></span>						
					</logic:equal>
					<logic:equal name="garantiaMotoForm" property="isRecall" value="true">
						<html:hidden property="sintoma"/>
						<html:text property="sintomaDescRecall" styleId="sintomaDescRecall" styleClass="text_field_menor" maxlength="100" readonly="true"/>
					</logic:equal>
					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.condicao"/>:
				</td>
				<td width="75%" class="text">
					<logic:equal name="garantiaMotoForm" property="isRecall" value="false">
						<core:if test="${statusLoteId != 1 and statusLoteId != 4}">
							<html:hidden property="condicaoId" styleId="condicaoId"/>
							<html:select property="condicao" styleId="condicao" disabled="true" styleClass="listSelectCode" onchange="showLabel('condicao','co');" >
								<html:option value=""></html:option>
								<html:optionsCollection property="listCondicoes" value="entityId" label="condicaoCode" />
							</html:select> &nbsp;
						</core:if>
						<core:if test="${statusLoteId == 1 or statusLoteId == 4}">
							<html:select property="condicao" styleId="condicao" disabled="${consulta}" styleClass="listSelectCode" onchange="showLabel('condicao','co');" >						
								<html:option value=""></html:option>
								<html:optionsCollection property="listCondicoes" value="entityId" label="condicaoCode" />
							</html:select> &nbsp; 
						</core:if>						
						<img src="../../images/icon_required.gif" alt="form.msg.required"/> &nbsp;  
						<span id="co"  style="display:none;" class="text"></span>
					</logic:equal>
					
					<logic:equal name="garantiaMotoForm" property="isRecall" value="true">
						<html:hidden property="condicao" styleId="condicao"/>
						<html:text property="condicaoDescRecall" styleId="condicaoDescRecall" styleClass="text_field_menor" maxlength="100" readonly="true"/>
					</logic:equal>
					
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="td_dark">	
					<bean:message key="garantia.form.sg.descricao.condicao"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="text">	
					<html:textarea property="condicaoProblema" styleId="condicaoProblema" styleClass="text_field_maior" style="width:98%" rows="6" disabled="disabled"/>
					<img src="../../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="td_dark">	
					<bean:message key="garantia.form.sg.descricao.causa"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="text">	
					<html:textarea property="causaProblema" styleId="causaProblema" styleClass="text_field_maior" style="width:98%" rows="6" disabled="disabled"/>
					<img src="../../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="td_dark">	
					<bean:message key="garantia.form.sg.descricao.diagnostico"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="text">	
					<html:textarea property="diagnostico" styleId="diagnostico" styleClass="text_field_maior" style="width:98%" rows="6" disabled="disabled"/>
					<img src="../../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="td_dark">	
					<bean:message key="garantia.form.sg.descricao.solucao"/>:
				</td>
			</tr>
			<tr height="25"> 
				<td colspan="2" class="text">	
					<html:textarea property="solucao" styleId="solucao" styleClass="text_field_maior" style="width:98%" rows="6" disabled="disabled"/>
					<img src="../../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.servico_1"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="servico_1" styleId="servico_1" styleClass="text_field" size="12" maxlength="13" readonly="${consulta}" onkeyup="javaScript:storeServico('1')"></html:text>&nbsp;
					<%--<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  --%>
					<span id="indicatorServ_1" style="display:none;"><html:img srcKey="form.moto.img.indicador" altKey="form.msg.indicador"/></span>
					<input type="hidden" name="escapedServico_1" id="escapedServico_1" value="null" />
					<ajax:autocomplete
							  baseUrl="${pageContext.request.contextPath}/LoadService.do"
							  source="servico_1"
							  target="descServico_1"	
							  minimumCharacters="3"	
							  className="autocomplete"				  
							  parameters="code={escapedServico_1},chassi={chassi},linhaId={linhaId}"
							  indicator="indicatorServ_1"
							  postFunction="fimServico_1"/>&nbsp;
					<html:text property="descServico_1" styleId="descServico_1" tabindex="200" styleClass="text_field_menor" readonly="true" ></html:text>					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.servico_2"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="servico_2" styleId="servico_2" styleClass="text_field" size="12" maxlength="13" readonly="${consulta}" onkeyup="javaScript:storeServico('2')"></html:text>&nbsp;
					<%--<html:img  srcKey="form.img.required" altKey="form.msg.required"/>--%> 
					<span id="indicatorServ_2" style="display:none;"><html:img srcKey="form.moto.img.indicador" altKey="form.msg.indicador"/></span>
					<input type="hidden" name="escapedServico_2" id="escapedServico_2" value="null" />
					<ajax:autocomplete
							  baseUrl="${pageContext.request.contextPath}/LoadService.do"
							  source="servico_2"
							  target="descServico_2"	
							  minimumCharacters="3"	
							  className="autocomplete"				  
							  parameters="code={escapedServico_2},chassi={chassi},linhaId={linhaId}"
							  indicator="indicatorServ_2"
							  postFunction="fimServico_2"/>&nbsp;
					<html:text property="descServico_2" styleId="descServico_2" tabindex="200" styleClass="text_field_menor" readonly="true" ></html:text>					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.servico_3"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="servico_3" styleId="servico_3" styleClass="text_field" size="12" maxlength="13" readonly="${consulta}" onkeyup="javaScript:storeServico('3')"></html:text>&nbsp;
					<%--<html:img  srcKey="form.img.required" altKey="form.msg.required"/>--%>
					<span id="indicatorServ_3" style="display:none;"><html:img srcKey="form.moto.img.indicador" altKey="form.msg.indicador"/></span>
					<input type="hidden" name="escapedServico_3" id="escapedServico_3" value="null" />
					<ajax:autocomplete
							  baseUrl="${pageContext.request.contextPath}/LoadService.do"
							  source="servico_3"
							  target="descServico_3"	
							  minimumCharacters="3"	
							  className="autocomplete"				  
							  parameters="code={escapedServico_3},chassi={chassi},linhaId={linhaId}"
							  indicator="indicatorServ_3"
							  postFunction="fimServico_3"/>&nbsp;
					<html:text property="descServico_3" styleId="descServico_3" tabindex="200" styleClass="text_field_menor" readonly="true" ></html:text>					
				</td>
			</tr>
			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.has_servTer"/>
				</td>
				<td width="75%" class="text">	
					<html:radio property="hasServTer" styleId="hasServTer" value="S" onclick="enableServTer();"/>&nbsp; 
					<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
					<html:radio property="hasServTer" styleId="hasServTer" value="N" onclick="enableServTer();"/>&nbsp; 
					<bean:message key="form.msg.no"/>&nbsp;&nbsp; 
				</td>
			</tr>
			</table>
			<div id="servTer_div" style="display:none;">	
				<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center">	
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.servTer"/>:
						</td>
						<td width="75%" class="text">	
							<html:hidden property="servTerUnFormat" styleId="servTerUnFormat"/>
							<html:text property="servTer" styleId="servTer" styleClass="text_field_menor" maxlength="10" onblur="validateVST('servTer')" readonly="${consulta}"/>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/> 
						</td>
					</tr>
					<tr height="50"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.justif"/>:
						</td>
						<td width="75%" class="text">	
							<html:textarea property="justif" styleId="justif" styleClass="text_field_menor" rows="5" readonly="${consulta}"/>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/> 
						</td>
					</tr>
				</table>	
			</div>	
			<logic:equal name="garantiaMotoForm" property="hasParecerYm" value="true">
			<fieldset>
				<legend  class="labelList"><bean:message key="analiseGarantia.label.analise.yamaha"/></legend>
				
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
			<tr>
				<td class="td_dark" align="left">	
					<bean:message key="analiseGarantia.label.analise.yamaha.parecer"/>:
				</td>
			</tr>
			<tr>
				<td>
					<html:textarea property="parecerYm" styleId="parecerYm" styleClass="text_field_maior" style="width:100%" rows="6" readonly="true"/>	
				</td>
			</tr>
			<%-- 
			<tr>
				<td class="td_dark" align="left">	
					<bean:message key="analiseGarantia.label.analise.yamaha.observacao"/>:
				</td>
			</tr>			
			<tr>
				<td>
					<html:textarea property="observacaoYm" styleId="observacaoYm" styleClass="text_field_maior" style="width:100%" rows="6"  readonly="true"/>	
				</td>
			</tr>
			--%>
			<logic:equal name="garantiaMotoForm" property="flClassificacaoTecnica" value="S">
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
						<html:select property="tipoProblema" styleId="tipoProblema" styleClass="text_field_list" disabled="true">
								<html:option value=""></html:option>
								<html:option value="AA1">AA1</html:option>
								<html:option value="AA2">AA2</html:option>
								<html:option value="AA3">AA3</html:option>
					    </html:select>
					</td>
					<td>
						<html:select property="classificaAcao" styleId="classificaAcao" styleClass="text_field_list" disabled="true">
								<html:option value=""></html:option>
								<html:option value="Solicitação de Fotos">Solicitação de Fotos</html:option>
								<html:option value="Solicitação de Maiores Informações do Reparo">Solicitação de Maiores Informações do Reparo</html:option>
								<html:option value="Liberar Sem Solicitação">Liberar Sem Solicitação</html:option>
					    </html:select>
					</td>
				</tr>	
			</logic:equal>
			</table>
			</fieldset>	
			</logic:equal>		
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">		
			<tr> 
				<td height="15">
				    <%-- 
					/** Esta TAG busca valores no banco para os campos Data da Venda e Recall
					 *  Ela é disparada na saída do foco do campo chassi que chama a função getGarantiaValues(),
					 *  esta função dispara o evento click() da figura, como especificado no parâmetro action da TAG.
					 --%>
					<ajax:updateField
								  baseUrl="${pageContext.request.contextPath}/GetGarantiaValues.do"
								  source="chassi"
								  target="dtVenda,isRevEntrega,isRecall,kmMin,kmMax,kmValida"
								  action="selldate" 
								  parser="new ResponseXmlParser()" 	 				  
								  parameters="chassi={chassi},isEdit={isEdit},entityId={entityId},linhaId={linhaId}" 
						  		  preFunction="clearValues"
  						          postFunction="resetValues"/>&nbsp;  
	  			</td>
			</tr>
			<tr height="25">
				<td colspan="2" align="center">	
					<core:if test="${consulta == false}">			
						<html:button property="gravar" styleClass="button_medium"  onclick="javascript:return gravarMotoForm(garantiaMotoForm)"><bean:message key="form.btn.save"/></html:button>
						&nbsp;					
						<%--
						<html:button property="limpar" styleClass="button_medium" onclick="javascript:resetCoSi();"><bean:message key="form.btn.clear"/></html:button>						
						&nbsp;
						 --%>
						<logic:equal name="garantiaMotoForm" property="isEdit" value="true">
							<html:button property="peca" styleClass="button_medium" onclick="javascript:editPeca();"><bean:message key="garantia.form.btn.peca"/></html:button>		
						</logic:equal>
					</core:if>
					<core:if test="${consulta == true}">
						<html:button property="verpeca" styleClass="button_medium" onclick="javascript:viewPeca();"><bean:message key="garantia.form.btn.viewPeca"/></html:button>		
					</core:if>
					<%--<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>--%>		
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><img src="../../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
			</tr>
			</table>
		</html:form>
		<script type="text/javascript">
			__loadEsconde();
			enableCortesia();
			enableServTer();
			<logic:equal name="garantiaMotoForm" property="isEdit" value="true">
				setFocus("numOS");
			</logic:equal>
			<logic:notEqual name="garantiaMotoForm" property="isEdit" value="true">
				setFocus("chassi");
			</logic:notEqual>				
			<logic:equal name="garantiaMotoForm" property="isRecall" value="false">				
				showLabel('sintoma','si');
				showLabel('condicao','co');
			</logic:equal>
			
			<logic:equal name="garantiaMotoForm" property="isRecall" value="true">
				window.document.getElementById("recall_div").style.display = "";
				window.document.getElementById("numIT_div").style.display = "";
			
			</logic:equal>
			window.document.getElementById("campaign_div").style.display = "none";
		</script>
		<ym:javaScriptExecuter/>
		<ym:confirmMessage />
		<ym:alertMessage />
	</body>
</html>