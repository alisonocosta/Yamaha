<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	   %>
<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"     prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"	   %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		   prefix="ajax"  %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......garantia_form_recall.jsp
 *   .: Criação.....27 de julho de 2007, 16:31
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de garantia para recall.
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
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>		
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script type="text/javascript">
			
			function voltar() {
				window.top.location.href ="javascript:newTitle('Relação de Garantias');";
				window.location.href = "Garantia.do?task=list&loteId=" + window.document.garantiaForm.lote.value;
			}
		
			function setTitle() {
			
				window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia - Recall');";
			
			}
			
			function save() {
			
				submitFormTsk(window.document.garantiaForm,"save",true);
			
			}
			
			function saveRecall() {
			
				submitFormTsk(window.document.garantiaForm,"saveRecall",true);
			
			}
			
			function cancel() {
				
				window.document.garantiaForm.validated.value = "false";
				
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
    		    
    		    		 submitFormTsk(form,"save",true); 
    		    	
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
			
			/** Este método trata a apresentação da descrição do valor selecionado nos objetos 
			 *  select do form.
			 */
			function showLabel(campoId,objId) {
			
				var campo  = window.document.getElementById(campoId);
				var objeto = window.document.getElementById(objId);
				
				var index  = campo.selectedIndex;
				var code   = campo.options[index].text;				
				
				/** Caso o valor selecionado seja vazio, devemos ocultar a mensagem */
				if ( campo.options[index].value == 0 ){
				
					Element.hide(objId);	
					
				} else {
				
					/** Verificamos de qual objeto veio a solicitação e exibimos a descrição correspondente 
					 *   E aqui tratamos a seleção dos objetos de Serviços 
					 */			
					if ( objId == "s1" || objId == "s2" || objId == "s3" ) {
				
						<logic:iterate name="garantiaForm" property="listServicos" id="service">
						
							if ( code == "<core:out value='${service.servicoCode}'/>" ) {
							
								objeto.innerHTML = "<core:out value='${service.descricao}'/>";
								Element.show(objId);
							
							}
						
						</logic:iterate>							
							
					}									
					
				}	
			
			}
			
			/** Função para apresentar e ocultar os campos de valor de terceiros */
			function enableServTer() {
			
				var layer_servTer	= window.document.getElementById("servTer_div"); 
				
				if ( window.document.garantiaForm.hasServTer[0].checked ) { 
				
					layer_servTer.style.display = ""; 
					window.document.getElementById("servTer").focus();
				
				} else if ( window.document.garantiaForm.hasServTer[1].checked ) { 
					
					window.document.getElementById("servTer").value = "";
					window.document.getElementById("justif").value  = "";
					layer_servTer.style.display = "none"; 
				
				}			
			
			}
			
			/** Função que habilita ou não a digitação do número da cortesia */
			function enableCortesia() {
				
				if ( window.document.garantiaForm.cortesia[0].checked ) { 
					
					window.document.getElementById("autEsp").readOnly = false;
					window.document.getElementById("autEsp").focus();
				
				} else if ( window.document.garantiaForm.cortesia[1].checked ) { 
					
					window.document.getElementById("autEsp").value = "";
					window.document.getElementById("autEsp").readOnly = true;
				
				}
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="3">	
		
		<html:form action="/Garantia" method="post" focus="chassi">			
			<html:hidden property="entityId"/>
			<html:hidden property="lote"/>	
			<html:hidden property="validated"/>	
			<html:hidden property="isRecall"/>
			<jsp:include flush="true" page="include_alerts.jsp"/><br>
								
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr> 
				<td height="5" colspan="2">
														
					<table width="100%" border="0" cellpadding="2" cellspacing="2" style="border: 1 1 1 1 solid #788C9B;">
					<tr height="25"> 
						<td width="8%"  class="td_dark">	
							<bean:message key="garantia.form.sg.loteNum"/>:
						</td>
						<td width="15%" class="text">	
							<bean:write name="garantiaForm" property="lote"/>
						</td>
						<td width="9%" class="td_dark">	
							<bean:message key="garantia.form.sg.sgNum"/>:
						</td>
						<td width="10%" class="text">	
							<bean:write name="garantiaForm" property="entityId"/>&nbsp;
						</td>
						<td width="8%" class="td_dark">	
							<bean:message key="garantia.form.sg.chassi"/>:
						</td>
						<td class="text" colspan="3">
							<html:text property="chassi" styleClass="text_field_maior" maxlength="13" readonly="true"></html:text>							
						</td>
					</tr>
					<tr height="25"> 
						<td width="8%"  class="td_dark">	
							<bean:message key="garantia.form.sg.dtVenda"/>:
						</td>
						<td class="text" width="17%">	
							<html:text property="dtVenda" styleClass="text_field_date" readonly="true"></html:text>
							
						</td>
						<td width="9%" class="td_dark">	
							<bean:message key="garantia.form.sg.numOS"/>:
						</td>
						<td width="10%" class="text">	
							<html:text property="numOS" styleClass="text_field_maior" maxlength="10"></html:text>
						</td>
						<td width="8%" class="td_dark">	
							<bean:message key="garantia.form.sg.dtAbert"/>:
						</td>
						<td class="text">	
							<ym:inputDate icon="../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="dtAbert"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 fieldYears		="4"
								 tabIndex       ="1000"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="garantiaForm" property="dtAbert"/>						 
							</ym:inputDate> &nbsp;
							<img src="../images/icon_required.gif" alt="form.msg.required"/> 
						</td>
						<td width="8%" class="td_dark">	
							<bean:message key="garantia.form.sg.dtFech"/>:
						</td>
						<td class="text">	
							<ym:inputDate icon="../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="dtFech"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 fieldYears		="4"
								 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="garantiaForm" property="dtFech"/>
							</ym:inputDate> &nbsp;
							<img src="../images/icon_required.gif" alt="form.msg.required"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<core:if test="${idTipo == 1}">			
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.km"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="km" styleClass="text_field_menor" maxlength="10"/>
						<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					</td>
				</tr>
			</core:if>			
			<core:if test="${idTipo == 2 or idTipo == 3 or idTipo == 4}">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.diasUso"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="diasUso" styleClass="text_field_menor" maxlength="10"/>
						<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					</td>
				</tr>
			</core:if>
			<core:if test="${idTipo == 2 or idTipo == 3}">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.hrsUso"/>:
					</td>
					<td width="75%" class="text">
						<html:text property="horasUso" styleClass="text_field_menor" maxlength="10"/>
						<img src="../images/icon_required.gif" alt="form.msg.required"/>  
					</td>
				</tr>
			</core:if>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.cortesia"/>:
				</td>
				<td width="75%" class="text">													
					<input type="radio" name="cortesia" value="S" onclick="enableCortesia();"/>&nbsp; 
					<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
					<input type="radio" name="cortesia" value="N" checked="checked" onclick="enableCortesia();"/>&nbsp; 
					<bean:message key="form.msg.no"/>&nbsp;&nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.numCortesia"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="autEsp" styleClass="text_field_menor"  maxlength="10" readonly="true"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.sintoma"/>:
				</td>
				<td width="75%" class="text">	
					<html:hidden property="sintomaIdRecall"/>
					<b><bean:write name="garantiaForm" property="sintomaDescRecall"/></b>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.condicao"/>:
				</td>
				<td width="75%" class="text">
					<html:hidden property="condicaoIdRecall"/>
					<b><bean:write name="garantiaForm" property="condicaoDescRecall"/></b>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.servico_1"/>:
				</td>
				<td width="75%" class="text">
					<html:select property="servico_1" styleClass="listSelectCode" onchange="showLabel('servico_1','s1');">
						<html:option value=""></html:option>
						<html:optionsCollection property="listServicos" value="entityId" label="servicoCode" />
					</html:select> &nbsp; 
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  &nbsp;
					<span id="s1"  style="display:none;" class="text"></span>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.servico_2"/>:
				</td>
				<td width="75%" class="text">
					<html:select property="servico_2" styleClass="listSelectCode" onchange="showLabel('servico_2','s2');">
						<html:option value=""></html:option>
						<html:optionsCollection property="listServicos" value="entityId" label="servicoCode" />
					</html:select> &nbsp; 
					<img src="../images/icon_required.gif" alt="form.msg.required"/> &nbsp;
					<span id="s2"  style="display:none;" class="text"></span>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.servico_3"/>:
				</td>
				<td width="75%" class="text">
					<html:select property="servico_3" styleClass="listSelectCode" onchange="showLabel('servico_3','s3');">
						<html:option value=""></html:option>
						<html:optionsCollection property="listServicos" value="entityId" label="servicoCode" />
					</html:select> &nbsp; 
					<img src="../images/icon_required.gif" alt="form.msg.required"/> &nbsp;
					<span id="s3"  style="display:none;" class="text"></span> 
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.has_servTer"/>
				</td>
				<td width="75%" class="text">	
					<input type="radio" name="hasServTer" value="S" onclick="enableServTer();"/>&nbsp; 
					<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
					<input type="radio" name="hasServTer" value="N" checked="checked" onclick="enableServTer();"/>&nbsp; 
					<bean:message key="form.msg.no"/>&nbsp;&nbsp;
					<img src="../images/icon_required.gif" alt="form.msg.required"/>  
				</td>
			</tr>
			</table>
			<div id="servTer_div" style="display:none;">	
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">	
					<tr height="25"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.servTer"/>:
						</td>
						<td width="75%" class="text">	
							<html:hidden property="servTerUnFormat"/>
							<html:text property="servTer" styleClass="text_field_menor" maxlength="10" onblur="validateVST('servTer')"/>
						</td>
					</tr>
					<tr height="50"> 
						<td width="25%" class="td_dark">	
							<bean:message key="garantia.form.sg.justif"/>:
						</td>
						<td width="75%" class="text">	
							<html:textarea property="justif" styleClass="text_field_menor" rows="5"/>
						</td>
					</tr>
				</table>	
			</div>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.numeroIT"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="recallId" styleClass="text_field_menor" readonly="true"></html:text>
						  					
				</td>
			</tr>	
			<tr> 
				<td height="15">&nbsp;</td>
			</tr>
			<tr height="25">
				<td colspan="2" align="center">
					<%--<html:button property="gravar" styleClass="button_medium"  onclick="javascript:return gravarForm(garantiaForm)"><bean:message key="form.btn.save"/></html:button>	--%>
					<html:button property="gravar" styleClass="button_medium"  onclick="javascript:window.alert('Em Construção!')"><bean:message key="form.btn.save"/></html:button>
					&nbsp;
					<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>						
					&nbsp;
					<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>		
				</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><img src="../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
			</tr>
			</table>
			
		</html:form>
		<script type="text/javascript">
			enableCortesia();
			enableServTer();
			showLabel('servico_1','s1');
			showLabel('servico_2','s2');
			showLabel('servico_3','s3');
		</script>
		<ym:javaScriptExecuter/>
		<ym:confirmMessage />
		<ym:alertMessage />
	</body>
</html>