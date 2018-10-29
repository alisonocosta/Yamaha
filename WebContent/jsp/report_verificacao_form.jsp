<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_verificacao_form.jsp
 *   .: Criação.....09 de julho de 2007
 *   .: Autor.......Gisele Panosso
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Tela: Formulário do relatório de Verificação.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
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
			
			function voltar() { 
				
				window.top.location.href ="javascript:newTitle('Relatórios');";
				window.location.href = "Relatorio.do?task=showList";
				
			}  
			function validationForm() { 
					
					var x = document.getElementById("linhaId");
					var linhaSel = false;
					for (i = 1; i < x.options.length; i++){
						if (x.options[i].selected == true){
							linhaSel = true;
						}
					}			
					
					if ( linhaSel == false ){
						window.alert("O campo linha é obrigatório!");
						window.document.reportForm.linhaId.focus();
						
						return false;
					
					}
					
					if (window.document.reportForm.dataInicio.value == "") {
						window.alert("O campo Data Início é obrigatório!");
						window.document.reportForm.dataInicio.focus();
						
						return false;
					
					}
					if (window.document.reportForm.dataFim.value == "") {
						window.alert("O campo Data Fim é obrigatório!");
						window.document.reportForm.dataFim.focus();
						
						return false;
					
					}
					
					return true;
					
				}  
			</script> 		
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
	
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
                <td class="td_dark">
 					<bean:message key="verificacao.form.concessionaria"/>:                                                   
                </td>
				<td>
					<html:select property="concessionariaId" styleClass="listSelectMaior" >
						<html:option value=""></html:option>
						<html:optionsCollection property="concessionariaList" value="entityId" label="razaoSocial" />
					</html:select> 
				</td>            	
			</tr>            				
            <tr>
                <td class="td_dark">
 					<bean:message key="verificacao.form.valorMinimo"/>:                                                   
                </td>
            	<td>
            		<html:text name="reportForm" property="valorMinimo"/> 
            	</td>
            </tr>	
            <tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="zeroKm.form.dtIni"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataInicio"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 fieldYears		="4">
						 <bean:write name="reportForm" property="dataInicio"/>						 
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					<bean:message key="garantia.form.sg.dt.default"/>
				</td>
			</tr>
			<tr> 
				<td class="td_dark">
 					<bean:message key="zeroKm.form.dtFim"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                dayHeaders		="D|S|T|Q|Q|S|S"
						todayLabel		="Hoje"
						fieldName		="dataFim"
						fieldClass		="text_field_date"
						datePattern	    ="dd/MM/yyyy"
						fieldLength	    ="11"
						fieldYears		="4">
						<bean:write name="reportForm" property="dataFim"/>						 
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					<bean:message key="garantia.form.sg.dt.default"/>
				</td>
			</tr>		            
            <tr height="25" >
				<td colspan="2" align="center">
					<html:button property="gerar relatório" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioVerificacao',validationForm());"><bean:message key="form.btn.save"/></html:button>	
					&nbsp;
					<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
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