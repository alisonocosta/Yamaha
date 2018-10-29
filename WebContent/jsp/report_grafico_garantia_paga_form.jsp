<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%-- 
/* Resource Inform�tica LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_grafico_garantia_paga_form.jsp
 *   .: Cria��o.....12 de abril de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Tela: Formul�rio do Relat�rio de Gr�fico Garantia Paga por Modelo de Produto.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/calendar/calendar.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>		
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
				
					return false;
				}
	 		}

			function voltar() { 
				
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relat�rios');";
				window.location.href = "Relatorio.do?task=showList";
				
			}   
			
			/** realiza as valida��es de campos obrigat�rios */
			function validateForm() {
			
				var dataFApuracao = window.document.reportForm.dataFim;
				var dataIApuracao = window.document.reportForm.dataInicio;
				
				if ( dataIApuracao.value == "" ) {
				
					window.alert("A data de In�cio da apura��o � obrigat�ria!");
					dataIApuracao.focus();
					return false;
					
				} else if ( dataFApuracao.value == "" ) {
				
					window.alert("A data Final de apura��o � obrigat�ria!");
					dataFApuracao.focus();
					return false;
					
				}
				__showAnima();			
				return true;
			}	
			
			/** Move o foco para o pr�ximo objeto, aplic�vel a caixa de texto
			 *  quando maxLength foi atingido o foco � direcionado para o pr�ximo objeto
			 *
			 *  obj  - Objeto 
			 *  tam  - tamanho do objeto
			 */
			function checkLen(x,y) {
				if (y.length==x.maxLength) {
			
					var isThis =x.name;
					
					if ( isThis == "dataInicio" ) {
				
						window.document.getElementById("reportForm").dataFim.focus();
					
					} 
				}
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
		<html:form action="/Relatorio" method="post" >
		<table>
			<tr>
                <td class="td_dark">
 					<bean:message key="lote.line"/>:                                                   
                </td>
				<td>
					<html:select property="linhaId" styleClass="listSelect">
						<html:option value=""></html:option>
						<html:optionsCollection property="linhaList" value="entityId" label="descricao" />
					</html:select> 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
				</td>            	
			</tr>	
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
		                 monthLabels	="Janeiro|Fevereiro|Mar�o|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataInicio"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 maxLength      ="10"
						 fieldYears		="4"
						 onKeyUp		="mascara_data('dataInicio',event);checkLen(this,this.value);"
						 tabIndex       ="1001"> <%-- Valor aleat�rio para ordem de tablula��o --%>
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
		                 monthLabels	="Janeiro|Fevereiro|Mar�o|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataFim"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 maxLength      ="10"
						 fieldYears		="4"
						 onKeyUp		="mascara_data('dataFim',event);"
						 tabIndex       ="1001"> <%-- Valor aleat�rio para ordem de tablula��o --%>
						 <bean:write name="reportForm" property="dataFim"/>
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
				</td>
			</tr>
			
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'processRelatorioGraficoGarantiaPaga',validateForm());"><bean:message key="form.btn.submit"/></html:button>	
					&nbsp;
					<html:button property="listar" styleClass="button_medium" onclick="javascript:return voltar();"><bean:message key="form.btn.preview"/></html:button>	
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><img src="../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigat�rio</td>
			</tr>             		
		</table>
	
	</html:form>
</body>
</html>			