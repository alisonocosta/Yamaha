<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="core" 	   %>
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"  %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......info_mercado_form.jsp
 *   .: Criação.....01 de julho de 2007, 17:02
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de infromações de mercado.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="garantiaForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>		
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>		
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script type="text/javascript">
		
			function save() {
			
				submitFormTsk(window.document.infoMercadoForm,"save",true);
			
			}
			
			function cancel() {
				
				window.document.infoMercadoForm.validated.value = "false";
				
			}
			
			function storeChassi() {
					
					var chassiFieldSource   = window.document.infoMercadoForm.chassi;
					var chassiFieldTarget   = window.document.infoMercadoForm.escapedChassi;			
					chassiFieldTarget.value = escape(chassiFieldSource.value);
					
			}
			
			function initProgress() {
				Element.hide('msg');
				Element.show('indicator');	
			}
			
			function finalProgress() {
			
				Element.hide('indicator');				
				if (window.document.infoMercadoForm.itemId.options.length == 0 ) {
					window.document.infoMercadoForm.itemId.disabled = true;					
				} else {
					window.document.infoMercadoForm.itemId.disabled = false;
				}
							
			}
						
			function reportError() {
			
				if (window.document.infoMercadoForm.itemId.options.length == 0) {
					window.document.getElementById("msg").innerHTML = "Erro - Valores não Encontrados!";
				}
				Element.show('msg');
				setTimeout("Element.hide('msg')", 2500); 
			}
			
			function gravarForm(form) {
			
    			var i;
    			var value   = "N"; 
    			var vData1  = form.dtAbert.value != "" ? true : false ;
    			var vData2  = form.dtFech.value != "" ? true : false ;
    			var data1   = form.dtAbert.value;
    			var data2   = form.dtFech.value;
    			
    			if ( vData1  && vData2 ) {
    			
	    			if ( comparaData( data1, data2 ) == 1 ) {
	    				
	    				window.alert("A data de fechamento deve ser maior que a data de Abertura!");
	    				form.dtFech.value = "";
	    				form.dtFech.focus();
	    				return false;
	    			
	    			}
	    		} else {
	    			if ( !vData1 ) {
	    				window.alert("A data de Abertura é obrigatória!");
	    				form.dtAbert.value = "";
	    				form.dtAbert.focus();
	    			} else if ( !vData2 ) {
	    				window.alert("A data de fechamento é obrigatória!");
	    				form.dtFech.value = "";
	    				form.dtFech.focus();
	    				
	    			}
	    			return false;
	    		}
    		    
    		    if ( validateGarantiaForm(form) ) {  	    
    		    	
    		    	if ( value == "N" )
    		    		 submitFormTsk(form,"save",true);
    		    	else {
    		    		 submitFormTsk(form,"saveRecall",true); 
    		    	}
    		    	
    		    } else 
    		    	return false;
			}
			
			function validateVST() {
				
				var valor = window.document.getElementById("servTer").value;
			
				var valorUnformat = clearFormat(valor);
				 
				if ( valor != "") {
					if (isNaN(valorUnformat) ) {
						window.alert("Valor incorreto!");
						window.document.getElementById("servTer").focus();
					} else {
						window.document.getElementById("servTerUnFormat").value = valorUnformat;
					}
				}
			}
			
			function clearFormat( valor ) {
				var val = new String();
				val = valor.toString().replace(".","");
				val = val.toString().replace(",",".");
				
				return val;
			
			}
			
			function comparaData( data1, data2 ) {
			
				var ano1 = data1.substring( 6 );
				var mes1 = data1.substring(3,5);
				var dia1 = data1.substring(0,2);
				var data1Str = ano1 + mes1 + dia1
				var ano2 = data2.substring( 6 );
				var mes2 = data2.substring(3,5);
				var dia2 = data2.substring(0,2);
				var data2Str = ano2 + mes2 + dia2;
				if( data1Str == data2Str ) {
					return 0;
				}
				if( data1Str > data2Str ) {
					/* Alert( 'data1 maior que data2' );*/
					return 1;
				}
				if( data1Str == data2Str ) {
					/* alert( 'data1 igual a data2' );*/
					return 0;
				}	
				if( data1Str < data2Str ) {
					/* alert( 'data1 menor que data2' ); */
					return -1;
				}	
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="1">	
		
		<html:form action="/InfoMercado" method="post" focus="chassi">			
			<html:hidden property="entityId"/>
			<html:hidden property="validated"/>
			<jsp:include flush="true" page="include_alerts.jsp"/>					
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr> 
				<td height="5" colspan="2">&nbsp;</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.tipo.produto"/>:
				</td>
				<td width="75%" class="text">
					<logic:iterate name="infoMercadoForm" property="listTipoProduto" id="tipoProduto">
				
						<html-el:radio property="tipoProdutoId" value="${tipoProduto.entityId}"/> 
						<core:out value="${tipoProduto.descTipoProd}"/> <br>		
					
					</logic:iterate>					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.chassi"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="chassi" styleClass="text_field_menor" maxlength="12" onkeyup="javaScript:storeChassi()"></html:text>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
					<span id="indicator" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>
					<input type="hidden" name="escapedChassi" value="null"/>
					<script type="text/javascript">
						new AjaxJspTag.Autocomplete(
							"<%= request.getContextPath() %>/DeterminaChassi.do", {
								minimumCharacters: "5",
								parameters: "chassi={escapedChassi}", 
								target: "chassi",
								className: "autocomplete",
								source: "chassi",
								indicator: "indicator"
							});
					</script>  
				</td>
			</tr>
			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.total.horas"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="totalHrs" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.data.venda"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataVenda"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 fieldYears		="4">
						 <bean:write name="infoMercadoForm" property="dataVenda"/>						 
					</ym:inputDate> &nbsp;
					<bean:message key="garantia.form.sg.dt.default"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.data.problema"/>:
				</td>
				<td width="75%" class="text">	
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dataProblema"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 fieldYears		="4">
						 <bean:write name="infoMercadoForm" property="dataProblema"/>						 
					</ym:inputDate> &nbsp;
					<bean:message key="garantia.form.sg.dt.default"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.tipo.gasolina"/>:
				</td>
				<td width="75%" class="text">
					<logic:iterate name="infoMercadoForm" property="listTipoGasolina" id="tipoGasolina">
				
						<html-el:radio property="tipoGasolinaId" value="${tipoGasolina.entityId}"/> 
						<core:out value="${tipoGasolina.descricao}"/> <br>		
					
					</logic:iterate>					
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.local.uso"/>:
				</td>
				<td width="75%" class="text">
					<html-el:checkbox property="localUsoSalg" value="S"/><bean:message key="infoMercado.form.agua.salgada"/>&nbsp;
					<html-el:checkbox property="localUsoDoce" value="D"/><bean:message key="infoMercado.form.agua.doce"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.cidade.uso"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="cidadeUso" styleClass="text_field_menor" maxlength="30"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.tipo.uso"/>:
				</td>
				<td width="75%" class="text">
					<logic:iterate name="infoMercadoForm" property="listTipoUso" id="tipoUso">
				
						<html-el:radio property="tipoUsoId" value="${tipoUso.entityId}"/> 
						<core:out value="${tipoUso.descricao}"/> <br>		
					
					</logic:iterate>					
				</td>
			</tr>			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.casco.util"/>:
				</td>
				<td width="75%" class="text">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td width="10%" class="text">
							<bean:message key="infoMercado.form.casco.marca"/>:
						</td>
						<td width="40%" class="text" align="left">
							<html:text property="marcaCasco" styleClass="text_field_maior" maxlength="20"/>
						</td>
						<td width="10%" class="text">
							<bean:message key="infoMercado.form.casco.modelo"/>:
						</td>
						<td width="40%" class="text" align="left">
							<html:text property="modeloCasco" styleClass="text_field_maior" maxlength="20"/>
						</td>
					<tr>
					</table>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.helice"/>:
				</td>
				<td width="75%" class="text">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10%" class="text">
							<bean:message key="infoMercado.form.helice.marca"/>:
						</td>
						<td width="40%" class="text">
							<html:text property="marcaHelice" styleClass="text_field_maior" maxlength="20"/>
						</td>
						<td width="10%" class="text">
							<bean:message key="infoMercado.form.casco.modelo"/>:
						</td>
						<td width="40%" class="text">
							<html:text property="passoHelice" styleClass="text_field_maior" maxlength="20"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.helice.material"/>:
				</td>
				<td width="75%" class="text">					
					<html:radio property="materHelice" value="I"/><bean:message key="infoMercado.form.helice.material.inox"/>&nbsp;&nbsp;
					<html:radio property="materHelice" value="A"/><bean:message key="infoMercado.form.helice.material.alum"/>&nbsp;&nbsp;
					<html:radio property="materHelice" value="P"/><bean:message key="infoMercado.form.helice.material.plas"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.peca.causadora"/>:
				</td>
				<td width="75%" class="text">	
					<div id="msg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 11px Arial;color:#900"></div>
					<html:select property="itemId" styleClass="listSelect">
						<html:option value="">&nbsp;</html:option>
					</html:select>	
					<ajax:select
							  baseUrl="${pageContext.request.contextPath}/LoadItens.do"
							  source="chassi"
							  target="itemId"	
							  eventType="blur"						  
							  parameters="chassi={chassi}"
							  preFunction="initProgress"
							  postFunction="finalProgress"
							  errorFunction="reportError"/>	
				</td>
			</tr>	
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="infoMercado.form.rotacao.maxima"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="rotacaoMaxima" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="tr_line" colspan="2" align="center">	
					<bean:message key="infoMercado.form.msg.sintoma"/><img src="../images/icon_required.gif" alt="form.msg.required"/>  
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" colspan="2">	
					<bean:message key="infoMercado.form.condicao"/><br>
					<html:textarea property="condicao" styleClass="text_field_maior" rows="5"></html:textarea>					
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="tr_line" colspan="2" align="center">	
					<bean:message key="infoMercado.form.msg.causa"/><img src="../images/icon_required.gif" alt="form.msg.required"/>  
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" colspan="2">	
					<bean:message key="infoMercado.form.causa"/><br>
					<html:textarea property="causa" styleClass="text_field_maior" rows="5"></html:textarea>					
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="tr_line" colspan="2" align="center">	
					<bean:message key="infoMercado.form.msg.diagnostico"/><img src="../images/icon_required.gif" alt="form.msg.required"/>  
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" colspan="2">	
					<bean:message key="infoMercado.form.diagnostico"/><br>
					<html:textarea property="diagnostico" styleClass="text_field_maior" rows="5"></html:textarea>					
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" colspan="2">	
					<bean:message key="infoMercado.form.acao.tomada"/>
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" colspan="2">	
					<html:textarea property="acaoTomada" styleClass="text_field_maior" rows="5"></html:textarea>
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="tr_line" colspan="2">	
					<bean:message key="infoMercado.form.analise"/>
				</td>				
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" colspan="2">	
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="text" colspan="4">
							<html:textarea property="analise" styleClass="text_field_maior" rows="5"></html:textarea><br>
						</td>
					</tr>
					<tr>
						<td class="text" width="15%">										
							<bean:message key="infoMercado.form.tipo.problema"/>
						</td>
						<td class="text" width="35%">
							<html:text property="tipoProblema" styleClass="text_field_menor" maxlength="20"/>
						</td>
						<td class="text" width="15%">
							<bean:message key="infoMercado.form.gravidade"/>
						</td>
						<td class="text" width="35%">
							<html:text property="gravidade" styleClass="text_field_menor" maxlength="20"/>					
						</td>
					</tr>
					</table>
				</td>				
			</tr>
			<tr> 
				<td height="15">&nbsp;</td>
			</tr>
			<tr height="25">
				<td colspan="2" align="center">
					<html:button property="gravar" styleClass="button_medium"  onclick="javascript:return gravarForm(garantiaForm)"><bean:message key="form.btn.save"/></html:button>	
					&nbsp;
					<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
					&nbsp;
					<html:button property="cancelar" styleClass="button_medium" onclick="javascript:window.top.Windows.close(window.top.Windows.focusedWindow.getId());"><bean:message key="form.btn.cancel"/></html:button>	
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
		<ym:confirmMessage />
		<ym:alertMessage />
	</body>
</html>