<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>

<ym:checkLogon roleName="sg_lt_solg" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......solicitacao_garantia_list.jsp
 *   .: Criação.....11 de junho de 2007, 10:58
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem de solitações de garantias.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script type="text/javascript">
			
			function incluir(loteId) { 
				__showAnima();
				window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia');";
				window.location.href = "Garantia.do?task=add&loteId=" + loteId;
				
			}   
			
			function confirmRemove(loteId, garantiaId) {
			
				if ( window.confirm("Confirma a exclusão da Sol. e Garantia Nº " + garantiaId + "?") )
					window.location.href = "Garantia.do?task=delete&loteId=" + loteId + "&garantiaId=" + garantiaId;
							
			}
			
			function voltar() {
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relação de Lotes');";
				window.location.href = "Lote.do?task=list&page=1&dir=desc&sort=1&limit=20";
			}
			
			function alter() {
				__showAnima();
				window.top.location.href ="javascript:newTitle('Alteração de Solicitação de Garantia');";
			}
			
			function consulta() {
				__showAnima();
				window.top.location.href ="javascript:newTitle('Consulta de Solicitação de Garantia');";
			}
			
		</script>
	</head>
	<body leftmargin="0" topmargin="0" onLoad="__loadEsconde();">
	<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <img src="../images/run.gif"/>
		            <br /><br />Carregando Aguarde...
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>	
	
		<html:form action="/Garantia" method="get">

			<html:hidden property="task"/>
			<br>
			<jsp:include flush="true" page="include_alerts.jsp"/>
			
			<center>
			
				<table border="0" cellpadding="1" cellspacing="1" width="98%">
					<tr>
						<td width="20%" class="td_dark"><b><bean:message key="lote.title"/>:</b></td>
						<td><c:out value="${lote.entityId}"/></td>
						<td align="right" valign="middle" rowspan="4">
							<c:if test="${validInclude == true}">
								<html-el:button property="incluirBtn" styleClass="button_large" onclick="javascript:incluir(${lote.entityId})"><bean:message key="garantia.list.btn.insert"/></html-el:button>&nbsp;
							</c:if>
							<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>		
						</td>
					</tr>
					<tr>
						<td class="td_dark"><b><bean:message key="lote.line"/>:</b></td>
						<td><c:out value="${lote.linha.descricao}"/></td>
					</tr>
					<tr>
						<td class="td_dark"><b><bean:message key="lote.status"/>:</b></td>
						<td><c:out value="${lote.statusLote.descricaoReduzida}"/></td>
					</tr>	
					<tr>
						<td class="td_dark"><b><bean:message key="lote.liberateDate"/>:</b></td>
						<td><c:out value="${lote.maskedDataLiberacao}"/></td>
					</tr>				
				</table>
				
				<br>
				<display:table name="requestScope.garantiaForm.listGarantias" 
				               requestURI="${pageContext.request.contextPath}/Garantia.do"
				               export="false" 
				               pagesize="${qtdeLinhas}"
				               id="garantia"
				               class="grid">
				    
					<display:column titleKey="garantia.list.sgNum" style="text-align: center;" sortable="true" headerClass="headerAlign">
						<c:out value="${garantia.entityId}"/>
					</display:column>
					
					<display:column titleKey="garantia.list.status" sortable="true" headerClass="headerAlign" media="html" style="text-align: center;">									
						<c:out value="${garantia.statusGarantia.descricao}"/>						
					</display:column>	
					
					<display:column titleKey="garantia.list.chassi" sortable="true" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${garantia.modelo}"/>
					</display:column>	
					
					<display:column titleKey="garantia.list.dtAbert" sortable="true"  defaultorder="descending" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${garantia.maskedDataAberturaOS}"/>
					</display:column>
					
					<display:column titleKey="garantia.list.dtFech" sortable="true"  defaultorder="descending" headerClass="headerAlign" style="text-align: center;">
						<c:out value="${garantia.maskedDataFechamentoOS}"/>
					</display:column>
					
					<display:column titleKey="garantia.list.actions" media="html" headerClass="headerAlign">
						<center>
							<c:if test="${garantia.hasCampaign == false}">
								<html-el:link action="/Garantia.do?task=alter&garantiaId=${garantia.entityId}&readonly=true" 
												  style="text-decoration: underline;"
												  onclick="consulta()"
												  onmouseout="window.status='';return true;"
									   			  onmouseover="window.status='Consultar';return true;">
									   	
										<bean:message key="garantia.list.ativ.detail"/> 
										
								</html-el:link>
							</c:if>
							<c:if test="${garantia.hasCampaign == true}">
								<html-el:link action="/Garantia.do?task=alterCampaign&garantiaId=${garantia.entityId}&readonly=true" 
											  style="text-decoration: underline;"
											  onclick="consulta()"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Consultar';return true;">
								   	
									<bean:message key="garantia.list.ativ.detail"/> 
									
								</html-el:link>
							</c:if>
							&nbsp; | &nbsp;
							
							<%-- Para permitir a alteração da solicitação de garantia o status lote deve ser igual a 1 ou 4 --%>
							<c:if test="${validAlter == true && (garantia.validAlterGarantia == true)}">	
								
								<%-- Para alteração de garantia para uma Campanha devemos direcionar para a task alterCampaign --%>
								<c:if test="${garantia.hasCampaign == true}">
								<html-el:link action="/Garantia.do?task=alterCampaign&garantiaId=${garantia.entityId}" 
											  style="text-decoration: underline;"
											  onclick="alter()"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Alterar';return true;">
								   	
									<bean:message key="garantia.list.ativ.mod"/> 
									
								</html-el:link>
								</c:if>
								
								<%-- Para alteração de garantia sem camapanha devemos direcionar para a task alter --%>
								<c:if test="${garantia.hasCampaign == false}">
								<html-el:link action="/Garantia.do?task=alter&garantiaId=${garantia.entityId}" 
											  style="text-decoration: underline;"
											  onclick="alter()"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Alterar';return true;">
								   	
									<bean:message key="garantia.list.ativ.mod"/> 
									
								</html-el:link>
								</c:if>
							</c:if>
							
							<%-- Quando o status do lote for diferente de 1 e 4, não apresentamos o link --%>
							<c:if test="${validAlter == false || garantia.validAlterGarantia == false}">	
								<i><bean:message key="garantia.list.ativ.mod"/></i>
							</c:if>
							
							&nbsp; | &nbsp;
							
							<%-- Para permitir a exclusão da solicitação de garantia o status lote deve ser igual a 1 --%>
							<c:if test="${validAlter == true && garantia.validAlterGarantia == true}">
								<html-el:link href="javascript:confirmRemove(${garantia.lote.entityId}, ${garantia.entityId});" 
											  style="text-decoration: underline;"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Excluir';return true;">
								   	
									<bean:message key="garantia.list.ativ.delete"/> 
									
								</html-el:link>
							</c:if>
							
							<%-- Quando o status do lote for diferente de 1, não apresentamos o link --%>
							<c:if test="${validAlter == false || garantia.validAlterGarantia == false}">
								<i><bean:message key="garantia.list.ativ.delete"/></i>
							</c:if>
							
						</center>
					</display:column>
					
				</display:table>	
				
			</center>

		</html:form>
		<ym:alertMessage />
	</body>
</html:html>