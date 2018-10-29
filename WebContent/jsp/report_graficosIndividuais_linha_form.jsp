<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html"    %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_graficosIndividuais_linha_form.jsp
 *   .: Criação.....14 de abril de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário do Relatório de Gráficos Individuais por Linha.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/calendar/calendar.css"></link>			
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>	
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>
		<script language="JavaScript" src="../scripts/General/form.js"></script>		
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 	
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/dragdrop.js"></script>	
			
		<script language="javascript">

			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					window.document.reportForm.create.click();
				}
	 		}

			function voltar() { 
				
				window.top.location.href ="javascript:newTitle('Relatórios');";
				window.location.href = "Relatorio.do?task=showList";
				
			}   
			
			/** realiza as validações de campos obrigatórios */
			function validateForm() {
				
				var dataInicioAp = window.document.reportForm.dataInicio;
				var dataFimAp    = window.document.reportForm.dataFim;
				var indexModel   = window.document.reportForm.modelo.selectedIndex;
				var valueModel   = window.document.reportForm.modelo.options[indexModel].value;
				
				if ( dataInicioAp.value == "" ) {
				
					window.alert("A Data de Início é obrigatória!");
					dataInicioAp.focus();
					return false;
					
				} else if ( dataFimAp.value == "" ) {
				
					window.alert("A Data Final é obrigatória!");
					dataFimAp.focus();
					return false;
					
				} else if ( valueModel == "" ) {
				
					window.alert("Selecione um Modelo!");
					window.document.reportForm.modelo.focus();
					return false;
				}
				
				__showAnima();
				return true;
			}
				
			function initProgress() {
				Element.hide('msg');
				Element.show('indicator');	
			}
			
			function finalProgress() {
			
				Element.hide('indicator');				
				if (window.document.reportForm.modelo.options.length == 0 ) {
					window.document.getElementById("msg").innerHTML = "Não foram localizados modelos disponíveis para Linha informada!";
					Element.show('msg');
				}
							
			}
						
			function reportError() {
			
				if (window.document.reportForm.modelo.options.length == 0) {
					window.document.msg.innerHTML = "Erro - Valores não Encontrados!";
				}
				Element.show('msg');
				setTimeout("Element.hide('msg')", 2500);
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
		<table width="98%">		
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="report.form.linha"/>:                                                   
                </td>
				<td class="text">
					<html:select property="linhaId" styleClass="listSelect" >
						<html:option value=""></html:option>
						<html:optionsCollection property="linhaList" value="entityId" label="descricao" />
					</html:select>	
					<span id="indicator" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>				
				</td>            	
			</tr>	
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="graficosIndividuais.form.empresa"/>:                                                   
                </td>
				<td class="text">
					<html:select property="empresaId" styleClass="listSelect" >
						<html:option value=""></html:option>
						<html:optionsCollection property="empresaList" value="entityId" label="orgCode" />
					</html:select> 
					&nbsp;
					<bean:message key="graficosIndividuais.form.data.consolidado"/>
				</td>            	
			</tr> 
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="graficosIndividuais.form.modelo"/>:                                                   
                </td>
				<td class="text">
					<div id="msg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 11px Arial;color:#900"></div>
					<html:select property="modelo" styleClass="listSelect" >
						<html:option value=""><bean:message key="graficosIndividuais.form.select"/></html:option>
						<html:optionsCollection property="modeloList" value="modelo" label="modelo" />
					</html:select> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
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
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'processRelatorioGraficosIndividuaisLinha',validateForm());"><bean:message key="form.btn.submit"/></html:button>	
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
		<ajax:select
			baseUrl="${pageContext.request.contextPath}/LoadModels.do"
  			source="linhaId"
  			target="modelo"
  			parameters="linhaId={linhaId}"
  			preFunction="initProgress"
			postFunction="finalProgress"
			errorFunction="reportError"
		/>
	</html:form>
</body>
</html:html>		