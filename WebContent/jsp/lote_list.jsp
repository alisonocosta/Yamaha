<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>

<ym:checkLogon roleName="sg_lt_mst" />
 
<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......lote_list_new.jsp
 *   .: Criação.....23 de dezembro de 2009, 00:03
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem de lotes.
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
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		<script language="JavaScript"  src="../scripts/General/form.js"></script>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script type="text/javascript">
			
			function incluir() {
				__showAnima();
				window.top.location.href ="javascript:newTitle('Incluir Lote');" 
				window.location.href = "Lote.do?task=add";
			
			}
			
			/** Função chamada para abrir a janela de Relação de Cupons.
			 *  parâmetro:  id do lote - loteId
			 */
			function listCupom(loteId) {
				window.top.location.href ="javascript:newTitle('Relação de Revisões');" 
				window.location.href = "Cupom.do?task=list&loteId=" + loteId;
			}
			 
			/** Função chamada para abrir a janela de Relação de Garantias.
			 *  parâmetro:  id do lote - loteId
			 */
			function listGarantia(loteId) { 
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relação de Garantias');" 
				window.location.href = "Garantia.do?task=list&loteId=" + loteId;
			}
			 
			 /** Função chamada para abrir a janela de Relação de Garantias para a linha de motocicleta.
			 *  parâmetro:  id do lote - loteId
			 */
			function listGarantiaMoto(loteId) { 
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relação de Garantias');" 
				window.location.href = "GarantiaMoto.do?task=list&loteId=" + loteId;
			}
			
			/** Função chamada para retornar a lista de lotes de acordo com o status
			 *  parâmetro: id do status do lote: statusLoteId
			 */
			function buscar() {
			
				var field   = window.document.loteForm.criterio;
				var fLoteId = window.document.loteForm.entityId.value;
				
				if ( fLoteId != "" && fLoteId != null ) {
				
					__showAnima();
					window.location.href = "Lote.do?task=listSearch&loteId=" + fLoteId;
				
				} else {
				
					var status = "";
					for ( i = 0; i < field.options.length; i++ ) {
						
						if ( field.options[i].selected == true ) {
						
							status += field.options[i].value;
							if(i < (field.options.length -1))
								status += ";";
						
						}
						
					}	
					__showAnima();
					window.location.href = "Lote.do?task=listSearch&status=" + status;
					
				}
			
			}
			function confirmar(){			
				if ( window.confirm("Confirma a liberação do lote?") ) {
					return true;
				}else {
					return false;				
				}
			}
			
			function confExclusao() {
				if ( window.confirm("Confirma a cancelamento do lote?") ) {
					return true;
				}else {
					return false;				
				}			
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
		<html:form action="/Lote" method="get" >

			<html:hidden property="task"/>
			
			<jsp:include flush="true" page="include_alerts.jsp"/>
			
			<center>
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">	
				<tr>
					<td class="td_dark"><bean:message key="lote.statusLote"/></td>
					<td class="td_dark"><bean:message key="lote.loteNumber"/></td>
					<td >&nbsp;</td>
				</tr>
				<tr>
					<td>				
						<html:select property="criterio" styleClass="text_field_list" multiple="true" size="3">
								<html:optionsCollection property="listStatusLote" value="entityId" label="mascara" />
						</html:select><br>
						<bean:message key="lote.searchCTRL"/>
					</td>
					<td>
						<html:text property="entityId" maxlength="9" size="10" styleClass="text_field_date" onkeypress="return blockChar(event);"/>
					</td>
					<td>
						<html:button property="pesquisar" styleClass="button_medium"  onclick="javascript:buscar();"><bean:message key="form.btn.search"/></html:button>&nbsp;	
						<html:button property="inc" styleClass="button_medium"  onclick="javascript:incluir();"><bean:message key="form.btn.include"/></html:button>	
					</td>					
				</tr>
				</table>
				
				<display:table name="requestScope.loteForm.listResults" 
				               requestURI="${pageContext.request.contextPath}/Lote.do"
				               defaultsort="1" 
				               defaultorder="descending" 
				               export="false" 
				               pagesize="${qtdeLinhas}"
				               sort="list" 
				               id="lote"
				               class="grid">
				    				    
					<display:column titleKey="lote.title" style="text-align: right;" headerClass="headerAlign" sortable="true" property="loteId" defaultorder="descending"/>
										
					<display:column titleKey="lote.type" media="html" headerClass="headerAlign">
						<c:if test="${lote.tipoId == 1}">
							<html-el:link href="javascript:listCupom(${lote.loteId});" style="text-decoration: underline;">
								<c:out value="${lote.descricaoTipoLote}"/>	
							</html-el:link>	
						</c:if>		
						<c:if test="${lote.tipoId == 2 && lote.linhaId == 1}">											
							<html-el:link href="javascript:listGarantiaMoto(${lote.loteId});" style="text-decoration: underline;">
								<c:out value="${lote.descricaoTipoLote}"/>	
							</html-el:link>	

						</c:if>	
						<c:if test="${lote.tipoId == 2 && lote.linhaId == 2}">											
							<html-el:link href="javascript:listGarantia(${lote.loteId});" style="text-decoration: underline;">
								<c:out value="${lote.descricaoTipoLote}"/>	
							</html-el:link>	

						</c:if>			
					</display:column>
					
					<display:column titleKey="lote.type" media="pdf excel csv rtf" headerClass="headerAlign">						
						 <c:out value="${lote.descricaoTipoLote}"/>	
					</display:column>
					
					<display:column titleKey="lote.line" headerClass="headerAlign">
						<c:out value="${lote.descricaoLinha}"/>
					</display:column>	
					
					<display:column titleKey="lote.status" media="html" headerClass="headerAlign">
						<font color="<c:out value='${lote.statusCor}'/>"><c:out value="${lote.statusMascara}"/></font>
					</display:column>
					
					<display:column titleKey="lote.status" media="pdf excel csv rtf" headerClass="headerAlign">
						<c:out value="${lote.statusMascara}"/>
					</display:column>
					
					<display:column titleKey="lote.statusAdiantamento" headerClass="headerAlign">
						<c:out value="${lote.descricaoStatusAdiantamento}"/>
					</display:column>
					
					<display:column titleKey="lote.openDate" style="text-align: center;" headerClass="headerAlign">
						<c:out value="${lote.maskedDataLote}"/>
					</display:column>	
					
					<display:column titleKey="lote.liberateDate" style="text-align: center;" headerClass="headerAlign">
						<c:out value="${lote.maskedDataLiberacao}"/>
					</display:column>
					
					<display:column titleKey="lote.activity" media="html" headerClass="headerAlign">
						<center>
						
							<%-- Verificamos se Status do lote é diferente de 2 - Liberado --%>	 
							<c:if test="${lote.releaseValid == true}">
								<html-el:link action="/Lote.do?task=liberate"
								   			  paramName="lote" 
								   			  paramId="loteId" 
								   			  paramProperty="loteId"
								   			  style="text-decoration: underline;"
								   			  onclick="return confirmar()"
								   			  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Liberar Lote';return true;">
								   			  
									<bean:message key="lote.liberate"/> 
								</html-el:link>
							</c:if>
						
							<%-- Se for igual não possui link  --%>  
							<c:if test="${lote.releaseValid == false}">
								<i><bean:message key="lote.liberate"/></i>
							</c:if>
						 	
							&nbsp;|&nbsp;
							
							<%-- Verificamos se Status do lote permite o cancelamento e se não existe moviemento de itens   --%>
							<c:if test="${lote.cancelValid == true && (!lote.hasMovimento)}">
								<html-el:link action="/Lote.do?task=cancel" 
											  paramName="lote" 
											  paramId="loteId" 
											  paramProperty="loteId"
											  style="text-decoration: underline;"
											  onclick="return confExclusao()"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Cancelar Lote';return true;">
											  
									<bean:message key="lote.cancel"/> 
								</html-el:link>
							</c:if>
														  
							<c:if test="${(lote.cancelValid == false || (lote.hasMovimento))}">
								<i><bean:message key="lote.cancel"/></i>
							</c:if>
						</center>
					</display:column>
					
				</display:table>
				
			</center>
		
		</html:form>
		<ym:alertMessage />	
		<ym:javaScriptExecuter/>	
	</body>

</html:html>