<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_garantiaPecasMod_form.jsp
 *   .: Criação.....18 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Tela: Formulário do relatório de garantia e peças por modelo.
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
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>
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
	
		<jsp:include flush="true" page="include_alerts.jsp"/>
		<html:form action="/Relatorio" method="post">
	
		<table width="300">
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="verificacao.form.concessionaria"/>:                                                   
                </td>
				<td width="75%" class="text" colspan="3">
					<html:select property="concessionariaId" styleClass="listSelectMaior" >
						<html:option value=""></html:option>
						<html:optionsCollection property="concessionariaList" value="entityId" label="razaoSocial" />
					</html:select> 
				</td>            	
			</tr> 
			<tr>
                <td class="td_dark">
 					<bean:message key="lote.line"/>:                                                   
                </td>
				<td class="text">
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
				<td class="text">
					<html:select property="modeloId" styleClass="listSelect">
						<html:option value=""></html:option>
						<html:optionsCollection property="modeloList" value="modelo" label="modelo" />
					</html:select> 
				</td>        
			</tr>				
            <tr>
                <td class="td_dark">
 					<bean:message key="garantiaPecas.form.moeda"/>:   
                </td>
            	<td class="text">
					<html:select property="moeda" styleClass="listSelect"> 
						<html:option value=""></html:option>
						<html:option value="real">Real</html:option>
						<html:option value="dolar">Dolar</html:option>
					</html:select> 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
            	</td>
            </tr>							
            <tr>
				<td width="25%" class="td_dark">
 					<bean:message key="mensalModelo.form.mes"/>:                                                   
                </td>
            	<td class="text">
            		<html:text name="reportForm" property="dataFim" styleClass="text_field_date" onkeyup="mascara_mes('dataFim',event)"/> 
            		<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					<bean:message key="mensalModelo.form.dtDefault"/>
            	</td>
            </tr>	
 			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioGarantiaPecasModelo',true);"><bean:message key="form.btn.submit"/></html:button>	
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