<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="core"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_gerenciamento_mes_form.jsp
 *   .: Criação.....23 de Outubro de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário do Relatório de Gerenciamento Mês.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/calendar/calendar.css"></link>		
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>
		<script language="JavaScript" src="../scripts/General/form.js"></script>		
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 	
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>	
		<script language="javascript">

			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					window.document.reportForm.create.click();
				}
	 		}
			
			/** realiza as validações de campos obrigatórios */
			function validateForm() {
			
				var dataIniApuracao = window.document.reportForm.dataInicio;
				var dataFinApuracao = window.document.reportForm.dataFim;
				
				<core:if test="${isInnerUser}" >
				
					var indexConc = window.document.reportForm.concessionariaId.selectedIndex;
   					var concId    = window.document.reportForm.concessionariaId.options[indexConc].value;
   					
					if ( concId == "" ) {
						window.alert("Selecione uma Concessionária!");
						window.document.reportForm.concessionariaId.focus();
						return false;
					}
				
				</core:if>
				
				if ( dataIniApuracao.value == "" ) {
				
					window.alert("A data de apuração é obrigatória!");
					dataIniApuracao.focus();
					return false;
					
				} else if ( dataFinApuracao.value == "" ) {
				
					window.alert("A data de apuração é obrigatória!");
					dataFinApuracao.focus();
					return false;
					
				} 
				
				window.document.reportForm.create.disabled = true;
				__showAnima();
				return true;
			}	
			
			function voltar() { 
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relatórios');";
				window.location.href = "Relatorio.do?task=showList";
				
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
		<input type="hidden" name="isInnerUser" value="<core:out value='${isInnerUser}'/>" />
		<table width="98%">
			<core:if test="${isInnerUser}" >
				<tr>
	                <td width="25%" class="td_dark">
	 					<bean:message key="verificacao.form.concessionaria"/>:                                                   
	                </td>
					<td class="text">
						<html:select property="concessionariaId" styleClass="listSelect" >
							<html:option value="">Selecione</html:option>
							<html:optionsCollection property="concessionariaList" value="entityId" label="razaoSocial" />
						</html:select> 
					</td>            	
				</tr> 
			</core:if>
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="report.form.linha"/>:                                                   
                </td>
				<td class="text">
					<html:select property="linhaId" styleClass="listSelect" >
						<html:option value=""></html:option>
						<html:optionsCollection property="linhaList" value="entityId" label="descricao" />
					</html:select> 
					&nbsp;
					<bean:message key="graficosIndividuais.form.data.consolidado"/>
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
						 tabIndex       ="1002"> <%-- Valor aleatório para ordem de tablulação --%>
						 <bean:write name="reportForm" property="dataFim"/>
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
				</td>
			</tr>
			
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'processRelatorioGerenciamentoMes',validateForm());"><bean:message key="form.btn.submit"/></html:button>
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