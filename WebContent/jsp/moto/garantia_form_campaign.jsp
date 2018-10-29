<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	%>
<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el"%>
<%@taglib uri="http://struts.apache.org/tags-logic"    prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"	    %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		   prefix="ajax"    %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>

<ym:checkLogon roleName="sg_cd_solg" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......garantia_form_campaign.jsp
 *   .: Criação.....23 de julho de 2008, 17:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de garantia para camapanha de modificação técnica.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="garantiaMotoForm" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>				
		<link rel="stylesheet" type="text/css" href="../../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/progressbar/carregador.css"></link>
		<script language="JavaScript" src="../../scripts/General/form.js"></script>
		<script language="JavaScript" src="../../scripts/Formatter/maskDate.js"></script>	
		<script language="JavaScript" src="../../scripts/Formatter/cpfCnpj.js"></script>
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
		
			
			/* Setamos uma variável que determina o estado do campo do form 
			 * conforme o status de um lote
			 */
			<core:set var="readonly" value="false"/>
			
			<core:if test="${readOnly}">
			
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
			
				window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia para Campanha');";
			
			}
			
			function setTitleAlter(title) {
			
				window.top.location.href ="javascript:newTitle('" + title + "');";
			
			}
			
			function save() {
				__showAnima();
				submitFormTsk(window.document.garantiaMotoForm,"saveCampaign",true);
			
			}
			
			function cancel( loteId ) {
			
				__showAnima();
				window.location.href = "GarantiaMoto.do?task=cancelIncludeCampaign&loteId=" + loteId;
				
			}
			
			/** 
			*	Função para solicitar uma confirmação ao usuário e direciona para outra Janela
			*   Ela é disparada pelo controle (Action)
			*/
			function redirectGar( loteId ) {
			
				var mensagem = "Pressione OK para incluir uma nova Garantia ou \n" +
							   "Pressione Cancelar para retornar para a Relação de Garantias";
			
				if ( window.confirm(mensagem) ) { 
					__showAnima();
					window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia');";
					window.location.href = "GarantiaMoto.do?task=addWithNewLote&loteId=" + loteId;
					
				} else {
					__showAnima();
					window.top.location.href ="javascript:newTitle('Relação de Garantias');";
					window.location.href = "GarantiaMoto.do?task=list&loteId=" + loteId;	
				
				}
			
			}
			
			/* ////////////////////////////////////////////*/
			/* Início das validações e gravação da revisão */
			/* ////////////////////////////////////////////*/
			function gravarForm(form) {
			
    			var i;
    			var isServTer; 
    			var vData1  = form.dtAbert.value != "" ? true : false ;
    			var vData2  = form.dtFech.value != "" ? true : false ;
    			var data1   = form.dtAbert.value;
    			var data2   = form.dtFech.value;
    			
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
	    			
	    			return false;
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
	    		
	    		<core:if test="${idTipo == 1}">
	    		
	    			var phasKM = true; 
	    			
	    			if(window.document.garantiaMotoForm.km.value == ""){	    				
	    				window.document.garantiaMotoForm.km.value = 0;
	    			}
	    			if ( window.document.garantiaMotoForm.hasKM.value == "false" )
	    				phasKM = false;
		    		if ( phasKM ) {
			    		/* Validação da quilomtragem */ 	    		
			    		if ( !checkKM() )
			    			return false;	
			    		if ( !checkKMMax() ) 
			    			if ( !window.confirm("Confirma o valor " +  window.document.getElementById('km').value + " para Quilometragem?") ) 
			    				return false;
			    	}
		    					
	    		</core:if>
    		    
   		    	window.document.garantiaMotoForm.gravar.disabled = true;
   		    	__showAnima();
   		    	submitFormTsk(form,"includeCampaign",true);
    		    	
    		  
			}
			
			/*////////////////////////////////////////////////////////////*/
			/*                 Valida o valor de serviços                 */
			/*////////////////////////////////////////////////////////////*/
			function validateVST() {
				
				if ( isNumber("servTer") ) {
				
					var valor 		  = Number(deci(window.document.getElementById("servTer").value));
					var valorlimite   = Number(window.document.getElementById("limitServTer").value);
					 
					if ( window.document.getElementById("servTer").value != "") {
						if ( valor > valorlimite ) {
							
							window.alert("O Valor de Serviço de Terceiro ultrapassou o limite permitido!");
							window.document.getElementById("servTer").focus();
							return false;
								
						} else {					
							window.document.getElementById("servTerUnFormat").value = valor;
						}
					}
					
				} else {
					return false;
				}
				
			}
			
			/*  Validação digitação de valor númerico com casas decimais */
			/*  As casas decimais podem ser definidas com uso de vírgula ou ponto */ 
			function isNumber(field) {
				
				var n = window.document.getElementById(field).value;

				var isMoeda = false;
				var indexP = n.indexOf('.');
				var indexV = n.indexOf(',');
				
				if ( indexP != -1 && indexV != -1 ) {
					
					if ( indexP < indexV ) 
						isMoeda = true;					
					
				} else if (!(indexP == -1 && indexV != -1) || !( indexP == -1 && indexV == -1) ) {
				
					isMoeda = true;	
				
				}
				
				if (isNaN(deci(n)) || !isMoeda) {
					window.alert("Valor inválido!");
					window.document.getElementById(field).value = "";
					window.document.getElementById(field).focus();
					return false;
				}
				
				return true;
			}
			
			/* funcao de formatacao de casas decimais */ 
			function deci(N) {
				//window.alert("Valor pré - Converção:" + N);
				//N = Math.round(N * 100) / 100;
					
       			texto = N.toString();
      			texto2 = "";
       			if (texto.indexOf(',') != -1) {
				  for (var i = 0; i < texto.length; i++){
					  if(texto.charAt(i) == ",")
						texto2 += ".";
					  else
						texto2 += texto.charAt(i);
				  }
				  texto = texto2;
			   } 	                    
          	   texto[texto.indexOf(',')] = ".";
       		   ponto = texto.indexOf('.');
    		   if (ponto == -1){
 			   	texto += ".00";
 				Term = texto
    		   } else {
 				texto += "0";
 				decimal = ponto + 3;
 				Term = texto.substring(0,decimal);
    		   }
    		   if (Term == ".0") { 
			  	Term = "0.00";
			   }
       		   if (Term == ".00") { 
			  	Term = "0.00";
			   }
				//if (Term == "NaN.00"){ Term = "Erro";}
				//window.alert("Valor pós - Conversão:" + Term);
				return Term;
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
			
			/** Função para confirmação da opção de Campanha e restauração da página */
			function confirmCampaign() {
			
				var form  = window.document.garantiaMotoForm;
				var obj   = window.document.getElementById("campaignId");			
				var index = obj.selectedIndex;				
				
				if ( index != 0 && index != "" ) {
				
						if ( window.confirm("Confirma a opção da Campanha?") ) {
						
							submitFormTsk(form,"addCampaign",true);
						
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
				
						if ( window.document.getElementById("garantiaMotoForm").km != undefined )
							window.document.getElementById("garantiaMotoForm").km.focus();
						else
							window.document.getElementById("garantiaMotoForm").horasUso.focus();
					
					} 
				}
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
		<html:form action="/GarantiaMoto" method="post" focus="chassi">			
			<html:hidden property="entityId"/>
			<html:hidden property="isEdit"/>
			<html:hidden property="lote"/>	
			<html:hidden property="linhaId"/>
			<html:hidden property="statusLoteId" value="<core:out value='${statusLoteId}'/>"/>
			<html:hidden property="validated"/>	
			<html:hidden property="campaignId"/>
			<html:hidden property="sysDate"/>
			<html:hidden property="alterState"/>
			<html:hidden property="hasKM"/>
			<input type="hidden" name="kmMin" id="kmMin" value="<core:out value='${kmMin}'/>"/>
			<input type="hidden" name="kmMax" id="kmMax" value="<core:out value='${kmMax}'/>"/>
			<input type="hidden" name="limitServTer"     value="<core:out value='${limitServTer}'/>"/>
			
			<jsp:include flush="true" page="../include_alerts.jsp"/><br>
				
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" id="form">			
			<tr> 
				<td height="5" colspan="2">
														
					<table width="100%" border="0" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;" id="form">
					<tr height="25"> 
						<td width="8%"  class="td_dark">	
							<bean:message key="garantia.form.sg.loteNum"/>:
						</td>
						<td width="17%" class="text">	
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
							<html:text property="chassi" styleClass="text_field_maior" maxlength="13" readonly="true"></html:text>
							<img src="../../images/icon_required.gif" alt="Campo de preenchimento obrigatório!" id="reqChassi">							
						</td>
					</tr>
					<tr height="25"> 
						<td width="8%"  class="td_dark">	
							<bean:message key="garantia.form.sg.dtVenda"/>:
						</td>
						<td class="text" width="17%">	
							<html:text property="dtVenda" styleClass="text_field_date" readonly="true"></html:text>
							<img src="../../images/icon_calendar.gif" border="0" alt="Data da venda">
							
						</td>
						<td width="9%" class="td_dark">	
							<bean:message key="garantia.form.sg.numOS"/>:
						</td>
						<td width="10%" class="text">
							<html:text property="numOS" styleClass="text_field_maior" maxlength="10" readonly="${readonly}"></html:text>							
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
								 readOnly	    ="${readonly}"
								 disabled		="${readonly}"
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
								 readOnly	    ="${readonly}"
								 disabled		="${readonly}"
								 onKeyUp		="mascara_data('dtFech',event);checkLen(this,this.value);"
								 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="garantiaMotoForm" property="dtFech"/>
							</ym:inputDate> &nbsp;
							<img src="../../images/icon_required.gif" alt="form.msg.required"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
			<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center" id="form">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.campaignNum"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="campaignNum" styleClass="text_field_menor" readonly="true"></html:text>					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.campaignSubject"/>:
				</td>
				<td width="75%" class="text">
					<html:textarea property="subjectCampaign" styleClass="text_field_maior" rows="3" readonly="true"/>
				</td>
			</tr>
			
			</table>
			<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center" id="form">	
			<core:if test="${idTipo == 1}">			
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.km"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="km" styleClass="text_field_menor" maxlength="10" readonly="${readonly}" onblur="checkKM();"/>
						<span id="motocicletaReq"></span>
					</td>
				</tr>
			</core:if>
			<core:if test="${idTipo == 2 or idTipo == 3}">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.hrsUso"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="horasUso" styleClass="text_field_menor" maxlength="10" readonly="${readonly}"/>
						<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
					</td>
				</tr>
			</core:if>
			</table>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" id="form">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.sintoma"/>:
				</td>
				<td width="75%" class="text">
					<html:hidden property="sintomaIdCampaign"/>	
					<html:text property="sintomaDescCampaign" styleClass="text_field_menor" maxlength="100" readonly="true"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.condicao"/>:
				</td>
				<td width="75%" class="text">
					<html:hidden property="condicaoIdCampaign"/>
					<html:text property="condicaoDescCampaign" styleClass="text_field_menor" maxlength="100" readonly="true"/>
				</td>
			</tr>
			<tr> 
				<td width="25%" class="td_dark">				
					<bean:message key="garantia.form.sg.servicos"/>:					
				</td>
				<td  width="75%" class="text">
				
					<display:table name="requestScope.garantiaMotoForm.listServicos" 
				               requestURI="${pageContext.request.contextPath}/GarantiaMoto.do"
				               export="false" 
				               id="service" 
				               class="grid">
				               
				               <display:column titleKey="garantia.form.sg.servicoCode" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:out value="${service.servicoCode}"/>
							   </display:column>
							   
							   <display:column titleKey="garantia.form.sg.servicos" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:out value="${service.descricao}"/>
							   </display:column>
				
					</display:table>
					
				</td>
			</tr>
			<tr> 
				<td  width="25%" class="td_dark">				
					<bean:message key="garantia.form.sg.pieces"/>:					
				</td>
				<td  width="75%" class="text">
				
					<display:table name="requestScope.garantiaMotoForm.listPieces" 
				               requestURI="${pageContext.request.contextPath}/GarantiaMoto.do"
				               export="false" 
				               id="peca" 
				               class="grid">
				               
				               <display:column titleKey="garantia.form.sg.pieceCode" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:if test="${peca.isCausingPiece}">
										<span style="background-color: #e2f3b8"><core:out value="${peca.piece.itemCode}"/></span>
									</core:if>
									<core:if test="${!peca.isCausingPiece}">
										<core:out value="${peca.piece.itemCode}"/>
									</core:if>
							   </display:column>
							   
							   <display:column titleKey="garantia.form.sg.pieces" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:out value="${peca.piece.descricao}"/>
							   </display:column>
							   
							   <display:column titleKey="garantia.form.sg.quantityPiece" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:out value="${peca.quantityPiece}"/>
							   </display:column>
							   
							   <display:column titleKey="garantia.form.sg.recoveredPiece" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:out value="${peca.recoveredPiece}"/>
							   </display:column>
							   
							   <display:column titleKey="garantia.form.sg.sendPiece" style="text-align: center;width:25%" sortable="true" headerClass="headerAlign">
									<core:if test="${peca.isSendPiece}">									
										<input type="checkbox" name="deleteTargets" value="<core:out value='${peca.entityId}'/>" checked="checked"/>									
									</core:if>
									<core:if test="${!peca.isSendPiece}">									
										<input type="checkbox" name="deleteTargets" value="<core:out value='${peca.entityId}'/>"/>									
									</core:if>															
							   </display:column>
				
					</display:table>
					
				</td>
			</tr>
			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.has_servTer"/>
				</td>
				<td width="75%" class="text">	
					<html:radio property="hasServTer" value="S" onclick="enableServTer();" disabled="${readonly}"/>&nbsp; 
					<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
					<html:radio property="hasServTer" value="N" onclick="enableServTer();" disabled="${readonly}"/>&nbsp; 
					<bean:message key="form.msg.no"/>&nbsp;&nbsp; 
				</td>
			</tr>
			</table>
			<div id="servTer_div" style="display:none;">	
				<table width="98%" border="0" cellpadding="1" cellspacing="1" align="center" id="form">	
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.servTer"/>:
						</td>
						<td width="75%" class="text">	
							<html:hidden property="servTerUnFormat"/>
							<html:text property="servTer" styleClass="text_field_menor" readonly="${readonly}" maxlength="10" onblur="validateVST('servTer')" />
							<img src="../../images/icon_required.gif" alt="form.msg.required"/> 
						</td>
					</tr>
					<tr height="50"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.justif"/>:
						</td>
						<td width="75%" class="text">	
							<html:textarea property="justif" readonly="${readonly}" styleClass="text_field_menor" rows="5" />
							<img src="../../images/icon_required.gif" alt="form.msg.required"/> 
						</td>
					</tr>
				</table>	
			</div>	
			<div align="center">			
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" id="form">
				<tr height="25">
					<td colspan="2" align="center">		
						<core:if test="${!readonly}">	
							<html:button property="gravar" styleClass="button_medium"  onclick="javascript:return gravarForm(garantiaMotoForm)"><bean:message key="form.btn.save"/></html:button>											
						</core:if>
						&nbsp;
						<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>		
					</td>
				</tr>
				<tr> 
					<td height="15" colspan="2">&nbsp;</td>
				</tr>
				<tr> 
					<td height="25" class="text" colspan="2"><img src="../../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
				</tr>
				</table>
			</div>
		</html:form>
		<script type="text/javascript">
			__loadEsconde();
			enableServTer();			
		</script>
		<ym:javaScriptExecuter/>
		<ym:confirmMessage />
		<ym:alertMessage />
	</body>
</html>