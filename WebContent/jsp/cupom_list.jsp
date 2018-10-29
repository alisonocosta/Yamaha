<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>

<ym:checkLogon roleName="sg_lt_rev" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......cupom_list.jsp
 *   .: Criação.....10 de maio de 2007, 08:36
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem de cupons.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script type="text/javascript">
			function setTitle() {
			
				window.top.location.href ="javascript:newTitle('Relação Revisões');";
			
			}
			
			function incluir() { 
				window.top.location.href ="javascript:newTitle('Incluir Revisão');"; 
				window.location.href = "Cupom.do?task=add&loteId=" + <c:out value="${lote.entityId}"/> ;
			} 
			function confirmar(){			
				if ( window.confirm("Confirma a exclusão da revisão?") ) {
					return true;
				}else {
					return false;				
				}
			}
			
			function confirmarAnter() {
			
				if ( window.confirm("Confirma a alteração da revisão?") ) {
				
					return true;
					
				}else {
				
					return false;				
					
				}
			
			}
			
			function voltar() {
				window.top.location.href ="javascript:newTitle('Relação de Lotes');";
				window.location.href = "Lote.do?task=list&page=1&dir=desc&sort=1&limit=20";
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
	
		<html:form action="/Cupom" method="post">

			<html:hidden property="task"/>			
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>
			<center>
				<table border="0" cellpadding="1" cellspacing="1" width="98%">
					<tr>
						<td width="20%" class="td_dark"><b><bean:message key="lote.title"/>:</b></td>
						<td><c:out value="${lote.entityId}"/></td>
						<td align="right" valign="middle" rowspan="3">
							<c:if test="${validInclude == true}">
								<html-el:button property="incluirBtn" styleClass="button_large" onclick="javascript:incluir();"><bean:message key="cupom.btn.insert"/></html-el:button>&nbsp;
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
				</table>
			</center>
		</html:form>
			<center>
				<br>
				
				<display:table name="requestScope.cupomForm.listResults"
							   requestURI="${pageContext.request.contextPath}/Cupom.do"							   
				               export="false" 		               
				               pagesize="${qtdeLinhas}"
				               id="cupom"
				               class="grid">
				    <display:column titleKey="cupom.chassi" sortable="true" headerClass="sortable" media="html" >									
						<c:out value="${cupom.chassi}"/>						
					</display:column>	
					
					<display:column titleKey="cupom.typeRev" sortable="true" headerClass="sortable" style="text-align: center;">
						<c:out value="${cupom.revisao.descricao}"/>
					</display:column>	
					
					<display:column titleKey="cupom.dateRev" sortable="true" headerClass="sortable" style="text-align: center;">
						<c:out value="${cupom.maskedDataRevisao}"/>
					</display:column>
					
					<display:column titleKey="cupom.value" sortable="true" headerClass="sortable" style="text-align: right;" >
						<c:out value="${cupom.valorTotalRevisao}"/>
					</display:column>	
					
					<display:column titleKey="cupom.actions" media="html" style="text-align: center;" >
						<center>
							
							<%-- Verificamos se Status do lote premite alteração e exclusão --%>
							<c:if test="${cupom.validateAlter == true }">
							
								<html-el:link action="/Cupom.do?task=alter&cupomId=${cupom.entityId}" 
											  style="text-decoration: underline;"
											  onclick="return confirmarAnter()"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Alterar';return true;">
								   	
									<bean:message key="cupom.alter"/> 
								</html-el:link>	
							</c:if>
							
							<c:if test="${cupom.validateAlter == false}">
							
								<i><bean:message key="cupom.alter"/></i>
							
							</c:if>
							
							<c:if test="${cupom.validateRemove == true}">
							
								&nbsp;|&nbsp;
								
								<html-el:link action="/Cupom.do?task=delete&cupomId=${cupom.entityId}"
								              style="text-decoration: underline;"
								              onclick="return confirmar()"
								              onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Excluir';return true;">
								              
									<bean:message key="cupom.remove"/> 
								</html-el:link>
							
							</c:if>
							
							<c:if test="${cupom.validateRemove == false}">
								
								&nbsp;|&nbsp;
								
								<i><bean:message key="cupom.remove"/></i>
							
							</c:if>
							
						</center>
					</display:column>
					
				</display:table>				
				
			</center>

	</body>

</html:html>