<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_garantiaPecas_form.jsp
 *   .: Criação.....28 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Tela: Formulário do relatório de garantia e peças.
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
			
			// Realiza a validação dos campos do formulário
			function validation() {
			
				var isValid =  true;
				var empresaObj = window.document.getElementById("empresaId");
				var linhaObj   = window.document.getElementById("linhaId");
				var moedaObj   = window.document.getElementById("moeda");
				var anoObj     = window.document.getElementById("ano");
				
				if ( empresaObj == undefined || empresaObj.value == "" ) {
					isValid =  false;
					window.alert("O campo Empresa é obrigatório!");
					empresaObj.focus();
				} else if ( linhaObj == undefined || linhaObj.value == "" ) {
					isValid =  false;
					window.alert("O campo Linha é obrigatório!");
					linhaObj.focus();
				} else if ( moedaObj == undefined || moedaObj.value == "" ) {
					isValid =  false;
					window.alert("O campo Moeda é obrigatório!");
					moedaObj.focus();
				} else if ( anoObj == undefined || anoObj.value == "" ) {
					isValid =  false;
					window.alert("O campo Ano é obrigatório!");
					anoObj.focus();
				}
			
				return isValid;
			}		
		</script>		
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
	
		<table width="300">
			<tr>
                <td width="25%" class="td_dark">
 					<bean:message key="recusa.form.empresa"/>:                                                   
                </td>
				<td width="75%" class="text">
					<html:select property="empresaId" styleClass="listSelect" >
						<html:option value=""></html:option>
						<html:optionsCollection property="empresaList" value="entityId" label="orgCode" />
					</html:select> 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>
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
                <td class="td_dark">
 					<bean:message key="garantiaPecas.form.ano"/>:                                                   
                </td>
            	<td class="text">
            		<html:text name="reportForm" property="ano" styleClass="text_field_date" /> 
            		<img src="../images/icon_required.gif" alt="form.msg.required"/>  
            		<bean:message key="garantiaPecas.form.anoDefault"/>
            	</td>
            </tr>	
 			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioGarantiaPecas',validation());"><bean:message key="form.btn.submit"/></html:button>	
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