<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>

<%--<ym:checkLogon roleName="ym_sg_analise_gar" /> --%>

<%-- 
/* Yamaha.
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......analiseGarantia_list.jsp
 *   .: Criação.....10 de dezembro de 2012, 20:37
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem de lotes de Garantias par análise.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/progressbar/carregador.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/yamaha.css"></link>
		<script type="text/javascript" src="../../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript" src="../../scripts/General/form.js"></script>
		<script type="text/javascript">			
		
			/** Função chamada para retornar a lista de lotes de acordo com o status
			 *  parâmetro: id do status do lote: statusLoteId
			 */
			function buscar() {

				var criterio  = window.document.getElementById("criterio");
				var parametro = window.document.getElementById("parametro");
				
				if(criterio.options[criterio.selectedIndex].value == 0){
					
					window.alert("Selecione uma opção de pesquisa!");					
					criterio.focus();
					
					return false
					
				} else if(parametro.value == ""){
					
					window.alert("Informe o valor para a pesquisa!");					
					parametro.focus();
					
					return false;
					
				} else if(criterio.options[criterio.selectedIndex].value != "CHASSI" && criterio.options[criterio.selectedIndex].value != "CONCESSIONARIA" && criterio.options[criterio.selectedIndex].value != "REPRESENTANTE") {
					if(isNaN(parametro.value)){
						window.alert("Valor informado inválido!");					
						parametro.focus();
						return false;
					} 
				}
				
				__showAnima();
				submitFormTsk(window.document.analiseGarantiaForm,"list",true);

			}

			function analisar(garantiaId) {
				
				__showAnima();
				submitFormTsk(window.document.analiseGarantiaForm,"analisar&garantiaId=" + garantiaId,true);

			}
			
			function analisarNautica(garantiaId) {
				
				__showAnima();
				window.location.href = "AnaliseGarantiaNautica.do?task=analisar&garantiaId=" + garantiaId;

			}			
			
		</script>
	</head>
	<body leftmargin="0" topmargin="0" onLoad="__loadEsconde();">
	<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <img src="../../images/run.gif"/>
		            <br /><br />Carregando Aguarde...
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>	
	
		<html:form action="/AnaliseGarantia" method="post" focus="criterio">

			<html:hidden property="task"/>
			<br>
			<jsp:include flush="true" page="../include_alerts.jsp"/>
			
			<center>
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">	
				<tr>
					<td class="td_dark" width="40%" align="right"><bean:message key="analiseGarantia.select"/></td>
					<td class="td_dark" width="40%" align="right"><bean:message key="analiseGarantia.parametro"/></td>
					<td class="text" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td align="center">				
						<html-el:select property="criterio" styleId="criterio" styleClass="text_field_list">
							<c:forEach var="tipo" items="${listOptionsSearch}">
								<html-el:option value="${tipo.key}"><c:out value="${tipo.value}"/></html-el:option>
							</c:forEach>
						</html-el:select>
					</td>
					<td align="center">
						<html:text property="parametro" styleId="parametro" maxlength="15" size="20" styleClass="text_field_maior"/>
					</td>	
					<td align="left">
						<html:button property="pesquisar" styleClass="button_medium"  onclick="javascript:buscar();"><bean:message key="form.btn.search"/></html:button>
					</td>		
				</tr>				
				</table>
				
				<br>
				<display:table name="requestScope.analiseGarantiaForm.listResults" 
				               requestURI="${pageContext.request.contextPath}/AnaliseGarantia.do"
				               export="false" 
				               pagesize="20"
				               id="analiseGarantia"
				               class="grid">
				    
					<display:column titleKey="analiseGarantia.lista.lote" style="text-align: center;" sortable="false" headerClass="headerAlign">
						<c:out value="${analiseGarantia.loteId}"/>
					</display:column>
					
					<display:column titleKey="analiseGarantia.lista.concessionaria" sortable="faslse" headerClass="headerAlign">									
						<c:out value="${analiseGarantia.razaoSocialConcessionaria}"/>						
					</display:column>	
					<c:if test="${analiseGarantia.linhaId != 1 }">
						<display:column titleKey="analiseGarantia.lista.nomeRepresentante" sortable="faslse" headerClass="headerAlign">									
							<c:out value="${analiseGarantia.nomeRepresentanteNautica}"/>						
						</display:column>	
					</c:if>
					<display:column titleKey="analiseGarantia.lista.garantia" sortable="faslse" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${analiseGarantia.garantiaId}"/>
					</display:column>	
					
					<display:column titleKey="analiseGarantia.lista.chassi" sortable="faslse" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${analiseGarantia.modelo}"/>
					</display:column>
					
					<display:column titleKey="analiseGarantia.lista.dtAprovacao" sortable="false" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${analiseGarantia.maskedDataAprovacao}"/>
					</display:column>
					
					<display:column titleKey="analiseGarantia.lista.dtLiberacao" sortable="false" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${analiseGarantia.maskedDataLiberacao}"/>
					</display:column>
					
					<display:column titleKey="analiseGarantia.lista.status" sortable="false" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${analiseGarantia.dsStatusMov}"/>
					</display:column>
					
					<display:column titleKey="analiseGarantia.lista.acao" headerClass="headerAlign" style="text-align: center;">
						<center>
							<c:if test="${analiseGarantia.linhaId == 1 }">
								<html-el:link href="javascript:analisar(${analiseGarantia.garantiaId});" 
											  style="text-decoration: underline;"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Analisar';return true;">
								   	
									<bean:message key="analiseGarantia.lista.analisar"/> 									
								</html-el:link>
							</c:if>
							<c:if test="${analiseGarantia.linhaId != 1 }">
								<html-el:link href="javascript:analisarNautica(${analiseGarantia.garantiaId});" 
											  style="text-decoration: underline;"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Analisar';return true;">
								   	
									<bean:message key="analiseGarantia.lista.analisar"/> 									
								</html-el:link>
							</c:if>
						</center>
					</display:column>
					
				</display:table>	
				
			</center>

		</html:form>
		<ym:alertMessage />
	</body>
</html:html>