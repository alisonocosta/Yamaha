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
		<jsp:include flush="true" page="include_alerts.jsp"/><br>
		<html:form action="/Teste" method="post">
		<table>
			<tr>
                <td width="25%" class="td_dark">
 					Texto:                                                   
                </td>
				<td class="text">
					<html:textarea property="linhaDescricao" rows="10" cols="100"></html:textarea> 
				</td>            	
			</tr> 			
			
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="create" styleClass="button_medium" onclick="submitFormTsk(cupomForm,'processamento',true);"><bean:message key="form.btn.submit"/></html:button>	
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>           		
		</table>
	
	</html:form>
</body>
</html>			