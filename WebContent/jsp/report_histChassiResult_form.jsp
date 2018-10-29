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
 *   .: Objeto......report_histChassiResult_form.jsp
 *   .: Criação.....22 de agosto de 2007
 *   .: Autor.......Gisele Cristina Panosso
 *   .: Descrição...Tela: Lista o resultado do relatório Histórico do Chassi.
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
			
			<table width="600" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left"  class="td_dark" width="40%"><bean:message key="consultaLote.forms.yamaha"/></td>
					<td align="left"  class="td_dark" width="40%"><bean:message key="consultaHistChassi.form.title"/></td>
					<td align="right" class="td_dark" width="20%">
						<bean:message key="infoMercado.list.dataInfo"/>:
						<fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />
					</td>	
				</tr>	
<%--			
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
--%>								
			</table>
	
			<p/>

			<table width="700" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
			<c:forEach items="${listCabecalho}" var="cabecalho">
				<tr class="tr_line">
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.produto"/></td>
					<td align="left" class="text"    colspan="5"><c:out value="${cabecalho.produto}"/></td>	
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.origem"/></td>
					<td align="left" class="text"    width="15%"><c:out value="${cabecalho.origem}"/></td>	
				</tr>
				<tr class="tr_line">
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.revenda"/></td>
					<td align="left" class="text"    colspan="5"><c:out value="${cabecalho.revenda}"/></td>	
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.fabMod"/></td>
					<td align="left" class="text"    width="15%"><c:out value="${cabecalho.fabModelo}"/></td>	
				</tr>
				<tr class="tr_line">
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.dtVenda"/></td>
					<td align="left" class="text"    width="15%"><c:out value="${cabecalho.strDtVenda}"/></td>	
					<td align="left" class="td_dark" width="12%"><bean:message key="notaFiscal.launch.numberNF"/></td>
					<td align="left" class="text"    width="12%"><c:out value="${cabecalho.notaFiscal}"/></td>	
					<td align="left" class="td_dark" width="13%"><bean:message key="recusa.form.empresa"/></td>
					<td align="left" class="text"    width="13%"><c:out value="${cabecalho.empresa}"/></td>	
					<td align="left" class="td_dark" width="10%"><bean:message key="consultaHistChassi.form.dtRevenda"/></td>
					<td align="left" class="text"    width="10%"><c:out value="${cabecalho.strDtRevenda}"/></td>						
				</tr>
				<tr class="tr_line">
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.dtFab"/></td>
					<td align="left" class="text"    width="15%"><c:out value="${cabecalho.strDtFabricacao}"/></td>	
					<td align="left" class="td_dark" width="12%"><bean:message key="consultaHistChassi.form.cor"/></td>
					<td align="left" class="text"    width="12%"><c:out value="${cabecalho.cor}"/></td>	
					<td align="left" class="td_dark" width="13%"><bean:message key="consultaHistChassi.form.garantia"/></td>
					<td align="left" class="text"    width="13%"><c:out value="${cabecalho.nrGarantia}"/></td>	
					<td align="left" class="td_dark" width="10%"><bean:message key="infoMercado.form.chassi"/></td>
					<td align="left" class="text"    width="10%"><c:out value="${cabecalho.chassi}"/></td>						
				</tr>
				<tr class="tr_line">
					<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.tipoUso"/></td>
					<td align="left" class="text"    colspan="7"><c:out value="${cabecalho.tipoUso}"/></td>
				</tr>				
			</c:forEach>		
			</table>
	
			<p/>

			<table width="700" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
				<tr>
					<td align="left" class="td_dark" colspan="7">
						<bean:message key="consultaHistChassi.form.revisoes"/>
					</td>
				</tr>
				<tr>
					<td align="left"   class="td_dark" width="10%"><bean:message key="consultaHistChassi.form.numero"/></td>
					<td align="center" class="td_dark" width="10%"><bean:message key="infoMercado.form.data"/></td>
					<td align="left"   class="td_dark" width="35%"><bean:message key="verificacao.form.concessionaria"/></td>
					<%--<td align="left"   class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.situacao"/></td>--%>
					<td align="left"   class="td_dark" width="15%"><bean:message key="garantia.form.peca.lote"/></td>
					<td align="left"   class="td_dark" width="10%"><bean:message key="garantia.list.status"/></td>
					<td align="left"   class="td_dark" width="5%">&nbsp;</td>
				</tr>
				<c:forEach items="${listRevisao}" var="revisao">
					<tr class="tr_line">
						<td align="left" class="text" width="10%"><c:out value="${revisao.nrRevisao}"/></td>	
						<td align="left" class="text" width="10%">
							<fmt:formatDate pattern="dd/MM/yyyy" value="${revisao.dtRevisao}"/>
						</td>	
						<td align="left" class="text" width="35%"><c:out value="${revisao.concessionaria}"/></td>	
						<%-- %><td align="left" class="text" width="15%"><c:out value="${revisao.situacao}"/></td>		--%>
						<td align="left" class="text" width="15%"><c:out value="${revisao.nrLote}"/></td>	
						<td align="left" class="text" width="10%"><c:out value="${revisao.statusLote}"/></td>	
						<td align="left" class="text" width="5%"><c:out value="${revisao.autorizacao}"/></td>											
					</tr>				
				</c:forEach>		
			</table>			
			
			<p/>
			
			<c:if test="${isInterUser}">
				<table width="700" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
					<tr>
						<td align="left" class="td_dark" colspan="10">
							<bean:message key="notaFiscal.guarantee"/>
						</td>
					</tr>
					<tr>
						<td align="center" class="td_dark" colspan="2"><bean:message key="notaFiscal.guarantee"/></td>
						<td align="left"   class="td_dark" width="10%"><bean:message key="garantia.form.sg.numOS"/></td>
						<td align="center" class="td_dark" width="10%"><bean:message key="lote.openDate"/></td>
						<td align="center" class="td_dark" width="10%"><bean:message key="consultaHistChassi.form.dtFech"/></td>
						<td align="left"   class="td_dark" width="25%"><bean:message key="verificacao.form.concessionaria"/></td>
						<%-- %><td align="left"   class="td_dark" width="10%"><bean:message key="consultaHistChassi.form.situacao"/></td>--%>
						<td align="left"   class="td_dark" width="10%"><bean:message key="garantia.form.peca.lote"/></td>
						<td align="left"   class="td_dark" width="10%"><bean:message key="garantia.list.status"/></td>
						<td align="left"   class="td_dark" width="3%">&nbsp;</td>
		
					</tr>
					<c:forEach items="${listGarantia}" var="garantia" varStatus="counter">
						<tr class="tr_line">
							<td class="text" align="center">
								<html-el:link href="JavaScript:showHide('detail_${counter}');" styleClass="text_link2" style="text-decoration:none;">
									<bean:message key="consultaLote.forms.sinalMais"/>
								</html-el:link>
							</td>				
							<td class="text">
								<c:out value="${garantia.garantia}"/>						
							</td>					
							<td class="text">
								<c:out value="${garantia.numeroOs}"/>						
							</td>
							<td class="text" align="center">
								<fmt:formatDate pattern="dd/MM/yyyy" value="${garantia.dtAberturaOs}"/>
							</td>					
							<td class="text" align="center">
								<fmt:formatDate pattern="dd/MM/yyyy" value="${garantia.dtFechamentoOs}"/>
							</td>
							<td class="text">
								<c:out value="${garantia.concessionaria}"/>						
							</td><%--							
							<td class="text">
								<c:out value="${garantia.situacao}"/>						
							</td>--%>
							<td class="text">
								<c:out value="${garantia.nrLote}"/>						
							</td>
							<td class="text">
								<c:out value="${garantia.statusLote}"/>						
							</td>
							<td class="text">
								<c:out value="${garantia.cortesia}"/>						
							</td>											
						</tr>
						<tr id="detail_<c:out value="${counter}"/>" style="display:none">
						
							<td colspan="2"></td>
							<td colspan="8" align="left">
							
								<table width="100%" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;">
									<tr>
										<td align="left" class="td_dark" width="15%"><bean:message key="notaFiscal.pecaValue"/></td>
										<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.vlServ3"/></td>
										<td align="left" class="td_dark" width="15%"><bean:message key="consultaHistChassi.form.codServ"/></td>
										<td align="left" class="td_dark" width="15%"><bean:message key="garantia.form.sg.sintoma"/></td>
										<td align="left" class="td_dark" width="15%"><bean:message key="garantia.form.sg.condicao"/></td>
										<td align="left" class="td_dark" width="25%"><bean:message key="garantia.form.sg.defeito"/></td>
									</tr>
									<c:forEach items="${garantia.listPecas}" var="pecas">
										<tr class="tr_line">
											<td class="text">
												<c:out value="${pecas.peca}"/>				
											</td>
											<td class="text">
												<c:out value="${pecas.vlServico3}"/>				
											</td>
											<td class="text">
												<c:out value="${pecas.codServico}"/>				
											</td>
											<td class="text">
												<c:out value="${pecas.codSintoma}"/>				
											</td>
											<td class="text">
												<c:out value="${pecas.codCondicao}"/>				
											</td>
											<td class="text">
												<c:out value="${pecas.defeito}"/>				
											</td>
										</tr>
									</c:forEach>	
									<tr>
										<td align="left" class="td_dark" colspan="6">
											<bean:message key="consultaHistChassi.form.parAnalista"/>
										</td>
									</tr>
									<c:forEach items="${garantia.listParecer}" var="parecer">
										<tr class="tr_line">
											<td class="text">
												<c:out value="${parecer.parecerAnalista}"/>				
											</td>
										</tr>
									</c:forEach>																			
								</table>
							</td>
						</tr>							
					</c:forEach>			
				</table>
				
				<p/>
			</c:if>
			<table width="700" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
				<c:forEach items="${listTotais}" var="total">
					<tr class="tr_line">
						<td align="left" class="td_dark" width="10%"><bean:message key="consultaHistChassi.form.totais"/></td>
						<td align="left" class="td_dark" width="13%"><bean:message key="consultaHistChassi.form.vlRev"/></td>
						<td align="left" class="text"    width="10%"><c:out value="${total.vlRevisao}"/></td>	
						<td align="left" class="td_dark" width="13%"><bean:message key="consultaHistChassi.form.vlGaran"/></td>
						<td align="left" class="text"    width="10%"><c:out value="${total.vlGarantia}"/></td>	
						<td align="left" class="td_dark" width="10%"><bean:message key="consultaHistChassi.form.vlPeca"/></td>
						<td align="left" class="text"    width="10%"><c:out value="${total.vlPecas}"/></td>		
						<td align="left" class="td_dark" width="14%"><bean:message key="consultaHistChassi.form.vlTerc"/></td>
						<td align="left" class="text"    width="10%"><c:out value="${total.vlTerceiros}"/></td>	
					</tr>				
				</c:forEach>		
			</table>			
			
			</center>
		</html:form>
	</body>
</html:html>
