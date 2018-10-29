<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......estoque_form.jsp
 *   .: Criação.....16 de abril de 2008  13:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de cadastro de estoque da concessionária.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/calendar/calendar.css"></link>		
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>
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
		</script>
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
		<table>
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="recusa.form.empresa"/>:                                                   
                </td>
				<td class="text">
					<html:select property="empresaId" styleClass="listSelect" >
						<html:option value=""></html:option>
						<html:optionsCollection property="empresaList" value="entityId" label="orgCode" />
					</html:select> 
				</td>            	
			</tr> 
			<tr>
				<td width="25%" class="td_dark">	
					<bean:message key="graficosIndividuais.form.data.inicioApuracao"/>:
				</td>			
				<td class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataInicio"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 maxLength      ="10"
						 fieldYears		="4"
						 onKeyUp		="mascara_data('dataInicio',event);checkLen(this,this.value);"
						 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
						 <bean:write name="reportForm" property="dataInicio"/>
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="td_dark">	
					<bean:message key="graficosIndividuais.form.data.finalApuracao"/>:
				</td>			
				<td class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataFim"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 maxLength      ="10"
						 fieldYears		="4"
						 onKeyUp		="mascara_data('dataFim',event);checkLen(this,this.value);"
						 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
						 <bean:write name="reportForm" property="dataFim"/>
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
				</td>
			</tr>
			
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioGraficosIndividuais',validateForm());"><bean:message key="form.btn.submit"/></html:button>	
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