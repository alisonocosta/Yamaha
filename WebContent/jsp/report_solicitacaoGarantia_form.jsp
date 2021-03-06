<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="core"    %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_solicitacaoGarantia_form.jsp
 *   .: Criação.....31 de julho de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Tela: Formulário do relatório Solicitação de Garantia.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/calendar/calendar.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>				
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 		
		<script language="javascript">
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
			
			function voltar() { 
				
				window.top.location.href ="javascript:newTitle('Relatórios');";
				window.location.href = "Relatorio.do?task=showList";
				
			}   		
		</script>			
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
		<jsp:include flush="true" page="include_alerts.jsp"/>
		<table>
			<core:if test="${isConc == false}">
				<html:hidden property="concessionariaId"/>
				<html:hidden property="estado"/>
			</core:if>  
			<core:if test="${isConc == true}">
				<tr>
	                <td width="25%" class="td_dark">
	 					<bean:message key="notaFiscal.launch.concessionaria"/>:                                                   
	                </td>
					<td>
						<html:select property="concessionariaId" styleClass="listSelectMaior">
							<html:option value=""></html:option>
							<html:optionsCollection property="concessionariaList" value="entityId" label="razaoSocial" />
						</html:select> 
					</td>            	
				</tr>	
			</core:if>  				
            <tr>
                <td width="25%" class="td_dark">
 					<bean:message key="lote.title"/>:                                                   
                </td>
            	<td>
            		<html:text name="reportForm" property="lote" styleClass="text"/> 
            	</td>
            </tr>
            <tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioSolicitacaoGarantia',true);"><bean:message key="form.btn.submit"/></html:button>	
					&nbsp;
					<html:button property="listar" styleClass="button_medium" onclick="javascript:return voltar();"><bean:message key="form.btn.preview"/></html:button>						
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><img src="../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
			</tr>             		
		</table>
	
	</html:form>
</body>
</html>			