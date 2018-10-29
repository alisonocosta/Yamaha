<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......solicitacao_garantia_list.jsp
 *   .: Criação.....01 de julho de 2007, 10:23
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Tela: Listagem de solitações de informações de mercado.
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
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script type="text/javascript">
			
			function incluir() { 
			
				window.top.location.href ="javascript:newTitle('Incluir Informativo');";
				window.location.href = "InfoMercado.do?task=showBlankForm";
				
			}   
			
			function remove(infoMercadoId) {
			
				if ( window.confirm("Confirma a exclusão do Informativo Nº " + infoMercadoId + "?") )
					window.location.href = "InfoMercado.do?task=delete&entityId=" + infoMercadoId;
							
			}	
			
			function liberar(infoMercadoId) {
			
				if ( window.confirm("Confirma a liberação do Informativo Nº " + infoMercadoId + "?") )
					window.location.href = "InfoMercado.do?task=release&entityId=" + infoMercadoId;
							
			}			
			
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
	
		<html:form action="/InfoMercado" method="post">
		<br>
			<jsp:include flush="true" page="include_alerts.jsp"/>
			
			<center>

				<table border="0" cellpadding="1" cellspacing="1" width="98%">
					<tr>
						<td align="left" valign="middle">
						
							<table border="0" cellpadding="1" cellspacing="1" width="100%">
								<tr>
									<td>
										<html:select property="filterType" styleClass="listSelect">
											<option></option>
											<html:option value="1">Chassi</html:option>
											<html:option value="2">Status</html:option>
										</html:select>
										<html:text property="filterValue" styleClass="text_field_menor"/>
									</td>
								</tr>
							</table>
						
						</td>
						<td align="right" valign="middle" width="30%">
							<html-el:button property="filterBtn" styleClass="button_medium" onclick="submitFormTsk(infoMercadoForm,'list',true)"><bean:message key="form.lbl.filter"/></html-el:button>&nbsp;
							<html-el:button property="incluirBtn" styleClass="button_medium" onclick="javascript:incluir()"><bean:message key="form.btn.include"/></html-el:button>
						</td>
					</tr>
				</table>

				<span class="labelList">
				<b><bean:message key="infoMercado.list.msg.title"/></b>
				</span>
				<br>
				
				<display:table name="requestScope.infoMercadoForm.listResults" 
				               requestURI="${pageContext.request.contextPath}/InfoMercado.do"
				               defaultsort="1" 
				               defaultorder="ascending" 
				               export="false" 
				               pagesize="20"
				               id="infoMercado"
				               class="grid">
				    
					<display:column titleKey="infoMercado.list.refYMDB" style="text-align: center;" sortable="true">
						<c:out value="${infoMercado.entityId}"/>
					</display:column>
					
					<display:column titleKey="infoMercado.list.chassi" sortable="true" headerClass="sortable" style="text-align: center;">									
						<c:out value="${infoMercado.chassi}"/>						
					</display:column>	
					
					<display:column titleKey="infoMercado.form.data" sortable="true" headerClass="sortable" style="text-align: center;">
						<c:out value="${infoMercado.maskedDataInfo}"/>
					</display:column>	
					
					<display:column titleKey="infoMercado.list.dataProb" sortable="true"  defaultorder="descending" headerClass="sortable" style="text-align: center;">
						<c:out value="${infoMercado.maskedDataProblema}"/>
					</display:column>
					
					<display:column titleKey="infoMercado.list.status" sortable="true"  defaultorder="descending" headerClass="sortable" style="text-align: center;">
						<c:out value="${infoMercado.statusInfoMercado.descricao}"/>
					</display:column>					
					
					<display:column titleKey="infoMercado.list.options" media="html">
						<center>
							<c:if test="${infoMercado.statusInfoMercado.entityId == 1 || infoMercado.statusInfoMercado.entityId == 3}">	
								<html-el:link href="javascript:liberar(${infoMercado.entityId});" style="text-decoration: underline;">
									<bean:message key="infoMercado.list.liberar"/>
								</html-el:link>	
							</c:if>
							
							<c:if test="${infoMercado.statusInfoMercado.entityId != 1 && infoMercado.statusInfoMercado.entityId != 3}">	
								<bean:message key="infoMercado.list.liberar"/>
							</c:if>
							
							&nbsp; | &nbsp;	
							
							<c:if test="${infoMercado.statusInfoMercado.entityId == 1 || infoMercado.statusInfoMercado.entityId == 3}">						
								<html-el:link action="/InfoMercado.do?task=showFilledForm&entityId=${infoMercado.entityId}" style="text-decoration: underline;">
									<bean:message key="infoMercado.list.alterar"/>
								</html-el:link>
								
								&nbsp; | &nbsp;							
							
								<html-el:link href="javascript:remove(${infoMercado.entityId});" style="text-decoration: underline;">
									<bean:message key="infoMercado.list.excluir"/> 
								</html-el:link>
							</c:if>
							
							<c:if test="${infoMercado.statusInfoMercado.entityId != 1 && infoMercado.statusInfoMercado.entityId != 3}">	
								<bean:message key="infoMercado.list.alterar"/>												
							
								&nbsp; | &nbsp;	
								
								<bean:message key="infoMercado.list.excluir"/> 
							</c:if>		
						</center>
					</display:column>
					
				</display:table>	
				
			</center>

		</html:form>
		<ym:alertMessage />
	</body>
</html:html>