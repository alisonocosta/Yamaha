<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="core" 	%>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%--<ym:checkLogon roleName="MalaDireta" />--%>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_malaDireta_form.jsp
 *   .: Criação.....30 de julho de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Tela: Formulário do relatório de Mala Direta.
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
		</script>			
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
	
		<table>
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="recusa.form.empresa"/>:                                                   
                </td>
				<td class="text" colspan="3">
					<html:select property="empresaId" styleClass="listSelect" >
						<html:option value=""></html:option>
						<html:optionsCollection property="empresaList" value="entityId" label="orgCode" />
					</html:select> 
				</td>            	
			</tr> 
			<core:if test="${isConc == false}">
				<html:hidden property="concessionariaId"/>
				<html:hidden property="estado"/>
			</core:if>  
			<core:if test="${isConc == true}">  
				<tr>
	                <td class="td_dark">
	 					<bean:message key="verificacao.form.concessionaria"/>:                                                   
	                </td>
					<td class="text" colspan="3">
						<html:select property="concessionariaId" styleClass="listSelectMaior" >
							<html:option value=""></html:option>
							<html:optionsCollection property="concessionariaList" value="entityId" label="razaoSocial" />
						</html:select> 
					</td>            	
				</tr>            
	            <tr>
	                <td class="td_dark">
	 					<bean:message key="malaDireta.form.estado"/>:                                                   
	                </td>
	            	<td class="text" colspan="3">
						<html:select property="estado" styleClass="listSelect"> 
							<html:option value=""></html:option>
							<html:option value="AC">Acre</html:option>
							<html:option value="AL">Alagoas</html:option>
							<html:option value="AP">Amapá</html:option>
							<html:option value="AM">Amazonas</html:option>
							<html:option value="BA">Bahia</html:option>
							<html:option value="CE">Ceará</html:option>
							<html:option value="DF">Distrito Federal</html:option>
							<html:option value="GO">Goiás</html:option>
							<html:option value="ES">Espírito Santo</html:option>
							<html:option value="MA">Maranhão</html:option>
							<html:option value="MT">Mato Grosso</html:option>
							<html:option value="MS">Mato Grosso do Sul</html:option>
							<html:option value="MG">Minas Gerais</html:option>
							<html:option value="PA">Pará</html:option>
							<html:option value="PB">Paraiba</html:option>
							<html:option value="PR">Paraná</html:option>	
							<html:option value="PE">Pernambuco</html:option>
							<html:option value="PI">Piauí</html:option>
							<html:option value="RJ">Rio de Janeiro</html:option>
							<html:option value="RN">Rio Grande do Norte</html:option>
							<html:option value="RS">Rio Grande do Sul</html:option>
							<html:option value="RO">Rondônia</html:option>
							<html:option value="RR">Rorâima</html:option>
							<html:option value="SP">São Paulo</html:option>
							<html:option value="SC">Santa Catarina</html:option>
							<html:option value="SE">Sergipe</html:option>
							<html:option value="TO">Tocantins</html:option>
						</html:select> 
	            	</td>
	            </tr>	
            </core:if>
			<tr>
                <td class="td_dark">
 					<bean:message key="lote.line"/>:                                                   
                </td>
				<td colspan="3">
					<html:select property="linhaId" styleClass="listSelect">
						<html:option value=""></html:option>
						<html:optionsCollection property="linhaList" value="entityId" label="descricao" />
					</html:select> 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
				</td>            	
			</tr>					
            <tr>
                <td class="td_dark">
 					<bean:message key="infoMercado.form.casco.modelo"/>:                                                   
                </td>
            	<td class="text" colspan="3">
            		<html:text name="reportForm" property="modelo" styleClass="text"/> 
            	</td>
            </tr>	
            <tr>
                <td class="td_dark">
 					<bean:message key="infoMercado.form.chassi"/>:                                                   
                </td>
            	<td class="text">
            		<html:text name="reportForm" property="chassiIni" styleClass="text"/> 
            	</td>
            	<td class="td_dark">
            		<bean:message key="malaDireta.form.ate"/>
            	</td>            	
            	<td class="text">
            		<html:text name="reportForm" property="chassiFim" styleClass="text"/> 
            	</td>
            </tr>	  
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="zeroKm.form.dtIni"/>:
				</td>
				<td width="75%" class="text" colspan="3">	
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
						 onKeyUp		="mascara_data('dataInicio',event)">
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
				<td width="75%" class="text" colspan="3">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                dayHeaders		="D|S|T|Q|Q|S|S"
						todayLabel		="Hoje"
						fieldName		="dataFim"
						fieldClass		="text_field_date"
						datePattern	    ="dd/MM/yyyy"
						 fieldLength	="11"
						 maxLength      ="10"
						 fieldYears		="4"
						 onKeyUp		="mascara_data('dataFim',event)">
						<bean:write name="reportForm" property="dataFim"/>						 
					</ym:inputDate> &nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					<bean:message key="garantia.form.sg.dt.default"/>
				</td>
			</tr>	
 			<tr height="25">
				<td colspan="4" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioMalaDireta',true);"><bean:message key="form.btn.submit"/></html:button>	
					&nbsp;
					<html:button property="listar" styleClass="button_medium" onclick="javascript:return voltar();"><bean:message key="form.btn.preview"/></html:button>	
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="4">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="4"><img src="../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
			</tr>             		
		</table>
	</html:form>
</body>
</html>			