<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	    %>
<%@taglib uri="http://java.sun.com/jstl/fmt"		   prefix="fmt"     %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"      %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_consultaNfResult_form.jsp
 *   .: Criação.....09 de agosto de 2007, 10:04
 *   .: Autor.......Gisele Cristina Panosso
 *   .: Descrição...Tela: Lista o relatório de lotes.
 */
--%>

<html:html>
<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/General/form.js"></script>

		<script language="JavaScript">
			function showHide(wobj) {
		    	document.getElementById(wobj).style.display = (document.getElementById(wobj).style.display == 'none') ? '' : 'none';
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
	
		<html:form action="/Relatorio" method="post">
			
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>	
			<center>
			
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" class="td_dark" width="40%" colspan="2">
						<bean:message key="consultaLote.forms.title"/>
					</td>
					<td align="right"  class="td_dark" width="30%">
						<bean:message key="infoMercado.list.dataInfo"/>:
						<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />
					</td>	
				</tr>	
				<tr>
					<td align="left" class="td_dark" width="30%">
						<bean:message key="consultaLote.forms.periodo"/>:
						<bean:write name="reportForm" property="dataInicio"/> a <bean:write name="reportForm" property="dataFim"/>
					</td>
					<td align="center" class="td_dark" width="40%">
						<bean:message key="garantia.list.Linha"/>:
						<bean:write name="reportForm" property="linha"/>
					</td>
					<td align="right" class="td_dark" width="30%">
						<bean:message key="consultaLote.forms.hora"/>:
						<fmt:formatDate pattern="hh:mm" value="${now}" />
					</td>
				</tr>				
			</table>
	
			<p/>
			
			<table width="700" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
			<tr>
				<td align="left" class="td_dark" width="25%"><bean:message key="verificacao.form.concessionaria"/></td>
				<td align="left" class="td_dark" width="23%"><bean:message key="customer.ender"/></td>
				<td align="left" class="td_dark" width="12%"><bean:message key="customer.postalCode"/></td>
				<td align="left" class="td_dark" width="20%"><bean:message key="customer.city"/></td>
				<td align="left" class="td_dark" width="5%"><bean:message  key="customer.state"/></td>
				<td align="left" class="td_dark" width="15%"><bean:message key="consultaLote.forms.cnpj"/></td>
			</tr>
			<c:forEach items="${listConcessionaria}" var="concessionaria">
				<tr class="tr_line">
					<td class="text">
						<c:out value="${concessionaria.concessionaria}"/>						
					</td>					
					<td class="text">
						<c:out value="${concessionaria.endereco}"/>						
					</td>
					<td class="text">
						<c:out value="${concessionaria.cep}"/>						
					</td>					
					<td class="text">
						<c:out value="${concessionaria.cidade}"/>						
					</td>
					<td class="text">
						<c:out value="${concessionaria.estado}"/>						
					</td>
					<td class="text">
						<c:out value="${concessionaria.cnpj}"/>						
					</td>
				</tr>
				<tr class="tr_line">
					<td align="left" class="td_dark" colspan="6">
						<table width="100%" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;">
							<tr>
								<td align="left"   class="td_dark" width="5%"></td>
								<td align="left"   class="td_dark" width="15%"><bean:message key="notaFiscal.list.lote"/></td>
								<td align="left"   class="td_dark" width="15%"><bean:message key="notaFiscal.list.tipo"/></td>
								<td align="left"   class="td_dark" width="20%"><bean:message key="notaFiscal.list.status"/></td>
								<td align="center" class="td_dark" width="15%"><bean:message key="garantia.list.dtAbert"/></td>
								<td align="center" class="td_dark" width="15%"><bean:message key="consultaLote.forms.dtLiberacao"/></td>
								<td align="center" class="td_dark" width="15%"><bean:message key="garantia.list.dtFech"/></td>
							</tr>
							<tr>
								<c:forEach items="${concessionaria.listLote}" var="lotePrincipal" varStatus="counter">
								<tr class="tr_line">
										<td class="text" align="center">
											<html-el:link href="JavaScript:showHide('detail_${counter}');" styleClass="text_link2" style="text-decoration:none;">
												<bean:message key="consultaLote.forms.sinalMais"/>
											</html-el:link>
										</td>
										<td class="text">
											<c:out value="${lotePrincipal.lote}"/>						
										</td>						
										<td class="text">
											<c:out value="${lotePrincipal.tipoLote}"/>				
										</td>
										<td class="text">
											<c:out value="${lotePrincipal.descStatus}"/>				
										</td>					
										<td align="center" class="text">
											<c:out value="${lotePrincipal.maskedDtAbertura}"/>				
										</td>
										<td align="center" class="text">
											<c:out value="${lotePrincipal.maskedDtLiberacao}"/>
										</td>
										<td  align="center" class="text">
											<c:out value="${lotePrincipal.maskedDtFechamento}"/>
										</td>
									</tr>
									<tr id="detail_<c:out value="${counter}"/>" style="display:none">
									
										<td colspan="2"></td>
										<td colspan="5" align="left">
										
											<table width="80%" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;">
												<tr>
													<td align="left"   class="td_dark" width="50%"><bean:message key="consultaLote.forms.statusAnt"/></td>
													<td align="center" class="td_dark" width="15%"><bean:message key="consultaLote.forms.dtStatus"/></td>
												</tr>
												<c:forEach items="${lotePrincipal.listStatus}" var="loteStatus">
													<tr class="tr_line">
														<td class="text">
															<c:out value="${loteStatus.descStatusAnterior}"/>				
														</td>
														<td align="center" class="text">
															<c:out value='${loteStatus.maskedDtStatus}'/>				
														</td>
													</tr>
												</c:forEach>												
											</table>
										</td>
									</tr>					
								</c:forEach>								
							</tr>								
						</table>
					</td>						
				</tr>				
			</c:forEach>			
			</table>
			</center>
		</html:form>
	</body>
</html:html>
