<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="/tld/ym"  	 					   prefix="ym"	 %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="br.com.yamaha.sistemagarantia.model.Item" %>
<%@page import="br.com.yamaha.sistemagarantia.model.to.PecaTO" %>
<jsp:useBean id="garantiaPecaMotoForm" 
             type="org.apache.struts.validator.DynaValidatorActionForm" 
			 scope="request"/>

<%-- 
/* Yamaha.
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......peca_form.jsp
 *   .: Criação.....14 de outubro de 2012, 09:57
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de alteração de peça de garantia para linha de motococleta.
 */
--%> 

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="garantiaPecaMotoForm"/>		
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../../css/yamaha.css"></link>
		<script language="JavaScript" src="../../scripts/General/form.js"></script>		
		<script language="JavaScript">
		
			var options = new Array();
			<%
			
				int counter = 0;
			
				List options = (List) garantiaPecaMotoForm.get("options");
				Iterator it = options.iterator();
				
				while (it.hasNext()) {
					
					Item item = (Item) it.next();
					out.println("options[" + (counter++) + "] = '" + item.getEntityId() + " - " + item.getDescricao() + "';");
					
				}
			
			%>
		
		
			function redirectGar( loteId ) {
			
				if (window.confirm("Deseja incluir um nova garantia?") ) { 
				
					window.top.location.href ="javascript:newTitle('Incluir Garantia');";
					window.location.href = "/ym_sistema_garantia/GarantiaMoto.do?task=add&loteId=" + loteId;
					
				} else  { 	
				
					window.top.location.href ="javascript:newTitle('Relação de Garantias');";
					window.location.href = "/ym_sistema_garantia/GarantiaMoto.do?task=list&loteId=" + loteId;
				}		
			
			}
			
			function getOptions() {
			
				var result = "<option></option>" ;
			
				for (i=0; i<options.length; i++) {
				
					result += "<option value=" + options[i].replace(/ /g, "_") + ">" + options[i] + "</option>";
				
				}
				
				return result;
			
			}
		
			function getOptionsWithSelectedIndex(selectedIndex) {
			    
				var result = "<option></option>" ;
			
				for (i=0; i<options.length; i++) {
				
					if (options[i] == selectedIndex)
						result += "<option value=" + options[i].replace(/ /g, "_") + " selected>" + options[i] + "</option>";
					else
						result += "<option value=" + options[i].replace(/ /g, "_") + ">" + options[i] + "</option>";
				
				}
				
				return result;
			
			}
		
			function validateLine(lineId) {
			
				var item  = eval( "document.garantiaPecaMotoForm.identPeca_" + lineId );
				var qtd   = eval( "document.garantiaPecaMotoForm.qtdPeca_" + lineId );
				
				var itemValue = item.options[ item.selectedIndex ].value;
				var qtdValue  = qtd.value;
				
				if ( itemValue != "" && itemValue != null ) {
				
					if ( qtdValue == "" || qtdValue == null ) {
					
						window.alert("Por favor, digite uma combinação válida de valores. \n A quantidade é obrigatória para um item.");
					
					}
				
				}
			
			}
				
			
			function addRow() {
			
				var table = document.getElementById("values");
				var index = table.rows.length;
				var counter = document.forms[0].counter.value;
				
				var line = table.insertRow(index);
				line.id = counter;
				line.className = "tr_line";
				var a = line.insertCell(0);
				var b = line.insertCell(1);
				var c = line.insertCell(2);
				var d = line.insertCell(3);
				
				a.innerHTML = "<select name='identPeca_" + counter + "' style='font-family:Verdana; font-size:10px;'>" + getOptions() + "</select>";
				a.align = "center";

				b.innerHTML = "<input type='text' name='qtdPeca_" + counter + "' size='4' maxlength='4' style='font-family:Verdana; font-size:10px;' onKeyPress='return blockChar(event);' onBlur='validateLine(" + counter + ");' />";
				b.align = "center";

				c.innerHTML = "<table border='0' cellpadding='0' cellspacing='0'>" +
				              "<tr>" +
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='enviar_" + counter + "' value='true'/>Enviar" +
				              "</td>" +
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='cobrar_" + counter + "' value='true'/>Cobrar" +
				              "</td>" +
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='faltante_" + counter + "' value='true'/>Faltante" +
				              "</td>" + 
				              "</tr>" +
				              "</table>";
				c.align = "center";


				d.innerHTML = "<a href='javaScript:deleteRow(" + counter + ")'>" +
							  "<img src='../images/cancelar.jpg' border='0' alt='Remover Nota'/>" +
							  "</a>";
				d.align = "center";

				document.forms[0].counter.value = ++counter;
			
			}
		
			function addFilledRow(peca, qtd, enviar, cobrar, faltante) {
			
				var table = document.getElementById("values");
				var index = table.rows.length;
				var counter = document.forms[0].counter.value;
				
				var enviarCheck = "";
				if (enviar) { enviarCheck = "checked"; }

				var cobrarCheck = "";
				if (cobrar) { cobrarCheck = "checked"; }

				var faltanteCheck = "";
				if (faltante) { faltanteCheck = "checked"; }

				var line = table.insertRow(index);
				line.id = counter;
				line.className = "tr_line";
				var a = line.insertCell(0);
				var b = line.insertCell(1);
				var c = line.insertCell(2);
				var d = line.insertCell(3);
				
				a.innerHTML = "<select name='identPeca_" + counter + "' id='identPeca_" + counter + "' style='font-family:Verdana; font-size:10px;'>" + getOptionsWithSelectedIndex(peca) + "</select>";
				a.align = "center";

				b.innerHTML = "<input type='text' name='qtdPeca_" + counter + "' id='qtdPeca_" + counter + "' size='4' maxlength='4' style='font-family:Verdana; font-size:10px;' onKeyPress='return blockChar(event);' value='" + qtd + "' onBlur='validateLine(" + counter + ");' />";
				b.align = "center";

				c.innerHTML = "<table border='0' cellpadding='0' cellspacing='0'>" +
				              "<tr>" +
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='enviar_" + counter + "' " + enviarCheck + " value='true'/>Enviar" +
				              "</td>" +
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='cobrar_" + counter + "' " + cobrarCheck + " value='true'/>Cobrar" +
				              "</td>" +
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='faltante_" + counter + "' " + faltanteCheck + " value='true'/>Faltante" +
				              "</td>" + 
				              "</tr>" +
				              "</table>";
				c.align = "center";


				d.innerHTML = "<a href='javaScript:deleteRow(" + counter + ")'>" +
							  "<img src='../../images/cancelar.jpg' border='0' alt='Remover Nota'/>" +
							  "</a>";
				d.align = "center";

				document.forms[0].counter.value = ++counter;
			
			}		
		
			function deleteRow(index) {
			
				if ( window.confirm("Confirma exclusão da peça?") ) {
				
					var table = document.getElementById("values");
					table.deleteRow( document.getElementById(index).rowIndex );

				}
			
			}
		
			function initialize() {
			
				<%
					
					if ( garantiaPecaMotoForm.get("filledLines") != null ) {
	
						List filledLines = (List) garantiaPecaMotoForm.get("filledLines");
						if ( filledLines.size() > 0 ) {
							
							Iterator filledLinesIt = filledLines.iterator();
							while ( filledLinesIt.hasNext() ) {
								
								PecaTO peca = (PecaTO) filledLinesIt.next();
								
								String enviar = peca.isEnviar() ? "true" : "false";
								String cobrar = peca.isCobrar() ? "true" : "false";
								String faltante = peca.isFaltante() ? "true" : "false";
								
								
								out.println("addFilledRow('" + peca.getDescricao() + "', " + peca.getQuantidade() + ", " + enviar + ", " + cobrar + ", " + faltante + ");");
								
							}
							
						} else {
							
							out.println("addRow();");
							
						}
						
					} else {
						
						out.println("addRow();");
						
					}
				
				%>
				
			}
		
		</script>
		
	</head>
	<body leftmargin="0" topmargin="3" onload="javaScript:initialize();">

		<center>

			<html:form action="/GarantiaPecaMoto" method="post">
	
				<html:hidden property="counter" styleId="counter"/>
				<html:hidden property="entityId" styleId="entityId"/>
				<html:hidden property="chassi" styleId="chassi"/>
				<html:hidden property="loteId" styleId="loteId"/>
		
				<jsp:include flush="true" page="../include_alerts.jsp"/>
		
				<table width="98%" border="0" cellpadding="0" cellspacing="2" align="center">
					<tr> 
						<td height="15" colspan="2" align="center" class="tilteList">
							<bean:message key="garantia.form.peca.title.add"/>
						</td>
					</tr>			
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.peca.numSG"/>:
						</td>
						<td width="75%" class="text">	
							<bean:write name="garantiaPecaMotoForm" property="entityId"/>
						</td>
					</tr>
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.peca.desc"/>:
						</td>
						<td width="75%" class="text" valign="middle">
							<table border="0" cellpadding="0" cellspacing="1">
								<tr>
									<td><html:textarea property="descricaoDefeito" styleId="" cols="50" rows="5" style='font-family:Verdana; font-size:10px;'/></td>
									<td valign="middle"><html:img srcKey="form.moto.img.required" altKey="form.msg.required"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			
				<p/>
			
				<table id="values" width="98%" border="0" cellpadding="0" cellspacing="2" align="center">
					<tr> 
						<td height="15" colspan="4" align="center" class="tilteList">
							<bean:message key="garantia.form.peca.pecas"/>
						</td>
					</tr>
					<tr> 
						<td width="22%" class="td_dark" align="center">
							<bean:message key="garantia.form.peca.peca"/>
						</td>
						<td width="22%" class="td_dark" align="center">	
							<bean:message key="garantia.form.peca.qtd"/>
						</td>
						<td width="50%" class="td_dark" align="center">	
							&nbsp;
						</td>
						<td width="4%" class="td_lixeira" align="center"></td>
					</tr>
				</table>	

				<p/>
		
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td height="10" class="text" align="right">
							<input type="button" class="button_medium" name="lancar" value="Mais Peças" onclick="javascript:addRow();"/>					
						</td>			
					</tr>
				</table>
					
				<p/>
				
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td height="10" class="text" align="center" colspan="5">
							<input type="button" class="button_medium" name="salvar" value="Salvar" onclick="submitFormTsk(garantiaPecaMotoForm,'save',validateGarantiaPecaMotoForm(garantiaPecaMotoForm))"/><%--submitFormTsk(garantiaPecaForm,'save',validateGarantiaPecaForm(garantiaPecaForm)) --%>
							&nbsp;
							<html:button property="cancelar" styleClass="button_medium" onclick="javascript:window.top.Windows.close(window.top.Windows.focusedWindow.getId());"><bean:message key="form.btn.cancel"/></html:button>
						</td>
					</tr>
				</table>
	
			</html:form>

		</center>
		<ym:javaScriptExecuter/>
	</body>
	
</html:html>