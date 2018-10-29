<%@taglib uri="http://struts.apache.org/tags-bean"    	prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    	prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" 	prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic-el"  prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 							prefix="ym"      %>
<%@taglib uri="http://java.sun.com/jstl/core" 			prefix="core"  %>

<ym:checkLogon roleName="ym_sg_lista_relatorios_java" />

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>	
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/cep.js"></script>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script type="text/javascript">
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
	 		
			function alterarTitulo(title) { 
			
				window.top.location.href ="javascript:newTitle('" + title + "');";
			}   
					
		</script>		
	</head>
	<body leftmargin="0" topmargin="3" onLoad="__loadEsconde();">
	<div id="carregador_pai">
	    <div id="carregador">
	        <div align="center">
	            <img src="../images/run.gif"/>
	            <br /><br />Carregando Aguarde...
	        </div>
	        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
	    </div>
	</div>
		<html:form action="/Relatorio" method="post">
			<table border="0" cellpadding="2" cellspacing="1">
			
			<logic:iterate name="reportForm" property="listProgramas" id="programa">
				<core:if test="${programa.tipoRelatorio == 'J'}">
					<tr>
						<td class="labelList">
							<html:img srcKey="report.list.img.pdf" altKey="report.list.msg.pdf"/>&nbsp;
							<html-el:link action="${programa.urlAcesso}" 
										  onclick="javascript:return alterarTitulo('${programa.descricao}')" 
										  styleClass="labelList"
										  onmouseover="window.status='${programa.descricao}';return true;"
										  onmouseout="window.status='';return true;">
								<bean:write name="programa" property="descricao"/>
							</html-el:link>						
						</td>				
					</tr>
				</core:if>
				<core:if test="${programa.tipoRelatorio == 'P'}">
					<tr>
						<td class="labelList">
							<html:img srcKey="report.list.img.pdf" altKey="report.list.msg.pdf"/>&nbsp;
							<html-el:link 	href="../${programa.urlAcesso}${userId}" 
										 	onclick="javascript:return alterarTitulo('${programa.descricao}')" 
										 	styleClass="labelList"
										 	onmouseover="window.status='${programa.descricao}';return true;"
										 	onmouseout="window.status='';return true;">
								 <bean:write name="programa" property="descricao"/>
							</html-el:link>						
						</td>				
					</tr>
				</core:if>		
			</logic:iterate>
			
			<tr>
					<td class="labelList">
						&nbsp;
					</td>				
			</tr>			
			<%--
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioMalaDireta" onclick="javascript:return alterarTitulo('Arquivo para Mala Direta')">1. Arquivo para Mala Direta</html:link></td>				
				</tr>
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioProcRevisoesFase1" onclick="javascript:return alterarTitulo('Processamento de Revisões - Fase 1')">2. Processamento de Revisões - Fase 1</html:link></td>				
				</tr>		
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioProcRevisoesFase2" onclick="javascript:return alterarTitulo('Processamento de Revisões - Fase 2')">3. Processamento de Revisões - Fase 2</html:link></td>				
				</tr>							
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioProcPecasFase1" onclick="javascript:return alterarTitulo('Processamento de Peças - Fase1')">4. Processamento de Peças - Fase1</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioProcPecasFase2" onclick="javascript:return alterarTitulo('Processamento de Peças - Fase2')">5. Processamento de Peças - Fase2</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioRecusa" onclick="javascript:return alterarTitulo('Relatório de Recusa')">6. Relatório de Recusa</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioSolicitacaoGarantia" onclick="javascript:return alterarTitulo('Protocolo de documentos de Solicitação de Garantia')">7. Protocolo de documentos de Solicitação de Garantia</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioDocRevisao" onclick="javascript:return alterarTitulo('Protocolo de Documentos de Revisão')">8. Protocolo de Documentos de Revisão</html:link></td>				
				</tr>		
				<tr>
					<td class="td_dark">9. Acompanhamento de tempo de Análise</td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioConsultaLote" onclick="javascript:return alterarTitulo('Consulta de Lotes')">10. Consulta de Lotes</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioConsultaNF" onclick="javascript:return alterarTitulo('Consulta de Notas Fiscais')">11. Consulta de Notas Fiscais</html:link></td>				
				</tr>		
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioHistChassi" onclick="javascript:return alterarTitulo('Consulta Histórico do Chassi')">12. Consulta Histórico do Chassi</html:link></td>				
				</tr>					
				<tr>
					<td height="20">&nbsp;</td>
				</tr>			
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioZeroKm" onclick="javascript:return alterarTitulo('Relatório 0 Km')">1. Relatório 0 Km</html:link></td>				
				</tr>
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioMensalModelo" onclick="javascript:return alterarTitulo('Relatório Mensal por Modelo')">2. Relatório Mensal por Modelo</html:link></td>				
				</tr>			
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioGarantiaPecas" onclick="javascript:return alterarTitulo('Relatório de Garantia e Peças')">3. Relatório de Garantia e Peças</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioGarantiaPecasModelo" onclick="javascript:return alterarTitulo('Relatório de Garantia e Peças por Modelo')">4. Relatório de Garantia e Peças por Modelo</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioImportedParts" onclick="javascript:return alterarTitulo('Imported Parts Claim List')">5. Imported Parts Claim List</html:link></td>				
				</tr>	
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioGraficosIndividuais" onclick="javascript:return alterarTitulo('Gráficos Individuais')">6. Relatório de Gráficos Individuais</html:link></td>				
				</tr>
				<tr>
					<td class="td_dark"><html:link action="/Relatorio.do?task=prepareRelatorioServiceReport" onclick="javascript:return alterarTitulo('Gráficos Service Report')">7. Relatório de Gráficos Service Report</html:link></td>				
				</tr>
			--%>	
			</table>
		</html:form>
		</body>
</html>