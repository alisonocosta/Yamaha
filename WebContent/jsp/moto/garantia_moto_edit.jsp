<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="core"    %>
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......garantia_edit.jsp
 *   .: Criação.....28 de junho de 2007, 10:57
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de edição de  garantia.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="garantiaMotoForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../../css/autocomplete/autocomplete.css"></link>
		<script language="JavaScript" src="../../scripts/General/form.js"></script>
		<script language="JavaScript" src="../../scripts/Formatter/cpfCnpj.js"></script>		
		<script language="JavaScript" src="../../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script type="text/javascript">
		
			var alterState = "<bean:write name='garantiaMotoForm' property='alterState'/>";
			
			/** Confirma se usuário quer remover um serviço
			 *  e rediciona para o método da action pra remover
			 *  parâmetro: seq - número da seqüência
			 */
			function removeService(servicoCode) {
				
				if ( window.confirm( "Confirma a remoção do serviço nº " + servicoCode  + "?" ) ) {
					
					var garantiaId = window.document.garantiaMotoForm.entityId.value;
				
					window.location.href = "/ym_garantia_ccs/GarantiaMoto.do?task=removeService&garantiaId=" + garantiaId + "&servicoCode=" + servicoCode;
				
				} 
			
			}
			
			function voltar() {
				window.top.location.href ="javascript:newTitle('Relação de Garantias');";
				window.location.href = "/ym_garantia_ccs/GarantiaMoto.do?task=list&loteId=" + window.document.garantiaMotoForm.lote.value;
			}
			
			function save() {
			
				submitFormTsk(window.document.garantiaMotoForm,"save",true);
			
			}
			
			function saveRecall() {
			
				submitFormTsk(window.document.garantiaMotoForm,"saveRecall",true);
			
			}
			
			function cancel() {
				
				window.document.garantiaMotoForm.validated.value = "false";
				
			}
			
			function gravarMotoForm(form) {
			
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
	    		
	    		for (i=0;i<window.document.garantiaMotoForm.recall.length;i++){ 
    			
       				if (window.document.garantiaMotoForm.recall[i].checked) {
       				
          				value = window.document.garantiaMotoForm.recall[i].value; 
          				
    				} 
    				
    		    }
    		    
    		    if ( validategarantiaMotoForm(form) ) { 
    		    	if ( value == "N" )
    		    		 submitFormTsk(form,"save",true);
    		    	else {
    		    		 submitFormTsk(form,"saveRecall",true); 
    		    	}
    		    	
    		    } else 
    		    	return false;
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
			
			function enableNumber() {
			
				if ( window.document.garantiaMotoForm.recall[0].checked ) {
					hiddenFields(); 
				} else if ( window.document.garantiaMotoForm.recall[1].checked ) {
					showFields();
				}
							
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
			
			function showFields() {
				
				var layer_1	= window.document.getElementById("isRecall_1"); 
				var layer_2	= window.document.getElementById("isRecall_2"); 
   				layer_1.style.display = "";  
   				layer_2.style.display = "";  				
			
			}
						
			/** Oculta os campos do div recall */
			function hiddenFields() {			
			
				var layer_1	= window.document.getElementById("isRecall_1"); 
				var layer_2	= window.document.getElementById("isRecall_2"); 
   				layer_1.style.display = "none";  
   				layer_2.style.display = "none"; 
   				
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="3">	
		
		<html:form action="/GarantiaMoto" method="post">			
			<html:hidden property="entityId" styleId="entityId"/>
			<html:hidden property="idTipo" styleId="idTipo"/>
			<html:hidden property="lote" styleId="lote"/>	
			<html:hidden property="validated" styleId="validation"/>	
			<html:hidden property="chassi" styleId="chassi"/>	
			<html:hidden property="isEdit" styleId="isEdit"/>
			<html:hidden property="alterState" styleId="alterState"/>
			<input type="hidden" name="qtdeServicos" id="qtdeServicos" value="<core:out value='${qtdeServicos}'/>">
			<jsp:include flush="true" page="../include_alerts.jsp"/>					
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr> 
				<td height="5" colspan="2">&nbsp;</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.loteNum"/>:
				</td>
				<td width="75%" class="text">	
					<bean:write name="garantiaMotoForm" property="lote"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.chassi"/>:
				</td>
				<td width="75%" class="text">				
					<bean:write name="garantiaMotoForm" property="chassi"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.recall"/>:
				</td>
				<td width="75%" class="text">													
					<html:radio property="recall" styleId="recall" value="S" disabled="true"/>&nbsp; 
					<bean:message key="form.msg.yes"/>	&nbsp;&nbsp;	
					<html:radio property="recall" styleId="recall" value="N" disabled="true"/>&nbsp; 
					<bean:message key="form.msg.no"/>  &nbsp;&nbsp; 
				</td>
			</tr>
			<logic:equal name="garantiaMotoForm" property="recall" value="S">
			
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.numeroIT"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="numeroIT" styleClass="text_field_menor" readonly="true"/>
					<bean:message key="garantia.form.sg.recall"/>
				</td>
			</tr>
			</logic:equal>
			<logic:equal name="garantiaMotoForm" property="idTipo" value="1">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.km"/>:
					</td>
					<td width="75%" class="text">
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">
							<html:text property="km" styleId="km" styleClass="text_field_menor" disabled="true"/>
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html:text property="km" styleId="km" styleClass="text_field_menor"/>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
						</logic:equal>
					</td>
				</tr>
			</logic:equal>
			
			<logic:equal name="garantiaMotoForm" property="idTipo" value="2">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.diasUso"/>:
					</td>
					<td width="75%" class="text">
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">
							<html:text property="diasUso" styleId="diasUso" styleClass="text_field_menor" maxlength="10" disabled="true"/>
							
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html:text property="diasUso" styleId="diasUso" styleClass="text_field_menor" maxlength="10"/>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
						</logic:equal>
					</td>
				</tr>
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.hrsUso"/>:
					</td>
					<td width="75%" class="text">
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">
							<html:text property="horasUso" styleId="horasUso" styleClass="text_field_menor" maxlength="10" disabled="true"/>
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html:text property="horasUso" styleId="horasUso" styleClass="text_field_menor" maxlength="10"/>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
						</logic:equal>
					</td>
				</tr>
			</logic:equal>
			</table>	
			<%--  
			 *  Campos que devem ser apresentados apenas quando a não for recall
			 *  Para todas as linhas de Produtos			
			 --%>
			<div id="isRecall_1">	
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.sintoma"/>:
					</td>
					<td width="75%" class="text">	
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">
							<html:select property="sintoma" styleId="sintoma" styleClass="listSelect" disabled="true">
								<html:optionsCollection property="listSintomas" value="entityId" label="descricaoCode" />
							</html:select>
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html:select property="sintoma" styleId="sintoma" styleClass="listSelect" >
								<html:option value="" ></html:option>
								<html:optionsCollection property="listSintomas" value="entityId" label="descricaoCode" />
							</html:select>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
						</logic:equal>
					</td>
				</tr>
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.condicao"/>:
					</td>
					<td width="75%" class="text">
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">
							<html:select property="condicao" styleId="condicao" styleClass="listSelect" disabled="true">
								<html:optionsCollection property="listCondicoes" value="entityId" label="descricaoCode" />
							</html:select>
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html:select property="condicao" styleId="condicao" styleClass="listSelect" >
								<html:option value=""></html:option>
								<html:optionsCollection property="listCondicoes" value="entityId" label="descricaoCode" />
							</html:select>
							<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
						</logic:equal>
					</td>
				</tr>
				
				</table>
			</div>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.autEsp"/>:
				</td>
				<td width="75%" class="text">	
					<logic:equal name="garantiaMotoForm" property="alterState" value="true">
						<html-el:select property="autEsp" styleId="autEsp" styleClass="listSelect" disabled="true">
							<html:optionsCollection property="listAut" value="entityId" label="numAutorizacao" />
						</html-el:select>
					</logic:equal>
					<logic:equal name="garantiaMotoForm" property="alterState" value="false">
						<div id="msg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 11px Arial;color:#900"></div>
						<html-el:select property="autEsp" styleId="autEsp" styleClass="listSelect">
							<html-el:option value="">Selecione</html-el:option>
							<html:optionsCollection property="listAut" value="entityId" label="numAutorizacao" />
						</html-el:select>
					</logic:equal>						
				</td>
			</tr>	
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.dtAbert"/>:
				</td>
				<td width="75%" class="text">	
					<logic:equal name="garantiaMotoForm" property="alterState" value="true">
						<ym:inputDate icon="../../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dtAbert"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 fieldYears		="4"
							 disabled       = "true">
							 <bean:write name="garantiaMotoForm" property="dtAbert"/>						 
						</ym:inputDate> &nbsp;
					</logic:equal>
					<logic:equal name="garantiaMotoForm" property="alterState" value="false">
						<ym:inputDate icon="../../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dtAbert"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 fieldYears		="4">
							 <bean:write name="garantiaMotoForm" property="dtAbert"/>						 
						</ym:inputDate> &nbsp;
						<img src="../../images/icon_required.gif" alt="form.msg.required"/>  
						<bean:message key="garantia.form.sg.dt.default"/>
					</logic:equal>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="garantia.form.sg.dtFech"/>:
				</td>
				<td width="75%" class="text">	
					<logic:equal name="garantiaMotoForm" property="alterState" value="true">
						<ym:inputDate icon="../../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dtFech"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 fieldYears		="4"
							 disabled       = "true">
							 <bean:write name="garantiaMotoForm" property="dtFech"/>
						</ym:inputDate> &nbsp;
					</logic:equal>
					<logic:equal name="garantiaMotoForm" property="alterState" value="false">
						<ym:inputDate icon="../../images/icon_calendar.gif" 
			                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                 dayHeaders		="D|S|T|Q|Q|S|S"
							 todayLabel		="Hoje"
							 fieldName		="dtFech"
							 fieldClass		="text_field_date"
							 datePattern	="dd/MM/yyyy"
							 fieldLength	="11"
							 fieldYears		="4">
							 <bean:write name="garantiaMotoForm" property="dtFech"/>
						</ym:inputDate> &nbsp;
						<img src="../../images/icon_required.gif" alt="form.msg.required"/>
						<bean:message key="garantia.form.sg.dt.default"/>  
					</logic:equal>
				</td>
			</tr>
			</table>
			<%--  
			 *  Campos que devem ser apresentados apenas quando a não for recall
			 *  Para todas as linhas de Produtos			
			 --%>
			<div id="isRecall_2">	
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
				<tr height="25"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.servTer"/>:
					</td>
					<td width="75%" class="text">
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">
							<html-el:text property="servTer" styleId="servTer" styleClass="text_field_menor" disabled = "true"/>
						</logic:equal>	
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html:hidden property="servTerUnFormat" styleId="servTerUnFormat"/>
							<html:text property="servTer" styleId="servTer" styleClass="text_field_menor" maxlength="10" onblur="validateVST('servTer')"/>
						</logic:equal>
					</td>
				</tr>
				<tr height="50"> 
					<td width="25%" class="td_dark">	
						<bean:message key="garantia.form.sg.justif"/>:
					</td>
					<td width="75%" class="text">
						<logic:equal name="garantiaMotoForm" property="alterState" value="true">	
							<html-el:textarea property="justif" styleId="justif" styleClass="text_field_menor" rows="5" disabled = "true"/>
						</logic:equal>
						<logic:equal name="garantiaMotoForm" property="alterState" value="false">
							<html-el:textarea property="justif" styleId="justif" styleClass="text_field_menor" rows="5"/>
						</logic:equal>
					</td>
				</tr>	
				<tr> 
					<td height="15" colspan="2" class="tilteList" align="center">
						<bean:message key="garantia.form.msg.servicos"/> &nbsp;
						<img src="../../images/icon_required.gif" alt="form.msg.required"/>					
					</td>
				</tr>	
				<core:if test="${qtdeServicos > 0}">
					<core:forEach var="i" begin="1" end="${qtdeServicos}">
						<tr> 
							<td width="25%" class="td_dark"><core:out value="${i}"/> - <bean:message key="garantia.form.sg.codigo.servico"/>:</td>
							<td width="75%" class="text">
								<logic:equal name="garantiaMotoForm" property="alterState" value="true">							
									<input type="text" name="servico_<core:out value='${i}'/>"   id="servico_<core:out value='${i}'/>" size="5" maxlength="5" disabled="disabled" >							
								</logic:equal>
								<logic:equal name="garantiaMotoForm" property="alterState" value="false">
									<input type="text" name="servico_<core:out value='${i}'/>" id="servico_<core:out value='${i}'/>" size="5" maxlength="5">&nbsp; 
									<span id="l<core:out value='${i}'/>" class="text"></span>
								</logic:equal>
							</td>
						</tr>				
					</core:forEach>
				
					<script language="javascript">					
							var cont = 1;
					</script>
					<core:forTokens var="i" delims=";" items="${codigoServicos}">  
						<script language="javascript">
							<logic:equal name="garantiaMotoForm" property="alterState" value="true">
								window.document.getElementById("servico_" + cont).value ="<core:out value='${i}'/>";
							</logic:equal>
							<logic:equal name="garantiaMotoForm" property="alterState" value="false">
								window.document.getElementById("servico_" + cont).value ="<core:out value='${i}'/>";
								var content = "<a href='javascript:removeService(" 
											  + <core:out value='${i}'/>
											  + ")' style='text-decoration: underline;'> "	
											  + "<bean:message key="garantia.edit.remove"/>" 
											  + "</a>";											  
								window.document.getElementById("l" + cont).innerHTML = content;
							</logic:equal>
							cont++;							
						</script>
					</core:forTokens >
				</core:if>	
				</table>
			</div>	
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">		
			<tr> 
				<td height="15">&nbsp;</td>
			</tr>
			<tr height="25">
				<td colspan="2" align="center">
					<logic:equal name="garantiaMotoForm" property="alterState" value="true">
						<html:button property="gravar" styleClass="button_medium"  onclick="javascript:return gravarMotoForm(garantiaMotoForm)" disabled="true"><bean:message key="form.btn.save"/></html:button>	
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium" disabled="true"><bean:message key="form.btn.clear"/></html:reset>	
					</logic:equal>
					<logic:equal name="garantiaMotoForm" property="alterState" value="false">
						<html:button property="gravar" styleClass="button_medium"  onclick="javascript:return gravarMotoForm(garantiaMotoForm)"><bean:message key="form.btn.save"/></html:button>	
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
					</logic:equal>
					&nbsp;
					<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>		
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><img src="../../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
			</tr>
			</table>
		</html:form>
		<script type="text/javascript">enableNumber();</script>
		<ym:confirmMessage />
		<ym:alertMessage />
	</body>
</html>