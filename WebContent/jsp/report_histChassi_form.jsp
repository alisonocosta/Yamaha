<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>    
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>

<%-- 
/* Resource Inform�tica LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......report_histChassi_form.jsp
 *   .: Cria��o.....22 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Tela: Formul�rio do Relat�rio Consulta de Historico do Chassi.
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
				
				window.top.location.href ="javascript:newTitle('Relat�rios');";
				window.location.href = "Relatorio.do?task=showList";
				
			}   		
		</script>	
	</head>
	<body leftmargin="0" topmargin="3">
	
		<html:form action="/Relatorio" method="post">
		<table>
            <tr>
                <td width="25%" class="td_dark">
 					<bean:message key="customer.chassi"/>:                                                   
                </td>
            	<td>
            		<html:text name="reportForm" property="chassi" styleClass="text"/> 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  
            	</td>
            </tr>
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(reportForm,'proccessRelatorioHistChassi',true);"><bean:message key="form.btn.submit"/></html:button>	
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