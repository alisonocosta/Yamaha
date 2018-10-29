<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- 
/* Resource Inform�tica LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_pagamentos_form.jsp
 *   .: Cria��o.....09 de julho de 2007
 *   .: Autor.......Gisele Panosso
 *                  Thiago Uriel M. Garcia
 *   .: Descri��o...Tela: Formul�rio do relat�rio de Pagamentos.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/calendar/calendar.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
	
		<table>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="zeroKm.form.dtIni"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Mar�o|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
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
				<td width="25%" class="td_dark">
 					<bean:message key="zeroKm.form.dtFim"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                monthLabels	="Janeiro|Fevereiro|Mar�o|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
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
 					<bean:message key="pagamentos.form.tipoServico"/>:   
                </td>
				<td>
					<html:select property="tipoServico" styleClass="listSelect">
						<html:option value=""></html:option>
						<%-- <html:optionsCollection property="listTiposProd" value="entityId" label="descricaoCode" /> --%>
					</html:select> 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>   										 					                                                					
				</td>            	
			</tr>  
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="gerar relat�rio" styleClass="button_medium" onclick="javascript:return gravarForm(garantiaForm)"><bean:message key="form.btn.save"/></html:button>	
					&nbsp;
					<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
					&nbsp;
					<html:button property="cancelar" styleClass="button_medium" onclick="javascript:window.top.location.href='javascript:winClose();'"><bean:message key="form.btn.cancel"/></html:button>	
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